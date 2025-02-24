/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin;
import FinanceManager.purchaseInvoice_table_and_form;
import Login.Login;
import User.Admin;
import User.PurchaseManager;
import Utility.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import PurchaseManager.PurchaseOrder_table_and_form;
import InventoryManager.ItemPanel;
import InventoryManager.StockPanel;
import InventoryManager.SupplierPanel;
import SalesManager.AddSalesPanel;
import SalesManager.CreatePurchaseRequisitionPanel;
import SalesManager.PurchaseRequisitionRecordsPanel;
import SalesManager.SalesRecordsPanel;
import SalesManager.SalesReportPanel;
import User.FinanceManager;
import User.InventoryManager;
import User.SalesManager;
import User.Users;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author tanwa
 */
public class AdminPage extends javax.swing.JFrame {
    
    private final DefaultTableModel model = new DefaultTableModel();
    private final TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
    private final String[] columnName = {"ID","Username","Full Name", "Password", "Email","Contact Number", "Role", "Image Path"};
    private int row = -1;
    private File img, selectedFile, selectedTxt = null;
    private String user_id, fullname, username, password, email, contact_number, role, img_path;
    private final String file = "src/User/User.dat";
    private final Admin admin;
    private Users user;
    private boolean userDeleted = false;
    
    /**
     * Creates new form AdminProfile
     * @param admin
     */
    public AdminPage(Admin admin) {
        this.admin = admin;
        setSize(1074, 750);
        initComponents();
        loadUser();
    }
   

    private void clearText(){
        username_tbx.setEnabled(true);
        add_btn.setEnabled(true);
        edit_btn.setEnabled(false);
        delete_btn.setEnabled(false);
        user_table.clearSelection();
        username_tbx.setText("");
        password_tbx.setText("");
        fullname_tbx.setText("");
        email_tbx.setText("");
        contactnum_tbx.setText("");
        role_group.clearSelection();
        img = null;
        selectedFile = null;
        selectedTxt = null;
        userDeleted = false;
        uploadImg_btn.setBackground(null);
        confirmpassword_tbx.setText("");
    }
    
    
    private void loadUser(){
        model.setColumnIdentifiers(columnName);
        model.setRowCount(0);
        
        // Read user data from the .DAT file
        List<Object> objects = txt.ReadFromDAT(file);
        // Loop through each object and add data to the table model
        for (Object object : objects) {
            if (object instanceof Users user) {
                String user_id = user.getUser_id();
                if (!user_id.equals(admin.getUser_id())){
                    String fullname = user.getFullname();
                    String username = user.getUsername();
                    String password = user.getPassword();
                    String email = user.getEmail();
                    String contact_number = user.getContact_number();
                    String role = user.getRole();
                    String img_path = user.getImage_path();
                    // Populate the table row
                    String[] row = {user_id, fullname, username, password, email, contact_number, role, img_path};
                    model.addRow(row);
                }
            }
        }
        
        user_table.setRowSorter(sorter);

        // Programmatically sort the table by column 0 in ascending order
        sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(0, SortOrder.ASCENDING)));

        // Apply the sorting
        sorter.sort();
        
    }
    
     private boolean checkDuplicate(String username){
        List<Object> objects = txt.ReadFromDAT(file);
        // Loop through each object and add data to the table model
        for (Object object : objects) {
            if (object instanceof Users user) {
               if (username.equals(user.getUsername())) {
                   return true;
                }
            }  
        }
        return false;
    }
    
    private List<Object> rewriteChosenUser(){
        List<Object> objects = txt.ReadFromDAT(file);
        List<Object> updatedObjects = new ArrayList<>();
        // Loop through each object and add data to the table model
        for (Object object : objects) {
            if (object instanceof Users user) {
               if (user_id.equals(user.getUser_id())) {
                    // Skip this user (effectively deleting it)
                    userDeleted = true;
                    continue;
                }
                // Add all other objects back to the updated list
                updatedObjects.add(object);
            }  
        }  
        return updatedObjects;
    }
    
     private String getSelectedRole() {
        if (sales_rb.isSelected()) return "sales";
        if (purchase_rb.isSelected()) return "purchase";
        if (admin_rb.isSelected()) return "admin";
        if (inventory_rb.isSelected()) return "inventory";
        if (finance_rb.isSelected()) return "finance";
        return null;
    }
    
    private void getUserInfo(){
        // Initialize needed information from text fields
        fullname = fullname_tbx.getText().trim();
        username = username_tbx.getText().trim();
        password = confirmpassword_tbx.getText().trim();
        email = email_tbx.getText().trim();
        contact_number = contactnum_tbx.getText().trim();
        
        // Validate each field and show a specific message if any field is empty
        if (fullname.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your full name.");
            return;
        }
        else if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a username.");
            return;
        }
        else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a password.");
            return;
        }
        else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an email address.");
            return;
        }
        else if (contact_number.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a contact number.");
            return;
        }
        
        // Copy the file to the new location with the new name
        if (img != null || selectedFile != null){
            try {
                Files.copy(selectedFile.toPath(), img.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,"File upload failed");
            }
        }
        else{
            img = new File("src/Images_File/blank.png");
        }
        
        if (role_group.getSelection() == null){
            JOptionPane.showMessageDialog(null, "Please select a role");
            return;
        }
        
        role = getSelectedRole();
        // Determine which role is selected and create the corresponding user object
        user = switch (role) {
            case "sales" -> new SalesManager(username, fullname, password, email, contact_number, role, img.getPath());
            case "purchase" -> new PurchaseManager(username, fullname, password, email, contact_number, role, img.getPath());
            case "admin" -> new Admin(username, fullname, password, email, contact_number, role, img.getPath());
            case "inventory" -> new InventoryManager(username, fullname, password, email, contact_number, role, img.getPath());
            case "finance" -> new FinanceManager(username, fullname, password, email, contact_number, role, img.getPath());
            default -> null;
        };

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        role_group = new javax.swing.ButtonGroup();
        AdminPage = new javax.swing.JTabbedPane();
        profile = new javax.swing.JPanel();
        manageUser = new javax.swing.JPanel();
        register_form = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        fullname_tbx = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        confirmpassword_tbx = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        email_tbx = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        contactnum_tbx = new javax.swing.JTextField();
        username_tbx = new javax.swing.JTextField();
        password_tbx = new javax.swing.JTextField();
        sales_rb = new javax.swing.JRadioButton();
        purchase_rb = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        inventory_rb = new javax.swing.JRadioButton();
        finance_rb = new javax.swing.JRadioButton();
        admin_rb = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        uploadImg_btn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        user_table = new javax.swing.JTable();
        edit_btn = new javax.swing.JButton();
        delete_btn = new javax.swing.JButton();
        add_btn = new javax.swing.JButton();
        clear_btn = new javax.swing.JButton();
        autoRegister_btn = new javax.swing.JButton();
        addSales = new javax.swing.JPanel();
        salesRecord = new javax.swing.JPanel();
        salesReport = new javax.swing.JPanel();
        createPurchaseRequisition = new javax.swing.JPanel();
        purchaseRequisitionRecord = new javax.swing.JPanel();
        purchaseOrder = new javax.swing.JPanel();
        purchaseInvoice = new javax.swing.JPanel();
        checkStock = new javax.swing.JPanel();
        itemPanel = new javax.swing.JPanel();
        supplierPanel = new javax.swing.JPanel();
        logout = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1080, 650));
        setName("AdminPage"); // NOI18N
        setResizable(false);

        AdminPage.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        profile.setMaximumSize(new java.awt.Dimension(1024, 650));
        profile.setMinimumSize(new java.awt.Dimension(1024, 650));
        profile.setPreferredSize(new java.awt.Dimension(880, 650));

        javax.swing.GroupLayout profileLayout = new javax.swing.GroupLayout(profile);
        profile.setLayout(profileLayout);
        profileLayout.setHorizontalGroup(
            profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        profileLayout.setVerticalGroup(
            profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        AdminPage.addTab("User Profile", profile);
        Profile_form profile_form = new Profile_form(admin);
        profile.setLayout(new BorderLayout());
        profile.add(profile_form, BorderLayout.CENTER);

        profile.revalidate();
        profile.repaint();

        manageUser.setMaximumSize(new java.awt.Dimension(1024, 650));
        manageUser.setMinimumSize(new java.awt.Dimension(1024, 650));
        manageUser.setPreferredSize(new java.awt.Dimension(880, 650));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Full Name:");

        fullname_tbx.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fullname_tbx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fullname_tbxKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Username:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Password:");

        confirmpassword_tbx.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        confirmpassword_tbx.setEnabled(false);
        confirmpassword_tbx.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                confirmpassword_tbxFocusLost(evt);
            }
        });
        confirmpassword_tbx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmpassword_tbxMouseEntered(evt);
            }
        });
        confirmpassword_tbx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                confirmpassword_tbxKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Confirm Password:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Email:");

        email_tbx.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        email_tbx.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                email_tbxFocusLost(evt);
            }
        });
        email_tbx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                email_tbxKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Contact Number:");

        contactnum_tbx.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        contactnum_tbx.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                contactnum_tbxFocusLost(evt);
            }
        });

        username_tbx.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        username_tbx.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                username_tbxFocusLost(evt);
            }
        });
        username_tbx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                username_tbxKeyReleased(evt);
            }
        });

        password_tbx.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        password_tbx.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                password_tbxFocusLost(evt);
            }
        });
        password_tbx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                password_tbxKeyReleased(evt);
            }
        });

        role_group.add(sales_rb);
        sales_rb.setText("Sales");

        role_group.add(purchase_rb);
        purchase_rb.setText("Purchase");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Role:");

        role_group.add(inventory_rb);
        inventory_rb.setText("Inventory");

        role_group.add(finance_rb);
        finance_rb.setText("Finance");

        role_group.add(admin_rb);
        admin_rb.setText("Admin");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Profile Picture:");

        uploadImg_btn.setText("Upload Image (.jpg or .png)");
        uploadImg_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadImg_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout register_formLayout = new javax.swing.GroupLayout(register_form);
        register_form.setLayout(register_formLayout);
        register_formLayout.setHorizontalGroup(
            register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(register_formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(register_formLayout.createSequentialGroup()
                        .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5))
                            .addGroup(register_formLayout.createSequentialGroup()
                                .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addGap(2, 2, 2))
                            .addComponent(jLabel4))
                        .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(register_formLayout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(fullname_tbx, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(username_tbx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(password_tbx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(confirmpassword_tbx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(email_tbx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(contactnum_tbx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(register_formLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(inventory_rb, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(admin_rb, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(purchase_rb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(finance_rb, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sales_rb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(register_formLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(2, 2, 2)
                        .addComponent(uploadImg_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        register_formLayout.setVerticalGroup(
            register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(register_formLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fullname_tbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(username_tbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(password_tbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(confirmpassword_tbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(email_tbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(contactnum_tbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(register_formLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(purchase_rb)
                            .addComponent(jLabel10)
                            .addComponent(admin_rb))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inventory_rb)
                            .addComponent(finance_rb)))
                    .addGroup(register_formLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(sales_rb)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(register_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(uploadImg_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        user_table.setModel(model);
        user_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        user_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                user_tableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(user_table);

        edit_btn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        edit_btn.setText("Edit");
        edit_btn.setEnabled(false);
        edit_btn.setPreferredSize(new java.awt.Dimension(100, 27));
        edit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_btnActionPerformed(evt);
            }
        });

        delete_btn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        delete_btn.setText("Delete");
        delete_btn.setEnabled(false);
        delete_btn.setPreferredSize(new java.awt.Dimension(100, 27));
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });

        add_btn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add_btn.setText("Add");
        add_btn.setPreferredSize(new java.awt.Dimension(100, 27));
        add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btnActionPerformed(evt);
            }
        });

        clear_btn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clear_btn.setText("Clear");
        clear_btn.setPreferredSize(new java.awt.Dimension(100, 27));
        clear_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_btnActionPerformed(evt);
            }
        });

        autoRegister_btn.setText("Auto Register");
        autoRegister_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoRegister_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(edit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clear_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(autoRegister_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(autoRegister_btn)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout manageUserLayout = new javax.swing.GroupLayout(manageUser);
        manageUser.setLayout(manageUserLayout);
        manageUserLayout.setHorizontalGroup(
            manageUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageUserLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(register_form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        manageUserLayout.setVerticalGroup(
            manageUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageUserLayout.createSequentialGroup()
                .addGroup(manageUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(manageUserLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(manageUserLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(register_form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        AdminPage.addTab("Manage User", manageUser);

        javax.swing.GroupLayout addSalesLayout = new javax.swing.GroupLayout(addSales);
        addSales.setLayout(addSalesLayout);
        addSalesLayout.setHorizontalGroup(
            addSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        addSalesLayout.setVerticalGroup(
            addSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        AdminPage.addTab("Add Sales", addSales);
        AddSalesPanel addSales_form = new AddSalesPanel(admin.getUser_id());
        addSales.setLayout(new BorderLayout());
        addSales.add(addSales_form, BorderLayout.CENTER);

        addSales.revalidate();
        addSales.repaint();

        javax.swing.GroupLayout salesRecordLayout = new javax.swing.GroupLayout(salesRecord);
        salesRecord.setLayout(salesRecordLayout);
        salesRecordLayout.setHorizontalGroup(
            salesRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        salesRecordLayout.setVerticalGroup(
            salesRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        AdminPage.addTab("Sales Record", salesRecord);
        SalesRecordsPanel salesRecords_form = new SalesRecordsPanel(admin.getUser_id());
        salesRecord.setLayout(new BorderLayout());
        salesRecord.add(salesRecords_form, BorderLayout.CENTER);

        salesRecord.revalidate();
        salesRecord.repaint();

        javax.swing.GroupLayout salesReportLayout = new javax.swing.GroupLayout(salesReport);
        salesReport.setLayout(salesReportLayout);
        salesReportLayout.setHorizontalGroup(
            salesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        salesReportLayout.setVerticalGroup(
            salesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        AdminPage.addTab("Sales Report", salesReport);
        SalesReportPanel salesReport_form = new SalesReportPanel();
        salesReport.setLayout(new BorderLayout());
        salesReport.add(salesReport_form, BorderLayout.CENTER);

        salesReport.revalidate();
        salesReport.repaint();

        javax.swing.GroupLayout createPurchaseRequisitionLayout = new javax.swing.GroupLayout(createPurchaseRequisition);
        createPurchaseRequisition.setLayout(createPurchaseRequisitionLayout);
        createPurchaseRequisitionLayout.setHorizontalGroup(
            createPurchaseRequisitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        createPurchaseRequisitionLayout.setVerticalGroup(
            createPurchaseRequisitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        AdminPage.addTab("Create Purchase Requisition", createPurchaseRequisition);
        CreatePurchaseRequisitionPanel purchaseRequisition_form = new CreatePurchaseRequisitionPanel(admin.getUser_id());
        createPurchaseRequisition.setLayout(new BorderLayout());
        createPurchaseRequisition.add(purchaseRequisition_form, BorderLayout.CENTER);

        createPurchaseRequisition.revalidate();
        createPurchaseRequisition.repaint();

        purchaseRequisitionRecord.setMaximumSize(new java.awt.Dimension(1024, 650));
        purchaseRequisitionRecord.setMinimumSize(new java.awt.Dimension(1024, 650));
        purchaseRequisitionRecord.setPreferredSize(new java.awt.Dimension(880, 650));

        javax.swing.GroupLayout purchaseRequisitionRecordLayout = new javax.swing.GroupLayout(purchaseRequisitionRecord);
        purchaseRequisitionRecord.setLayout(purchaseRequisitionRecordLayout);
        purchaseRequisitionRecordLayout.setHorizontalGroup(
            purchaseRequisitionRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        purchaseRequisitionRecordLayout.setVerticalGroup(
            purchaseRequisitionRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        AdminPage.addTab("Purchase Requisition Record", purchaseRequisitionRecord);
        PurchaseRequisitionRecordsPanel purchaseRequisitionRecord_form = new PurchaseRequisitionRecordsPanel(editPermission.edit, admin.getUser_id());
        purchaseRequisitionRecord.setLayout(new BorderLayout());
        purchaseRequisitionRecord.add(purchaseRequisitionRecord_form, BorderLayout.CENTER);

        purchaseRequisitionRecord.revalidate();
        purchaseRequisitionRecord.repaint();

        purchaseOrder.setMaximumSize(new java.awt.Dimension(1024, 650));
        purchaseOrder.setMinimumSize(new java.awt.Dimension(1024, 650));
        purchaseOrder.setPreferredSize(new java.awt.Dimension(880, 650));

        javax.swing.GroupLayout purchaseOrderLayout = new javax.swing.GroupLayout(purchaseOrder);
        purchaseOrder.setLayout(purchaseOrderLayout);
        purchaseOrderLayout.setHorizontalGroup(
            purchaseOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        purchaseOrderLayout.setVerticalGroup(
            purchaseOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        AdminPage.addTab("Manage Purchase Order", purchaseOrder);
        PurchaseOrder_table_and_form purchaseOrder_panel = new PurchaseOrder_table_and_form(editPermission.approve, admin.getUser_id());
        purchaseOrder.add(purchaseOrder_panel);

        purchaseOrder.setLayout(new BorderLayout());
        purchaseOrder.add(purchaseOrder_panel, BorderLayout.CENTER);
        purchaseOrder.revalidate();
        purchaseOrder.repaint();

        purchaseInvoice.setMaximumSize(new java.awt.Dimension(1024, 650));
        purchaseInvoice.setMinimumSize(new java.awt.Dimension(1024, 650));
        purchaseInvoice.setPreferredSize(new java.awt.Dimension(880, 650));

        javax.swing.GroupLayout purchaseInvoiceLayout = new javax.swing.GroupLayout(purchaseInvoice);
        purchaseInvoice.setLayout(purchaseInvoiceLayout);
        purchaseInvoiceLayout.setHorizontalGroup(
            purchaseInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        purchaseInvoiceLayout.setVerticalGroup(
            purchaseInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        AdminPage.addTab("Manage Purchase Invoice", purchaseInvoice);
        purchaseInvoice_table_and_form purchaseInvoice_form = new purchaseInvoice_table_and_form(editPermission.approve, admin.getUser_id());
        purchaseInvoice.setLayout(new BorderLayout());
        purchaseInvoice.add(purchaseInvoice_form, BorderLayout.CENTER);

        purchaseInvoice.revalidate();
        purchaseInvoice.repaint();

        checkStock.setMaximumSize(new java.awt.Dimension(1024, 650));
        checkStock.setMinimumSize(new java.awt.Dimension(1024, 650));
        checkStock.setPreferredSize(new java.awt.Dimension(880, 650));

        javax.swing.GroupLayout checkStockLayout = new javax.swing.GroupLayout(checkStock);
        checkStock.setLayout(checkStockLayout);
        checkStockLayout.setHorizontalGroup(
            checkStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        checkStockLayout.setVerticalGroup(
            checkStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        AdminPage.addTab("Check Stock Level", checkStock);
        StockPanel stock_form = new StockPanel();
        checkStock.setLayout(new BorderLayout());
        checkStock.add(stock_form, BorderLayout.CENTER);

        checkStock.revalidate();
        checkStock.repaint();

        javax.swing.GroupLayout itemPanelLayout = new javax.swing.GroupLayout(itemPanel);
        itemPanel.setLayout(itemPanelLayout);
        itemPanelLayout.setHorizontalGroup(
            itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        itemPanelLayout.setVerticalGroup(
            itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        AdminPage.addTab("Item", itemPanel);
        ItemPanel item_form = new ItemPanel();
        itemPanel.setLayout(new BorderLayout());
        itemPanel.add(item_form, BorderLayout.CENTER);

        itemPanel.revalidate();
        itemPanel.repaint();

        javax.swing.GroupLayout supplierPanelLayout = new javax.swing.GroupLayout(supplierPanel);
        supplierPanel.setLayout(supplierPanelLayout);
        supplierPanelLayout.setHorizontalGroup(
            supplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        supplierPanelLayout.setVerticalGroup(
            supplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        AdminPage.addTab("Supplier", supplierPanel);
        SupplierPanel supplier_form = new SupplierPanel(editPermission.edit);
        supplierPanel.setLayout(new BorderLayout());
        supplierPanel.add(supplier_form, BorderLayout.CENTER);

        supplierPanel.revalidate();
        supplierPanel.repaint();

        logout.setMaximumSize(new java.awt.Dimension(1024, 650));
        logout.setMinimumSize(new java.awt.Dimension(1024, 650));
        logout.setPreferredSize(new java.awt.Dimension(880, 650));
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
            .addGap(0, 650, Short.MAX_VALUE)
        );

        AdminPage.addTab("Log Out", logout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(AdminPage)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AdminPage, javax.swing.GroupLayout.PREFERRED_SIZE, 597, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clear_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_btnActionPerformed
        // TODO add your handling code here:
        clearText();
        confirmpassword_tbx.setEnabled(false);
    }//GEN-LAST:event_clear_btnActionPerformed

    private void add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btnActionPerformed
        // TODO add your handling code here:

        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to add this user?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            getUserInfo();
            if (user != null){
                // Generate User ID and save the user
                user_id = user.Generate_UserID();
                user.setUser_id(user_id); // Ensure setUser_id() is defined
                System.out.println("Generated User ID: " + user.getUser_id()); // Debug line
                txt.WriteToDAT(file, user);
                JOptionPane.showMessageDialog(null, "User registered successfully!");
                loadUser();
                clearText();
            }
        }
    }//GEN-LAST:event_add_btnActionPerformed

    
    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnActionPerformed
        // TODO add your handling code here:

        int confirm = JOptionPane.showConfirmDialog(null,
            "Are you sure want to delele this user?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION){
            // Warning to user when no row selected
            if(row == -1){
                JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            }
            else{
                List<Object> updatedObjects = rewriteChosenUser();
                if (!img_path.equals("src\\Images_File\\blank.png")) {
                    BDUtility.deleteFile(img_path);
                }

                if (userDeleted){
                    txt.WriteToDAT(file, updatedObjects);
                    JOptionPane.showMessageDialog(null, "User has been deleted");
                    model.removeRow(row);
                    user_table.getRowSorter().allRowsChanged(); // Refresh sorter to prevent stale indices
                    row = -1; // To initialize to no row selected
                    clearText();
                    confirmpassword_tbx.setEnabled(false);
                    this.validate();
                    this.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Failed to delete user.");
                }
            } 
        }     
    }//GEN-LAST:event_delete_btnActionPerformed

    private void edit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_btnActionPerformed
        // TODO add your handling code here:

        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to update this user's details",
            "Confirmation",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION){
            List<Object> updatedObjects = rewriteChosenUser();
            getUserInfo();
            user.setUser_id(user_id); // Ensure setUser_id() is defined
            updatedObjects.add(user);
            txt.WriteToDAT(file, updatedObjects);
            JOptionPane.showMessageDialog(null, "User updated successfully!");
            
            clearText();
            loadUser();
            confirmpassword_tbx.setEnabled(false);
        }

        
    }//GEN-LAST:event_edit_btnActionPerformed

    private void user_tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_tableMouseReleased
        // TODO add your handling code here:
        username_tbx.setEnabled(false);
        role_group.clearSelection();
        add_btn.setEnabled(false);
        edit_btn.setEnabled(true);
        delete_btn.setEnabled(true);
        confirmpassword_tbx.setEnabled(true);
        int viewRow = user_table.getSelectedRow(); // To know which row I am selecting
        row = user_table.convertRowIndexToModel(viewRow);

        // To get value from model based on the selected column and fix column
        user_id = (String) model.getValueAt(row, 0);
        fullname = (String) model.getValueAt(row, 1);
        username = (String) model.getValueAt(row, 2);
        password = (String) model.getValueAt(row, 3);
        email = (String) model.getValueAt(row, 4);
        contact_number = (String) model.getValueAt(row, 5);
        role = (String) model.getValueAt(row, 6);
        img_path = (String) model.getValueAt(row, 7);

        // Set to the textfield
        username_tbx.setText(username);
        password_tbx.setText(password);
        confirmpassword_tbx.setText(password);
        fullname_tbx.setText(fullname);
        email_tbx.setText(email);
        contactnum_tbx.setText(contact_number);
        switch (role){
            case "admin" -> admin_rb.setSelected(true);
            case "purchase" -> purchase_rb.setSelected(true);
            case "sales" -> sales_rb.setSelected(true);
            case "finance" -> finance_rb.setSelected(true);
            case "inventory" -> inventory_rb.setSelected(true);
            default -> {}
        }
    }//GEN-LAST:event_user_tableMouseReleased

    private void password_tbxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_password_tbxKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            confirmpassword_tbx.requestFocusInWindow();
        }
    }//GEN-LAST:event_password_tbxKeyReleased

    private void password_tbxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password_tbxFocusLost
        // TODO add your handling code here:

        // Check if the password textbox is empty
        if (password_tbx.getText().trim().isEmpty()) {
            confirmpassword_tbx.setText("");
            confirmpassword_tbx.setEnabled(false);
        }
        // Check if the password meets criteria
        else if (!Validator.isValidPassword(password_tbx.getText())) {
            JOptionPane.showMessageDialog(null, """
                Please make sure your password meets the following criteria:
                - At least 8 characters
                - At least one uppercase letter
                - At least one lowercase letter
                - At least one digit
                - At least one symbol (excluding ',', '{}', '[]', '=')
                """);
                password_tbx.setText("");
            }
            // Enable the confirm password textbox if criteria are met
            else {
                confirmpassword_tbx.setEnabled(true);
            }
    }//GEN-LAST:event_password_tbxFocusLost

    private void username_tbxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_username_tbxKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            password_tbx.requestFocusInWindow();
        }
    }//GEN-LAST:event_username_tbxKeyReleased

    private void username_tbxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_username_tbxFocusLost
        if (username_tbx.getText().trim().isEmpty()) {
        }
        else if (checkDuplicate(username_tbx.getText())){
            JOptionPane.showMessageDialog(null,"The username is already exist.");
            username_tbx.setText("");
        }
    }//GEN-LAST:event_username_tbxFocusLost

    private void email_tbxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_email_tbxKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            contactnum_tbx.requestFocusInWindow();
        }
    }//GEN-LAST:event_email_tbxKeyReleased

    private void email_tbxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_email_tbxFocusLost
        // TODO add your handling code here:
        if (email_tbx.getText().trim().isEmpty()) {
        }
        else if (!Validator.isValidEmailAddress(email_tbx.getText())){
            JOptionPane.showMessageDialog(null,"This is a invalid email.");
            email_tbx.setText("");
        }
    }//GEN-LAST:event_email_tbxFocusLost

    private void confirmpassword_tbxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confirmpassword_tbxKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            email_tbx.requestFocusInWindow();
        }
    }//GEN-LAST:event_confirmpassword_tbxKeyReleased

    private void confirmpassword_tbxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmpassword_tbxFocusLost
        // TODO add your handling code here:
        if (confirmpassword_tbx.getText().trim().isEmpty() || password_tbx.getText().trim().isEmpty()) {

        }
        else if (!confirmpassword_tbx.getText().equals(password_tbx.getText())) {
            JOptionPane.showMessageDialog(null, "Passwords do not match. Please try again.");
            confirmpassword_tbx.setText(""); // Clear the confirm password field
        }
    }//GEN-LAST:event_confirmpassword_tbxFocusLost

    private void fullname_tbxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fullname_tbxKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            username_tbx.requestFocusInWindow();
        }
    }//GEN-LAST:event_fullname_tbxKeyReleased

    private void logoutComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_logoutComponentShown
        // TODO add your handling code here:
        Login login = new Login();
        this.setVisible(false);
        login.setVisible(true);
    }//GEN-LAST:event_logoutComponentShown

    private void autoRegister_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoRegister_btnActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Txt File to Upload");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Txt Files", "txt"));

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedTxt = fileChooser.getSelectedFile();
            
            List<Map<String,String>> allObj = txt.ReadFromTxt(selectedTxt.getAbsolutePath());
            List<Map<String,String>> errorObj = new ArrayList();
            
            if(allObj == null){
                JOptionPane.showMessageDialog(null, "The file is empty.");
            }
            else{
                try{
                    for (Map<String, String> obj: allObj){
                        username = obj.get("username");
                        fullname = obj.get("full_name");
                        password = obj.get("password");
                        email = obj.get("email");
                        contact_number = obj.get("contact_number");
                        role = obj.get("role");
                        img_path = "src/Images_File/blank.png";

                        user = switch (role) {
                            case "sales" -> new SalesManager(username, fullname, password, email, contact_number, role, img_path);
                            case "purchase" -> new PurchaseManager(username, fullname, password, email, contact_number, role, img_path);
                            case "admin" -> new Admin(username, fullname, password, email, contact_number, role, img_path);
                            case "inventory" -> new InventoryManager(username, fullname, password, email, contact_number, role, img_path);
                            case "finance" -> new FinanceManager(username, fullname, password, email, contact_number, role, img_path);
                            default -> null;
                        };

                        if(checkDuplicate(username)|| !Validator.isValidEmailAddress(email)|| 
                           !Validator.isValidPassword(password) || !Validator.isValidPhoneNum(contact_number)){
                            user = null;
                        }

                        if (user != null){
                            user_id = user.Generate_UserID();
                            user.setUser_id(user_id);
                            txt.WriteToDAT(file, user);
                        }
                        else{
                            errorObj.add(obj);
                        }   
                    }
                    
                    JOptionPane.showMessageDialog(null, "There are " + errorObj.size() + " user unable to register.");
                    txt.WriteToTxt(selectedTxt.getAbsolutePath(), errorObj);
                    loadUser();
                }
                catch(NullPointerException e){
                    JOptionPane.showMessageDialog(null, "This file conduct wrong format of data.");
                }

            } 
            
        } else {
            JOptionPane.showMessageDialog(null,"No file selected");
        }
    }//GEN-LAST:event_autoRegister_btnActionPerformed

    private void confirmpassword_tbxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmpassword_tbxMouseEntered
        if (!password_tbx.getText().trim().isEmpty()) {
            confirmpassword_tbx.setEnabled(true);
        }
    }//GEN-LAST:event_confirmpassword_tbxMouseEntered

    private void contactnum_tbxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contactnum_tbxFocusLost
        if (contactnum_tbx.getText().trim().isEmpty()) {
        }
        else if (!Validator.isValidPhoneNum(contactnum_tbx.getText())){
            JOptionPane.showMessageDialog(
                    null,
                    "This is a invalid phone number. Please follow this example(e.g. +60123456789)"
            );
            contactnum_tbx.setText(""); // Clear the contact number field
        }
    }//GEN-LAST:event_contactnum_tbxFocusLost

    private void uploadImg_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadImg_btnActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image to Upload");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "png"));

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

            // Set the new file name
            File destinationDirectory = new File("src/Images_File");
            if (!destinationDirectory.exists()) {
                destinationDirectory.mkdirs(); // Create the directory if it doesn't exist
            }

            img = new File(destinationDirectory, username_tbx.getText() + ".png");
            uploadImg_btn.setBackground(Color.green);

        } else {
            JOptionPane.showMessageDialog(null,"No file selected");
        }
    }//GEN-LAST:event_uploadImg_btnActionPerformed
  
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Admin admin = new Admin();
                new AdminPage(admin).setVisible(true);
            }
        });
    }
    


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane AdminPage;
    private javax.swing.JPanel addSales;
    private javax.swing.JButton add_btn;
    private javax.swing.JRadioButton admin_rb;
    private javax.swing.JButton autoRegister_btn;
    private javax.swing.JPanel checkStock;
    private javax.swing.JButton clear_btn;
    private javax.swing.JTextField confirmpassword_tbx;
    private javax.swing.JTextField contactnum_tbx;
    private javax.swing.JPanel createPurchaseRequisition;
    private javax.swing.JButton delete_btn;
    private javax.swing.JButton edit_btn;
    private javax.swing.JTextField email_tbx;
    private javax.swing.JRadioButton finance_rb;
    private javax.swing.JTextField fullname_tbx;
    private javax.swing.JRadioButton inventory_rb;
    private javax.swing.JPanel itemPanel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel logout;
    private javax.swing.JPanel manageUser;
    private javax.swing.JTextField password_tbx;
    private javax.swing.JPanel profile;
    private javax.swing.JPanel purchaseInvoice;
    private javax.swing.JPanel purchaseOrder;
    private javax.swing.JPanel purchaseRequisitionRecord;
    private javax.swing.JRadioButton purchase_rb;
    private javax.swing.JPanel register_form;
    private javax.swing.ButtonGroup role_group;
    private javax.swing.JPanel salesRecord;
    private javax.swing.JPanel salesReport;
    private javax.swing.JRadioButton sales_rb;
    private javax.swing.JPanel supplierPanel;
    private javax.swing.JButton uploadImg_btn;
    private javax.swing.JTable user_table;
    private javax.swing.JTextField username_tbx;
    // End of variables declaration//GEN-END:variables
}
