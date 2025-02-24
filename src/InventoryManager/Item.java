
package InventoryManager;
import Utility.InvalidValue;
import Utility.duplicate_id;
import Utility.txt;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Item implements Serializable{
    private int stockLevel, reorderLevel;
    private String itemID, itemName, description, category, supplierID;
    private double unitPrice;
    private Date itemEntryDate;
    static Map<String, Item> all_item = new LinkedHashMap<>();
    
    
    public Item(int itemID, String itemName, String description, String category, double unitPrice, String supplierID, int stockLevel, int reorderLevel, Date itemEntryDate){
        // Format itemID with leading zeros
        this.itemID= String.format("i%03d", itemID);
        this.itemName= itemName;
        this.description=description;
        this.category=category;
        this.unitPrice=unitPrice;
        this.supplierID=supplierID;
        this.stockLevel=stockLevel;
        this.reorderLevel=reorderLevel;
        this.itemEntryDate=itemEntryDate;
        
        all_item.put(this.itemID, this);
        Utility.txt.WriteToDAT("item.dat", this);
    }
    
    public Item(String itemID, String itemName, String description, String category, double unitPrice, String supplierID, int stockLevel, int reorderLevel, Date itemEntryDate){
        this.itemID= itemID;
        this.itemName= itemName;
        this.description=description;
        this.category=category;
        this.unitPrice=unitPrice;
        this.supplierID=supplierID;
        this.stockLevel=stockLevel;
        this.reorderLevel=reorderLevel;
        this.itemEntryDate=itemEntryDate;
        
        all_item.put(this.itemID, this);
    }
    
     public void editItem(String itemName, String description, String category, double unitPrice, String supplierID, int stockLevel, int reorderLevel, Date itemEntryDate) throws IOException{
        this.itemName= itemName;
        this.description=description;
        this.category=category;
        this.unitPrice=unitPrice;
        this.supplierID=supplierID;
        this.stockLevel=stockLevel;
        this.reorderLevel=reorderLevel;
        this.itemEntryDate=itemEntryDate;
        
        rewriteFile();
    }

    public static void rewriteFile() throws IOException{
        List<Object> contents = new ArrayList(all_item.values());
        txt.WriteToDAT("item.dat", contents);
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) throws InvalidValue{
        if (itemID <= 0) {
            throw new InvalidValue("Item ID must be a positive integer.");
        }
        this.itemID = String.format("i%03d", itemID);
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) throws InvalidValue {
        String supIdStr = supplierID.replaceAll("[^0-9]", ""); // Extract numeric part
        int currentId = Integer.parseInt(supIdStr);
        if (currentId <= 0) {
            throw new InvalidValue("Supplier ID must be a positive integer.");
        }
        this.supplierID = supplierID;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) throws InvalidValue {
        if (stockLevel < 0) {
            throw new InvalidValue("Stock level cannot be negative.");
        }
        this.stockLevel = stockLevel;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) throws InvalidValue {
        if (reorderLevel < 0) {
            throw new InvalidValue("Reorder level cannot be negative.");
        }
        this.reorderLevel = reorderLevel;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) throws InvalidValue {
        if (itemName == null || itemName.trim().isEmpty()) {
            throw new InvalidValue("Item name cannot be empty.");
        }
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws InvalidValue {
        if (description == null || description.trim().isEmpty()) {
            throw new InvalidValue("Description cannot be empty.");
        }
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) throws InvalidValue {
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidValue("Category cannot be empty.");
        }
        this.category = category;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) throws InvalidValue {
        if (unitPrice <= 0) {
            throw new InvalidValue("Unit price must be greater than zero.");
        }
        this.unitPrice = unitPrice;
    }
    
   
    public Date getItemEntryDate() {
        return itemEntryDate;
    }

    public void setItemEntryDate(Date itemEntryDate) throws InvalidValue {
        if (itemEntryDate == null) {
            throw new InvalidValue("Item entry date cannot be null.");
        }
        this.itemEntryDate = itemEntryDate;
    }

    public static Map<String, Item> getAll_item() {
        return all_item;
    }

    public static void setAll_item(Map<String, Item> all_item) {
        Item.all_item = all_item;
    }

    
    
    public Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("itemID", itemID);
        map.put("itemName", itemName);
        map.put("description", description);
        map.put("category", category);
        map.put("unitPrice", unitPrice);
        map.put("supplierID", supplierID);
        map.put("stockLevel", stockLevel);
        map.put("reorderLevel", reorderLevel);
        return map;
    }
    
    public Map<String, String> toStrMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("itemID", itemID);
        map.put("itemName", itemName);
        map.put("description", description);
        map.put("category", category);
        map.put("unitPrice", String.format("%.2f", unitPrice));
        map.put("supplierID", supplierID);
        map.put("stockLevel", "" + stockLevel);
        map.put("reorderLevel", "" + reorderLevel);
        map.put("itemEntryDate", new SimpleDateFormat("yyyy-MM-dd").format(itemEntryDate));
        return map;
    }
    
    
    
    public static int getNextItemId(String filePath) {
        int maxId = 0;
        try {
            // Loop through the data to find the max ID
            for (String it_id : all_item.keySet()) {
                String itIdStr = it_id.replaceAll("[^0-9]", ""); // Extract numeric part
                int currentId = Integer.parseInt(itIdStr);
                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading item file: " + e.getMessage());
        }

        return maxId + 1; // Return the next available ID
    }
    
    
//    public String toString() {
//        // Format unitPrice to 2 decimal places
//        String formattedUnitPrice = String.format("%.2f", unitPrice);
//        
//        // Format itemEntryDate in yyyy-MM-dd format
//        String formattedEntryDate = new SimpleDateFormat("yyyy-MM-dd").format(itemEntryDate);
//
//        return String.format("{itemID=%s, itemName=%s, description=%s, category=%s, unitPrice=%s, supplierID=Sup%03d, stockLevel=%d, reorderLevel=%d, itemEntryDate=%s}", 
//                             itemID, itemName, description, category, formattedUnitPrice, supplierID, stockLevel, reorderLevel, formattedEntryDate);
//    }
    
    public static void Read(){
        List<Object> all_obj = txt.ReadFromDAT("item.dat");
        for (Object obj : all_obj) {
            Item it = (Item) obj;
            all_item.put(it.getItemID(), it);
        }
    }
    
    public static void writeTxt(String file) throws IOException{
        List<Map<String, String>> item_dict = new ArrayList<>();
        for (Item it : all_item.values()) {
            item_dict.add(it.toStrMap());      
        }     
        
        txt.WriteToTxt(file + ".txt", item_dict);        
    }
    
    public static void ReadTxt(String file) throws FileNotFoundException, ParseException, duplicate_id, IOException{
        List<Map<String,String>> all_obj = txt.ReadFromTxt(file);
        Map<String,Item> imported_item = new LinkedHashMap<>();
        
        for (Map<String,String> dict: all_obj){
//            System.out.println(dict);
//            System.out.println(!all_item.containsKey(dict.get("itemID")));
//            System.out.println(Supplier.getAll_sp().containsKey(dict.get("supplierID")));
            try{
                if (!all_item.containsKey(dict.get("itemID")) && 
                        (Supplier.getAll_sp().containsKey(dict.get("supplierID")))){
                    double unitPrice = Double.parseDouble(dict.get("unitPrice"));
                    int stockLevel = Integer.parseInt(dict.get("stockLevel"));
                    int reorderLevel = Integer.parseInt(dict.get("reorderLevel"));
                    Date itemEntryDate = new SimpleDateFormat("yyyy-MM-dd").parse(dict.get("itemEntryDate"));
                    Item instance = new Item(dict.get("itemID"), dict.get("itemName"), 
                            dict.get("description"), dict.get("category"), unitPrice, dict.get("supplierID"), stockLevel, reorderLevel, itemEntryDate);

                    imported_item.put(dict.get("itemID"), instance);
                } else {
                    throw new duplicate_id("The import of item batch is cancelled due to conflict with the existing item(s) or the absent of supplier(s).");
                }
//            System.out.println("after" + all_item.toString());

            } catch (ParseException e){
                System.out.println("An error occurred." + e.getMessage());
            }
           
        }
        all_item.putAll(imported_item);
        rewriteFile();
        
    }
    
    public static TableModel populateTable() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[] {
            "itemID", "itemName", "description", "category", 
            "unitPrice", "supplierID", "stockLevel", 
            "reorderLevel", "itemEntryDate"
        }, 0
        ){
            @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
        };
        
        for (Item item : all_item.values()) {

            model.addRow(new Object[] { 
                item.getItemID(), 
                item.getItemName(),
                item.getDescription(),
                item.getCategory(),
                item.getUnitPrice(),
                item.getSupplierID(),
                item.getStockLevel(),
                item.getReorderLevel(),
                item.getItemEntryDate()
            });
        }

        return model;
    }    
    
}

//    public static void loadItems(String itemFile, DefaultTableModel itemModel) {
//        List<Map<String, String>> items;
//
//        // Set up table headers
//        String[] itemColumnNames = {
//            "Item ID", "Item Name", "Description", "Category", 
//            "Unit Price", "Supplier ID", "Stock Level", 
//            "Reorder Level", "Item Entry Date"
//        };
//        itemModel.setColumnIdentifiers(itemColumnNames);
//
//        try {
//            // Read items from the file using txt.Read
//            items = txt.Read(itemFile);
//
//            // Loop through the items and add them to the table
//            for (Map<String, String> item : items) {
//                String[] newRow = {
//                    item.get("itemID"),
//                    item.get("itemName"),
//                    item.get("description"),
//                    item.get("category"),
//                    item.get("unitPrice"),
//                    item.get("supplierID"),
//                    item.get("stockLevel"),
//                    item.get("reorderLevel"),
//                    item.get("itemEntryDate")
//                };
//                itemModel.addRow(newRow); 
//            }
//        } catch (FileNotFoundException e) {
//            System.err.println("File not found: " + e.getMessage());
//        } catch (Exception e) {
//            System.err.println("Error loading items: " + e.getMessage());
//        }
//    }       
     
