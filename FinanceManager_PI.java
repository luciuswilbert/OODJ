/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package asm;

import asm.utilities.editPermission;
import java.awt.BorderLayout;

public class FinanceManager_PI extends javax.swing.JFrame {

    /**
     * Creates new form FinanceManager_PI
     */
    public FinanceManager_PI() {
        initComponents();
        setSize(1024,650);
    }
    private asm.PurchaseOrder_table_and_form purchaseOrder_panel;
    private asm.purchaseInvoice_table_and_form purchaseInvoice_panel;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FinanceManager = new javax.swing.JTabbedPane();
        FM_user = new javax.swing.JPanel();
        FM_inventory = new javax.swing.JPanel();
        FM_po = new javax.swing.JPanel();
        FM_pi = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        FinanceManager.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        javax.swing.GroupLayout FM_userLayout = new javax.swing.GroupLayout(FM_user);
        FM_user.setLayout(FM_userLayout);
        FM_userLayout.setHorizontalGroup(
            FM_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 912, Short.MAX_VALUE)
        );
        FM_userLayout.setVerticalGroup(
            FM_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        FinanceManager.addTab("User", FM_user);

        javax.swing.GroupLayout FM_inventoryLayout = new javax.swing.GroupLayout(FM_inventory);
        FM_inventory.setLayout(FM_inventoryLayout);
        FM_inventoryLayout.setHorizontalGroup(
            FM_inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 912, Short.MAX_VALUE)
        );
        FM_inventoryLayout.setVerticalGroup(
            FM_inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        FinanceManager.addTab("Inventory", FM_inventory);

        javax.swing.GroupLayout FM_poLayout = new javax.swing.GroupLayout(FM_po);
        FM_po.setLayout(FM_poLayout);
        FM_poLayout.setHorizontalGroup(
            FM_poLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 912, Short.MAX_VALUE)
        );
        FM_poLayout.setVerticalGroup(
            FM_poLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        FinanceManager.addTab("Purchase Order", FM_po);
        purchaseOrder_panel = new asm.PurchaseOrder_table_and_form(editPermission.approve, "FM0001");
        FM_po.add(purchaseOrder_panel);

        FM_po.setLayout(new BorderLayout());
        FM_po.add(purchaseOrder_panel, BorderLayout.CENTER);

        javax.swing.GroupLayout FM_piLayout = new javax.swing.GroupLayout(FM_pi);
        FM_pi.setLayout(FM_piLayout);
        FM_piLayout.setHorizontalGroup(
            FM_piLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 912, Short.MAX_VALUE)
        );
        FM_piLayout.setVerticalGroup(
            FM_piLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        FinanceManager.addTab("Puchase Invoice", FM_pi);
        purchaseInvoice_panel = new asm.purchaseInvoice_table_and_form(editPermission.edit, "FM0001");
        FM_pi.add(purchaseInvoice_panel);

        FM_pi.setLayout(new BorderLayout());
        FM_pi.add(purchaseInvoice_panel, BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FinanceManager, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FinanceManager, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FinanceManager_PI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FinanceManager_PI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FinanceManager_PI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinanceManager_PI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinanceManager_PI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FM_inventory;
    private javax.swing.JPanel FM_pi;
    private javax.swing.JPanel FM_po;
    private javax.swing.JPanel FM_user;
    private javax.swing.JTabbedPane FinanceManager;
    // End of variables declaration//GEN-END:variables
}
