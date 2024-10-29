package OODJassignment;
public class Supplier {
    private int supId;
    private String supName;
    private String supEmail;
    private String supConNum;

    public Supplier() {
        
    }
    
    public Supplier(int supId, String supName, String supEmail, String supConNum) {
        this.supId = supId;
        this.supName = supName;
        this.supEmail = supEmail;
        this.supConNum = supConNum;
        System.out.println("HI!")
    }
    
    public String getSupId() {
        return String.format("Sup" + "%03d", supId);
    }
    
    public void setSupId(int supId) {
        this.supId = supId;
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
}
