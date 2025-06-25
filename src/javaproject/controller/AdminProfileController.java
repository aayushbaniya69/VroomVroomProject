package javaproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaproject.dao.AdminProfileDao;
import javaproject.model.SellerData;
import javaproject.view.AdminDashboardView;
import javaproject.view.ChangePassword;
import javaproject.view.SellerLoginForm;
import javax.swing.JOptionPane;
import javaproject.view.Adminpage;

public class AdminProfileController {
    private Adminpage view;
    private String email;
    private SellerData currentSeller;

    public AdminProfileController(Adminpage view, String email) {
        this.view = view;
        
        if (email == null || email.trim().isEmpty()) {
            System.err.println("ERROR: Invalid email provided to AdminProfileController");
            JOptionPane.showMessageDialog(view, "Invalid email provided!", "Error", JOptionPane.ERROR_MESSAGE);
            view.dispose();
            return;
        }
        
        this.email = email.trim();
        System.out.println("AdminProfileController initialized with email: '" + this.email + "'");
        
        init();

        OpenDashboard backToDashboard = new OpenDashboard();
        this.view.backToDashboard(backToDashboard);
        
        ChangeProfile changeProfile = new ChangeProfile();
        this.view.updateProfile(changeProfile);
        
        ChangePass changePassword = new ChangePass();
        this.view.changePassword(changePassword); 
        
        Deleteaccount deleteAccount = new Deleteaccount();
        this.view.deleteAccount(deleteAccount);
    }

    public AdminProfileController(Adminpage view) {
        this.view = view;
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }
    
    // FIX: Complete init method with PAN number
    public void init() {
    System.out.println("=== ADMIN INIT DEBUG ===");
    System.out.println("Initializing admin profile for email: '" + email + "'");
    
    AdminProfileDao dao = new AdminProfileDao();
    SellerData seller = dao.getUserByEmail(email);
    
    if (seller != null) {
        this.currentSeller = seller;
        System.out.println("Seller found successfully!");
        
        // Debug what we got from database
        System.out.println("Retrieved from database:");
        System.out.println("  FullName: '" + seller.getFullName() + "'");
        System.out.println("  Email: '" + seller.getEmail() + "'");
        System.out.println("  Location: '" + seller.getLocation() + "'");
        System.out.println("  ContactNumber: '" + seller.getContactNumber() + "'");
        System.out.println("  PanNumber: '" + seller.getPanNumber() + "'");
        
        // Set form fields - ALL CONSISTENT AND SIMPLE
        view.getUpdateFullName().setText(seller.getFullName() != null ? seller.getFullName() : "");
        view.getUpdateEmail().setText(seller.getEmail() != null ? seller.getEmail() : "");
        view.getUpdateAddress().setText(seller.getLocation() != null ? seller.getLocation() : "");
        view.getUpdatePhoneNumber().setText(seller.getContactNumber() != null ? seller.getContactNumber() : "");
        view.getUpdatePanNumber().setText(seller.getPanNumber() != null ? seller.getPanNumber() : ""); // SIMPLE!
        
        // Debug final form field values
        System.out.println("Final form field values:");
        System.out.println("  FullName field: '" + view.getUpdateFullName().getText() + "'");
        System.out.println("  Email field: '" + view.getUpdateEmail().getText() + "'");
        System.out.println("  Location field: '" + view.getUpdateAddress().getText() + "'");
        System.out.println("  Phone field: '" + view.getUpdatePhoneNumber().getText() + "'");
        System.out.println("  PAN field: '" + view.getUpdatePanNumber().getText() + "'");
        
        System.out.println("Form fields populated successfully!");
    } else {
        System.err.println("ERROR: Seller not found for email: " + email);
        JOptionPane.showMessageDialog(view, 
            "Admin Profile not found for email: " + email + "\nPlease check your login.", 
            "Error", JOptionPane.ERROR_MESSAGE);
    }
    System.out.println("=== END ADMIN INIT DEBUG ===");
}
        
    public class OpenDashboard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Returning to admin dashboard");
            AdminDashboardView dashboard = new AdminDashboardView();
            SellerDashboardController dashboardController = new SellerDashboardController(dashboard, email);
            dashboardController.open();
            close();
        }
    }
        
    // FIX: Complete ChangeProfile with PAN number support
    public class ChangeProfile implements ActionListener {
        private boolean isEditing = false;
        private SellerData originalSeller;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isEditing) {
                System.out.println("=== ENTERING ADMIN EDIT MODE ===");
                
                if (currentSeller != null) {
                    // Store original data including PAN number
                    originalSeller = new SellerData(
                        currentSeller.getFullName(),
                        currentSeller.getEmail(),
                        currentSeller.getLocation(),
                        currentSeller.getContactNumber(),
                        currentSeller.getPassword(),
                        currentSeller.getRePassword(),
                        currentSeller.getPanNumber()
                    );
                    System.out.println("Original seller data stored:");
                    System.out.println("  Email: '" + originalSeller.getEmail() + "'");
                    System.out.println("  Location: '" + originalSeller.getLocation() + "'");
                    System.out.println("  PanNumber: '" + originalSeller.getPanNumber() + "'");
                } else {
                    System.err.println("ERROR: currentSeller is null!");
                    return;
                }
                
                view.setFieldsEnabled(true);
                view.getUpdateEmail().setEnabled(false); // Email should not be editable
                isEditing = true;
                view.setChangeProfileButtonText("Save Changes");
                System.out.println("Edit mode enabled");
                
            } else {
                System.out.println("=== SAVING ADMIN CHANGES ===");
                
                int response = JOptionPane.showConfirmDialog(view, 
                    "Do you want to save the changes?", 
                    "Confirm Save", 
                    JOptionPane.YES_NO_OPTION);
                    
                if (response == JOptionPane.YES_OPTION) {
                    System.out.println("Admin confirmed save");
                    
                    String updatedFullName = view.getUpdateFullName().getText().trim();
                    String updatedLocation = view.getUpdateAddress().getText().trim();
                    String updatedPhone = view.getUpdatePhoneNumber().getText().trim();
                    String updatedPanNumber = "";
                    
                    // FIX: Get PAN number from form
                    try {
                        if (view.getUpdatePanNumber() != null) {
                            updatedPanNumber = view.getUpdatePanNumber().getText().trim();
                        }
                    } catch (Exception ex) {
                        System.err.println("Could not get PAN number from form: " + ex.getMessage());
                    }
                    
                    System.out.println("Form values to save:");
                    System.out.println("  FullName: '" + updatedFullName + "'");
                    System.out.println("  Location: '" + updatedLocation + "'");
                    System.out.println("  Phone: '" + updatedPhone + "'");
                    System.out.println("  PanNumber: '" + updatedPanNumber + "'");
                    System.out.println("  Email (original): '" + email + "'");
                    
                    // Validation
                    if (updatedFullName.isEmpty() || updatedLocation.isEmpty() || updatedPhone.isEmpty()) {
                        JOptionPane.showMessageDialog(view, 
                            "Please fill in all required fields.", 
                            "Validation Error", 
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    // Create updated seller with PAN number
                    SellerData updatedSeller = new SellerData(
                        updatedFullName,
                        email,
                        updatedLocation,
                        updatedPhone,
                        currentSeller.getPassword(),
                        currentSeller.getRePassword(),
                        updatedPanNumber
                    );
                    
                    System.out.println("Created SellerData object for update");
                    
                    AdminProfileDao dao = new AdminProfileDao();
                    if (dao.updateSellerProfile(updatedSeller)) {
                        JOptionPane.showMessageDialog(view, 
                            "Profile updated successfully!", 
                            "Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Profile updated successfully");
                        
                        // Update currentSeller with new data
                        currentSeller = updatedSeller;
                        originalSeller = updatedSeller;
                    } else {
                        JOptionPane.showMessageDialog(view, 
                            "Failed to update profile! Please try again.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                        System.err.println("Failed to update profile");
                        
                        // Restore original values
                        restoreOriginalValues();
                    }
                    
                    // Exit edit mode
                    view.setFieldsEnabled(false);
                    isEditing = false;
                    view.setChangeProfileButtonText("Update Profile");
                } else {
                    // Cancel changes
                    System.out.println("Changes cancelled");
                    restoreOriginalValues();
                    view.setFieldsEnabled(false);
                    isEditing = false;
                    view.setChangeProfileButtonText("Update Profile");
                }
            }
        }
        
        // FIX: Complete restoreOriginalValues with PAN number
        private void restoreOriginalValues() {
            if (originalSeller != null) {
                view.getUpdateFullName().setText(originalSeller.getFullName());
                view.getUpdateEmail().setText(originalSeller.getEmail());
                view.getUpdateAddress().setText(originalSeller.getLocation());
                view.getUpdatePhoneNumber().setText(originalSeller.getContactNumber());
                
                // Restore PAN number too
                try {
                    if (view.getUpdatePanNumber() != null) {
                        view.getUpdatePanNumber().setText(originalSeller.getPanNumber());
                    }
                } catch (Exception e) {
                    System.err.println("Could not restore PAN number: " + e.getMessage());
                }
            }
        }
    }
    
    class ChangePass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Opening change password dialog with email: " + email);
            ChangePassword changePasswordView = new ChangePassword();
            AdminChangePasswordController changePassController = new AdminChangePasswordController(changePasswordView, email);
            changePassController.open();
            close();
        }
    }
    
    // FIX: Complete Deleteaccount with correct redirect
    class Deleteaccount implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(view, 
                "Are you sure you want to delete your account?\nThis action cannot be undone!", 
                "Confirm Account Deletion", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
                
            if (response == JOptionPane.YES_OPTION) {
                System.out.println("Attempting to delete admin account for email: " + email);
                
                AdminProfileDao dao = new AdminProfileDao();
                
                SellerData sellerToDelete = currentSeller;
                if (sellerToDelete == null) {
                    sellerToDelete = dao.getUserByEmail(email);
                }
                
                if (sellerToDelete == null) {
                    JOptionPane.showMessageDialog(view, 
                        "Seller not found! Cannot delete account.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    System.err.println("ERROR: Seller not found for deletion: " + email);
                    return;
                }
                
                if (dao.deleteAccount(sellerToDelete)) {
                    JOptionPane.showMessageDialog(view, 
                        "Account deleted successfully!", 
                        "Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Account deleted successfully");
                    
                    // FIX: Return to SELLER login screen, not user login
                    view.dispose();
                    SellerLoginForm sellerLogin = new SellerLoginForm();
                    SellerLoginController loginController = new SellerLoginController(sellerLogin);
                    loginController.open();
                } else {
                    JOptionPane.showMessageDialog(view, 
                        "Failed to delete account! Please try again.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    System.err.println("ERROR: Failed to delete account for: " + email);
                }
            } else {
                System.out.println("Account deletion cancelled");
            }
        }
    }
}