/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FinanceManager;

import Admin.Profile_form;
import InventoryManager.StockPanel;
import Login.Login;
import User.FinanceManager;
import Utility.editPermission;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;

public class FinanceManager_PI extends javax.swing.JFrame {
    private PurchaseManager.PurchaseOrder_table_and_form purchaseOrder_panel;
    private purchaseInvoice_table_and_form purchaseInvoice_panel;
    private Profile_form profile_panel;
    DefaultTableModel itemModel=new DefaultTableModel();
    private final FinanceManager finance;


    public FinanceManager_PI(FinanceManager finance) {
        this.finance = finance;
        initComponents();
        setSize(1074, 750);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FinanceManager = new javax.swing.JTabbedPane();
        FM_user = new javax.swing.JPanel();
        FM_inventory = new javax.swing.JPanel();
        FM_po = new javax.swing.JPanel();
        FM_pi = new javax.swing.JPanel();
        logout = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        FinanceManager.setPreferredSize(new java.awt.Dimension(1024, 615));

        FM_user.setPreferredSize(new java.awt.Dimension(1024, 615));

        javax.swing.GroupLayout FM_userLayout = new javax.swing.GroupLayout(FM_user);
        FM_user.setLayout(FM_userLayout);
        FM_userLayout.setHorizontalGroup(
            FM_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        FM_userLayout.setVerticalGroup(
            FM_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        FinanceManager.addTab("User Profile", FM_user);
        Profile_form profile_form = new Profile_form(finance);
        FM_user.setLayout(new BorderLayout());
        FM_user.add(profile_form, BorderLayout.CENTER);

        FM_user.revalidate();
        FM_user.repaint();

        FM_inventory.setPreferredSize(new java.awt.Dimension(1024, 615));

        javax.swing.GroupLayout FM_inventoryLayout = new javax.swing.GroupLayout(FM_inventory);
        FM_inventory.setLayout(FM_inventoryLayout);
        FM_inventoryLayout.setHorizontalGroup(
            FM_inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        FM_inventoryLayout.setVerticalGroup(
            FM_inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        FinanceManager.addTab("Inventory", FM_inventory);
        StockPanel stock_form = new StockPanel();
        FM_inventory.setLayout(new BorderLayout());
        FM_inventory.add(stock_form, BorderLayout.CENTER);

        FM_inventory.revalidate();
        FM_inventory.repaint();

        FM_po.setPreferredSize(new java.awt.Dimension(1024, 615));

        javax.swing.GroupLayout FM_poLayout = new javax.swing.GroupLayout(FM_po);
        FM_po.setLayout(FM_poLayout);
        FM_poLayout.setHorizontalGroup(
            FM_poLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        FM_poLayout.setVerticalGroup(
            FM_poLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        FinanceManager.addTab("Purchase Order", FM_po);
        purchaseOrder_panel = new PurchaseManager.PurchaseOrder_table_and_form(editPermission.approve, finance.getUser_id());
        FM_po.add(purchaseOrder_panel);

        FM_po.setLayout(new BorderLayout());
        FM_po.add(purchaseOrder_panel, BorderLayout.CENTER);

        FM_pi.setPreferredSize(new java.awt.Dimension(1024, 615));

        javax.swing.GroupLayout FM_piLayout = new javax.swing.GroupLayout(FM_pi);
        FM_pi.setLayout(FM_piLayout);
        FM_piLayout.setHorizontalGroup(
            FM_piLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        FM_piLayout.setVerticalGroup(
            FM_piLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        FinanceManager.addTab("Puchase Invoice", FM_pi);
        purchaseInvoice_panel = new purchaseInvoice_table_and_form(editPermission.approve, finance.getUser_id());
        FM_pi.add(purchaseInvoice_panel);

        FM_pi.setLayout(new BorderLayout());
        FM_pi.add(purchaseInvoice_panel, BorderLayout.CENTER);

        logout.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                logoutComponentShown(evt);
            }
        });

        javax.swing.GroupLayout logoutLayout = new javax.swing.GroupLayout(logout);
        logout.setLayout(logoutLayout);
        logoutLayout.setHorizontalGroup(
            logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        logoutLayout.setVerticalGroup(
            logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        FinanceManager.addTab("Log Out", logout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FinanceManager, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FinanceManager, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_logoutComponentShown
        // TODO add your handling code here:
        Login login = new Login();
        this.setVisible(false);
        login.setVisible(true);
    }//GEN-LAST:event_logoutComponentShown

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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FinanceManager finance = new FinanceManager();
                new FinanceManager_PI(finance).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FM_inventory;
    private javax.swing.JPanel FM_pi;
    private javax.swing.JPanel FM_po;
    private javax.swing.JPanel FM_user;
    private javax.swing.JTabbedPane FinanceManager;
    private javax.swing.JPanel logout;
    // End of variables declaration//GEN-END:variables
}
