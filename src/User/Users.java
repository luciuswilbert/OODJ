package User;

import Utility.txt;
import java.io.Serializable;
import java.util.List;


public abstract class Users implements Serializable{
    private static final long serialVersionUID = 1L;
    private String user_id, fullname, username, password, email, contact_number, image_path, role;
    protected static int cur_num = 0;
    protected final int id_paddingSize = 4;
    public abstract String Generate_UserID();
    private static List<Object> objects = txt.ReadFromDAT("src/User/User.dat");
    


    public Users(String username, String password, String fullname, String email, String contact_number, String role,
            String img) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.contact_number = contact_number;
        this.role = role;
        this.image_path = img;
    }
    

    public Users() {
    }
    

    public String getUser_id() {
        return user_id;
    }
    
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String Password){
        password = Password;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        email = Email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String Contact_Number) {
        contact_number = Contact_Number;
    }
    
   public void readLastID() {
        List<Object> objects = txt.ReadFromDAT("src/User/User.dat"); // Read all objects from the file
        int maxID = 0; // Initialize max ID to 0

        // Loop through all objects and find the maximum ID
        for (Object object : objects) {
            String userID = null;

            switch (object) {
                case Admin admin -> userID = admin.getUser_id();
                case PurchaseManager purchase -> userID = purchase.getUser_id();
                case FinanceManager finance -> userID = finance.getUser_id();
                case SalesManager sale -> userID = sale.getUser_id();
                case InventoryManager inventory -> userID = inventory.getUser_id();
                default -> {
                }
            }

            // Extract the numeric portion and update maxID
            if (userID != null && !userID.isEmpty()) {
                try {
                    int numericID = Integer.parseInt(userID.substring(2)); // Ignore the prefix
                    maxID = Math.max(maxID, numericID);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid user ID format: " + userID);
                }
            }
        }

        // Update cur_num with the maximum ID
        cur_num = maxID;
        System.out.println("The highest ID found is: " + cur_num);
    }
   
   
    @ Override
    public String toString(){
        return "Please specify which detail you want.";
    }

}
