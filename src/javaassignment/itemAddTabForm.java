/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaassignment;

/**
 *
 * @author Lucius
 */
public class itemAddTabForm extends javax.swing.JFrame {

    /**
     * Creates new form itemAddTabForm
     */
    public itemAddTabForm() {
        initComponents();
        setTitle("Add Item");
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        itemTab = new javax.swing.JMenu();
        itemAddTab = new javax.swing.JMenu();
        itemEditTab = new javax.swing.JMenu();
        itemDeleteTab = new javax.swing.JMenu();
        itemViewTab = new javax.swing.JMenu();
        supTab = new javax.swing.JMenu();
        supAddTab = new javax.swing.JMenu();
        supEditTab = new javax.swing.JMenu();
        supDeleteTab = new javax.swing.JMenu();
        supViewTab = new javax.swing.JMenu();
        stockTab = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        itemTab.setText("Item");

        itemAddTab.setText("Add");
        itemTab.add(itemAddTab);

        itemEditTab.setText("Edit");
        itemEditTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemEditTabMouseClicked(evt);
            }
        });
        itemTab.add(itemEditTab);

        itemDeleteTab.setText("Delete");
        itemTab.add(itemDeleteTab);

        itemViewTab.setText("View");
        itemTab.add(itemViewTab);

        jMenuBar1.add(itemTab);

        supTab.setText("Supplier");

        supAddTab.setText("Add");
        supAddTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supAddTabMouseClicked(evt);
            }
        });
        supTab.add(supAddTab);

        supEditTab.setText("Edit");
        supEditTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supEditTabMouseClicked(evt);
            }
        });
        supTab.add(supEditTab);

        supDeleteTab.setText("Delete");
        supTab.add(supDeleteTab);

        supViewTab.setText("View");
        supTab.add(supViewTab);

        jMenuBar1.add(supTab);

        stockTab.setText("Stock");
        jMenuBar1.add(stockTab);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 377, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void supAddTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supAddTabMouseClicked
    }//GEN-LAST:event_supAddTabMouseClicked

    private void supEditTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supEditTabMouseClicked
   
    }//GEN-LAST:event_supEditTabMouseClicked

    private void itemEditTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemEditTabMouseClicked
        new itemEditTabForm().setVisible(true);
    }//GEN-LAST:event_itemEditTabMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(itemAddTabForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(itemAddTabForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(itemAddTabForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(itemAddTabForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new itemAddTabForm().setVisible(true);
            }
        });
    }

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenu itemAddTab;
    public javax.swing.JMenu itemDeleteTab;
    public javax.swing.JMenu itemEditTab;
    public javax.swing.JMenu itemTab;
    public javax.swing.JMenu itemViewTab;
    public javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenu stockTab;
    public javax.swing.JMenu supAddTab;
    public javax.swing.JMenu supDeleteTab;
    public javax.swing.JMenu supEditTab;
    public javax.swing.JMenu supTab;
    public javax.swing.JMenu supViewTab;
    // End of variables declaration//GEN-END:variables
}