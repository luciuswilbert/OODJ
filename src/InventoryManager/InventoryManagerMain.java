/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package InventoryManager;


import Admin.Profile_form;
import Login.Login;
import User.InventoryManager;
import Utility.editPermission;
import java.awt.BorderLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class InventoryManagerMain extends javax.swing.JFrame {
    private final InventoryManager inventory;

    public InventoryManagerMain(InventoryManager inventory){
        this.inventory = inventory;
        setSize(1074, 750);
        initComponents();
        refreshPage();
        
        // Add a ChangeListener for specific tabs
        jTabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("change");
                refreshPage();
            }
        });
    }
    
    private ItemPanel itemPanel;
    private SupplierPanel supplierPanel;
    private StockPanel stockPanel;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        profile = new javax.swing.JPanel();
        item_panel = new javax.swing.JPanel();
        supplier_panel = new javax.swing.JPanel();
        stock_panel = new javax.swing.JPanel();
        logout = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(670, 30));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1024, 650));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        profile.setPreferredSize(new java.awt.Dimension(1024, 650));

        javax.swing.GroupLayout profileLayout = new javax.swing.GroupLayout(profile);
        profile.setLayout(profileLayout);
        profileLayout.setHorizontalGroup(
            profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1018, Short.MAX_VALUE)
        );
        profileLayout.setVerticalGroup(
            profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 556, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Profile", profile);
        Profile_form profile_form = new Profile_form(inventory);
        profile.setLayout(new BorderLayout());
        profile.add(profile_form, BorderLayout.CENTER);

        profile.revalidate();
        profile.repaint();

        item_panel.setPreferredSize(new java.awt.Dimension(1024, 650));

        javax.swing.GroupLayout item_panelLayout = new javax.swing.GroupLayout(item_panel);
        item_panel.setLayout(item_panelLayout);
        item_panelLayout.setHorizontalGroup(
            item_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1018, Short.MAX_VALUE)
        );
        item_panelLayout.setVerticalGroup(
            item_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 556, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Item", item_panel);
        item_panel.setLayout(new BorderLayout());

        supplier_panel.setPreferredSize(new java.awt.Dimension(1024, 650));

        javax.swing.GroupLayout supplier_panelLayout = new javax.swing.GroupLayout(supplier_panel);
        supplier_panel.setLayout(supplier_panelLayout);
        supplier_panelLayout.setHorizontalGroup(
            supplier_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1018, Short.MAX_VALUE)
        );
        supplier_panelLayout.setVerticalGroup(
            supplier_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 556, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Supplier", supplier_panel);
        supplier_panel.setLayout(new BorderLayout());

        stock_panel.setPreferredSize(new java.awt.Dimension(1024, 650));

        javax.swing.GroupLayout stock_panelLayout = new javax.swing.GroupLayout(stock_panel);
        stock_panel.setLayout(stock_panelLayout);
        stock_panelLayout.setHorizontalGroup(
            stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1018, Short.MAX_VALUE)
        );
        stock_panelLayout.setVerticalGroup(
            stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 556, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Stock", stock_panel);
        stock_panel.setLayout(new BorderLayout());

        logout.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                logoutComponentShown(evt);
            }
        });

        javax.swing.GroupLayout logoutLayout = new javax.swing.GroupLayout(logout);
        logout.setLayout(logoutLayout);
        logoutLayout.setHorizontalGroup(
            logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1018, Short.MAX_VALUE)
        );
        logoutLayout.setVerticalGroup(
            logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 556, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Log Out", logout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
    }//GEN-LAST:event_jTabbedPane1MouseClicked

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
            java.util.logging.Logger.getLogger(InventoryManagerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InventoryManagerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InventoryManagerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InventoryManagerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InventoryManager inventory = new InventoryManager();
                new InventoryManagerMain(inventory).setVisible(true);
            }
        });
    }
    
    private void refreshPage() {
        // create an instance for each panel
        itemPanel = new ItemPanel();
        jTabbedPane1.setComponentAt(jTabbedPane1.indexOfTab("Item"), itemPanel);

        supplierPanel = new SupplierPanel(editPermission.edit);
        jTabbedPane1.setComponentAt(jTabbedPane1.indexOfTab("Supplier"), supplierPanel);
//        supplier_panel.add(supplierPanel, BorderLayout.CENTER);

        
        stockPanel = new StockPanel();
        jTabbedPane1.setComponentAt(jTabbedPane1.indexOfTab("Stock"), stockPanel);
//        stock_panel.add(stockPanel, BorderLayout.CENTER);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel item_panel;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel logout;
    private javax.swing.JPanel profile;
    private javax.swing.JPanel stock_panel;
    private javax.swing.JPanel supplier_panel;
    // End of variables declaration//GEN-END:variables
}
