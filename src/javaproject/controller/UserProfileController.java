package javaproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaproject.dao.UserProfileDao;
import javaproject.model.UserData;
import javaproject.view.AdminDashboardView;
import javaproject.view.ChangePassword;
import javaproject.view.LoginForm;
import javaproject.view.user;
import javax.swing.JOptionPane;

public class UserProfileController {
    private user view;
    private String email;

    public UserProfileController(user view, String email) {
        this.view = view;
        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Invalid email provided!", "Error", JOptionPane.ERROR_MESSAGE);
            view.dispose();
            return;
        }
        this.email = email;
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
    UserProfileDao dao = new UserProfileDao();
    System.out.println("Fetching user for email: " + email);
    UserData user = dao.getUserByEmail(email);
    
    if (user != null) {
        System.out.println("User found: FirstName=" + user.getFirstName() +
                          ", LastName=" + user.getLastName() +
                          ", Email=" + user.getEmail() +
                          ", Address=" + user.getAddress() +
                          ", ContactNumber=" + user.getContactNumber());
        view.getUpdateFirstName().setText(user.getFirstName() != null ? user.getFirstName() : "Not set");
        view.getUpdateLastName().setText(user.getLastName() != null ? user.getLastName() : "Not set");
        view.getUpdateEmail().setText(user.getEmail() != null ? user.getEmail() : "Not set");
        view.getUpdateAddress().setText(user.getAddress() != null ? user.getAddress() : "Not set");
        view.getUpdatePhoneNumber().setText(user.getContactNumber() != null ? user.getContactNumber() : "Not set");
    } else {
        System.err.println("User not found for email: " + email + ". Check database or email parameter.");
        JOptionPane.showMessageDialog(view, "User Profile not found!", "Error", JOptionPane.ERROR_MESSAGE);
        // view.dispose(); // Comment out to debug why page still loads
    }
}
        
    public class OpenDashboard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminDashboardView dashboard = new AdminDashboardView();
            SellerDashboardController dashboardController = new SellerDashboardController(dashboard, email);
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
                originalUser = new UserData(
                    view.getUpdateFirstName().getText(),
                    view.getUpdateLastName().getText(),
                    view.getUpdateEmail().getText(),
                    view.getUpdateAddress().getText(),
                    view.getUpdatePhoneNumber().getText(),
                    null, null, null
                );
                view.setFieldsEnabled(true);
                view.getUpdateEmail().setEnabled(false); // Email non-editable
                isEditing = true;
                view.setChangeProfileButtonText("Save Changes");
            } else {
                int response = JOptionPane.showConfirmDialog(view, "Save changes?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
                if (response == JOptionPane.OK_OPTION) {
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
                            JOptionPane.showMessageDialog(view, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            originalUser = updatedUser;
                        } else {
                            JOptionPane.showMessageDialog(view, "Failed to update profile!", "Error", JOptionPane.ERROR_MESSAGE);
                            if (originalUser != null) {
                                view.getUpdateFirstName().setText(originalUser.getFirstName());
                                view.getUpdateLastName().setText(originalUser.getLastName());
                                view.getUpdateEmail().setText(originalUser.getEmail());
                                view.getUpdateAddress().setText(originalUser.getAddress());
                                view.getUpdatePhoneNumber().setText(originalUser.getContactNumber());
                            }
                        }
                    }
                    view.setFieldsEnabled(false);
                    isEditing = false;
                    view.setChangeProfileButtonText("Update Profile");
                } else {
                    if (originalUser != null) {
                        view.getUpdateFirstName().setText(originalUser.getFirstName());
                        view.getUpdateLastName().setText(originalUser.getLastName());
                        view.getUpdateEmail().setText(originalUser.getEmail());
                        view.getUpdateAddress().setText(originalUser.getAddress());
                        view.getUpdatePhoneNumber().setText(originalUser.getContactNumber());
                    }
                    view.setFieldsEnabled(false);
                    isEditing = false;
                    view.setChangeProfileButtonText("Update Profile");
                }
            }
        }
    }
    
    class ChangePass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ChangePassword changePasswordView = new ChangePassword();
            ChangePasswordController changePassController = new ChangePasswordController(changePasswordView, email);
            changePassController.open();
            close();
        }
    }
    
    class Deleteaccount implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete your Account?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                UserProfileDao dao = new UserProfileDao();
                UserData userToDelete = dao.getUserByEmail(email);
                if (userToDelete == null) {
                    JOptionPane.showMessageDialog(view, "User not found or error retrieving profile!", "Error", JOptionPane.ERROR_MESSAGE);
                    System.err.println("Debug: getUserByEmail returned null for email: " + email);
                    return;
                }
                if (dao.deleteAccount(userToDelete)) {
                    JOptionPane.showMessageDialog(view, "Account deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    view.dispose();
                    LoginForm loginview = new LoginForm();
                    LoginController loginController = new LoginController(loginview);
                    loginController.open();
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to delete Account", "Error", JOptionPane.ERROR_MESSAGE);
                    System.err.println("Debug: deleteAccount failed for email: " + email);
                }
            }           
        }
    }
}