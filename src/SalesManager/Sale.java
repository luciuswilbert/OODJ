/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SalesManager;
import Utility.duplicate_id;
import java.util.Date;
import java.util.Map;
import Utility.txt;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import InventoryManager.Item;
import Utility.InvalidValue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author isabelle
 */
public class Sale implements Serializable{
    private static final long serialVersionUID = 8799853516484518300L;
    private String saleId = null;
    private Date creationDate; 
    private Date saleDate;
    private String itemId;
    private int quantitySold;
    private String creatorId;
    private Date modifiedDate;
    private String modifierId;
    private String modifyReason;
    private static Map<String, Sale> saleData = new LinkedHashMap<>();
        
    public Sale(String saleId, String itemId, int quantitySold, Date saleDate, String userId, boolean file) {
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
       
        saleData.put(saleId, this);

        if (file){
            txt.WriteToDAT("sales.dat", this);
        }        
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public static Map<String, Sale> getSaleData() {
        return saleData;
    }

    public static void setSaleData(Map<String, Sale> saleData) {
        Sale.saleData = saleData;
    }
    
    private Map<String, String> convertDict() {
        Field[] allFields = this.getClass().getDeclaredFields();
        Map<String, String> dict = new LinkedHashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        for (Field field : allFields) {
            try {
                field.setAccessible(true); // Allow access to private fields

                // Skip if the field is of type Map
                if (Map.class.isAssignableFrom(field.getType())) {
                    continue; // Skip Map fields
                }

                Object value = field.get(this);
                String str_value = "";

                // Handle null values
                if (value == null) {
                    str_value = "null";
                } 
                // Format Date fields
                else if (value instanceof Date date) {
                    str_value = dateFormat.format(date);
                } 
                // Handle purchaseRequisitionData (skip maps or other complex fields)
                else if (field.getType() == Map.class) {
                    continue; // Skip the purchaseRequisitionData map
                } else {
                    str_value = value.toString();
                }
                
         
                // Add field name and value to the dictionary
                dict.put(field.getName(), str_value);
            } catch (IllegalAccessException e) {
                System.err.println("Could not access field: " + field.getName());
            }
        }

        return dict;
    }
    
    public static List<Map<String,String>> viewSaleData() {
        // read data 
        List<Map<String,String>> sales_dict = new ArrayList<>();
        for (Sale sl : saleData.values()) {
            sales_dict.add(sl.convertDict());
        }
        return sales_dict;   
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
    
     public static void rewriteFile(){
        List<Object> contents = new java.util.ArrayList(saleData.values());
        txt.WriteToDAT("sales.dat", contents);
    }
    
    public static void Read(){
        List<Object> all_obj = txt.ReadFromDAT("sales.dat");
        for (Object obj : all_obj) {
            if (obj instanceof Sale) {
                Sale sl = (Sale) obj;
                saleData.put(sl.getSaleId(), sl);
            } else {
                System.err.println("Unexpected data type: " + obj.getClass().getName());
            }
        }
    }    
            
    
    public static void writeTxt(String file) throws IOException{
        List<Map<String, String>> sale_dict = viewSaleData();
        txt.WriteToTxt(file + ".txt", sale_dict);        
    }
     
    public static void ReadTxt(String file) throws FileNotFoundException, ParseException, duplicate_id{
        List<Map<String,String>> all_obj = txt.ReadFromTxt(file);
        Map<String,Sale> imported_sl = new LinkedHashMap<>();
        
        for (Map<String,String> dict: all_obj){      
            if (!saleData.containsKey(dict.get("saleId"))){
                // Extract the data from each row
                String saleId = dict.get("saleId");
                String creatorId = dict.get("creatorId");
                String itemId = dict.get("itemId");
                String saleDateString = dict.get("saleDate");
                String quantitySoldString = dict.get("quantitySold");

                Date saleDate = null;
                int quantitySold = -1;

                // Convert from string to date/int
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                try {
                    // Parse the string into a Date
                    saleDate = sdf.parse(saleDateString);
                    // Parse the string int an int
                    quantitySold = Integer.parseInt((String) quantitySoldString);
                } catch (ParseException ex) {
                    System.out.println("An error occurred." + ex.getMessage());
                }
                Sale newSale = new Sale(saleId, itemId, quantitySold, saleDate, creatorId, false);
                imported_sl.put(saleId, newSale);
            } else {
                throw new duplicate_id("The import of sales batch is cancelled due to conflict with the existing sale(s).");
            }
        }
        saleData.putAll(imported_sl);
        rewriteFile();
        System.out.println(saleData.toString());
    } 
    
    public String nextRecordId() {
        int newIdNum;
        String id;
        
        // read data from file
        List<Map<String, String>> fileData = viewSaleData();

        if (fileData == null || fileData.isEmpty()){
            newIdNum = 1;
        }
        else {
            List<String> ids = new ArrayList<>();

            // extract the id
            for(Map<String, String> singleRecord: fileData) {
                    ids.add(singleRecord.get("saleId"));      
            }

            // get the last id & slice to get only the number 
            String lastId = ids.getLast().substring(2);

            // convert the string to int and increment by one
            newIdNum = Integer.parseInt(lastId) + 1;
        }

        // create the new id
        int idPaddingSize = 4;
        String idNum = String.format("%0" + idPaddingSize + "d", newIdNum);
        id = String.join("", "SL", idNum);
        
        return id;
    }
    
    public Map<String,List<String>> updateItemSelectionList(List<String> itemNames, List<String> itemIds) {
        // read data from item.txt
        Map<String, Item> itemData = Item.getAll_item();
        
        if (itemData != null && !itemData.isEmpty()) {
            // extract the item id & name
            for (Item item : itemData.values()) {
                itemNames.add(item.getItemName());
            itemIds.add(item.getItemID());
        }
        }
        
        Map<String,List<String>> map =new HashMap();
        map.put("itemNames", itemNames);
        map.put("itemIds", itemIds);
        return map;
    }
    
    
    public boolean sufficientStockLevel(String itemId, int quantitySold) {
        // Retrieve all item data
        Map<String, Item> itemData = Item.getAll_item();

        // Check if itemData is valid and not empty
        if (itemData == null || itemData.isEmpty()) {
            return false; // No items available
        }

        // Fetch the item by ID
        Item item = itemData.get(itemId);

        // Check if item exists and has sufficient stock
        return item != null && item.getStockLevel() >= quantitySold;
    }
    

    public void deductStockLevel(String itemId, int quantitySold) throws InvalidValue {
        // Retrieve all item data
        Map<String, Item> itemData = Item.getAll_item();
        if (itemData == null || itemData.isEmpty()) {
            throw new InvalidValue("Item data is empty or unavailable");
        }

        // Fetch the item by ID
        Item item = itemData.get(itemId);
        if (item == null) {
            throw new InvalidValue("Item with ID " + itemId + " does not exist");
        }

        // Check stock level
        if (item.getStockLevel() < quantitySold) {
            throw new InvalidValue("Insufficient stock for item ID: " + itemId);
        }

        // Deduct stock level
        item.setStockLevel(item.getStockLevel() - quantitySold);

        // Update the item dat
        Item.setAll_item(itemData);

        // Persist the updated data to file
        try {
            Item.rewriteFile();
        } catch (IOException ex) {
            Logger.getLogger(Sale.class.getName()).log(Level.SEVERE, "Failed to rewrite item data file", ex);
            throw new RuntimeException("Failed to persist item data", ex);
        }
    }
    
    public void updateSaleRecord(String saleId, String itemId, Date saleDate, int quantitySold, Date modifiedDate, String modifierId, String modifyReason) {
        Sale sale = saleData.get(saleId);
        sale.setItemId(itemId);
        sale.setSaleDate(saleDate);
        sale.setQuantitySold(quantitySold);
        sale.setModifiedDate(modifiedDate);
        sale.setModifierId(modifierId);
        sale.setModifyReason(modifyReason);
        
        // rewrite dat file
        rewriteFile();
    }
    
}