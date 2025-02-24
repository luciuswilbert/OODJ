package User;


public class FinanceManager extends Users {
    public FinanceManager(String username,String fullname, String password, String email, String contact_number, 
            String role, String img) {
        super(username, password, fullname, email, contact_number, role, img);
    }

    public FinanceManager() {
        super();
    }
   
    
    @Override
    public final String Generate_UserID(){
        readLastID();
        String id_num = String.format("%0" + id_paddingSize + "d", cur_num + 1);
        return String.join("", "FM", id_num);
    }


}
