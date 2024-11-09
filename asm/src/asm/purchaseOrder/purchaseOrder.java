package asm.purchaseOrder;
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
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class purchaseOrder {
    static int cur_po_num = 0;
    static Map<String, purchaseOrder> all_po = new LinkedHashMap<>();
    final int id_paddingSize = 4;
    private static enum po_stat{PENDING, APPROVED, REJECTED}
    private static enum payment_stat{NULL, PENDING, PAID}
    
    private String po_id;
    private String pr_id;
    private String supplier_id;
    private GregorianCalendar order_date;
    private GregorianCalendar delivery_date;
    private po_stat po_status; // pending, approved, rejected
    private payment_stat payment_status; // null, pending, paid
    private String approved_by;
    // private String approved_by 
    private GregorianCalendar approval_date;
    
    // informs the Java compiler that any calling code will have to handle exceptions of the type Exception
    public purchaseOrder(String pr_id, String supplier_id) throws IOException{
        String id_num = String.format("%0" + id_paddingSize + "d", cur_po_num + 1);
        po_id = String.join("", "po", id_num);
        cur_po_num += 1;
        this.pr_id = pr_id;
        this.supplier_id = supplier_id;
        order_date = new GregorianCalendar();
        delivery_date = null;
        po_status = po_stat.PENDING;
        payment_status = payment_stat.NULL;
        approved_by = null;
        approval_date = null;
        
        this.add_new_record();
        
        Map<String, Object> record_in_txt = this.convertDict();
        
        txt.Write("Purchase_Order", record_in_txt, true);
    }
    
    public purchaseOrder(String po_id, String pr_id, String supplier_id, GregorianCalendar order_date, 
            GregorianCalendar delivery_date, po_stat po_status, payment_stat payment_status, 
            String approved_by, GregorianCalendar approval_date){
        this.po_id = po_id;
        this.pr_id = pr_id;
        this.supplier_id = supplier_id;
        this.order_date = order_date;
        this.delivery_date = delivery_date;
        this.po_status = po_status;
        this.payment_status = payment_status;
        this.approved_by = approved_by;
        this.approval_date = approval_date;
        
        this.add_new_record();
    }
    
    private boolean add_new_record(){
        all_po.put(po_id, this);
        
        return true;
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

//    public static void setAll_po(List<purchaseOrder> all_po) {
//        purchaseOrder.all_po = all_po;
//    }

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

    public GregorianCalendar getDelivery_date() {
        return delivery_date;
    }
    
    public String getDelivery_date_str() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MMM-yyyy");
        return formattedDate.format(delivery_date.getTime());
    }

    public void setDelivery_date(GregorianCalendar delivery_date) {
        this.delivery_date = delivery_date;
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

    public String getPayment_status() {
        return payment_status.toString();
    }

    public void setPayment_status(String payment_status_str) {
        payment_stat payment_status_obj = switch (payment_status_str) {
            case "NULL" -> payment_stat.NULL;
            case "PENDING" -> payment_stat.PENDING;
            default -> payment_stat.PAID;
        };
        this.payment_status = payment_status_obj;
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
        return formattedDate.format(approval_date.getTime());
    }

    public void setApproval_date(GregorianCalendar approval_date) {
        this.approval_date = approval_date;
    }    
    
    public static String[] getEnumNames(Enum<?>[] e) {
        return Arrays.stream(e).map(Enum::name).toArray(String[]::new);
    }
    
    public static String[] getPo_stat_enum() {
        return getEnumNames(po_stat.values());
    } 
    
    public static String[] getPayment_stat_enum() {
        return getEnumNames(payment_stat.values());
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
                
                GregorianCalendar order_date, delivery_date = null, approval_date = null;
                po_stat po_status;
                payment_stat payment_status;
            
                DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                Date date = formatter.parse(dict.get("order_date"));
                order_date = new GregorianCalendar();
                order_date.setTime(date);
                
                if (!"null".equals(dict.get("delivery_date"))){
                    date = formatter.parse(dict.get("delivery_date"));
                    delivery_date = new GregorianCalendar();
                    delivery_date.setTime(date);
                }
                
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
                
                payment_status = switch (dict.get("payment_status")) {
                    case "NULL" -> payment_stat.NULL;
                    case "PENDING" -> payment_stat.PENDING;
                    default -> payment_stat.PAID;
                };
                
                purchaseOrder instance = new purchaseOrder(dict.get("po_id"), 
                    dict.get("pr_id"), dict.get("supplier_id"), order_date, delivery_date, 
                    po_status, payment_status, dict.get("approved_by"), approval_date);
               
                all_po.put(dict.get("po_id"), instance);

            } catch (ParseException e){
                System.out.println("An error occurred." + e.getMessage());
            }
           
        }
        
    }
}
