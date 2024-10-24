// Creating as text File using FileWriter 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Txt{

    public static List<Map<String,Object>> Read(String file) throws IOException
    {
        // Attach a file to FileReader  
        File fr = new File(file);

        // Wrap FileReader in Scanner for efficient reading
        Scanner scan = new Scanner(fr);

        // Create a empty List of Dictionary
        List<Map<String, Object>> users = new ArrayList<>();


        // Read file line by line
        while (scan.hasNextLine()) {
            // Read and trim the line
            // trim use to delete leading and trailing whitespace
            String line = scan.nextLine().trim(); 
            

            // Remove curly braces and split by comma
            line = line.replaceAll("[{}]", ""); // Remove {}
            String[] pairs = line.split(","); // Split by comma


            // Create a dictionary for the user
            Map<String, Object> user = new LinkedHashMap<>();
            Object value;
            for (String pair : pairs) {
                // Split each pair by equal
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim().replaceAll("\"", ""); // Remove quotes
                    if (key.equals("user_id")){
                        value = Integer.valueOf(keyValue[1].trim().replaceAll("\"", ""));
                    }
                    else{
                        value = keyValue[1].trim().replaceAll("\"", ""); // Remove quotes
                    }
                    
                    user.put(key, value); // Add to the dictionary
                }
            }

            // Add the user dictionary to the users list
            users.add(user);
        }
        // Close the reader  
        scan.close();
        return users;
    }


    public static void Write(String file, List<Map<String, Object>> contents)throws IOException {
        // Try-with-resources to ensure FileWriter is closed properly
        try (FileWriter writer = new FileWriter(file)) {
            // Iterate over the contents list and write each map to the file
            for (Map<String, Object> content : contents) {
                // Convert each Map to a String and write it to the file
                writer.write(content.toString() + System.lineSeparator()); // Adds a new line after each map
            }

            // Close the FileWriter
            writer.close();
        }
        
    }
    
}