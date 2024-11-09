package asm.utilities; // Creating as text File using FileWriter 
import java.io.File; // Import the File class
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.GregorianCalendar;

public class txt{

    // Static methods Can be called directly using the class name without creating an object of that class
    // don’t need instance-specific data
    public static List<Map<String,String>> Read(String file) throws FileNotFoundException
    {
        // Create a empty List of Dictionary
        List<Map<String, String>> all_obj = new ArrayList<>();
        try {
            // Attach a file to FileReader  
            File fr = new File(file);

            // The Scanner can take different types of inputs, like System.in (for reading user input) 
            // or a File object (for reading from a file).
            Scanner scan = new Scanner(fr);

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
                Map<String, String> obj = new LinkedHashMap<>();

                // for each pair in pairs
                for (String pair : pairs) {
                    // pair = "a=1"
                    // Split by =, keyValue = ["a", "1"]
                    String[] keyValue = pair.split("=");
                    String value; // Declare the value variable

                    // Check whether it is only two value in keyValue list
                    if (keyValue.length == 2) {
                        String key = keyValue[0].trim();
                        value = keyValue[1].trim();
                        obj.put(key, value); // Add to the dictionary
                    }
                }

                // Add the user dictionary to the users list
                all_obj.add(obj);
            }
            // Close the reader  
            scan.close();
            
        } catch (FileNotFoundException e){
            System.out.println("An error occurred." + e.getMessage());
        }
        return all_obj;
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
        } catch (IOException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
        
    }
    
    public static void Write(String file, Map<String, Object> content, boolean append)throws IOException {
        // Try-with-resources to ensure FileWriter is closed properly
        try (FileWriter writer = new FileWriter(file, append)) {
            // Convert each Map to a String and write it to the file
            writer.write(content.toString() + System.lineSeparator()); // Adds a new line after each map

            // Close the FileWriter
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
        
    }
    
    public boolean check_semicolon(Object obj){
        if (obj instanceof String){
            return (obj.toString().contains(",")) || 
                    (obj.toString().contains("{")) ||
                    (obj.toString().contains("}")) ||
                    (obj.toString().contains("[")) ||
                    (obj.toString().contains("]")) ||
                    (obj.toString().contains("="));
        }
        
        return false;
    }
    
}