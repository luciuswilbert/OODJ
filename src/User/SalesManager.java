package User;

public class SalesManager extends Users{
    public SalesManager(String username,String fullname, String password, String email, String contact_number, 
            String role, String img) {
        super(username, password, fullname, email, contact_number, role, img);
    }
    
    public SalesManager() {
        super();
    }

    @Override
    public final String Generate_UserID(){
        readLastID();
        String id_num = String.format("%0" + id_paddingSize + "d", cur_num + 1);
        return String.join("", "SM", id_num);
    }
    
}


