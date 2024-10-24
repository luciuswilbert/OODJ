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

        // The Scanner can take different types of inputs, like System.in (for reading user input) 
        // or a File object (for reading from a file).
        Scanner scan = new Scanner(fr);

        // Create a empty List of Dictionary
        List<Map<String, Object>> users = new ArrayList<>();


        // Read file line by line
        while (scan.hasNextLine()) {
            // Read and trim the line
            // trim use to delete leading and trailing whitespace
            String line = scan.nextLine().trim(); 
            

            // Remove {}
            line = line.replaceAll("[{}]", ""); 
            //After this step, the input string becomes [a=1, b=c].

            // Split by comma
            String[] pairs = line.split(","); 
            // After this step, you get an array: ["a=1", "b=c"].


            // Create a dictionary for the user
            Map<String, Object> user = new LinkedHashMap<>();

            // for each pair in pairs
            for (String pair : pairs) {
                // pair = "a=1"
                // Split by =, keyValue = ["a", "1"]
                String[] keyValue = pair.split("=");
                Object value; // Declare the value variable

                try {
                    // Check whether it is only two value in keyValue list
                    if (keyValue.length == 2) {
                        String key = keyValue[0].trim();
                        if (key.equals("user_id")){
                            value = Integer.valueOf(keyValue[1].trim());
                        }
                        else{
                            value = keyValue[1].trim();
                        }
                        user.put(key, value); // Add to the dictionary
                    }
                    

                } catch (NumberFormatException e) {
                    System.out.println(e);
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