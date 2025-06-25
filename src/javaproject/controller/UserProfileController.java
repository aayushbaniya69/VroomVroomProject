package javaproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaproject.dao.UserProfileDao;
import javaproject.model.UserData;
import javaproject.view.ChangePassword;
import javaproject.view.DashboardView;
import javaproject.view.LoginForm;
import javaproject.view.user;
import javax.swing.JOptionPane;

public class UserProfileController {
    private user view;
    private String email;
    private UserData currentUser; // FIX: Store the original user data

    public UserProfileController(user view, String email) {
        this.view = view;
        
        if (email == null || email.trim().isEmpty()) {
            System.err.println("ERROR: Invalid email provided to UserProfileController");
            JOptionPane.showMessageDialog(view, "Invalid email provided!", "Error", JOptionPane.ERROR_MESSAGE);
            view.dispose();
            return;
        }
        
        this.email = email.trim();
        System.out.println("UserProfileController initialized with email: '" + this.email + "'");
        
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

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }
    
    public void init() {
        System.out.println("=== INIT DEBUG ===");
        System.out.println("Initializing user profile for email: '" + email + "'");
        
        UserProfileDao dao = new UserProfileDao();
        UserData user = dao.getUserByEmail(email);
        
        if (user != null) {
            this.currentUser = user; // FIX: Store the user data
            System.out.println("User found successfully!");
            
            // FIX: Debug what we got from database
            System.out.println("Retrieved from database:");
            System.out.println("  FirstName: '" + user.getFirstName() + "'");
            System.out.println("  LastName: '" + user.getLastName() + "'");
            System.out.println("  Email: '" + user.getEmail() + "'");
            System.out.println("  Address: '" + user.getAddress() + "'");
            System.out.println("  ContactNumber: '" + user.getContactNumber() + "'");
            
            // Set form fields
            view.getUpdateFirstName().setText(user.getFirstName() != null ? user.getFirstName() : "");
            view.getUpdateLastName().setText(user.getLastName() != null ? user.getLastName() : "");
            view.getUpdateEmail().setText(user.getEmail() != null ? user.getEmail() : "");
            view.getUpdateAddress().setText(user.getAddress() != null ? user.getAddress() : "");
            view.getUpdatePhoneNumber().setText(user.getContactNumber() != null ? user.getContactNumber() : "");
            
            // FIX: Debug what we set in form fields
            System.out.println("Set in form fields:");
            System.out.println("  FirstName field: '" + view.getUpdateFirstName().getText() + "'");
            System.out.println("  LastName field: '" + view.getUpdateLastName().getText() + "'");
            System.out.println("  Email field: '" + view.getUpdateEmail().getText() + "'");
            System.out.println("  Address field: '" + view.getUpdateAddress().getText() + "'");
            System.out.println("  Phone field: '" + view.getUpdatePhoneNumber().getText() + "'");
            
            System.out.println("Form fields populated successfully!");
        } else {
            System.err.println("ERROR: User not found for email: " + email);
            JOptionPane.showMessageDialog(view, 
                "User Profile not found for email: " + email + "\nPlease check your login.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("=== END INIT DEBUG ===");
    }
        
    public class OpenDashboard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Returning to dashboard");
            DashboardView dashboard = new DashboardView();
            DashboardController dashboardController = new DashboardController(dashboard, email);
            dashboardController.open();
            close();
        }
    }
        
    public class ChangeProfile implements ActionListener {
        private boolean isEditing = false;
        private UserData originalUser;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isEditing) {
                System.out.println("=== ENTERING EDIT MODE ===");
                
                // FIX: Use the stored currentUser data, not form fields
                if (currentUser != null) {
                    originalUser = new UserData(
                        currentUser.getFirstName(),
                        currentUser.getLastName(),
                        currentUser.getEmail(),      // FIX: Use original email
                        currentUser.getAddress(),
                        currentUser.getContactNumber()
                    );
                    System.out.println("Original user data stored:");
                    System.out.println("  Email: '" + originalUser.getEmail() + "'");
                    System.out.println("  Address: '" + originalUser.getAddress() + "'");
                } else {
                    System.err.println("ERROR: currentUser is null!");
                    return;
                }
                
                view.setFieldsEnabled(true);
                view.getUpdateEmail().setEnabled(false); // Email should not be editable
                isEditing = true;
                view.setChangeProfileButtonText("Save Changes");
                System.out.println("Edit mode enabled");
                
            } else {
                System.out.println("=== SAVING CHANGES ===");
                
                int response = JOptionPane.showConfirmDialog(view, 
                    "Do you want to save the changes?", 
                    "Confirm Save", 
                    JOptionPane.YES_NO_OPTION);
                    
                if (response == JOptionPane.YES_OPTION) {
                    System.out.println("User confirmed save");
                    
                    // FIX: Create updated user with ORIGINAL email, not from form
                    String updatedFirstName = view.getUpdateFirstName().getText().trim();
                    String updatedLastName = view.getUpdateLastName().getText().trim();
                    String updatedAddress = view.getUpdateAddress().getText().trim();
                    String updatedPhone = view.getUpdatePhoneNumber().getText().trim();
                    
                    System.out.println("Form values to save:");
                    System.out.println("  FirstName: '" + updatedFirstName + "'");
                    System.out.println("  LastName: '" + updatedLastName + "'");
                    System.out.println("  Address: '" + updatedAddress + "'");
                    System.out.println("  Phone: '" + updatedPhone + "'");
                    System.out.println("  Email (original): '" + email + "'");
                    
                    // FIX: Use the original email parameter, not from form
                    UserData updatedUser = new UserData(
                        updatedFirstName,
                        updatedLastName,
                        email,              // FIX: Use original email parameter
                        updatedAddress,
                        updatedPhone
                    );
                    
                    System.out.println("Created UserData object for update:");
                    updatedUser.printDebugInfo();
                    
                    UserProfileDao dao = new UserProfileDao();
                    if (dao.updateUser(updatedUser)) {
                        JOptionPane.showMessageDialog(view, 
                            "Profile updated successfully!", 
                            "Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Profile updated successfully");
                        
                        // Update currentUser with new data
                        currentUser = updatedUser;
                        originalUser = updatedUser;
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
        
        private void restoreOriginalValues() {
            if (originalUser != null) {
                view.getUpdateFirstName().setText(originalUser.getFirstName());
                view.getUpdateLastName().setText(originalUser.getLastName());
                view.getUpdateEmail().setText(originalUser.getEmail());
                view.getUpdateAddress().setText(originalUser.getAddress());
                view.getUpdatePhoneNumber().setText(originalUser.getContactNumber());
            }
        }
    }
    
    class ChangePass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Opening change password dialog with email: " + email);
            ChangePassword changePasswordView = new ChangePassword();
            ChangePasswordController changePassController = new ChangePasswordController(changePasswordView, email);
            changePassController.open();
            close();
        }
    }
    
    class Deleteaccount implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(view, 
                "Are you sure you want to delete your account?\nThis action cannot be undone!", 
                "Confirm Account Deletion", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
                
            if (response == JOptionPane.YES_OPTION) {
                System.out.println("Attempting to delete account for email: " + email);
                
                UserProfileDao dao = new UserProfileDao();
                
                // FIX: Use currentUser if available, otherwise create from email
                UserData userToDelete = currentUser;
                if (userToDelete == null) {
                    userToDelete = dao.getUserByEmail(email);
                }
                
                if (userToDelete == null) {
                    JOptionPane.showMessageDialog(view, 
                        "User not found! Cannot delete account.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    System.err.println("ERROR: User not found for deletion: " + email);
                    return;
                }
                
                if (dao.deleteAccount(userToDelete)) {
                    JOptionPane.showMessageDialog(view, 
                        "Account deleted successfully!", 
                        "Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Account deleted successfully");
                    
                    // Return to login screen
                    view.dispose();
                    LoginForm loginview = new LoginForm();
                    LoginController loginController = new LoginController(loginview);
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