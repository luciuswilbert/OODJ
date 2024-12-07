/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SalesManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import User.User;

/**
 *
 * @author isabelle
 */
public class SalesManager extends User{
    public SalesManager(String userId, String username, String password, String fullName, String email, String contactNumber, role_state role, String image) {
        super(userId, username, password, fullName, email, contactNumber, role, image);
    }
    
    public SalesManager() {
        super();
    }
    
    public List<Map<String,String>> viewDataFromFile(String filename) {
        // read data from item.txt
        try {
            return asm.utilities.txt.Read(filename);
        } catch (FileNotFoundException ex) {
            return null;
        }
    }
    
    public String nextRecordId(String filename) {
        int newIdNum;
        String id;
        
        // read data from file
        List<Map<String, String>> fileData = viewDataFromFile(filename);

        if (fileData == null || fileData.isEmpty()){
            newIdNum = 1;
        }
        else {
            List<String> ids = new ArrayList<>();

            // extract the id
            for(Map<String, String> singleRecord: fileData) {
                if ("purchase_requisition.txt".equals(filename)) {
                    ids.add(singleRecord.get("requisitionId"));
                } else {
                    ids.add(singleRecord.get("saleId"));
                }
                
            }

            // get the last id & slice to get only the number 
            String lastId = ids.getLast().substring(2);

            // convert the string to int and increment by one
            newIdNum = Integer.parseInt(lastId) + 1;
        }

        // create the new id
        int idPaddingSize = 4;
        String idNum = String.format("%0" + idPaddingSize + "d", newIdNum);
        if ("purchase_requisition.txt".equals(filename)) {
            id = String.join("", "pr", idNum);
        } else {
            id = String.join("", "sl", idNum);
        }
        
        return id;
    }
    
    public Map<String,List<String>> updateItemSelectionList(List<String> itemNames, List<String> itemIds) {
        // read data from item.txt
        List<Map<String, String>> itemData = viewDataFromFile("item.txt");

        
        if (itemData != null && !itemData.isEmpty()) {
            // extract the item id & name
            for (Map<String, String> item : itemData) {
                itemNames.add(item.get("itemName"));
                itemIds.add(item.get("itemID"));
            }
        }
        
        Map<String,List<String>> map =new HashMap();
        map.put("itemNames", itemNames);
        map.put("itemIds", itemIds);
        return map;
    }
    
    public Map<String,List<?>> updateItemSelectionList(List<String> itemNames, List<String> itemIds, List<Integer> stockLevels, List<Integer> reorderLevels) {
        // read data from item.txt
        List<Map<String, String>> itemData = viewDataFromFile("item.txt");

        if (itemData != null && !itemData.isEmpty()) {
            // extract the item id & name
            for (Map<String, String> item : itemData) {
                itemNames.add(item.get("itemName"));
                itemIds.add(item.get("itemID"));
                stockLevels.add(Integer.valueOf(item.get("stockLevel")));
                reorderLevels.add(Integer.valueOf(item.get("reorderLevel")));
            }
        }
        
        Map<String,List<?>> map =new HashMap();
        map.put("itemNames", itemNames);
        map.put("itemIds", itemIds);
        map.put("stockLevels", stockLevels);
        map.put("reorderLevels", reorderLevels);
        return map;
    }
    
    public boolean overwriteSalesRecords(List<Map<String, Object>> contents) {
        // Write the updated contents to the file
        try {
            asm.utilities.txt.Write("sales.txt", contents);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
}


