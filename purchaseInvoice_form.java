/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package asm;

import asm.utilities.editPermission;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author brenna
 */
public class purchaseInvoice_form extends javax.swing.JPanel {
    private editPermission permission;
    private purchaseInvoice selected_pi;
    private String creator;
    javax.swing.JTable pi_table;
    
    
    public purchaseInvoice_form(JTable pi_table,editPermission permission,String creator){
         this.pi_table=pi_table;
         this.permission=permission;
         this.creator=creator;
         initComponents();
         settingPermission();
         BTN_update.setVisible(false);
         BTN_clear.setVisible(false);
         BTN_delete.setVisible(false);
         clearPanel();
    }
    
    private void settingPermission(){
        switch(permission){
            case editPermission.approve -> { //for inventory
                DATE_expected_delivery.setEnabled(false);
                DATE_actual_delivery.setEnabled(true);
                CB_pi_status.setEnabled(false);
                CB_delivery_status.setEnabled(true);
            }
            case editPermission.edit -> { //for finance
                DATE_expected_delivery.setEnabled(true);
                DATE_actual_delivery.setEnabled(false);
                CB_pi_status.setEnabled(true);
                CB_delivery_status.setEnabled(false);
            }
        }
    }
    
    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BTN_clear = new javax.swing.JButton();
        BTN_delete = new javax.swing.JButton();
        BTN_update = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        DATE_actual_delivery = new com.toedter.calendar.JDateChooser();
        DATE_created = new com.toedter.calendar.JDateChooser();
        CB_pi_status = new javax.swing.JComboBox<>();
        CB_delivery_status = new javax.swing.JComboBox<>();
        TF_pi_id = new javax.swing.JTextField();
        TF_po_id = new javax.swing.JTextField();
        TF_sp_id = new javax.swing.JTextField();
        TF_created = new javax.swing.JTextField();
        DATE_expected_delivery = new com.toedter.calendar.JDateChooser();

        setPreferredSize(new java.awt.Dimension(400, 500));

        BTN_clear.setText("Clear");
        BTN_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_clearActionPerformed(evt);
            }
        });

        BTN_delete.setText("Delete");
        BTN_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_deleteActionPerformed(evt);
            }
        });

        BTN_update.setText("Update");
        BTN_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_updateActionPerformed(evt);
            }
        });

        jLabel1.setText("Purchase Invoice ID:");

        jLabel2.setText("Purchase Order ID:");

        jLabel3.setText("Supplier ID:");

        jLabel4.setText("Expected Delivery Date:");

        jLabel5.setText("Actual Delivery Date:");

        jLabel6.setText("Created By:");

        jLabel7.setText("Created Date:");

        jLabel8.setText("Delivery Status:");

        jLabel9.setText("Payment Status:");

        CB_pi_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOT_PAID", "PAID" }));

        CB_delivery_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDING", "SHIPPED", "DELIVERED" }));

        TF_pi_id.setEditable(false);
        TF_pi_id.setFocusable(false);

        TF_po_id.setEditable(false);
        TF_po_id.setFocusable(false);

        TF_sp_id.setEditable(false);
        TF_sp_id.setFocusable(false);

        TF_created.setEditable(false);
        TF_created.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TF_createdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CB_pi_status, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CB_delivery_status, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DATE_created, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(BTN_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BTN_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BTN_update, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(TF_pi_id, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(TF_po_id, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(TF_sp_id, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(DATE_expected_delivery, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(DATE_actual_delivery, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TF_created, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_clear)
                    .addComponent(BTN_delete)
                    .addComponent(BTN_update))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(TF_pi_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(TF_po_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))
                                        .addGap(35, 35, 35))
                                    .addComponent(TF_sp_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(DATE_expected_delivery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DATE_actual_delivery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TF_created, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(DATE_created, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CB_pi_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CB_delivery_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_clearActionPerformed
    if (checkChanges()){
            int input = JOptionPane.showOptionDialog(null, "Are you sure you want to clear the form? \nChanges you made will not be saved.", "Unsaved Changes", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

            if(input == JOptionPane.OK_OPTION)
            {
                clearPanel();
                pi_table.clearSelection();
                BTN_clear.setVisible(false);
            }
        } else {
            clearPanel();
            pi_table.clearSelection();
            BTN_clear.setVisible(false);
        }
    }//GEN-LAST:event_BTN_clearActionPerformed

    private void BTN_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_deleteActionPerformed
        Map<String, purchaseInvoice> new_dict = purchaseInvoice.getAll_pi();
        new_dict.remove(selected_pi.getPi_id());
        purchaseInvoice.setAll_pi(new_dict);
        try {
            purchaseInvoice.rewriteFile();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), 
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
        }


        clearPanel();
        pi_table.setModel(purchaseInvoice.populateTable(permission, creator));
    }//GEN-LAST:event_BTN_deleteActionPerformed
//expected_date, actual_date, created_date
    private void BTN_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_updateActionPerformed
       if((checkChanges())){
         GregorianCalendar expected_date = new GregorianCalendar();
            if (DATE_expected_delivery.getDate() == null){
                expected_date = selected_pi.getExpected_date();
            } else {
                expected_date.setTime(DATE_expected_delivery.getDate());
            }
            
            GregorianCalendar actual_date = new GregorianCalendar();
            if (DATE_actual_delivery.getDate() == null){
                actual_date = selected_pi.getActual_date();
            } else {
                actual_date.setTime(DATE_actual_delivery.getDate());
            }  
            
            GregorianCalendar created_date = new GregorianCalendar();
            if (DATE_created.getDate() == null){
                created_date = selected_pi.getActual_date();
            } else {
                created_date.setTime(DATE_created.getDate());
            }
            
            try {
                selected_pi.editInvoice((String)TF_sp_id.getText(),expected_date,actual_date,(String)TF_created.getText(),created_date,(String)CB_pi_status.getSelectedItem(),(String)CB_delivery_status.getSelectedItem());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), 
                                       "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            clearPanel();
            pi_table.setModel(purchaseInvoice.populateTable(permission, creator));
       }
    }//GEN-LAST:event_BTN_updateActionPerformed

    private void TF_createdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TF_createdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TF_createdActionPerformed

    public void populateForm(String pi_id){
        clearPanel();
        selected_pi=purchaseInvoice.getAll_pi().get(pi_id);
        System.out.println(selected_pi);
        TF_pi_id.setText(selected_pi.getPi_id());
        TF_po_id.setText(selected_pi.getPo_id());
        TF_sp_id.setText(selected_pi.getSupplier_id());
        DATE_expected_delivery.setDate(selected_pi.getExpected_date().getTime());
        
        if(selected_pi.getActual_date()!=null){
            DATE_actual_delivery.setDate(selected_pi.getActual_date().getTime());
        }
        
        TF_created.setText(selected_pi.getCreated_by());
        DATE_created.setDate(selected_pi.getCreated_date().getTime());
        CB_pi_status.setSelectedItem(selected_pi.getPi_status());
        CB_delivery_status.setSelectedItem(selected_pi.getDelivery_status());
        
        if (permission == editPermission.edit){
            if ("PAID".equals(selected_pi.getPi_status())){
                DATE_expected_delivery.setEnabled(false);
                DATE_actual_delivery.setEnabled(true);
                CB_pi_status.setEnabled(false);
                CB_delivery_status.setEnabled(true);
                BTN_update.setVisible(false);
                BTN_delete.setVisible(false);
            } else {
                DATE_expected_delivery.setEnabled(true);
                DATE_actual_delivery.setEnabled(true);
                CB_pi_status.setEnabled(true);
                CB_delivery_status.setEnabled(true);
                BTN_update.setVisible(true);
                BTN_delete.setVisible(true);
            }
        } else {
            if ("DELIVERED".equals(selected_pi.getDelivery_status())){
                DATE_expected_delivery.setEnabled(false);
                DATE_actual_delivery.setEnabled(false);
                CB_pi_status.setEnabled(false);
                CB_delivery_status.setEnabled(false);
                BTN_update.setVisible(false);
                BTN_delete.setVisible(false);
            } else {
                DATE_expected_delivery.setEnabled(false);
                DATE_actual_delivery.setEnabled(true);
                CB_pi_status.setEnabled(false);
                CB_delivery_status.setEnabled(true);
                BTN_update.setVisible(true);
                BTN_delete.setVisible(false);
            }
        }
        
        BTN_clear.setVisible(true);
                
    }
    
    private boolean checkChanges(){
        boolean expected_date;
        if(selected_pi.getExpected_date() == null){
            expected_date = DATE_expected_delivery.getDate() != null;
        } else {
            expected_date = !selected_pi.getExpected_date().getTime().equals(DATE_expected_delivery.getDate());
        }

        boolean created_date;
        if(selected_pi.getCreated_date() == null){
            created_date = DATE_created.getDate() != null;
        } else {
            created_date = !selected_pi.getCreated_date().getTime().equals(DATE_created.getDate());
        }
        
        boolean actual_date;
        if(selected_pi.getActual_date() == null){
            actual_date = DATE_actual_delivery.getDate() != null;
        } else {
            actual_date = !selected_pi.getActual_date().getTime().equals(DATE_actual_delivery.getDate());
        }
        
        return  expected_date || actual_date ||
                !TF_created.getText().equals(selected_pi.getCreated_by()) || created_date ||
                !CB_pi_status.getSelectedItem().equals(selected_pi.getPi_status())||
                !CB_delivery_status.getSelectedItem().equals(selected_pi.getDelivery_status());
    }

    
    private void clearPanel(){
        TF_pi_id.setText("");
        TF_po_id.setText("");
        TF_sp_id.setText("");
        DATE_expected_delivery.setDate(null);
        DATE_actual_delivery.setDate(null);
        TF_created.setText("");
        DATE_created.setDate(null);
        CB_pi_status.setSelectedIndex(-1);
        CB_delivery_status.setSelectedIndex(-1);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_clear;
    private javax.swing.JButton BTN_delete;
    private javax.swing.JButton BTN_update;
    private javax.swing.JComboBox<String> CB_delivery_status;
    private javax.swing.JComboBox<String> CB_pi_status;
    private com.toedter.calendar.JDateChooser DATE_actual_delivery;
    private com.toedter.calendar.JDateChooser DATE_created;
    private com.toedter.calendar.JDateChooser DATE_expected_delivery;
    private javax.swing.JTextField TF_created;
    private javax.swing.JTextField TF_pi_id;
    private javax.swing.JTextField TF_po_id;
    private javax.swing.JTextField TF_sp_id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}