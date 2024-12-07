/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package SalesManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author isabelle
 */
public class SalesReportPanel extends javax.swing.JPanel {
    private DefaultTableModel model = new DefaultTableModel();
    private final String[] columnName = {"Item ID", "Quantity Sold"};
    private List<Map<String,String>> saleData = new ArrayList<>();
    private Sale sale = new Sale();
    /**
     * Creates new form SalesReportPanel2
     */
    public SalesReportPanel() {
        model.setColumnIdentifiers(columnName);
        
        initComponents();
        
        // Read data from sales.txt
        saleData = sale.viewSaleData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblWelcome3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jLabel13 = new javax.swing.JLabel();
        btnGenerateReport = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();

        lblWelcome3.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblWelcome3.setText("Sales Report");

        jLabel12.setText("Month:");

        jLabel13.setText("Year:");

        btnGenerateReport.setText("Generate");
        btnGenerateReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateReportActionPerformed(evt);
            }
        });

        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWelcome3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btnGenerateReport)))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblWelcome3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerateReport))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(375, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateReportActionPerformed
        // Clear the existing rows in the table
        model.setRowCount(0);
        
        // Populate table
        Map<String, Integer> salesSummary = new HashMap<>(); 

        for (Map<String, String> rowData : saleData) {
            String itemId = rowData.get("itemId");
            String saleDate = rowData.get("saleDate");
            int quantitySold = Integer.parseInt(rowData.get("quantitySold"));

            // Split the date string into day, month, and year
            String[] dateParts = saleDate.split("-"); 
            
            // Array of month names
            String[] months = {
                "Jan", "Feb", "Mar", "Apr", "May", "Jun", 
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
            };

            // Get the selected month (0-based index, 0 = January, 11 = December)
            int selectedMonth = jMonthChooser1.getMonth();

            // Filter the month and year 
            if (dateParts.length == 3 && dateParts[1].equalsIgnoreCase(months[selectedMonth]) && dateParts[2].equals("2024")) {
                salesSummary.put(itemId, salesSummary.getOrDefault(itemId, 0) + quantitySold);
            }
        }

        for (Map.Entry<String, Integer> entry : salesSummary.entrySet()) {
            Object[] row = {entry.getKey(), entry.getValue()};
            model.addRow(row);
        }
        
        
        // Populate chart
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        int rowCount = jTable1.getRowCount();

        // Assuming salesSummary is a Map<String, Integer> containing item IDs and total sales
        for (Map.Entry<String, Integer> entry : salesSummary.entrySet()) {
            String itemId = entry.getKey();
            int totalSales = entry.getValue();
            dcd.setValue(totalSales, "Sales", itemId);
        }

        JFreeChart jchart = ChartFactory.createBarChart("Monthly Sales", "Items", "Sale Count", dcd, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = jchart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        Color clr3 = new Color(204, 0, 51);
        renderer.setSeriesPaint(0, clr3);

        // Add the chart panel to panel
        ChartPanel chartPanel = new ChartPanel(jchart);
        jPanel1.removeAll();
        jPanel1.add(chartPanel, BorderLayout.CENTER);
        jPanel1.setPreferredSize(new Dimension(850, 300));
        jPanel1.revalidate();
    }//GEN-LAST:event_btnGenerateReportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerateReport;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JLabel lblWelcome3;
    // End of variables declaration//GEN-END:variables
}
