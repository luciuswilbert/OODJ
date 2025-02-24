/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InventoryManager;

import Utility.duplicate_id;
import Utility.txt;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Lucius
 */
public class Supplier implements Serializable{
    private String supId;
    private String supName;
    private String supEmail;
    private String supConNum;
    static Map<String, Supplier> all_sp = new LinkedHashMap<>();

    public Supplier() {
    }

    public Supplier(int supId, String supName, String supEmail, String supConNum) {
        this.supId = String.format("Sup%03d", supId);
        this.supName = supName;
        this.supEmail = supEmail;
        this.supConNum = supConNum;
        
        all_sp.put(this.supId, this);
        Utility.txt.WriteToDAT("Supplier.dat", this);
    }
    
    public Supplier(String supId, String supName, String supEmail, String supConNum) {
        this.supId = supId;
        this.supName = supName;
        this.supEmail = supEmail;
        this.supConNum = supConNum;
        
        all_sp.put(this.supId, this);
    }
    
    public void editSup(String supId, String supName, String supEmail, String supConNum) throws IOException{
        this.supId = supId;
        this.supName = supName;
        this.supEmail = supEmail;
        this.supConNum = supConNum;
        
        rewriteFile();
    }

    public static void rewriteFile() throws IOException{
        List<Object> contents = new ArrayList(all_sp.values());
        txt.WriteToDAT("Supplier.dat", contents);
    }


    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public static Map<String, Supplier> getAll_sp() {
        return all_sp;
    }

    public static void setAll_sp(Map<String, Supplier> all_sp) {
        Supplier.all_sp = all_sp;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSupEmail() {
        return supEmail;
    }

    public void setSupEmail(String supEmail) {
        this.supEmail = supEmail;
    }

    public String getConNum() {
        return supConNum;
    }

    public void setConNum(String supConNum) {
        this.supConNum = supConNum;
    }

    public String getSupConNum() {
        return supConNum;
    }

    public void setSupConNum(String supConNum) {
        this.supConNum = supConNum;
    }
    

    // Static method to get the next Supplier ID
    public static int getNextSupplierId(String filePath) {
        int maxId = 0;
        try {
            // Loop through the data to find the max ID
            for (String sup_id : all_sp.keySet()) {
                String supIdStr = sup_id.replaceAll("[^0-9]", ""); // Extract numeric part
                int currentId = Integer.parseInt(supIdStr);
                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading supplier file: " + e.getMessage());
        }

        return maxId + 1; // Return the next available ID
    }
    
    public String toString() {
        return String.format("{supId=%s, supName=%s, supConNum=%s, supEmail=%s}", 
                              getSupId(), supName, supConNum, supEmail);
    }
    
    public static void Read(){
        List<Object> all_obj = txt.ReadFromDAT("Supplier.dat");
        for (Object obj : all_obj) {
            Supplier sp = (Supplier) obj;
            all_sp.put(sp.getSupId(), sp);
        }
    }
    
    public Map<String, String> toMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("supId", supId);
        map.put("supName", supName);
        map.put("supConNum", supConNum);
        map.put("supEmail", supEmail);
        return map;
    }
    
    public static void writeTxt(String file) throws IOException{
        List<Map<String, String>> sp_dict = new ArrayList<>();
        for (Supplier po : all_sp.values()) {
            sp_dict.add(po.toMap());      
        }     
        
        txt.WriteToTxt(file + ".txt", sp_dict);        
    }
    
    public static void ReadTxt(String file) throws FileNotFoundException, ParseException, duplicate_id, IOException{
        List<Map<String,String>> all_obj = txt.ReadFromTxt(file);
        Map<String, Supplier> imported_sp = new LinkedHashMap<>();
        
        for (Map<String,String> dict: all_obj){            
            if (!all_sp.containsKey(dict.get("supId"))){
                Supplier instance = new Supplier(dict.get("supId"), 
                        dict.get("supName"), dict.get("supConNum"), dict.get("supEmail"));
                
                imported_sp.put(dict.get("supId"), instance);
            } else {
                throw new duplicate_id("The import of suppler batch is cancelled due to conflict with the existing supplier(s).");
            }
           
        }
        all_sp.putAll(imported_sp);
        rewriteFile();
    }
    
    public static TableModel populateTable() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[] {"Supplier ID", "Name", "Contact Number", "Email"}, 0
        ){
            @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
        };
        
        // ArrayList<purchaseOrder> po_list = new ArrayList<purchaseOrder>(all_po.values());
        List<Supplier> sp_list;
        
        sp_list = new ArrayList(all_sp.values());

        for (Supplier sp : sp_list) {

            model.addRow(new Object[] { 
                sp.getSupId(),
                sp.getSupName(),
                sp.getConNum(), 
                sp.getSupEmail()
            });
        }

        return model;
    }
    
}
