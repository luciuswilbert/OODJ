package Utility; 
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import javax.swing.JOptionPane;


public class txt{
    public static List<Object> ReadFromDAT(String file)
    {
        System.out.println("READING " + file + ".......");
        List<Object> all_obj = new ArrayList<>();
        
        if (!isValidFile(file, true)) {
            return all_obj; // Return empty list
        }

        Object obj;
        try {
            File f = new File(file);
            
            // Check if the file exists and is not empty
            if (!f.exists() || f.length() == 0) {
                System.out.println(file+ " file is empty or does not exist.");
                return all_obj; // Return an empty list
            }
            
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            while(true){
                try {
                    obj = ois.readObject();
                    all_obj.add(obj);
                } catch (EOFException e) {
                    // End of file reached, break the loop
                    break;
                }
            }
            ois.close();
        } catch (FileNotFoundException | ClassNotFoundException e){
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e){
            System.out.println("IO error in reading dat file: " + e.getMessage());
        }
        return all_obj;
    }

    public static void WriteToDAT(String file, List<Object> contents){
        // Rewrite all records
        System.out.println("REWRITING " + file + ".......");
        if (isValidFile(file, false)){
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                for (Object content : contents) {
                    oos.writeObject(content);
                }
                oos.close();
            } catch (IOException e) {
                System.out.println("IO error in rewriting dat file: " + e.getMessage());
            }
        }
        
    }
    
    public static void WriteToDAT(String file, Object content){
        System.out.println("APPENDING " + file + ".......");
        if (isValidFile(file, false)){
            // Append a single record
            File f = new File(file);

            try (FileOutputStream fos = new FileOutputStream(f, true);
                 ObjectOutputStream oos = f.exists() && f.length() > 0
                         ? new AppendableObjectOutputStream(fos) // Custom stream without writing a new header
                         : new ObjectOutputStream(fos)) {

                oos.writeObject(content);
                oos.close();

            } catch (IOException e) {
                System.out.println("IO error in appending dat file: " + e.getMessage());
            }
        }
    }
    
    
//    public static void WriteToDAT(String file, Object content, boolean yes){
//        List<Object> existingContents = new ArrayList<>();
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
//            while (yes) {
//                existingContents.add(ois.readObject());
//            }
//        } catch (EOFException e) {
//            // End of file reached, this is expected
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("IO error in reading dat file: " + e.getMessage());
//        }
//
//        // Add the new content
//        existingContents.add(content);
//
//        // Rewrite the file with the updated contents
//        WriteToDAT(file, existingContents);
//        
//    }

    // Static methods Can be called directly using the class name without creating an object of that class
    // don’t need instance-specific data
    public static List<Map<String,String>> ReadFromTxt(String file)
    {
        // Create a empty List of Dictionary
        List<Map<String, String>> all_obj = new ArrayList<>();
        
        try{
            if (!isValidFile(file, true)){
                return all_obj;
            }

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
        }
        catch(IOException e){
            System.out.println("An error occurred." + e.getMessage());
        }
        return all_obj;
    }

    public static void WriteToTxt(String file, List<Map<String, String>> contents){
        // Write all records
        // Try-with-resources to ensure FileWriter is closed properly
        try(FileWriter writer = new FileWriter(file)){
            // Iterate over the contents list and write each map to the file
            for (Map<String, String> content : contents) {
                // Convert each Map to a String and write it to the file
                writer.write(content + System.lineSeparator()); // Adds a new line after each map
            }

            // Close the FileWriter
            writer.close();
        }
        catch(IOException e){
            System.out.println("An error occurred." + e.getMessage());
        }
    }
    
//    public static void WriteToTxt(String file, Map<String, Object> content, boolean append)throws IOException {
//        // Append a single record
//        // Try-with-resources to ensure FileWriter is closed properly
//        try (FileWriter writer = new FileWriter(file, append)) {
//            // Convert each Map to a String and write it to the file
//            writer.write(content.toString() + System.lineSeparator()); // Adds a new line after each map
//
//            // Close the FileWriter
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("An error occurred." + e.getMessage());
//        }
//        
//    }
    
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
    
    private static boolean isValidFile(String file, boolean read) {
    File f = new File(file);
    boolean exist = read? f.exists() && f.canRead(): true;
    if (!exist|| f.isDirectory()) {
        System.out.println("Invalid file: " + file);
        System.out.println("Read/Write operation cancelled.");
        return false;
    }
        return true;
    }
    
    public static String getUniqueFileName(){
        boolean unique = true;
        String fileName = "";
        while (unique){
            fileName = JOptionPane.showInputDialog(null, "Enter a new file name (e.g. \"Example\"):");
//            System.out.println(fileName);
            if(fileName == null){
                return null;
            } else if(fileName.equals("")){
                JOptionPane.showMessageDialog(null, "The file name entered is invalid. Please try again!", 
                "Duplicate File Name", 
                JOptionPane.WARNING_MESSAGE);
            } else {
                File f = new File(fileName + ".txt");
//            System.out.println(f.exists());

                if(f.exists()){
                    JOptionPane.showMessageDialog(null, "The file name entered exists in the application directory. Please try again!", 
                    "Duplicate File Name", 
                    JOptionPane.WARNING_MESSAGE);
                } else {
                    unique = false;
                }
            }
            
        }
        
        return fileName;
    }

}

// Custom ObjectOutputStream that skips writing the header
class AppendableObjectOutputStream extends ObjectOutputStream {
    public AppendableObjectOutputStream(FileOutputStream fos) throws IOException {
        super(fos);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }
}