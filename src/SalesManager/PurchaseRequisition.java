/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SalesManager;
import Utility.duplicate_id;
import Utility.txt;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import InventoryManager.Item;
import Utility.editPermission;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author isabelle
 */



public class PurchaseRequisition implements Serializable{
    private static final long serialVersionUID = 3607638094620154386L;
    private String requisitionId;
    private String requestorId;
    private Date requestDate; 
    private String itemId;
    private static enum approvalStatus {PENDING, APPROVED, REJECTED}
    private approvalStatus status;
    private int quantityRequested; 
    private static final Map<String, PurchaseRequisition> purchaseRequisitionData = new LinkedHashMap<>();
        
    public PurchaseRequisition(String requisitionId, String requestorId, String itemId, int quantityRequested, boolean file){
        this.requisitionId = requisitionId;      
        this.requestorId = requestorId;
        this.requestDate = new Date();
        this.itemId = itemId;
        this.quantityRequested = quantityRequested;
        this.status = approvalStatus.PENDING;
        
        // convert object to dictionary
        addNewRecord();
        
        // append dictionary to file
        if(file) {txt.WriteToDAT("purchase_requisition.dat", this);}
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

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(String status) {
        approvalStatus statusObj = switch (status) {
        case "PENDING" -> approvalStatus.PENDING;
        case "APPROVED" -> approvalStatus.APPROVED;
        default -> approvalStatus.REJECTED;
        };
                
        this.status = statusObj;
    }
    
    public static Map<String, PurchaseRequisition> getPurchaseRequisitionData() {
        return purchaseRequisitionData;
    }
    
    private boolean addNewRecord() {
        // create a dictionary with the purchase requisition id as the key and the purchase requisition object as the value
        purchaseRequisitionData.put(requisitionId, this);
        return true;
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
    
    public static List<Map<String,String>> viewPurchaseRequisitionData() {
        // read data from sales.txt
        List<Map<String,String>> pr_dict = new ArrayList<>();
        for (PurchaseRequisition po : purchaseRequisitionData.values()) {
            pr_dict.add(po.convertDict());
        }
        return pr_dict;   
    }
 

    public static void rewriteFile(){
        List<Object> contents = new ArrayList(purchaseRequisitionData.values());
        txt.WriteToDAT("purchase_requisition.dat", contents);
    }
    
    public static void Read(){
        List<Object> all_obj = txt.ReadFromDAT("purchase_requisition.dat");
        for (Object obj : all_obj) {
//            PurchaseRequisition pr = (PurchaseRequisition) obj;
//            purchaseRequisitionData.put(pr.getRequisitionId(), pr);
            if (obj instanceof PurchaseRequisition) {
                PurchaseRequisition pr = (PurchaseRequisition) obj;
                purchaseRequisitionData.put(pr.getRequisitionId(), pr);
            } else {
                System.err.println("Unexpected data type for PR: " + obj.getClass().getName());
            }
        }
    }
    
    public static void writeTxt(String file) throws IOException{
        List<Map<String, String>> pr_dict = viewPurchaseRequisitionData();
        
        txt.WriteToTxt(file + ".txt", pr_dict);        
    }

    public static void ReadTxt(String file) throws FileNotFoundException, ParseException, duplicate_id{
        List<Map<String,String>> all_obj = txt.ReadFromTxt(file);
        Map<String,PurchaseRequisition> imported_pr = new LinkedHashMap<>();
        
        for (Map<String,String> dict: all_obj){            
            if (!purchaseRequisitionData.containsKey(dict.get("saleId"))){
                String requisitionId = dict.get("requisitionId");
                String itemId = dict.get("itemId");
                String quantityRequestedString = dict.get("quantityRequested");
                String requestorId = dict.get("requestorId");

                int quantityRequested;
                // Parse the string int an int
                quantityRequested = Integer.parseInt((String) quantityRequestedString);
                // Append to file
                PurchaseRequisition new_pr = new PurchaseRequisition(requisitionId, requestorId, itemId, quantityRequested, false);
                imported_pr.put(requisitionId, new_pr);
                
            } else {
                throw new duplicate_id("The import of purchase requisition batch is cancelled due to conflict with the existing purchase requisition(s).");
            }
        }
        purchaseRequisitionData.putAll(imported_pr);
        rewriteFile();
        
    } 
    
    public String nextRecordId() {
        int newIdNum;
        String id;
        
        // read data from file
        List<Map<String, String>> fileData = viewPurchaseRequisitionData();

        if (fileData == null || fileData.isEmpty()){
            newIdNum = 1;
        }
        else {
            List<String> ids = new ArrayList<>();

            // extract the id
            for(Map<String, String> singleRecord: fileData) {
                ids.add(singleRecord.get("requisitionId"));
            }

            // get the last id & slice to get only the number 
            String lastId = ids.getLast().substring(2);

            // convert the string to int and increment by one
            newIdNum = Integer.parseInt(lastId) + 1;
        }

        // create the new id
        int idPaddingSize = 4;
        String idNum = String.format("%0" + idPaddingSize + "d", newIdNum);
        id = String.join("", "PR", idNum);
        
        return id;
    }
    
    public Map<String,List<?>> updateItemSelectionList(List<String> itemNames, List<String> itemIds, List<Integer> stockLevels, List<Integer> reorderLevels) {
        // read item data
        Map<String, Item> itemData = Item.getAll_item();

        if (itemData != null && !itemData.isEmpty()) {
            // extract the item id, name, stock level, and reorder level
            for (Item item : itemData.values()) {
                itemNames.add(item.getItemName());
                itemIds.add(item.getItemID());
                stockLevels.add(item.getStockLevel());
                reorderLevels.add(item.getReorderLevel());
            }
        }
        
        Map<String,List<?>> map =new HashMap();
        map.put("itemNames", itemNames);
        map.put("itemIds", itemIds);
        map.put("stockLevels", stockLevels);
        map.put("reorderLevels", reorderLevels);
        return map;
    }
    
     public static TableModel populateTable(editPermission permission, String creator) {
        DefaultTableModel model = new DefaultTableModel(
            new Object[] {"Purchase Requisition ID", "Item ID", "Quantity Requested", "Requestor ID", "Request Date"}, 0
        ){
            @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
        };
        
        List<PurchaseRequisition> pr_list;
        
        if (permission == editPermission.edit){
            pr_list = purchaseRequisitionData.values().stream()
            .filter(pr -> pr.getRequestorId().equals(creator))
            .toList();
        } else {
            pr_list = new ArrayList(purchaseRequisitionData.values());
        }
                   
        // Debugging: Check if getAll_po() returns any records
        System.out.println("Number of purchase requisition in system: " + purchaseRequisitionData.size());
        System.out.println("Number of purchase requisition in table: " + pr_list.size());

        for (PurchaseRequisition pr : pr_list) {

            model.addRow(new Object[] { 
                pr.getRequisitionId(),
                pr.getItemId(),
                pr.getQuantityRequested(),
                pr.getRequestorId(),
                pr.getRequestDate()
            });
        }

        return model;
    }
}
