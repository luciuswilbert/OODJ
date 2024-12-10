/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package asm;

import asm.utilities.editPermission;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

/**
 *
 * @author brenna
 */
public class purchaseInvoice_table_and_form extends javax.swing.JPanel {
    private editPermission permission;
    private String creator;
    private purchaseInvoice selected_pi;
    private TableRowSorter<DefaultTableModel> sorter;
    private RowFilter<DefaultTableModel, Object> filter;
    private List<RowFilter<Object,Object>> filters;
    private List<String> month_list = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
    
    public purchaseInvoice_table_and_form(editPermission permission,String creator) {
        try {
            purchaseInvoice.Read();
            this.permission=permission;
            this.creator=creator;
            initComponents();
            sorter = new TableRowSorter<>((DefaultTableModel) pi_table.getModel());
            filter = null;
            filters = new ArrayList<>();
        } catch (ParseException | IOException ex){
            System.out.println(ex.getMessage());
        }
        if (permission == editPermission.approve){
            BTN_arrived.setVisible(true);
            BTN_pay.setVisible(false);
            CH_stat.setVisible(false);
            CB_stat_fil.setVisible(false);
            CH_deliver.setVisible(true);
            CB_deliver_fil.setVisible(true);
        } else {
            BTN_arrived.setVisible(false);
            BTN_pay.setVisible(true);
            CH_deliver.setVisible(false);
            CB_deliver_fil.setVisible(false);
        }
    }
    
    private asm.purchaseInvoice_form purchaseInvoice_form;


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pi_scroll_panel = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        pi_table = new javax.swing.JTable();
        BTN_pay = new javax.swing.JButton();
        BTN_arrived = new javax.swing.JButton();
        CH_stat = new javax.swing.JCheckBox();
        MC_mth_fil = new com.toedter.calendar.JMonthChooser();
        CH_date = new javax.swing.JCheckBox();
        YC_yr_fil = new com.toedter.calendar.JYearChooser();
        CH_id = new javax.swing.JCheckBox();
        TF_id_fil = new javax.swing.JTextField();
        BT_fil = new javax.swing.JButton();
        CB_id_fil = new javax.swing.JComboBox<>();
        CB_stat_fil = new javax.swing.JComboBox<>();
        CB_date_fil = new javax.swing.JComboBox<>();
        CH_deliver = new javax.swing.JCheckBox();
        CB_deliver_fil = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(880, 600));

        pi_table.setModel(purchaseInvoice.populateTable(editPermission.edit, "FM0001"));
        pi_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pi_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(pi_table);

        jScrollPane2.setViewportView(jScrollPane1);

        BTN_pay.setText("Pay");
        BTN_pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_payActionPerformed(evt);
            }
        });

        BTN_arrived.setText("Arrived");
        BTN_arrived.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_arrivedActionPerformed(evt);
            }
        });

        CH_stat.setText("Filter by Status:");
        CH_stat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CH_statActionPerformed(evt);
            }
        });

        CH_date.setText("Filter by Date:");
        CH_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CH_dateActionPerformed(evt);
            }
        });

        CH_id.setText("Filter by ID:");
        CH_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CH_idActionPerformed(evt);
            }
        });

        BT_fil.setBackground(new java.awt.Color(255, 51, 51));
        BT_fil.setForeground(new java.awt.Color(255, 255, 255));
        BT_fil.setText("Clear Filter");
        BT_fil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_filActionPerformed(evt);
            }
        });

        CB_id_fil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Purchase Invoice", "Purchase Order", "Supplier", "Created By" }));
        CB_id_fil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_id_filActionPerformed(evt);
            }
        });

        CB_stat_fil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PAID", "NOT_PAID" }));

        CB_date_fil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Created", "Expected Delivery", "Actual Delivery" }));

        CH_deliver.setText("Filter by Delivery:");
        CH_deliver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CH_deliverActionPerformed(evt);
            }
        });

        CB_deliver_fil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDING", "SHIPPED", "DELIVERED" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CH_deliver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CB_deliver_fil, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(CH_id, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(CB_id_fil, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(TF_id_fil, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(CH_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CH_stat))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(CB_stat_fil, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BT_fil, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(CB_date_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(MC_mth_fil, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(YC_yr_fil, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTN_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181)
                        .addComponent(BTN_arrived, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pi_scroll_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CH_deliver)
                            .addComponent(CB_deliver_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TF_id_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CH_id)
                            .addComponent(CB_id_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(YC_yr_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MC_mth_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(CH_date)
                                .addComponent(CB_date_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CB_stat_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CH_stat)
                            .addComponent(BT_fil))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pi_scroll_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_arrived, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        purchaseInvoice_form = new asm.purchaseInvoice_form(pi_table, editPermission.edit, "FM0001");
        pi_scroll_panel.setViewportView(purchaseInvoice_form);
        addChangeListener(TF_id_fil, e -> update_id_filter(true));
        CB_id_fil.addItemListener(
            new ItemListener()
            {
                public void itemStateChanged(ItemEvent e)
                {
                    update_id_filter(true);
                }
            } );
            CB_stat_fil.addItemListener(
                new ItemListener()
                {
                    public void itemStateChanged(ItemEvent e)
                    {
                        update_stat_filter(true);
                    }
                } );
                CB_date_fil.addItemListener(
                    new ItemListener()
                    {
                        public void itemStateChanged(ItemEvent e)
                        {
                            update_date_filter(true);
                        }
                    } );
                    CB_stat_fil.addItemListener(
                        new ItemListener()
                        {
                            public void itemStateChanged(ItemEvent e)
                            {
                                update_stat_filter(true);
                            }
                        } );
                    }// </editor-fold>//GEN-END:initComponents

    private void pi_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pi_tableMouseClicked
        int row=pi_table.getSelectedRow();
        String pi_id=pi_table.getValueAt(row, pi_table.getColumn("Purchase Invoice ID").getModelIndex()).toString();
        purchaseInvoice_form.populateForm(pi_id);
    }//GEN-LAST:event_pi_tableMouseClicked

    private void BTN_payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_payActionPerformed
        try {
            int row = pi_table.getSelectedRow();
            String pi_id=pi_table.getValueAt(row, pi_table.getColumn("Purchase Invoice ID").getModelIndex()).toString();
            selected_pi = purchaseInvoice.getAll_pi().get(pi_id);
            selected_pi.setPi_status("PAID");
            selected_pi.editInvoice(selected_pi.getSupplier_id(),selected_pi.getExpected_date(),null,selected_pi.getCreated_by(),selected_pi.getCreated_date(),selected_pi.getPi_status(),selected_pi.getDelivery_status());
            pi_table.setModel(purchaseInvoice.populateTable(permission, creator));
        } catch (IOException ex) {
            Logger.getLogger(purchaseInvoice_table_and_form.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_BTN_payActionPerformed

    private void update_id_filter(boolean clear){
        if (clear){
            filters.clear();
        }
        
        if (CH_id.isSelected()){
            try {
                String id_fil = TF_id_fil.getText().trim();
                
                switch(CB_id_fil.getSelectedItem().toString()){
                    case "All" -> filters.add(RowFilter.regexFilter("(?i)" + id_fil, 0, 1, 2, 5));
                    case "Purchase Invoice" -> filters.add(RowFilter.regexFilter("(?i)" + id_fil, 0));
                    case "Purchase Order" -> filters.add(RowFilter.regexFilter("(?i)" + id_fil, 1));
                    case "Supplier" -> filters.add(RowFilter.regexFilter("(?i)" + id_fil, 2));
                    default -> filters.add(RowFilter.regexFilter("(?i)" + id_fil, 5));
                }
                
            } catch (java.util.regex.PatternSyntaxException e) {
                // Code To popup an INFORMATION_MESSAGE Dialog.
                JOptionPane.showMessageDialog(this, "No relevant record is found", 
                    "Table Filtering", 
                    JOptionPane.INFORMATION_MESSAGE);
            }   
            if (clear){
            update_date_filter(false);
            update_stat_filter(false);
            filter = RowFilter.andFilter(filters);
            sorter.setRowFilter(filter);
            pi_table.setRowSorter(sorter);
        }  
    }
    }
    
    private void update_date_filter(boolean clear){
        if (clear){
            filters.clear();
        }
        
        if (CH_date.isSelected()){
            try {
                String month = month_list.get(MC_mth_fil.getMonth());
                int year = YC_yr_fil.getYear();
                
                switch(CB_date_fil.getSelectedItem().toString()){
                    case "All" -> filters.add(RowFilter.regexFilter("(?i)" + month + "-" + year, 3, 4, 6));
                    case "Expected Delivery" -> filters.add(RowFilter.regexFilter("(?i)" + month + "-" + year, 3));
                    case "Actual Delivery" -> filters.add(RowFilter.regexFilter("(?i)" + month + "-" + year, 4));
                    default -> filters.add(RowFilter.regexFilter("(?i)" + month + "-" + year, 6));
                }
            
            } catch (java.util.regex.PatternSyntaxException e) {
                // Code To popup an INFORMATION_MESSAGE Dialog.
                JOptionPane.showMessageDialog(this, "No relevant record is found", 
                    "Table Filtering", 
                    JOptionPane.INFORMATION_MESSAGE);
            }       
        }
        
        if (clear){
            update_id_filter(false);
            update_stat_filter(false);
            update_deliver_filter(false);
            filter = RowFilter.andFilter(filters);
            sorter.setRowFilter(filter);
            pi_table.setRowSorter(sorter);
        }    
    }
    
    private void update_stat_filter(boolean clear){
        if (clear){
            filters.clear();
        }
        
        if (CH_stat.isSelected()){
            try {
                String stat_fil = CB_stat_fil.getSelectedItem().toString();
                filters.add(RowFilter.regexFilter("(?i)^" + stat_fil + "$", 7));
            } catch (java.util.regex.PatternSyntaxException e) {
                // Code To popup an INFORMATION_MESSAGE Dialog.
                JOptionPane.showMessageDialog(this, "No relevant record is found", 
                    "Table Filtering", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        if (clear){
            update_id_filter(false);
            update_date_filter(false);
            update_deliver_filter(false);
            filter = RowFilter.andFilter(filters);
            sorter.setRowFilter(filter);
            pi_table.setRowSorter(sorter);
        }  
    }
    
    private void update_deliver_filter(boolean clear){
        if (clear){
            filters.clear();
        }
        
        if (CH_deliver.isSelected()){
            try {
                String deliver_fil = CB_deliver_fil.getSelectedItem().toString();
                filters.add(RowFilter.regexFilter("(?i)" + deliver_fil, 8));
            } catch (java.util.regex.PatternSyntaxException e) {
                // Code To popup an INFORMATION_MESSAGE Dialog.
                JOptionPane.showMessageDialog(this, "No relevant record is found", 
                    "Table Filtering", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        if (clear){
            update_id_filter(false);
            update_date_filter(false);
            update_stat_filter(false);
            filter = RowFilter.andFilter(filters);
            sorter.setRowFilter(filter);
            pi_table.setRowSorter(sorter);
        }  
    }
    private void CH_statActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH_statActionPerformed
        if (CH_stat.isSelected()){
            update_stat_filter(false);
            filter = RowFilter.andFilter(filters);
            sorter.setRowFilter(filter);
            pi_table.setRowSorter(sorter);
        } else {
            update_id_filter(true);
        }
    }//GEN-LAST:event_CH_statActionPerformed

    private void CH_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH_dateActionPerformed
        if (CH_date.isSelected()){
            update_date_filter(false);
            filter = RowFilter.andFilter(filters);
            sorter.setRowFilter(filter);
            pi_table.setRowSorter(sorter);
        } else {
            update_stat_filter(true);
        }
    }//GEN-LAST:event_CH_dateActionPerformed

    private void CH_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH_idActionPerformed
        if (CH_id.isSelected()){
            update_id_filter(false);
            filter = RowFilter.andFilter(filters);
            sorter.setRowFilter(filter);
            pi_table.setRowSorter(sorter);
        } else {
            update_date_filter(true);
        }
    }//GEN-LAST:event_CH_idActionPerformed

    private void BT_filActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_filActionPerformed
        CH_id.setSelected(false);
        CH_date.setSelected(false);
        CH_stat.setSelected(false);
        CH_deliver.setSelected(false);

        // If checkbox is not selected, clear the filter
        sorter.setRowFilter(null); // Remove all filters
        pi_table.setRowSorter(sorter); // Update the table to reflect no filtering
    }//GEN-LAST:event_BT_filActionPerformed

    private void CB_id_filActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_id_filActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CB_id_filActionPerformed

    private void BTN_arrivedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_arrivedActionPerformed
        try {
            int row = pi_table.getSelectedRow();
            String pi_id=pi_table.getValueAt(row, pi_table.getColumn("Purchase Invoice ID").getModelIndex()).toString();
            selected_pi = purchaseInvoice.getAll_pi().get(pi_id);
            selected_pi.setDelivery_status("DELIVERED");
            selected_pi.setActual_date(new GregorianCalendar());
            
            selected_pi.editInvoice(selected_pi.getSupplier_id(),selected_pi.getExpected_date(),selected_pi.getActual_date(),selected_pi.getCreated_by(),selected_pi.getCreated_date(),selected_pi.getPi_status(),selected_pi.getDelivery_status());
            pi_table.setModel(purchaseInvoice.populateTable(permission, creator));
        } catch (IOException ex) {
            Logger.getLogger(purchaseInvoice_table_and_form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTN_arrivedActionPerformed

    private void CH_deliverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH_deliverActionPerformed

        if (CH_deliver.isSelected()){
            update_deliver_filter(false);
            filter = RowFilter.andFilter(filters);
            sorter.setRowFilter(filter);
            pi_table.setRowSorter(sorter);
        } else {
            update_deliver_filter(true);
        }
    }//GEN-LAST:event_CH_deliverActionPerformed


    public static void addChangeListener(JTextComponent text, ChangeListener changeListener) {
        Objects.requireNonNull(text);
        Objects.requireNonNull(changeListener);
        DocumentListener dl = new DocumentListener() {
            private int lastChange = 0, lastNotifiedChange = 0;

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                lastChange++;
                SwingUtilities.invokeLater(() -> {
                    if (lastNotifiedChange != lastChange) {
                        lastNotifiedChange = lastChange;
                        changeListener.stateChanged(new ChangeEvent(text));
                    }
                });
            }
        };
        text.addPropertyChangeListener("document", (PropertyChangeEvent e) -> {
            Document d1 = (Document)e.getOldValue();
            Document d2 = (Document)e.getNewValue();
            if (d1 != null) d1.removeDocumentListener(dl);
            if (d2 != null) d2.addDocumentListener(dl);
            dl.changedUpdate(null);
        });
        Document d = text.getDocument();
        if (d != null) d.addDocumentListener(dl);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_arrived;
    private javax.swing.JButton BTN_pay;
    private javax.swing.JButton BT_fil;
    private javax.swing.JComboBox<String> CB_date_fil;
    private javax.swing.JComboBox<String> CB_deliver_fil;
    private javax.swing.JComboBox<String> CB_id_fil;
    private javax.swing.JComboBox<String> CB_stat_fil;
    private javax.swing.JCheckBox CH_date;
    private javax.swing.JCheckBox CH_deliver;
    private javax.swing.JCheckBox CH_id;
    private javax.swing.JCheckBox CH_stat;
    private com.toedter.calendar.JMonthChooser MC_mth_fil;
    private javax.swing.JTextField TF_id_fil;
    private com.toedter.calendar.JYearChooser YC_yr_fil;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane pi_scroll_panel;
    private javax.swing.JTable pi_table;
    // End of variables declaration//GEN-END:variables
}