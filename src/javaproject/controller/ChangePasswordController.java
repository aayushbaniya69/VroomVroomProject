package javaproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaproject.dao.UserDao;
import javaproject.model.LoginRequest;
import javaproject.model.UserData;
import javaproject.view.ChangePassword;
import javaproject.view.DashboardView;
import javaproject.view.user;
import javax.swing.JOptionPane;

public class ChangePasswordController {
    private ChangePassword changePassword;
    private String email;
    private UserDao userDAO;
   
    public ChangePasswordController(ChangePassword changePassword, String email) {
        this.changePassword = changePassword;
        this.email = email;
        this.userDAO = new UserDao();
        
        System.out.println("ChangePasswordController initialized with email: " + email);
        
        // Set up toggle listeners (if you have show/hide password functionality)
        this.changePassword.getCurrentPassword().addActionListener(new ToggleOldPassword());
        this.changePassword.getNewPassword().addActionListener(new ToggleNewPassword());
        this.changePassword.getConfirmPassword().addActionListener(new ToggleConfirmPassword());
        
        if (email != null && !email.trim().isEmpty()) {
            changePassword.getEmail().setText(email);
        }
        
        UserProfileBack userProfileBack = new UserProfileBack();
        this.changePassword.BackToDashboard(userProfileBack);
        ChangePasswordAction changePasswordAction = new ChangePasswordAction();
        this.changePassword.ChangePassword(changePasswordAction);
    }
    
    public void open(){
        changePassword.setVisible(true);
    }
    
    public void close(){
        changePassword.dispose();
    }
    
    class UserProfileBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            user users = new user();
            UserProfileController userProfileController = new UserProfileController(users, email);
            userProfileController.open();
            close();
        }
    }
    
    class ChangePasswordAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("=== CHANGE PASSWORD ACTION ===");
            
            String currentEmail = changePassword.getEmail().getText().trim();
            String oldPassword = new String(changePassword.getCurrentPassword().getPassword()).trim();
            String newPassword = new String(changePassword.getNewPassword().getPassword()).trim();
            String confirmPassword = new String(changePassword.getConfirmPassword().getPassword()).trim();

            System.out.println("Email: '" + currentEmail + "'");
            System.out.println("Old Password Length: " + oldPassword.length());
            System.out.println("New Password Length: " + newPassword.length());
            System.out.println("Confirm Password Length: " + confirmPassword.length());

            // Validation
            if (currentEmail.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(changePassword, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(changePassword, "New password and confirm password do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newPassword.length() < 4) {
                JOptionPane.showMessageDialog(changePassword, "New password must be at least 4 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Verify current password
                System.out.println("Verifying current password...");
                LoginRequest loginRequest = new LoginRequest(currentEmail, oldPassword);
                UserData user = userDAO.login(loginRequest);
                
                if (user == null) {
                    JOptionPane.showMessageDialog(changePassword, "Current password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
                    System.err.println("Current password verification failed");
                    return;
                }

                System.out.println("Current password verified. Updating to new password...");
                
                // Update password
                boolean updated = userDAO.resetPassword(currentEmail, newPassword);
                if (updated) {
                    JOptionPane.showMessageDialog(changePassword, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Password change successful!");
                    
                    changePassword.dispose();
                    DashboardView dashboard = new DashboardView();
                    DashboardController dashboardController = new DashboardController(dashboard, currentEmail);
                    dashboardController.open();
                } else {
                    JOptionPane.showMessageDialog(changePassword, "Failed to change password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    System.err.println("Password change failed in database update");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(changePassword, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("Exception in password change: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
    
    class ToggleOldPassword implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean show = changePassword.getShowCurrentPassword().isSelected();
            changePassword.getCurrentPassword().setEchoChar(show ? (char) 0 : '•');
        }
    }
    
    class ToggleNewPassword implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean show = changePassword.getShowNewPassword().isSelected();
            changePassword.getNewPassword().setEchoChar(show ? (char) 0 : '•');
        }
    }
    
    class ToggleConfirmPassword implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean show = changePassword.getShowConfirmPassword().isSelected();
            changePassword.getConfirmPassword().setEchoChar(show ? (char) 0 : '•');
        }
    }
}