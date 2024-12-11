
package asm;
import asm.utilities.txt;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class Item {
    private int itemID, supplierID, stockLevel, reorderLevel;
    private String itemName, description, category;
    private double unitPrice;
    private Date itemEntryDate;
    
    
    public Item(int itemID, String itemName, String description, String category, double unitPrice, int supplierID, int stockLevel, int reorderLevel, Date itemEntryDate){
        this.itemID=itemID;
        this.itemName=itemName;
        this.description=description;
        this.category=category;
        this.unitPrice=unitPrice;
        this.supplierID=supplierID;
        this.stockLevel=stockLevel;
        this.reorderLevel=reorderLevel;
        this.itemEntryDate=itemEntryDate;
        
    }
    
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) throws InvalidValue{
        if (itemID <= 0) {
            throw new InvalidValue("Item ID must be a positive integer.");
        }
        this.itemID = itemID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) throws InvalidValue {
        if (supplierID <= 0) {
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
    
    
    
    public static int getNextItemId(String filePath) {
        int maxId = 0;
        try {
            // Read all data from the file
            List<Map<String, String>> itemData = txt.Read(filePath);

            // Loop through the data to find the max ID
            for (Map<String, String> data : itemData) {
                String itemIdStr = data.get("itemID").replaceAll("[^0-9]", ""); // Extract numeric part
                int currentId = Integer.parseInt(itemIdStr);
                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading item file: " + e.getMessage());
        }

        return maxId + 1; // Return the next available ID
    }
    
    
    public String toString() {
        // Format itemID with leading zeros
        String formattedItemId = String.format("item%03d", itemID);
        
        // Format unitPrice to 2 decimal places
        String formattedUnitPrice = String.format("%.2f", unitPrice);
        
        // Format itemEntryDate in yyyy-MM-dd format
        String formattedEntryDate = new SimpleDateFormat("yyyy-MM-dd").format(itemEntryDate);

        return String.format("{itemID=%s, itemName=%s, description=%s, category=%s, unitPrice=%s, supplierID=Sup%03d, stockLevel=%d, reorderLevel=%d, itemEntryDate=%s}", 
                             formattedItemId, itemName, description, category, formattedUnitPrice, supplierID, stockLevel, reorderLevel, formattedEntryDate);
    }
    
    public static void loadItems(String itemFile, DefaultTableModel itemModel) {
        List<Map<String, String>> items;

        // Set up table headers
        String[] itemColumnNames = {
            "Item ID", "Item Name", "Description", "Category", 
            "Unit Price", "Supplier ID", "Stock Level", 
            "Reorder Level", "Item Entry Date"
        };
        itemModel.setColumnIdentifiers(itemColumnNames);

        try {
            // Read items from the file using txt.Read
            items = txt.Read(itemFile);

            // Loop through the items and add them to the table
            for (Map<String, String> item : items) {
                String[] newRow = {
                    item.get("itemID"),
                    item.get("itemName"),
                    item.get("description"),
                    item.get("category"),
                    item.get("unitPrice"),
                    item.get("supplierID"),
                    item.get("stockLevel"),
                    item.get("reorderLevel"),
                    item.get("itemEntryDate")
                };
                itemModel.addRow(newRow); 
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error loading items: " + e.getMessage());
        }
    }       
    
    
   
}
