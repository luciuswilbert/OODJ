package javaassignment;

import javax.swing.SwingUtilities;

public class JavaAssignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InventoryManager inventoryManager = new InventoryManager();
                inventoryManager.setVisible(true); // Show the form
            }
        });
        
        
    }
    
    
}
