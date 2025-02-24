import Admin.AdminPage;
import User.Admin;
import User.FinanceManager;
import User.InventoryManager;
import User.PurchaseManager;
import User.SalesManager;
import java.io.IOException;
import User.Users;
import Utility.txt;
import static Utility.txt.ReadFromDAT;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Main {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
//       
        Admin admin = new Admin("twk74520", "Tan Wai Ken", "Twk74520@", "admin@example.com", "1", "admin","src/Images_File/Tan1234@.png");
        admin.setUser_id(admin.Generate_UserID());
//////        System.out.println(admin3.getEmail());
//////        txt.WriteToDAT("src/User/User.dat", admin3);
////        
////        
////        
        AdminPage ad = new AdminPage(admin);
        ad.setVisible(true);
//        
//        
//        List<Object> objects = ReadFromDAT("src/User/User.dat");
//
//        
//        List<Object> updatedObjects = new ArrayList<>();
//        // Loop through each object and add data to the table model
//        for (Object object : objects) {
//            if (object instanceof Users user) {
//               if (user.getUser_id().equals("00005")) {
//                    // Skip this user (effectively deleting it)
//                    user.setUser_id("SM0005");
//                }
//                // Add all other objects back to the updated list
//                updatedObjects.add(object);
//            }  
//        }
//       
//        txt.WriteToDAT("src/User/User.dat", updatedObjects);
        

//        try {^
//            String file = "src/File/User.txt";
//            List<Map<String, String>> users = txt.Read(file);
//
//            for(Map<String, String> user : users){
//                String username = (String) user.get("username");
//                String password = (String) user.get("password");
//            }
//
//            
////            int user_id = 2;
////
////            // user.get("user_id") returns an Object, so we cast it to int
////            // user ->: For each user (a map) in the users list:
////            users.removeIf(user -> user_id == (Integer) user.get("user_id")); 
////            Txt.Write(file, users);
//
//        } catch (IOException e) {
//            System.out.println(e);
//        }

        
        
    }
    
}        
        
        
        

        
 

