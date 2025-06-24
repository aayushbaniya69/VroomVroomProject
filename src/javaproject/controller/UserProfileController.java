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

    public UserProfileController(user view, String email) {
        this.view = view;
        
        // FIX: Better email validation
        if (email == null || email.trim().isEmpty()) {
            System.err.println("ERROR: Invalid email provided to UserProfileController");
            JOptionPane.showMessageDialog(view, "Invalid email provided!", "Error", JOptionPane.ERROR_MESSAGE);
            view.dispose();
            return;
        }
        
        this.email = email.trim(); // Remove any whitespace
        System.out.println("UserProfileController initialized with email: " + this.email);
        
        // Initialize the profile data
        init();

        // Set up event listeners
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
    
    // FIX: Enhanced init method with better error handling
    public void init() {
        System.out.println("Initializing user profile for email: " + email);
        
        UserProfileDao dao = new UserProfileDao();
        UserData user = dao.getUserByEmail(email);
        
        if (user != null) {
            System.out.println("User found successfully!");
            System.out.println("FirstName: " + user.getFirstName());
            System.out.println("LastName: " + user.getLastName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Address: " + user.getAddress());
            System.out.println("ContactNumber: " + user.getContactNumber());
            
            // FIX: Better null handling for form fields
            view.getUpdateFirstName().setText(user.getFirstName() != null ? user.getFirstName() : "");
            view.getUpdateLastName().setText(user.getLastName() != null ? user.getLastName() : "");
            view.getUpdateEmail().setText(user.getEmail() != null ? user.getEmail() : "");
            view.getUpdateAddress().setText(user.getAddress() != null ? user.getAddress() : "");
            view.getUpdatePhoneNumber().setText(user.getContactNumber() != null ? user.getContactNumber() : "");
            
            System.out.println("Form fields populated successfully!");
        } else {
            System.err.println("ERROR: User not found for email: " + email);
            JOptionPane.showMessageDialog(view, 
                "User Profile not found for email: " + email + "\nPlease check your login.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
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
                // Start editing mode
                System.out.println("Entering edit mode");
                originalUser = new UserData(
                    view.getUpdateFirstName().getText(),
                    view.getUpdateLastName().getText(),
                    view.getUpdateEmail().getText(),
                    view.getUpdateAddress().getText(),
                    view.getUpdatePhoneNumber().getText(),
                    null, null, null
                );
                view.setFieldsEnabled(true);
                view.getUpdateEmail().setEnabled(false); // Email should not be editable
                isEditing = true;
                view.setChangeProfileButtonText("Save Changes");
                System.out.println("Edit mode enabled");
            } else {
                // Save changes mode
                int response = JOptionPane.showConfirmDialog(view, 
                    "Do you want to save the changes?", 
                    "Confirm Save", 
                    JOptionPane.YES_NO_OPTION);
                    
                if (response == JOptionPane.YES_OPTION) {
                    System.out.println("Saving profile changes");
                    
                    // Check if there are any changes
                    boolean hasChanges = !view.getUpdateFirstName().getText().equals(originalUser.getFirstName()) ||
                                        !view.getUpdateLastName().getText().equals(originalUser.getLastName()) ||
                                        !view.getUpdateAddress().getText().equals(originalUser.getAddress()) ||
                                        !view.getUpdatePhoneNumber().getText().equals(originalUser.getContactNumber());

                    if (hasChanges) {
                        UserData updatedUser = new UserData(
                            view.getUpdateFirstName().getText(),
                            view.getUpdateLastName().getText(),
                            view.getUpdateEmail().getText(),
                            view.getUpdateAddress().getText(),
                            view.getUpdatePhoneNumber().getText(),
                            null, null, null
                        );
                        
                        UserProfileDao dao = new UserProfileDao();
                        if (dao.updateUser(updatedUser)) {
                            JOptionPane.showMessageDialog(view, 
                                "Profile updated successfully!", 
                                "Success", 
                                JOptionPane.INFORMATION_MESSAGE);
                            System.out.println("Profile updated successfully");
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
                    } else {
                        System.out.println("No changes detected");
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
            System.out.println("Opening change password dialog");
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
                UserData userToDelete = dao.getUserByEmail(email);
                
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