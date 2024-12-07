/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SalesManager;
import java.util.Date;
import java.util.Map;
import Utility.txt;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author isabelle
 */
public class Sale {
    private String saleId = null;
    private Date creationDate; 
    private Date saleDate;
    private String itemId;
    private int quantitySold;
    private String creatorId;
    private Date modifiedDate;
    private String modifierId;
    private String modifyReason;
    private final Map<String, Sale> saleData = new LinkedHashMap<>();
        
    public Sale(String saleId, String itemId, int quantitySold, Date saleDate, String userId) throws IOException {
        // Automatically assign sale id
        this.saleId = saleId;
        this.creationDate = new Date();
        this.saleDate = saleDate;
        this.itemId = itemId;
        this.quantitySold = quantitySold;
        this.creatorId = userId;
        this.modifiedDate = null;
        this.modifierId = null;
        this.modifyReason = null;
       
        // convert object to dictionary
        addNewRecord();

        Map<String, Object> saleDict = convertDict();
        
        // append dictionary to file
        txt.Write("sales.txt", saleDict, true);
        
    }
    
    public Sale() {
    }

    public String getSaleId() {
        return saleId;
    }

    public Date getcreationDate() {
        return creationDate;
    }

    public String getItemId() {
        return itemId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public String getUserId() {
        return creatorId;
    }

    public void setUserId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public void setModifyReason(String modifyReason) {
        this.modifyReason = modifyReason;
    }   
    
    private boolean addNewRecord() {
        // create a dictionary with the sale id as the key and the sale object as the value
        saleData.put(saleId, this);
        return true;
    }

    private Map<String, Object> convertDict() {
        Field[] allFields = this.getClass().getDeclaredFields();
        Map<String, Object> dict = new LinkedHashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        for (Field field : allFields) {
            try {
                field.setAccessible(true); // Allow access to private fields

                // Skip if the field is of type Map
                if (Map.class.isAssignableFrom(field.getType())) {
                    continue; // Skip Map fields
                }

                Object value = field.get(this);

                // Handle null values
                if (value == null) {
                    value = "null";
                } 
                // Format Date fields
                else if (value instanceof Date date) {
                    value = dateFormat.format(date);
                } 
                // Handle saleData (skip maps or other complex fields)
                else if (field.getType() == Map.class) {
                    continue; // Skip the saleData map
                }

                // Add field name and value to the dictionary
                dict.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                System.err.println("Could not access field: " + field.getName());
            }
        }

        return dict;
    }
    
    public List<Map<String,String>> viewSaleData() {
        // read data from sales.txt
        try {
            return asm.utilities.txt.Read("sales.txt");
        } catch (FileNotFoundException ex) {    
            return null;
        }   
    }
    
    public boolean writeSaleData(String saleId, String itemId, int quantitySold, Date saleDate, String userId) {
        // Append to file
        try {
            // Create a new Sale object
            Sale newSale = new Sale(saleId, itemId, quantitySold, saleDate, userId);            
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public boolean checkDuplicateSaleEntry(String itemId, String saleDate) {
        List<Map<String, String>> sales = viewSaleData();
        
        if (sales != null && !sales.isEmpty()) {
            // extract the item id and sale date
            for (Map<String, String> sale : sales) {
                if (sale.get("itemId").equals(itemId) && sale.get("saleDate").equals(saleDate)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

}