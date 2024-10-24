import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args)
    {
        try {
            String file = "User.txt";
            List<Map<String, Object>> users = Txt.Read(file);
            System.out.println(users.getClass().getName());
            System.out.println(users);

            for(Map<String, Object> user : users){
                Integer user_id = (Integer) user.get("user_id");
                System.out.println(user_id);
                System.out.println(user_id.getClass());
            }

            int user_id = 2;

            // user.get("user_id") returns an Object, so we cast it to int
            // user ->: For each user (a map) in the users list:
            users.removeIf(user -> user_id == (Integer) user.get("user_id")); 
            Txt.Write(file, users);

        } catch (IOException e) {
            System.out.println(e);
        }
        
    }

}

