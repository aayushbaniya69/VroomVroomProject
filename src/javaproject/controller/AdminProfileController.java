/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaproject.dao.AdminProfileDao;
import javaproject.model.SellerData;
import javaproject.view.AdminDashboardView;
import javaproject.view.ChangePassword;
import javaproject.view.LoginForm;
import javax.swing.JOptionPane;
import javaproject.view.Adminpage;

/**
 *
 * @author ACER
 */
public class AdminProfileController {
    private Adminpage view;
    private String email; // Email is the identifier

    public AdminProfileController(Adminpage view, String email) {
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

    AdminProfileController(Adminpage view) {
        this.view = view;
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }
    
    public void init() {
        AdminProfileDao dao = new AdminProfileDao();
        System.out.println("Fetching user for email: " + email);
        SellerData seller = dao.getUserByEmail(email);
        
        if (seller != null) {
            System.out.println("User found: " + seller.getEmail());
            view.getUpdateFullName().setText(seller.getFullName() != null ? seller.getFullName() : "");
            view.getUpdateEmail().setText(seller.getEmail() != null ? seller.getEmail() : "");
            view.getUpdateAddress().setText(seller.getLocation() != null ? seller.getLocation() : "");
            view.getUpdatePhoneNumber().setText(seller.getContactNumber() != null ? seller.getContactNumber() : "");    
        } else {
            System.err.println("User not found for email: " + email);
            JOptionPane.showMessageDialog(view, "User Profile not found!", "Error", JOptionPane.ERROR_MESSAGE);
            view.dispose();
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
        private SellerData originalUser;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isEditing) {
                originalUser = new SellerData(
                    view.getUpdateFullName().getText(),
                    view.getUpdateEmail().getText(),
                    view.getUpdateAddress().getText(),
                    view.getUpdatePhoneNumber().getText()
                );
                view.setFieldsEnabled(true);
                isEditing = true;
                view.setChangeProfileButtonText("Save Changes");
            } else {
                int response = JOptionPane.showConfirmDialog(view, "Save changes?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
                if (response == JOptionPane.OK_OPTION) {
                    boolean hasChanges = !view.getUpdateFullName().getText().equals(originalUser.getFullName()) ||
                                        !view.getUpdateEmail().getText().equals(originalUser.getEmail()) ||
                                        !view.getUpdateAddress().getText().equals(originalUser.getLocation()) ||
                                        !view.getUpdatePhoneNumber().getText().equals(originalUser.getContactNumber());

                    if (hasChanges) {
                        SellerData updatedUser = new SellerData(
                            view.getUpdateFullName().getText(),
                            view.getUpdateEmail().getText(),
                            view.getUpdateAddress().getText(),
                            view.getUpdatePhoneNumber().getText()
                        );
                        AdminProfileDao dao = new AdminProfileDao();
                        try {
                            if (dao.updateUser(updatedUser)) {
                                JOptionPane.showMessageDialog(view, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                originalUser = updatedUser;
                                email = updatedUser.getEmail();
                            } else {
                                JOptionPane.showMessageDialog(view, "Failed to update profile!", "Error", JOptionPane.ERROR_MESSAGE);
                                if (originalUser != null) {
                                    view.getUpdateFullName().setText(originalUser.getFullName());
                                    view.getUpdateEmail().setText(originalUser.getEmail());
                                    view.getUpdateAddress().setText(originalUser.getLocation());
                                    view.getUpdatePhoneNumber().setText(originalUser.getContactNumber());
                                }
                            }
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(AdminProfileController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    view.setFieldsEnabled(false);
                    isEditing = false;      
                    view.setChangeProfileButtonText("Change Profile");
                } else {
                    if (originalUser != null) {
                        view.getUpdateFullName().setText(originalUser.getFullName());
                        view.getUpdateEmail().setText(originalUser.getEmail());
                        view.getUpdateAddress().setText(originalUser.getLocation());
                        view.getUpdatePhoneNumber().setText(originalUser.getContactNumber());
                    }
                    view.setFieldsEnabled(false);
                    isEditing = false;
                    view.setChangeProfileButtonText("Change Profile");
                }
            }
        }
    }
    
    class ChangePass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            ChangePassword changePasswordView = new ChangePassword();
            ChangePasswordController changePassController = new ChangePasswordController(changePasswordView, email);
            changePassController.open();
        }
    }
    
    class Deleteaccount implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete your Account?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                AdminProfileDao dao = new AdminProfileDao();
                SellerData userToDelete = dao.getUserByEmail(email);
                if (userToDelete == null) {
                    JOptionPane.showMessageDialog(view, "User not found or error retrieving profile!", "Error", JOptionPane.ERROR_MESSAGE);
                    System.err.println("Debug: getUserByEmail returned null for email: " + email);
                    return;
                }
                try {
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
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdminProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }           
        }
    }
}