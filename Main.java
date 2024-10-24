import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args)
    {
        try {
            String file = "User.txt";
            List<Map<String, Object>> users = Txt.Read(file);
            int user_id = 2;

            // user.get("user_id") returns an Object, so we cast it to int
            // user ->: For each user (a map) in the users list:
            users.removeIf(user -> user_id == (int) user.get("user_id")); 
            Txt.Write(file, users);

        } catch (IOException e) {
            System.out.println(e);
        }
        
    }

}

