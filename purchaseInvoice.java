
package asm;

import asm.utilities.editPermission;
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
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class purchaseInvoice {
    static int cur_pi_num=0;
    static Map<String,purchaseInvoice> all_pi = new LinkedHashMap<>();
    final int id_paddingSize=4;
    private static enum pi_stat{NOT_PAID,PAID};
    private static enum delivery_stat{PENDING,SHIPPED,DELIVERED};
    
    private String pi_id;
    private String po_id;
    private String supplier_id;
    private GregorianCalendar expected_date;
    private GregorianCalendar actual_date;
    private String created_by;
    private GregorianCalendar created_date;
    private pi_stat pi_status;
    private delivery_stat delivery_status;
    //want amount ma
    
    public purchaseInvoice(String po_id, String supplier_id,String created_by)throws IOException{
    String id_num = String.format("%0"+id_paddingSize+"d",+cur_pi_num+1);
    pi_id=String.join("","PI",id_num);
    cur_pi_num += 1;
    this.po_id=po_id;
    this.supplier_id=supplier_id;
    expected_date=null;
    actual_date=null;
    this.created_by=created_by;
    this.created_date=new GregorianCalendar();
    pi_status=pi_stat.NOT_PAID;
    delivery_status = delivery_stat.PENDING;
    
    this.add_new_record();
    Map<String,Object> record_in_txt = this.convertDict();
    txt.Write("Purchase_Invoice", record_in_txt, true);
    }
    
    public purchaseInvoice (String pi_id,String po_id, String supplier_id, GregorianCalendar expected_date, GregorianCalendar actual_date,String created_by,GregorianCalendar created_date,pi_stat pi_status,delivery_stat delivery_status){
        this.pi_id=pi_id;
        this.po_id=po_id;
        this.supplier_id=supplier_id;
        this.expected_date=expected_date;
        this.actual_date=actual_date;
        this.created_by=created_by;
        this.created_date=created_date;
        this.pi_status=pi_status;
        this.delivery_status=delivery_status;
        
        this.add_new_record();
    }
    
    private boolean add_new_record(){
        all_pi.put(pi_id,this);
        return true;
    }
    
    public void editInvoice (String supplier_id, GregorianCalendar expected_date, GregorianCalendar actual_date,String created_by,GregorianCalendar created_date,String pi_status,String delivery_status) throws IOException{
        this.supplier_id=supplier_id;
        this.expected_date=expected_date;
        this.actual_date=actual_date; //maybe inventory can directly call this function?
        this.created_by=created_by;
        this.created_date=created_date;
        setPi_status(pi_status);
        setDelivery_status(delivery_status);
        
        rewriteFile();
    }
    
    public static void rewriteFile() throws IOException{
        List<Map<String,Object>> contents = new ArrayList<>();
        for(purchaseInvoice pi:all_pi.values()){
        Map<String,Object> record_in_txt = pi.convertDict();
        contents.add(record_in_txt);
    }
        txt.Write("Purchase_Invoice", contents);
    }
    
    public static int getCur_pi_num(){
        return cur_pi_num;
    }
    
    public static Map<String,purchaseInvoice> getAll_pi(){
        return all_pi;
    }
    
    public static void setAll_pi(Map<String,purchaseInvoice> all_pi){
        purchaseInvoice.all_pi = all_pi;
    }
    
    //getter and setter

    public String getPi_id() {
        return pi_id;
    }

    public void setPi_id(String pi_id) {
        this.pi_id = pi_id;
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

    public GregorianCalendar getExpected_date() {
        return expected_date;
    }
    
    public String getExpected_date_str() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MMM-yyyy");
        return formattedDate.format(expected_date.getTime());
    }

    public void setExpected_date(GregorianCalendar expected_date) {
        this.expected_date = expected_date;
    }

    public GregorianCalendar getActual_date() {
        return actual_date;
    }
    
    public String getActual_date_str() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MMM-yyyy");
        return actual_date == null? "null": formattedDate.format(actual_date.getTime());

    }

    public void setActual_date(GregorianCalendar actual_date) {
        this.actual_date = actual_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
    
    public GregorianCalendar getCreated_date(){
        return created_date;
    }

    public String getCreated_date_str() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MMM-yyyy");
        return created_date == null? "null": formattedDate.format(created_date.getTime());
    }

    public void setCreated_date(GregorianCalendar created_date) {
        this.created_date = created_date;
    }
    
    public String getPi_status() {
        return pi_status.toString();
    }

    public void setPi_status(String pi_status_str) {
        pi_stat pi_status_obj = switch(pi_status_str){
            case "NOT PAID" -> pi_stat.NOT_PAID;
            case "PAID" -> pi_stat.PAID;
            default -> pi_stat.NOT_PAID; ///more enum stuff
        };
        
        this.pi_status=pi_status_obj;
    }

    public String getDelivery_status() {
        return delivery_status.toString();
    }

    public void setDelivery_status(String deliver_status_str) {
        delivery_stat delivery_status_obj = switch(deliver_status_str){
            case "DELIVERED" -> delivery_stat.DELIVERED;
            case "SHIPPED" -> delivery_stat.SHIPPED;
            default -> delivery_stat.PENDING;
        };
        this.delivery_status=delivery_status_obj;
    }
    
    public static String[] getPi_stat_enum() {
        return Arrays.stream(pi_stat.values()).map(Enum::name).toArray(String[]::new); 
        //getEnumNames(po_stat.values());
    } 
    
    public static void read_pi(String file){
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
        List<Map<String,String>> all_obj = txt.Read("Purchase_Invoice");
        
        for (Map<String,String> dict: all_obj){            
            try{
                int pi_id = Integer.parseInt(dict.get("pi_id").substring(2));
                cur_pi_num = Math.max(cur_pi_num, pi_id);
                
                GregorianCalendar expected_date=null,actual_date = null,created_date;
                pi_stat pi_status;
                delivery_stat delivery_status;
            
                DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                
                Date date = formatter.parse(dict.get("expected_date"));  //expected date should be null too
                expected_date = new GregorianCalendar();
                expected_date.setTime(date);
                
                if (!"null".equals(dict.get("actual_date"))){
                    date = formatter.parse(dict.get("actual_date"));
                    actual_date = new GregorianCalendar();
                    actual_date.setTime(date);
                } 
                
                Date date2 = formatter.parse(dict.get("created_date"));
                created_date = new GregorianCalendar();
                created_date.setTime(date);
                
                pi_status = switch (dict.get("pi_status")) {
                    case "NOT PAID" -> pi_stat.NOT_PAID;
                    case "PAID" -> pi_stat.PAID;
                    default -> pi_stat.NOT_PAID;
                };
                
                delivery_status = switch (dict.get("delivery_status")) {
                    case "DELIVERED" -> delivery_stat.DELIVERED;
                    case "SHIPPED" -> delivery_stat.SHIPPED;
                    default -> delivery_stat.PENDING;
                };
                              
                purchaseInvoice instance = new purchaseInvoice(dict.get("pi_id"), 
                    dict.get("po_id"), dict.get("supplier_id"), expected_date, 
                    actual_date, dict.get("created_by"), created_date, pi_status,delivery_status);
               
                all_pi.put(dict.get("pi_id"), instance);

            } catch (ParseException e){
                System.out.println("An error occurred." + e.getMessage());
            }
           
        }
        
    }
    
    public static TableModel populateTable(editPermission permission, String creator) {
        DefaultTableModel model = new DefaultTableModel(
            new Object[] { "Purchase Invoice ID", "Purchase Order ID", "Supplier ID", "Expected Delivery Date", "Actual Delivery Date", "Created By", "Date", "Payment Status","Delivery Status"}, 0
        ){
            @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
        };
        
        List<purchaseInvoice> pi_list = null;
        
        if (permission == editPermission.edit){
            pi_list = all_pi.values().stream()
            .filter(pi -> pi.getCreated_by().equals(creator))
            .toList();
        } else if (permission == editPermission.approve){
            pi_list = all_pi.values().stream()
            .filter(pi -> pi.getPi_status().equals("PAID"))
            .toList();
            System.out.println("approve"+pi_list);
        } 
                   
        // Debugging: Check if getAll_po() returns any records
        System.out.println("Number of purchase invoice in system: " + all_pi.size());
        System.out.println("Number of purchase invoice in table: " + pi_list.size());

        for (purchaseInvoice pi : pi_list) {

            model.addRow(new Object[] { 
                pi.getPi_id(), 
                pi.getPo_id(), 
                pi.getSupplier_id(), 
                pi.getExpected_date_str(), 
                pi.getActual_date_str(), 
                pi.getCreated_by(),
                pi.getCreated_date_str(),
                pi.getPi_status(),
                pi.getDelivery_status()
            });
        }

        return model;
    }
    
    
    
    
}
