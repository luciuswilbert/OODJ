package asm;
import asm.utilities.editPermission;
import java.util.GregorianCalendar;
import java.util.List; 
import asm.utilities.txt;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class purchaseOrder {
    static int cur_po_num = 0;
    static Map<String, purchaseOrder> all_po = new LinkedHashMap<>();
    final int id_paddingSize = 4;
    private static enum po_stat{PENDING, APPROVED, REJECTED}
    
    private String po_id;
    private String pr_id;
    private String supplier_id;
    private GregorianCalendar order_date;
    private po_stat po_status; // pending, approved, rejected
    private String created_by;
    private String approved_by;
    private GregorianCalendar approval_date;
    
    // informs the Java compiler that any calling code will have to handle exceptions of the type Exception
    public purchaseOrder(String pr_id, String supplier_id, String created_by) throws IOException{
        String id_num = String.format("%0" + id_paddingSize + "d", cur_po_num + 1);
        po_id = String.join("", "PO", id_num);
        cur_po_num += 1;
        this.pr_id = pr_id;
        this.supplier_id = supplier_id;
        order_date = new GregorianCalendar();
        po_status = po_stat.PENDING;
        this.created_by = created_by;
        approved_by = null;
        approval_date = null;
        
        this.add_new_record();
        
        Map<String, Object> record_in_txt = this.convertDict();
        
        txt.Write("Purchase_Order", record_in_txt, true);
    }
    
    public purchaseOrder(String po_id, String pr_id, String supplier_id, GregorianCalendar order_date, 
            po_stat po_status, String created_by, 
            String approved_by, GregorianCalendar approval_date){
        this.po_id = po_id;
        this.pr_id = pr_id;
        this.supplier_id = supplier_id;
        this.order_date = order_date;
        this.po_status = po_status;
        this.created_by = created_by;
        this.approved_by = approved_by;
        this.approval_date = approval_date;
        
        this.add_new_record();
    }
    
    private boolean add_new_record(){
        all_po.put(po_id, this);
        
        return true;
    }
    
    public void editPO(String supplier_id, GregorianCalendar order_date, 
            String po_status, String created_by, String approved_by, 
            GregorianCalendar approval_date) throws IOException{
        this.supplier_id = supplier_id;
        this.order_date = order_date;
        setPo_status(po_status);
        this.created_by = created_by;
        this.approved_by = approved_by;
        this.approval_date = approval_date;
        
        rewriteFile();
    }

    public static void rewriteFile() throws IOException{
        List<Map<String, Object>> contents = new ArrayList<>();
        for (purchaseOrder po : all_po.values()) {
            Map<String, Object> record_in_txt = po.convertDict();
            contents.add(record_in_txt);      
        }     
        
        txt.Write("Purchase_Order", contents);
    }
    
    public static int getCur_po_num() {
        return cur_po_num;
    }

//    public static void setCur_po_num(int cur_po_num) {
//        purchaseOrder.cur_po_num = cur_po_num;
//    }

    public static Map<String, purchaseOrder> getAll_po() {
        return all_po;
    }

    public static void setAll_po(Map<String, purchaseOrder> all_po) {
        purchaseOrder.all_po = all_po;
    }

    public String getPr_id() {
        return pr_id;
    }

    public void setPr_id(String pr_id) {
        this.pr_id = pr_id;
    }

    public String getPo_id() {
        return po_id;
    }

    public void setPo_id(String po_id) {
        this.po_id = po_id;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public GregorianCalendar getOrder_date() {
        return order_date;
    }
    
    public String getOrder_date_str() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MMM-yyyy");
        return formattedDate.format(order_date.getTime());
    }

    public void setOrder_date(GregorianCalendar order_date) {
        this.order_date = order_date;
    }

    public String getPo_status() {
        return po_status.toString();
    }

    public void setPo_status(String po_status_str) {
        po_stat po_status_obj = switch (po_status_str) {
            case "PENDING" -> po_stat.PENDING;
            case "APPROVED" -> po_stat.APPROVED;
            default -> po_stat.REJECTED;
        };
                
        this.po_status = po_status_obj;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(String approved_by) {
        this.approved_by = approved_by;
    }

    public GregorianCalendar getApproval_date() {
        return approval_date;
    }
    
    public String getApproval_date_str() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MMM-yyyy");
        return approval_date == null? "null": formattedDate.format(approval_date.getTime());
    }

    public void setApproval_date(GregorianCalendar approval_date) {
        this.approval_date = approval_date;
    }    
    
//    public static String[] getEnumNames(Enum<?>[] e) {
//        return Arrays.stream(e).map(Enum::name).toArray(String[]::new);
//    }
    
    public static String[] getPo_stat_enum() {
        return Arrays.stream(po_stat.values()).map(Enum::name).toArray(String[]::new); 
        //getEnumNames(po_stat.values());
    } 
    
    public static void read_pr(String file){
        try{
            txt.Read(file);
        } catch (FileNotFoundException e){
            System.out.println("An error occurred." + e.getMessage());
        }
    }
    
    private Map<String, Object> convertDict(){
        Field[] allFields = this.getClass().getDeclaredFields();
        Map<String, Object> dict = new LinkedHashMap<>();
        for(Field field:allFields){      
            try{
                 if (!(Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers()))) {
                    var value = field.get(this);
                    field.setAccessible(true); // Allows access to private fields
                    if (value == null) {
                        value = "null"; // or another default value
                    } else if (field.getType() == GregorianCalendar.class){
                        SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MMM-yyyy");
                        GregorianCalendar calendar = (GregorianCalendar) value;
                        value = formattedDate.format(calendar.getTime());
                    } else {
                        // COnvert to string format
                        value = "" + value;
                    }
                    dict.put(field.getName(), value); 
                 }
            } catch (IllegalAccessException e){
                System.out.println("Could not access field: " + field.getName());
            }
        }
            
        return dict;
    }
    
    public static void Read() throws FileNotFoundException, ParseException{
        List<Map<String,String>> all_obj = txt.Read("Purchase_Order");
        
        for (Map<String,String> dict: all_obj){            
            try{
                int po_id = Integer.parseInt(dict.get("po_id").substring(2));
                cur_po_num = Math.max(cur_po_num, po_id);
                
                GregorianCalendar order_date, approval_date = null;
                po_stat po_status;
            
                DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                
                Date date = formatter.parse(dict.get("order_date"));
                order_date = new GregorianCalendar();
                order_date.setTime(date);
                
                if (!"null".equals(dict.get("approval_date"))){
                    date = formatter.parse(dict.get("approval_date"));
                    approval_date = new GregorianCalendar();
                    approval_date.setTime(date);
                } 
                
                po_status = switch (dict.get("po_status")) {
                    case "PENDING" -> po_stat.PENDING;
                    case "APPROVED" -> po_stat.APPROVED;
                    default -> po_stat.REJECTED;
                };
                
                String approved = "null".equals(dict.get("approved_by"))? null: dict.get("approved_by"); 
                                
                purchaseOrder instance = new purchaseOrder(dict.get("po_id"), 
                    dict.get("pr_id"), dict.get("supplier_id"), order_date, 
                    po_status, dict.get("created_by"), approved, approval_date);
               
                all_po.put(dict.get("po_id"), instance);

            } catch (ParseException e){
                System.out.println("An error occurred." + e.getMessage());
            }
           
        }
        
    }
    
    public static TableModel populateTable(editPermission permission, String creator) {
        DefaultTableModel model = new DefaultTableModel(
            new Object[] { "Purchase Order ID", "Purchase Requisition ID", "Supplier ID", "Order Date", "Status", "Created By", "Approved/Rejected By", "Approval Date"}, 0
        ){
            @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
        };
        
        // ArrayList<purchaseOrder> po_list = new ArrayList<purchaseOrder>(all_po.values());
        List<purchaseOrder> po_list;
        
        if (permission == editPermission.edit){
            po_list = all_po.values().stream()
            //.filter(po -> po.getCreated_by().equals(creator))
            .toList();
        } else {
            po_list = new ArrayList(all_po.values());
        }
                   
        // Debugging: Check if getAll_po() returns any records
        System.out.println("Number of purchase orders in system: " + all_po.size());
        System.out.println("Number of purchase orders in table: " + po_list.size());

        for (purchaseOrder po : po_list) {

            model.addRow(new Object[] { 
                po.getPo_id(), 
                po.getPr_id(), 
                po.getSupplier_id(), 
                po.getOrder_date_str(), 
                po.getPo_status(), 
                po.getCreated_by(),
                po.getApproved_by(),
                po.getApproval_date_str(),
            });
        }

        return model;
    }
}
