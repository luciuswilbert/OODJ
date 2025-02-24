package Utility;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class BDUtility {
    
    public static void setImage(JLabel label, String imagePath, int maxWidth, int maxHeight){
        
        try{
            // Determine which file exists
            File imageFile = new File(imagePath);

            BufferedImage originalImage = ImageIO.read(imageFile);

            // Get the original image's dimensions
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();

            // Calculate the scaling factor while preserving aspect ratio
            double scaleX = (double) maxWidth / originalWidth;
            double scaleY = (double) maxHeight / originalHeight;
            double scale = Math.min(scaleX, scaleY);  // Ensure the image fits within maxWidth & maxHeight

            // Calculate new dimensions for the resized image
            int newWidth = (int) (originalWidth * scale);
            int newHeight = (int) (originalHeight * scale);

             // Ensure the new dimensions are not smaller than 150x150
            newWidth = Math.max(newWidth, 150);
            newHeight = Math.max(newHeight, 150);

            // Resize the image
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // Convert resized image to BufferedImage for cropping
            BufferedImage resizedBufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedBufferedImage.createGraphics();
            g2d.drawImage(resizedImage, 0, 0, null);
            g2d.dispose();

            // Crop the image from the center if necessary
            int cropX = (newWidth - maxWidth) / 2;
            int cropY = (newHeight - maxHeight) / 2;

            // Crop the image if it's larger than the max size
            if (newWidth > maxWidth || newHeight > maxHeight) {
                resizedBufferedImage = resizedBufferedImage.getSubimage(cropX, cropY, maxWidth, maxHeight);
                }

            // Create a JLabel to hold the resized and cropped image
            label.setIcon(new ImageIcon(resizedBufferedImage));
            label.setBounds(0, 0, maxWidth, maxHeight);  // Set size to maxWidth and maxHeight
           
        }
        catch(IOException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public static void  deleteFile(String filePath){
        File file = new File(filePath);
        
        if (file.exists() || file.isFile()){
            // Try to delete the file and print a success or failure message
            if (file.delete()) {
                System.out.println("File deleted successfully");
            } else {
                System.out.println("Failed to delete the file");
            }
        }
        else {
            System.out.println("File not found or not a valid file");
        }
    }
    
}    
