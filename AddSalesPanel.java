/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package SalesManager;

import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author isabelle
 */
public class AddSalesPanel extends javax.swing.JPanel {
    private final DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final String[] columnName = {"Sales ID", "Item ID", "Item Name", "Sale Date", "Quantity Sold"};
    private int row = -1;
    private List<String> itemIds =  new ArrayList<>();
    private final SalesManager salesManager = new SalesManager();
    private final Sale sale = new Sale();
    private final String userId;

    /**
     * Creates new form SalesManagerAddSales
     * @param userId
     */
    public AddSalesPanel(String userId) {
        this.userId = userId;
        
        // initialize table
        model.setColumnIdentifiers(columnName);
        
        initComponents();
        
        updateSaleId();
        updateItemNameComboBox();

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAddNewSale = new javax.swing.JButton();
        lblWelcome = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        comboBoxItemName = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        spinnerQuantitySold = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnSaveNewSalesToFile = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        dateChooserSaleDate = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtSaleId = new javax.swing.JTextField();
        txtSearchBar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnDeleteNewSale = new javax.swing.JButton();
        btnEditNewSale = new javax.swing.JButton();
        txtItemId = new javax.swing.JTextField();
        btnClearNewSale = new javax.swing.JButton();
        btnClearSearch = new javax.swing.JButton();

        btnAddNewSale.setText("Add");
        btnAddNewSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewSaleActionPerformed(evt);
            }
        });

        lblWelcome.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblWelcome.setText("Add Sales");

        jLabel1.setText("Item Name:");

        comboBoxItemName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxItemNameItemStateChanged(evt);
            }
        });
        comboBoxItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxItemNameActionPerformed(evt);
            }
        });

        jLabel3.setText("Item ID:");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jTable2.setModel(model);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable2MouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel4.setText("Quantity Sold:");

        btnSaveNewSalesToFile.setText("Save to File");
        btnSaveNewSalesToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveNewSalesToFileActionPerformed(evt);
            }
        });

        jLabel5.setText("Sale Date:");

        jLabel6.setText("Sales ID:");

        txtSaleId.setEditable(false);
        txtSaleId.setBackground(new java.awt.Color(204, 204, 204));
        txtSaleId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaleIdActionPerformed(evt);
            }
        });

        txtSearchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchBarActionPerformed(evt);
            }
        });

        jLabel7.setText("Search Bar:");

        btnDeleteNewSale.setText("Delete");
        btnDeleteNewSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteNewSaleActionPerformed(evt);
            }
        });

        btnEditNewSale.setText("Edit");
        btnEditNewSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditNewSaleActionPerformed(evt);
            }
        });

        txtItemId.setEditable(false);
        txtItemId.setBackground(new java.awt.Color(204, 204, 204));
        txtItemId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemIdActionPerformed(evt);
            }
        });

        btnClearNewSale.setText("Clear");
        btnClearNewSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearNewSaleActionPerformed(evt);
            }
        });

        btnClearSearch.setText("Clear");
        btnClearSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnSearch)
                        .addGap(18, 18, 18)
                        .addComponent(btnClearSearch)
                        .addGap(78, 78, 78))
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSaleId, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnClearNewSale)
                                        .addGap(17, 17, 17)
                                        .addComponent(btnDeleteNewSale)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnEditNewSale)
                                        .addGap(14, 14, 14)
                                        .addComponent(btnAddNewSale))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(comboBoxItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtItemId, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateChooserSaleDate, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spinnerQuantitySold, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSaveNewSalesToFile)))
                        .addGap(50, 50, 50))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblWelcome))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblWelcome)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(comboBoxItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtSaleId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtItemId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(59, 59, 59)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(spinnerQuantitySold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dateChooserSaleDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnDeleteNewSale)
                                .addComponent(btnClearNewSale))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAddNewSale)
                                .addComponent(btnEditNewSale)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnClearSearch)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSaveNewSalesToFile)
                .addContainerGap(174, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void updateSaleId() {
        // create next record id
        txtSaleId.setText(salesManager.nextRecordId("sales.txt"));
    }
    
    private void updateNewSaleId() {
        // get the previous new sale id and slice to get only the number 
        String previousNewSaleId = txtSaleId.getText().substring(2);

        // convert the string to int and increment by one
        int newSaleIdNum = Integer.parseInt(previousNewSaleId) + 1;
        
        // create the new sale id
        int idPaddingSize = 4;
        String idNum = String.format("%0" + idPaddingSize + "d", newSaleIdNum);
        String saleId = String.join("", "sl", idNum);
        
        // assign new sale id to text box
        txtSaleId.setText(saleId);
    }
    
    
    private void updateItemNameComboBox() {
        List<String> itemNames =  new ArrayList<>();
        
        Map<String,List<String>> map = salesManager.updateItemSelectionList(itemNames, itemIds);
        
        itemNames = map.get("itemNames");
        itemIds = map.get("itemIds");

        // Update the combo box model dynamically
        for (String itemName: itemNames) {
            comboBoxItemName.addItem(itemName);
        }
        
        // set initial item selected
        comboBoxItemName.setSelectedIndex(-1);
        txtItemId.setText("");
    }
    
    private void clearTextField() {
        txtItemId.setText("");
        comboBoxItemName.setSelectedIndex(-1);
        dateChooserSaleDate.setDate(null);
        spinnerQuantitySold.setValue(0);
    }
    
    
    private void btnAddNewSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewSaleActionPerformed
        String quantitySold;
        String saleDateString;
        String itemId;
        String itemName;
        
        String salesId = txtSaleId.getText();

        // validate input for item selected
        if ("".equals(txtItemId.getText())) {
            JOptionPane.showMessageDialog(null, "Please select an item to add sales", "No Item Selected", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            itemId = txtItemId.getText();
            itemName = (String) comboBoxItemName.getSelectedItem();
        }
        
        // validate input for quantity sold
        if ((int) spinnerQuantitySold.getValue() <= 0) {
            JOptionPane.showMessageDialog(null, "Quantity Sold must be more than 0", "Invalid Quanity Sold Amount", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            quantitySold = Integer.toString((int) spinnerQuantitySold.getValue());
        }
        
        // validate input for sale date
        Date currentDate = new Date();
        if (dateChooserSaleDate.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Please select a sale date", "Missing Sale Date", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            if (dateChooserSaleDate.getDate().after(currentDate)) {
                JOptionPane.showMessageDialog(null, "Sale Date must not be after today's date", "Invalid Sale Date", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                saleDateString = sdf.format(dateChooserSaleDate.getDate());
            }
        }
        

        // check whether a sale record exists for the selected item and sale date
        if (sale.checkDuplicateSaleEntry(itemId, saleDateString)) {
            JOptionPane.showMessageDialog(null, "A sale record exists for the selected item and sale date", "Existing Sale Entry", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // check whether a duplicate sale record is added
            int rowCount = jTable2.getRowCount();
            int i = 0;
            String tableItemId;
            String tableSaleDateString;
            while (i < rowCount) {
                // Extract the data from each row
                tableItemId = (String) jTable2.getValueAt(i, 1);
                tableSaleDateString = (String) jTable2.getValueAt(i, 3);

                if (itemId.equals(tableItemId) && saleDateString.equals(tableSaleDateString)) {
                   JOptionPane.showMessageDialog(null, "You have added a sale recorded for the selected item and sale date", "Duplicate Sale Entry", JOptionPane.INFORMATION_MESSAGE);
                   return;
                } else {
                    i++;
                }
            }
            
            String newRow[] = {salesId, itemId, itemName, saleDateString, quantitySold};  

            model.addRow(newRow);   

            clearTextField();
            updateNewSaleId();
        }
        

                       
            
      
        
    }//GEN-LAST:event_btnAddNewSaleActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        DefaultTableModel ob =(DefaultTableModel) jTable2.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(ob);
        jTable2.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(txtSearchBar.getText()));
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSaveNewSalesToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveNewSalesToFileActionPerformed
        int rowCount = jTable2.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            // Extract the data from each row
            String saleId = (String) jTable2.getValueAt(i, 0);
            String itemId = (String) jTable2.getValueAt(i, 1);
            String saleDateString = (String) jTable2.getValueAt(i, 3);
            String quantitySoldString = (String) jTable2.getValueAt(i, 4);
     
            Date saleDate = null;
            int quantitySold = -1;
            
            // Convert from string to date/int
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            try {
                // Parse the string into a Date
                saleDate = sdf.parse(saleDateString);
                // Parse the string int an int
                quantitySold = Integer.parseInt((String) quantitySoldString);
            } catch (ParseException ex) {
                Logger.getLogger(AddSalesPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Append to file
            if (sale.writeSaleData(saleId, itemId, quantitySold, saleDate, userId)) {
                JOptionPane.showMessageDialog(this, "Data saved successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Error while saving data");
            }
        }
                
        // remove all rows in table
        model.setRowCount(0);
        clearTextField();
        
        // refresh sales id
        updateSaleId();
               
    }//GEN-LAST:event_btnSaveNewSalesToFileActionPerformed

    private void txtSaleIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaleIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaleIdActionPerformed

    private void btnDeleteNewSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteNewSaleActionPerformed
        if(row==-1){
            JOptionPane.showMessageDialog(this, "Please select a row to delete");
        }
        else {
            model.removeRow(row);
            clearTextField();
            row=-1;
        }        
    }//GEN-LAST:event_btnDeleteNewSaleActionPerformed

    private void btnEditNewSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditNewSaleActionPerformed
        if(row==-1){
            JOptionPane.showMessageDialog(this, "Please select a row to edit");
        }
        else {
            String quantitySold;
            String saleDateString;

            // validate input
            if ((int) spinnerQuantitySold.getValue() <= 0) {
                JOptionPane.showMessageDialog(null, "Quantity Sold must be more than 0", "Invalid Quanity Sold Amount", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else {
                quantitySold = Integer.toString((int) spinnerQuantitySold.getValue());
            }

            Date currentDate = new Date();
            if (dateChooserSaleDate.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Please select a sale date", "Missing Sale Date", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else {
                if (dateChooserSaleDate.getDate().after(currentDate)) {
                    JOptionPane.showMessageDialog(null, "Sale Date must not be after today's date", "Invalid Sale Date", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                    saleDateString = sdf.format(dateChooserSaleDate.getDate());
                }
            }
            
            String saleId = txtSaleId.getText();
            String itemId = txtItemId.getText();
            String itemName = (String) comboBoxItemName.getSelectedItem();

            String editedRow[] = {saleId, itemId, itemName, saleDateString, quantitySold};  

            model.removeRow(row);
            model.insertRow(row, editedRow);
            clearTextField();
            row=-1;
        }     
        
        
       
    }//GEN-LAST:event_btnEditNewSaleActionPerformed

    private void txtItemIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemIdActionPerformed

    private void jTable2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseReleased
        row = jTable2.getSelectedRow(); // get index of selected row
        
        // get value of selected row
        String saleId = String.valueOf(model.getValueAt(row, 0));
        String itemId = String.valueOf(model.getValueAt(row, 1));
        String itemName = String.valueOf(model.getValueAt(row, 2));
        String saleDate = String.valueOf(model.getValueAt(row, 3));
        String quantitySold = String.valueOf(model.getValueAt(row, 4));

        txtSaleId.setText(saleId);
        txtItemId.setText(itemId);
        comboBoxItemName.setSelectedItem(itemName);
        spinnerQuantitySold.setValue(Integer.valueOf(quantitySold));
        // convert string to date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Date saleDateParsed = null;
        try {
            saleDateParsed = sdf.parse(saleDate);
        } catch (ParseException ex) {
            Logger.getLogger(AddSalesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        dateChooserSaleDate.setDate(saleDateParsed);
    }//GEN-LAST:event_jTable2MouseReleased

    private void btnClearNewSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearNewSaleActionPerformed
        clearTextField();
    }//GEN-LAST:event_btnClearNewSaleActionPerformed

    private void txtSearchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchBarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchBarActionPerformed

    private void btnClearSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSearchActionPerformed
        txtSearchBar.setText("");
        DefaultTableModel ob = (DefaultTableModel) jTable2.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(ob);
        jTable2.setRowSorter(sorter);
        sorter.setRowFilter(null);
    }//GEN-LAST:event_btnClearSearchActionPerformed

    private void comboBoxItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxItemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxItemNameActionPerformed

    private void comboBoxItemNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxItemNameItemStateChanged
         // Get the selected index
        int selectedIndex = comboBoxItemName.getSelectedIndex();
        // Check if the event is a selection
        if (evt.getStateChange() == ItemEvent.SELECTED && selectedIndex != -1) {
            // set the item id based on the selected index
             txtItemId.setText(itemIds.get(selectedIndex));
        }
    }//GEN-LAST:event_comboBoxItemNameItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNewSale;
    private javax.swing.JButton btnClearNewSale;
    private javax.swing.JButton btnClearSearch;
    private javax.swing.JButton btnDeleteNewSale;
    private javax.swing.JButton btnEditNewSale;
    private javax.swing.JButton btnSaveNewSalesToFile;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> comboBoxItemName;
    private com.toedter.calendar.JDateChooser dateChooserSaleDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JSpinner spinnerQuantitySold;
    private javax.swing.JTextField txtItemId;
    private javax.swing.JTextField txtSaleId;
    private javax.swing.JTextField txtSearchBar;
    // End of variables declaration//GEN-END:variables
}
