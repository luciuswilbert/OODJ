package oodj_assignment;

import java.util.LinkedHashMap;
import java.util.Map;

public class item {
    private int itemID, supplierID, stockLevel, reorderLevel;
    private String itemName, description, category;
    private double unitPrice;
    
    public item(int itemID,String itemName, String description, String category, double unitPrice, int supplierID, int stockLevel, int reorderLevel){
        this.itemID=itemID;
        this.itemName=itemName;
        this.description=description;
        this.category=category;
        this.unitPrice=unitPrice;
        this.supplierID=supplierID;
        this.stockLevel=stockLevel;
        this.reorderLevel=reorderLevel;
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
    
    public int getItemID(){
        return itemID;
    }
    
    public double getUnitPrice(){
        return unitPrice;
    }
    
    public int getStockLevel(){
        return stockLevel;
    }
    
    public void setStockLevel(int quantity){
        stockLevel=quantity;
    }
    
}
