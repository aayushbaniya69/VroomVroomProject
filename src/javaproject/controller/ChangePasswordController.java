/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author ACER
 */
public class ChangePasswordController {
    private ChangePassword changePassword;
    private String email;
    private UserDao userDAO;
   
    public ChangePasswordController(ChangePassword changePassword, String email) {
    this.changePassword = changePassword;
    this.email = email;
    this.userDAO = new UserDao(); // Initialize UserDao
    
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
            String currentEmail = changePassword.getEmail().getText().trim();
            String oldPassword = new String(changePassword.getCurrentPassword().getPassword()).trim();
            String newPassword = new String(changePassword.getNewPassword().getPassword()).trim();
            String confirmPassword = new String(changePassword.getConfirmPassword().getPassword()).trim();

            if (currentEmail.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(changePassword, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(changePassword, "New password and confirm password do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                LoginRequest loginRequest = new LoginRequest(currentEmail, oldPassword);
                UserData user = userDAO.login(loginRequest);
                if (user == null) {
                    JOptionPane.showMessageDialog(changePassword, "Invalid email or old password.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean updated = userDAO.resetPassword(currentEmail,newPassword);
                if (updated) {
                    JOptionPane.showMessageDialog(changePassword, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    changePassword.dispose();
                    DashboardView dashboard=new DashboardView();
                    DashboardController dashboardController = new DashboardController(dashboard,currentEmail);
                    dashboardController.open();
                } else {
                    JOptionPane.showMessageDialog(changePassword, "Failed to change password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(changePassword, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    class ToggleOldPassword implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean show=changePassword.getShowCurrentPassword().isSelected();
            changePassword.getCurrentPassword().setEchoChar(show ? (char) 0 : '•');

        }
        
    }
    
    class ToggleNewPassword implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean show=changePassword.getShowNewPassword().isSelected();
            changePassword.getNewPassword().setEchoChar(show ? (char) 0 : '•');

        }
        
    }
    
    class ToggleConfirmPassword implements ActionListener{
         @Override
        public void actionPerformed(ActionEvent e) {
            boolean show=changePassword.getShowConfirmPassword().isSelected();
            changePassword.getConfirmPassword().setEchoChar(show ? (char) 0 : '•');

        }
        
    }
}
