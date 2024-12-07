/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SalesManager;
import Utility.txt;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author isabelle
 */
public class PurchaseRequisition {
    private String requisitionId;
    private String requestorId;
    private Date requestDate; 
    private String itemId;
    private int quantityRequested; 
    private final Map<String, PurchaseRequisition> purchaseRequisitionData = new LinkedHashMap<>();
        
    public PurchaseRequisition(String requisitionId, String requestorId, String itemId, int quantityRequested) throws IOException {
        this.requisitionId = requisitionId;      
        this.requestorId = requestorId;
        this.requestDate = new Date();
        this.itemId = itemId;
        this.quantityRequested = quantityRequested;
        
        // convert object to dictionary
        addNewRecord();

        Map<String, Object> purchaseRequisitionDict = convertDict();
        
        // append dictionary to file
        txt.Write("purchase_requisition.txt", purchaseRequisitionDict, true);
    }
    
    public PurchaseRequisition() {
    }
    
    public String getRequisitionId() {
        return requisitionId;
    }

    public String getRequestorId() {
        return requestorId;
    }

    public void setRequestorId(String requestorId) {
        this.requestorId = requestorId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantityRequested() {
        return quantityRequested;
    }

    public void setQuantityRequested(int quantityRequested) {
        this.quantityRequested = quantityRequested;
    }
    
    private boolean addNewRecord() {
        // create a dictionary with the purchase requisition id as the key and the purchase requisition object as the value
        purchaseRequisitionData.put(requisitionId, this);
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
                // Handle purchaseRequisitionData (skip maps or other complex fields)
                else if (field.getType() == Map.class) {
                    continue; // Skip the purchaseRequisitionData map
                }

                // Add field name and value to the dictionary
                dict.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                System.err.println("Could not access field: " + field.getName());
            }
        }

        return dict;
    }
    
    public List<Map<String,String>> viewPurchaseRequisitionData() {
        // read data from sales.txt
        try {
            return asm.utilities.txt.Read("purchase_requisition.txt");
        } catch (FileNotFoundException ex) {
            return null;
        }   
    }
    
    public boolean writePurchaseRequisitionData(String requisitionId, String requestorId, String itemId, int quantityRequested) {
        // Append to file
        try {
            // Create a new Purchase Requisition object
            PurchaseRequisition purchaseRequisition = new PurchaseRequisition(requisitionId, requestorId, itemId, quantityRequested);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    

   
}
