/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package PurchaseManager;

import Utility.duplicate_id;
import Utility.editPermission;
import Utility.txt;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
 * @author User
 */
public class PurchaseOrder_table_and_form extends javax.swing.JPanel {
    private editPermission permission;
    private String creator;
    private TableRowSorter<DefaultTableModel> sorter;
    private RowFilter<DefaultTableModel, Object> filter;
    private List<RowFilter<Object,Object>> filters;
    private List<String> month_list = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
    
    public PurchaseOrder_table_and_form(editPermission permission, String creator) {
        purchaseOrder.Read();
        this.permission = permission;
        this.creator = creator;
        initComponents();
        setApprovalButtons();
        sorter = new TableRowSorter<>((DefaultTableModel) po_table.getModel());
        filter = null;
        filters = new ArrayList<>();
    }
    
    public static ImageIcon resize(ImageIcon icon, int width, int height){
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) img.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(icon.getImage(), 0, 0, width, height, null);
        g2d.dispose();
        return new ImageIcon(img);
    }
    
    private void setApprovalButtons(){
        boolean showButton = permission == editPermission.approve;
        
        BT_approve.setVisible(showButton);
        BT_reject.setVisible(showButton);
    }

    // private asm.purchaseOrder.PurchaseOrder_panel purchaseOrder_form1;
    private PurchaseOrder_form purchaseOrder_form1;
    
    private void update_id_filter(boolean clear){
        if (clear){
            filters.clear();
        }
        
        if (CH_id.isSelected()){
            try {
                String id_fil = TF_id_fil.getText().trim();
                
                switch(CB_id_fil.getSelectedItem().toString()){
                    case "All" -> filters.add(RowFilter.regexFilter("(?i)" + id_fil, 0, 1, 2, 5, 6));
                    case "Purchase Order" -> filters.add(RowFilter.regexFilter("(?i)" + id_fil, 0));
                    case "Purchase Requisition" -> filters.add(RowFilter.regexFilter("(?i)" + id_fil, 1));
                    case "Supplier" -> filters.add(RowFilter.regexFilter("(?i)" + id_fil, 2));
                    case "Created By" -> filters.add(RowFilter.regexFilter("(?i)" + id_fil, 5));
                    default -> filters.add(RowFilter.regexFilter("(?i)" + id_fil, 6));
                }
                
            } catch (java.util.regex.PatternSyntaxException e) {
                // Code To popup an INFORMATION_MESSAGE Dialog.
                JOptionPane.showMessageDialog(this, "No relevant record is found", 
                    "Table Filtering", 
                    JOptionPane.INFORMATION_MESSAGE);
            }       
        }
        
        if (clear){
            update_date_filter(false);
            update_stat_filter(false);
            filter = RowFilter.andFilter(filters);
            sorter.setRowFilter(filter);
            po_table.setRowSorter(sorter);
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
                System.out.println(month + "-" + year);
                
                switch(CB_date_fil.getSelectedItem().toString()){
                    case "All" -> filters.add(RowFilter.regexFilter("(?i)" + month + "-" + year, 3, 7));
                    case "Order" -> filters.add(RowFilter.regexFilter("(?i)" + month + "-" + year, 3));
                    default -> filters.add(RowFilter.regexFilter("(?i)" + month + "-" + year, 7));
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
            filter = RowFilter.andFilter(filters);
            sorter.setRowFilter(filter);
            po_table.setRowSorter(sorter);
        }    
    }
    
    private void update_stat_filter(boolean clear){
        if (clear){
            filters.clear();
        }
        
        if (CH_stat.isSelected()){
            try {
                String stat_fil = CB_stat_fil.getSelectedItem().toString();
                System.out.println(stat_fil);
                filters.add(RowFilter.regexFilter("(?i)" + stat_fil, 4));
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
            filter = RowFilter.andFilter(filters);
            sorter.setRowFilter(filter);
            po_table.setRowSorter(sorter);
        }  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        scroll_po_panel = new javax.swing.JScrollPane();
        BT_fil = new javax.swing.JButton();
        CB_stat_fil = new javax.swing.JComboBox<>();
        CH_stat = new javax.swing.JCheckBox();
        CH_date = new javax.swing.JCheckBox();
        CH_id = new javax.swing.JCheckBox();
        TF_id_fil = new javax.swing.JTextField();
        YC_yr_fil = new com.toedter.calendar.JYearChooser();
        MC_mth_fil = new com.toedter.calendar.JMonthChooser();
        BT_reject = new java.awt.Button();
        BT_approve = new java.awt.Button();
        scroll_po_table = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        po_table = new javax.swing.JTable();
        CB_id_fil = new javax.swing.JComboBox<>();
        CB_date_fil = new javax.swing.JComboBox<>();
        BT_generate = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ITEM");
        jLabel1.setPreferredSize(new java.awt.Dimension(218, 64));

        setPreferredSize(new java.awt.Dimension(1024, 615));

        scroll_po_panel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll_po_panel.setVerifyInputWhenFocusTarget(false);

        BT_fil.setBackground(new java.awt.Color(255, 51, 51));
        BT_fil.setForeground(new java.awt.Color(255, 255, 255));
        BT_fil.setText("Clear Filter");
        BT_fil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_filActionPerformed(evt);
            }
        });

        CB_stat_fil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDING", "APPROVED", "REJECTED" }));

        CH_stat.setText("Filter by Status:");
        CH_stat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CH_statActionPerformed(evt);
            }
        });

        CH_date.setText("Filter by Date Range:");
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

        BT_reject.setLabel("Reject");
        BT_reject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_rejectActionPerformed(evt);
            }
        });

        BT_approve.setActionCommand("Approve");
        BT_approve.setLabel("Approve");
        BT_approve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_approveActionPerformed(evt);
            }
        });

        po_table.setModel(purchaseOrder.populateTable(permission, creator));
        po_table.setFillsViewportHeight(true);
        po_table.setMaximumSize(new java.awt.Dimension(1000, 250));
        po_table.setPreferredSize(new java.awt.Dimension(1000, 250));
        po_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        po_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        po_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                po_tableMouseClicked(evt);
            }
        });
        po_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF); // Disable automatic resizing to fit within the JScrollPane
        po_table.setPreferredScrollableViewportSize(new Dimension(1000, 250)); // Set preferred width larger than the visible area
        jScrollPane2.setViewportView(po_table);
        po_table.getAccessibleContext().setAccessibleDescription("");

        scroll_po_table.setViewportView(jScrollPane2);

        CB_id_fil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Purchase Order", "Purchase Requisition", "Supplier", "Created By", "Approved By" }));

        CB_date_fil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Order", "Approval" }));

        BT_generate.setBackground(new java.awt.Color(102, 102, 102));
        BT_generate.setForeground(new java.awt.Color(255, 255, 255));
        BT_generate.setText("Generate List");
        BT_generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_generateActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PURCHASE ORDER");
        jLabel2.setPreferredSize(new java.awt.Dimension(218, 64));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(CH_id, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(CB_id_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TF_id_fil))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(CH_stat)
                                            .addGap(18, 18, 18)
                                            .addComponent(CB_stat_fil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(CH_date, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(CB_date_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(MC_mth_fil, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(YC_yr_fil, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)))
                            .addGap(20, 20, 20)
                            .addComponent(BT_fil, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(scroll_po_table, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BT_generate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scroll_po_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BT_approve, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BT_reject, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(403, 403, 403))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TF_id_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CH_id)
                            .addComponent(CB_id_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(CH_date)
                                .addComponent(CB_date_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(MC_mth_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(YC_yr_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CB_stat_fil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CH_stat)
                            .addComponent(BT_fil))
                        .addGap(15, 15, 15)
                        .addComponent(scroll_po_table, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(scroll_po_panel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BT_approve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BT_reject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BT_generate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        purchaseOrder_form1 = new PurchaseOrder_form(po_table, permission, creator);
        scroll_po_panel.setViewportView(purchaseOrder_form1);
        CB_stat_fil.addItemListener(
            new ItemListener()
            {
                public void itemStateChanged(ItemEvent e)
                {
                    update_stat_filter(true);
                }
            } );
            addChangeListener(TF_id_fil, e -> update_id_filter(true));
            YC_yr_fil.addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent e) {
                        if ("year".equals(e.getPropertyName())) {
                            update_date_filter(true);
                        }
                    }
                });
                MC_mth_fil.addPropertyChangeListener(
                    new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent e) {
                            if ("month".equals(e.getPropertyName())) {
                                update_date_filter(true);
                            }
                        }
                    });
                    CB_id_fil.addItemListener(
                        new ItemListener()
                        {
                            public void itemStateChanged(ItemEvent e)
                            {
                                update_id_filter(true);
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
                        }// </editor-fold>//GEN-END:initComponents

    private void CH_statActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH_statActionPerformed
        if (CH_stat.isSelected()){
            update_stat_filter(false);
            filter = RowFilter.andFilter(filters);
            sorter.setRowFilter(filter);
            po_table.setRowSorter(sorter);
        } else {
            update_id_filter(true);
        }
    }//GEN-LAST:event_CH_statActionPerformed

    private void BT_approveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_approveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BT_approveActionPerformed

    private void po_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_po_tableMouseClicked
        int row = po_table.getSelectedRow();
        if (po_table.getRowCount() > 0){
            String po_id = po_table.getValueAt(row, po_table.getColumn("Purchase Order ID").getModelIndex()).toString();
            // JOptionPane.showMessageDialog(null, po_id); // get the value of a row and column.
            purchaseOrder_form1.populateForm(po_id);
        }
        
    }//GEN-LAST:event_po_tableMouseClicked

    private void CH_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH_idActionPerformed
        if (CH_id.isSelected()){
            update_id_filter(false);
            filter = RowFilter.andFilter(filters);
            sorter.setRowFilter(filter);
            po_table.setRowSorter(sorter);
        } else {
            update_date_filter(true);
        }
    }//GEN-LAST:event_CH_idActionPerformed

    private void BT_filActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_filActionPerformed
        CH_id.setSelected(false);
        CH_date.setSelected(false);
        CH_stat.setSelected(false);
        
        // If checkbox is not selected, clear the filter
        sorter.setRowFilter(null); // Remove all filters
        po_table.setRowSorter(sorter); // Update the table to reflect no filtering
    }//GEN-LAST:event_BT_filActionPerformed

    private void CH_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CH_dateActionPerformed
        if (CH_date.isSelected()){
            update_date_filter(false);
            filter = RowFilter.andFilter(filters);
            sorter.setRowFilter(filter);
            po_table.setRowSorter(sorter);
        } else {
            update_stat_filter(true);
        }
    }//GEN-LAST:event_CH_dateActionPerformed

    private void BT_generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_generateActionPerformed
        try {
            String fileName = txt.getUniqueFileName();
            
            purchaseOrder.writeTxt(fileName);
            JOptionPane.showMessageDialog(this, "A list of purchase orders is stored in the new " + fileName + " file", 
                    "Purchase Order List Generated", 
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), 
                    "Generate List Error", 
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BT_generateActionPerformed

    private void BT_rejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_rejectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BT_rejectActionPerformed

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
    private java.awt.Button BT_approve;
    private javax.swing.JButton BT_fil;
    private javax.swing.JButton BT_generate;
    private java.awt.Button BT_reject;
    private javax.swing.JComboBox<String> CB_date_fil;
    private javax.swing.JComboBox<String> CB_id_fil;
    private javax.swing.JComboBox<String> CB_stat_fil;
    private javax.swing.JCheckBox CH_date;
    private javax.swing.JCheckBox CH_id;
    private javax.swing.JCheckBox CH_stat;
    private com.toedter.calendar.JMonthChooser MC_mth_fil;
    private javax.swing.JTextField TF_id_fil;
    private com.toedter.calendar.JYearChooser YC_yr_fil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable po_table;
    private javax.swing.JScrollPane scroll_po_panel;
    private javax.swing.JScrollPane scroll_po_table;
    // End of variables declaration//GEN-END:variables
}
