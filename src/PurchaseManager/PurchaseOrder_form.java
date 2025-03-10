package PurchaseManager;

import Utility.editPermission;
import java.io.IOException;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class PurchaseOrder_form extends javax.swing.JPanel {
    private editPermission permission;
    private purchaseOrder selected_po;
    private String creator;
    javax.swing.JTable po_table;
    
    public PurchaseOrder_form(JTable po_table, editPermission permission, String creator) {
        this.po_table = po_table;
        this.permission = permission;
        this.creator = creator;
        initComponents();
        BT_save.setVisible(false);
        BT_del.setVisible(false);
        BT_clear.setVisible(false);
        settingPermission();
        clearPanel();
    }
    
    private void settingPermission(){
        switch (permission){
            case editPermission.readOnly -> {
                setReadOnly();
            }
                
            case editPermission.edit -> {
                CB_sup_id.setEnabled(true);
                CB_order_status.setEnabled(false);
                DC_order_date.setEnabled(true);
                DC_approval_date.setEnabled(false);
            }
                
            default -> {
                CB_sup_id.setEnabled(true);
                CB_order_status.setEnabled(true);
                DC_order_date.setEnabled(true);
                DC_approval_date.setEnabled(true);
            }
                   
        }           
    }
    
    private void setReadOnly(){
        CB_sup_id.setEnabled(false);
        CB_order_status.setEnabled(false);
        DC_order_date.setEnabled(false);
        DC_approval_date.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_clear = new javax.swing.JButton();
        btn_del = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_clear1 = new javax.swing.JButton();
        btn_del1 = new javax.swing.JButton();
        btn_save1 = new javax.swing.JButton();
        LB_po_id = new javax.swing.JLabel();
        TF_po_id = new javax.swing.JTextField();
        LB_pr_id = new javax.swing.JLabel();
        TF_pr_id = new javax.swing.JTextField();
        LB_sup_id = new javax.swing.JLabel();
        LB_order_date = new javax.swing.JLabel();
        LB_po_status = new javax.swing.JLabel();
        LB_created = new javax.swing.JLabel();
        LB_approved = new javax.swing.JLabel();
        LB_approval_date = new javax.swing.JLabel();
        CB_order_status = new javax.swing.JComboBox<>();
        DC_order_date = new com.toedter.calendar.JDateChooser();
        DC_approval_date = new com.toedter.calendar.JDateChooser();
        BT_clear = new javax.swing.JButton();
        BT_del = new javax.swing.JButton();
        BT_save = new javax.swing.JButton();
        LB_approval_date1 = new javax.swing.JLabel();
        String[] sup_options = {"SUP0001", "SUP0002", "SUP0003"}; // Replace with Lucius's code
        CB_sup_id = new javax.swing.JComboBox<>();
        TF_approved = new javax.swing.JTextField();
        TF_created = new javax.swing.JTextField();

        btn_clear.setText("Clear");

        btn_del.setText("Delete");

        btn_save.setText("Save");

        btn_clear1.setText("Clear");

        btn_del1.setText("Delete");

        btn_save1.setText("Save");

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(400, 500));

        LB_po_id.setText("Purchase Order ID: ");

        TF_po_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TF_po_idActionPerformed(evt);
            }
        });

        LB_pr_id.setText("Purchase Requisition ID: ");

        TF_pr_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TF_pr_idActionPerformed(evt);
            }
        });

        LB_sup_id.setText("Supplier ID: ");

        LB_order_date.setText("Order Date:");

        LB_po_status.setText("Purchase Order Status: ");

        LB_created.setText("Created By");

        LB_approved.setText("Approved/Rejected By:");

        LB_approval_date.setText("Approval Date:");

        //String[] status_options = Arrays.copyOf(purchaseOrder.getPo_stat_enum(), purchaseOrder.getPo_stat_enum().length +1);
        //status_options[status_options.length -1] = "";
        CB_order_status.setModel(new javax.swing.DefaultComboBoxModel<>(purchaseOrder.getPo_stat_enum()));
        CB_order_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_order_statusActionPerformed(evt);
            }
        });

        DC_order_date.setDateFormatString("dd-MMM-yyyy");

        DC_approval_date.setDateFormatString("dd-MMM-yyyy");

        BT_clear.setText("Clear");
        BT_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_clearActionPerformed(evt);
            }
        });

        BT_del.setText("Delete");
        BT_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_delActionPerformed(evt);
            }
        });

        BT_save.setText("Save");
        BT_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_saveActionPerformed(evt);
            }
        });

        CB_sup_id.setModel(new javax.swing.DefaultComboBoxModel<>(sup_options));
        CB_sup_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_sup_idActionPerformed(evt);
            }
        });

        TF_approved.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TF_approvedActionPerformed(evt);
            }
        });

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
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BT_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BT_del, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BT_save, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LB_po_id)
                            .addComponent(LB_po_status)
                            .addComponent(LB_pr_id)
                            .addComponent(LB_created)
                            .addComponent(LB_sup_id)
                            .addComponent(LB_approved)
                            .addComponent(LB_order_date)
                            .addComponent(LB_approval_date))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TF_po_id, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DC_order_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TF_pr_id, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DC_approval_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_order_status, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_sup_id, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TF_approved)
                            .addComponent(TF_created))))
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(LB_approval_date1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BT_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BT_save, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(BT_del, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LB_po_id)
                    .addComponent(TF_po_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LB_pr_id)
                    .addComponent(TF_pr_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LB_sup_id)
                    .addComponent(CB_sup_id, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LB_order_date)
                    .addComponent(DC_order_date, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LB_po_status)
                    .addComponent(CB_order_status, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LB_created)
                    .addComponent(TF_created, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LB_approved)
                    .addComponent(TF_approved, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LB_approval_date)
                    .addComponent(DC_approval_date, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(LB_approval_date1)
                .addContainerGap())
        );

        TF_po_id.setEditable(false);
        TF_pr_id.setEditable(false);
        DC_order_date.setDateFormatString("dd-MMM-yyyy");
        DC_approval_date.setDateFormatString("dd-MMM-yyyy");
        TF_approved.setEditable(false);
        TF_created.setEditable(false);
    }// </editor-fold>//GEN-END:initComponents

    private void TF_po_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TF_po_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TF_po_idActionPerformed

    private void TF_pr_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TF_pr_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TF_pr_idActionPerformed

    private void CB_order_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_order_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CB_order_statusActionPerformed

    private void BT_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_clearActionPerformed
        if (checkChanges()){
            int input = JOptionPane.showOptionDialog(null, "Are you sure you want to clear the form? \nChanges you made will not be saved.", "Unsaved Changes", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

            if(input == JOptionPane.OK_OPTION)
            {
                clearPanel();
                po_table.clearSelection();
                BT_clear.setVisible(false);
            }
        } else {
            clearPanel();
            po_table.clearSelection();
            BT_clear.setVisible(false);
        }
        
    }//GEN-LAST:event_BT_clearActionPerformed

    private void BT_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_delActionPerformed
        Map<String, purchaseOrder> new_dict = purchaseOrder.getAll_po();
        new_dict.remove(selected_po.getPo_id());
        purchaseOrder.setAll_po(new_dict);
        try {
            purchaseOrder.rewriteFile();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), 
                                   "ERROR", JOptionPane.ERROR_MESSAGE);
        }


        clearPanel();
        po_table.setModel(purchaseOrder.populateTable(permission, creator));
    }//GEN-LAST:event_BT_delActionPerformed

    private void BT_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_saveActionPerformed
        if (checkChanges()){
            GregorianCalendar order_dt = new GregorianCalendar();
            if (DC_order_date.getDate() == null){
                order_dt = selected_po.getOrder_date();
            } else {
                order_dt.setTime(DC_order_date.getDate());
            }
            
            GregorianCalendar approval_dt = new GregorianCalendar();
            if (DC_approval_date.getDate() == null){
                approval_dt = selected_po.getApproval_date();
            } else {
                approval_dt.setTime(DC_approval_date.getDate());
            }          
        
            try {
                selected_po.editPO((String)CB_sup_id.getSelectedItem(),order_dt, (String)CB_order_status.getSelectedItem(), (String) TF_created.getText(), (String) TF_approved.getText(), approval_dt);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), 
                                       "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            po_table.setModel(purchaseOrder.populateTable(permission, creator));
        }
    }//GEN-LAST:event_BT_saveActionPerformed

    private void CB_sup_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_sup_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CB_sup_idActionPerformed

    private void TF_approvedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TF_approvedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TF_approvedActionPerformed

    private void TF_createdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TF_createdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TF_createdActionPerformed

    
    
    public void populateForm(String po_id){
        clearPanel();
        selected_po = purchaseOrder.getAll_po().get(po_id);
        System.out.println(selected_po);
        TF_po_id.setText(selected_po.getPo_id());
        TF_pr_id.setText(selected_po.getPr_id());
        CB_sup_id.setSelectedItem(selected_po.getSupplier_id());
        DC_order_date.setDate(selected_po.getOrder_date().getTime());
        
        CB_order_status.setSelectedItem(selected_po.getPo_status());
        
        TF_created.setText(selected_po.getCreated_by());
        
        if(selected_po.getApproved_by()!=null){
            TF_approved.setText(selected_po.getApproved_by());
        }
        
        if(selected_po.getApproval_date() != null){
            DC_approval_date.setDate(selected_po.getApproval_date().getTime());
        }
        
        if (permission != editPermission.readOnly){
            if ("PENDING".equals(selected_po.getPo_status())){
                BT_save.setVisible(true);
                BT_del.setVisible(true);
                settingPermission();
            } else {
                BT_save.setVisible(false);
                BT_del.setVisible(false);
                setReadOnly();
            }
            
        }
        
        BT_clear.setVisible(true);
    }
    
    private void clearPanel(){
        TF_po_id.setText("");
        TF_pr_id.setText("");
        CB_sup_id.setSelectedIndex(-1);
        TF_approved.setText("");
        CB_order_status.setSelectedIndex(-1);
        TF_created.setText("");
        DC_order_date.setDate(null);
        DC_approval_date.setDate(null);

        BT_del.setVisible(false);
        BT_save.setVisible(false);
    }
    
    private boolean checkChanges(){
        boolean order_date;
        if(selected_po.getOrder_date() == null){
            order_date = DC_order_date.getDate() != null;
        } else {
            order_date = !selected_po.getOrder_date().getTime().equals(DC_order_date.getDate());
        }

        boolean approval_date;
        if(selected_po.getApproval_date() == null){
            approval_date = DC_approval_date.getDate() != null;
        } else {
            approval_date = !selected_po.getApproval_date().getTime().equals(DC_approval_date.getDate());
        }
        
        boolean approved_by;
        if (selected_po.getApproved_by() == null){
            approved_by = !"".equals(TF_approved.getText());
        } else {
            approved_by = !selected_po.getApproved_by().equals(TF_approved.getText());
        }
        
        System.out.println(!CB_sup_id.getSelectedItem().equals(selected_po.getSupplier_id()));
        System.out.println(order_date);
        System.out.println(!CB_order_status.getSelectedItem().equals(selected_po.getPo_status()));
        System.out.println(approved_by);
        System.out.println(approval_date);
        return !CB_sup_id.getSelectedItem().equals(selected_po.getSupplier_id()) ||
                order_date ||
                !CB_order_status.getSelectedItem().equals(selected_po.getPo_status()) ||
                approved_by ||
                approval_date;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_clear;
    private javax.swing.JButton BT_del;
    private javax.swing.JButton BT_save;
    private javax.swing.JComboBox<String> CB_order_status;
    private javax.swing.JComboBox<String> CB_sup_id;
    private com.toedter.calendar.JDateChooser DC_approval_date;
    private com.toedter.calendar.JDateChooser DC_order_date;
    private javax.swing.JLabel LB_approval_date;
    private javax.swing.JLabel LB_approval_date1;
    private javax.swing.JLabel LB_approved;
    private javax.swing.JLabel LB_created;
    private javax.swing.JLabel LB_order_date;
    private javax.swing.JLabel LB_po_id;
    private javax.swing.JLabel LB_po_status;
    private javax.swing.JLabel LB_pr_id;
    private javax.swing.JLabel LB_sup_id;
    private javax.swing.JTextField TF_approved;
    private javax.swing.JTextField TF_created;
    private javax.swing.JTextField TF_po_id;
    private javax.swing.JTextField TF_pr_id;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_clear1;
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_del1;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_save1;
    // End of variables declaration//GEN-END:variables
}

