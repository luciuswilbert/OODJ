package Admin;

import User.*;
import Utility.BDUtility;
import Utility.Validator;
import Utility.txt;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Profile_form extends javax.swing.JPanel {

    private final String file = "src/User/User.dat";
    private Users owner;
    private File img, selectedFile = null;
    
    public Profile_form(Users user){
        setSize(1074, 750);
        this.owner = user;
        initComponents();
        initialize(user);
        try {
            populateForm(user);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    public final void populateForm(Users user) throws FileNotFoundException{
        BDUtility.setImage(profile_lb, user.getImage_path(), 190, 190);
    }
    
    public final void populateForm(String path) throws FileNotFoundException{
        BDUtility.setImage(profile_lb, path, 190, 190);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profile_lb = new javax.swing.JLabel();
        save_btn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        fullname_lb = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        username_lb = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        email_lb = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        role_lb = new javax.swing.JTextField();
        contactnum_lb = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        userID_lb = new javax.swing.JTextField();
        password_lb = new javax.swing.JPasswordField();
        cancel_btn = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1024, 650));
        setMinimumSize(new java.awt.Dimension(1024, 650));
        setPreferredSize(new java.awt.Dimension(1024, 650));

        profile_lb.setBackground(new java.awt.Color(255, 153, 153));
        profile_lb.setMaximumSize(new java.awt.Dimension(190, 190));
        profile_lb.setMinimumSize(new java.awt.Dimension(190, 190));
        profile_lb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                profile_lbMouseReleased(evt);
            }
        });

        save_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        save_btn.setText("Save");
        save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_btnActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Full Name:");

        fullname_lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fullname_lb.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fullname_lbFocusLost(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Username:");

        username_lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        username_lb.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Password:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Email:");

        email_lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        email_lb.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                email_lbFocusLost(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Role:");

        role_lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        role_lb.setEnabled(false);

        contactnum_lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        contactnum_lb.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                contactnum_lbFocusLost(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Contact Number:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("User ID:");

        userID_lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userID_lb.setEnabled(false);

        password_lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        password_lb.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                password_lbFocusLost(evt);
            }
        });
        password_lb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                password_lbMouseReleased(evt);
            }
        });
        password_lb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                password_lbKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(userID_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(contactnum_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(role_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(85, 85, 85))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel9))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(2, 2, 2))
                            .addComponent(jLabel12))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(username_lb, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fullname_lb, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(email_lb)
                            .addComponent(password_lb, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                        .addGap(83, 83, 83))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(userID_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(fullname_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(username_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(password_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(email_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contactnum_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(role_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        cancel_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cancel_btn.setText("Cancel");
        cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(profile_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(293, 293, 293)
                .addComponent(save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(cancel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(293, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(154, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(profile_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(129, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_btnActionPerformed
        // TODO add your handling code here:
        
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to update?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

        } else {
            return;  // This will exit the method
        }
        
        // Validate each field and show a specific message if any field is empty
        if (fullname_lb.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your full name.");
            return;
        }
        else if (password_lb.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a password.");
            return;
        }
        else if (email_lb.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an email address.");
            return;
        }
        else if (contactnum_lb.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a contact number.");
            return;
        }
        
        // Read user data from the .DAT file
        List<Object> objects = txt.ReadFromDAT(file);
        List<Object> updatedObjects = new ArrayList<>();
        boolean userDeleted = false; // To track if the user is found
        // Loop through each object and add data to the table model
        for (Object object : objects) {
            if (object instanceof Users user) {
                if (userID_lb.getText().equals(user.getUser_id())) {
                    // Skip this user (effectively deleting it)
                    userDeleted = true;
                    continue;
                }
                // Add all other objects back to the updated list
                updatedObjects.add(object);
            }  
        }
        
        if(img != null & selectedFile != null){
            try {
                Files.copy(selectedFile.toPath(), img.toPath(), StandardCopyOption.REPLACE_EXISTING);
                owner.setImage_path(img.getPath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,"File upload failed");
            }
        }
                    
        owner.setFullname(fullname_lb.getText().trim());
        owner.setPassword(password_lb.getText().trim());
        owner.setEmail(email_lb.getText().trim());
        owner.setContact_number(contactnum_lb.getText().trim());  
        
        updatedObjects.add(owner);
         
        txt.WriteToDAT(file, updatedObjects);
            
        JOptionPane.showMessageDialog(null, "Profile details updated.");
        this.validate();
        this.repaint();  
    }//GEN-LAST:event_save_btnActionPerformed

    private void cancel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_btnActionPerformed
        // TODO add your handling code here:
        initialize(owner);
    }//GEN-LAST:event_cancel_btnActionPerformed

    private void password_lbMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_password_lbMouseReleased
        // TODO add your handling code here:
        if (password_lb.getEchoChar() == '*'){
            if (password_lb.getText().trim().equals(owner.getPassword())){
                String oldPassword = JOptionPane.showInputDialog(null, "Please enter your old password:");

                if (oldPassword == null) {
                    // Do nothing and exit the method
                    return;
                }
                
                
                if (oldPassword.equals(owner.getPassword())) {
                    // If the old password matches, allow them to proceed with their new password entry
                    JOptionPane.showMessageDialog(null, "Old password is correct. You may now enter a new password.");
                    password_lb.requestFocusInWindow();
                    password_lb.setEchoChar((char)0);
                } else {
                    // If the password does not match, notify the user and clear the input field
                    JOptionPane.showMessageDialog(null, "Incorrect old password. Please try again.");
                    email_lb.requestFocusInWindow();
                    password_lb.setEchoChar('*');

                }
            }
        }
    }//GEN-LAST:event_password_lbMouseReleased

    private void fullname_lbFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fullname_lbFocusLost
        // TODO add your handling code here:
        if (fullname_lb.getText().trim().isEmpty()){
            fullname_lb.setText(owner.getFullname());
        }
    }//GEN-LAST:event_fullname_lbFocusLost

    private void password_lbFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password_lbFocusLost
        // TODO add your handling code here:
        if (password_lb.getText().trim().isEmpty()){
            password_lb.setText(owner.getPassword());
        }
        else if (!Validator.isValidPassword(password_lb.getText())) {
            JOptionPane.showMessageDialog(null, """
                Please make sure your password meets the following criteria:
                - At least 8 characters
                - At least one uppercase letter
                - At least one lowercase letter
                - At least one digit
                - At least one symbol (excluding ',', '{}', '[]', '=')
            """);
            password_lb.requestFocusInWindow();
            password_lb.setText(owner.getPassword());
        } 
    }//GEN-LAST:event_password_lbFocusLost

    private void contactnum_lbFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contactnum_lbFocusLost
        // TODO add your handling code here:
        if (contactnum_lb.getText().trim().isEmpty()) {
            contactnum_lb.setText(owner.getContact_number());
        }
        else if (!Validator.isValidPhoneNum(contactnum_lb.getText())){
            JOptionPane.showMessageDialog(
                    null,
                    "This is a invalid phone number. Please follow this example(e.g. +60123456789)"
            );
            contactnum_lb.setText(owner.getContact_number());
        }
    }//GEN-LAST:event_contactnum_lbFocusLost

    private void email_lbFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_email_lbFocusLost
        // TODO add your handling code here:
        
        if (email_lb.getText().trim().isEmpty()) {
            email_lb.setText(owner.getEmail());
        }
        else if (!Validator.isValidEmailAddress(email_lb.getText())){
            JOptionPane.showMessageDialog(null,"This is a invalid email.");
            email_lb.setText(owner.getEmail());
        }

    }//GEN-LAST:event_email_lbFocusLost

    private void password_lbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_password_lbKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            email_lb.requestFocusInWindow();
        }
    }//GEN-LAST:event_password_lbKeyReleased

    private void profile_lbMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profile_lbMouseReleased
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image to Upload");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "png"));

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile(); // Only .png is acceptable

            // Set the new file name
            File destinationDirectory = new File("src/Images_File");
            if (!destinationDirectory.exists()) {
                destinationDirectory.mkdirs(); // Create the directory if it doesn't exist
            }

            img = new File(destinationDirectory, username_lb.getText() + ".png");
            try {
                populateForm(selectedFile.getPath());
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            }
            
        } else {
            JOptionPane.showMessageDialog(null,"No file selected");
        }
    }//GEN-LAST:event_profile_lbMouseReleased

    
    private void initialize(Users user){
        username_lb.setText(owner.getUsername());
        password_lb.setText(owner.getPassword());
        fullname_lb.setText(owner.getFullname());
        userID_lb.setText(owner.getUser_id());
        email_lb.setText(owner.getEmail());
        contactnum_lb.setText(owner.getContact_number());
        role_lb.setText(owner.getRole());  
        try{
            populateForm(owner.getImage_path());
        }
        catch (IOException e){
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel_btn;
    private javax.swing.JTextField contactnum_lb;
    private javax.swing.JTextField email_lb;
    private javax.swing.JTextField fullname_lb;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField password_lb;
    private javax.swing.JLabel profile_lb;
    private javax.swing.JTextField role_lb;
    private javax.swing.JButton save_btn;
    private javax.swing.JTextField userID_lb;
    private javax.swing.JTextField username_lb;
    // End of variables declaration//GEN-END:variables
}
