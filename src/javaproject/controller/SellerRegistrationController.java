/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import javaproject.view.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaproject.dao.UserDao;
import javaproject.model.UserData;
import javaproject.view.RegistrationView;
import javaproject.view.SellerRegistration;
import javax.swing.JOptionPane;
/**
 *
 * @author ACER
 */
public class SellerRegistrationController {
    private SellerRegistration registration;

    public SellerRegistrationController(SellerRegistration registration) {
        this.registration = registration;
        RegistrationUser register=new RegistrationUser();
        this.registration.sellerRegisterUser(register);
        BackLogin backLogin=new BackLogin();
        this.registration.backToLogin(backLogin);
        
    }

    class RegistrationUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String fullName = registration.getFullName().getText();
            String email = registration.getEmail().getText();
            String location=registration.getLocation().getText();
            String contactNumber = registration.getContactNumber().getText();
            String password = String.valueOf(registration.getPassword().getPassword());
            String rePassword = String.valueOf(registration.getRePassword().getPassword());
            String panNumber = registration.getPanNumber().getText();

            // Regex patterns
            String namePattern = "^[a-zA-Z]+$";
            String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
            String phoneNumberPattern = "^\\d{10}$";

            // Validate inputs using guard clauses
            if (fullName.isEmpty() || !fullName.matches(namePattern)) {
                JOptionPane.showMessageDialog(registration, "First name is required and must contain only letters.");
                return;
            }

            if (location.isEmpty()) {
                JOptionPane.showMessageDialog(registration, "Address is required.");
                return;
            }

            if (email.isEmpty() || !email.matches(emailPattern)) {
                JOptionPane.showMessageDialog(registration, "A valid email is required.");
                return;
            }

            if (contactNumber.isEmpty() || !contactNumber.matches(phoneNumberPattern)) {
                JOptionPane.showMessageDialog(registration, "Phone number must be exactly 10 digits.");
                return;
            }

            if (password.isEmpty() || password.length() < 6) {
                JOptionPane.showMessageDialog(registration, "Password must be at least 6 characters long.");
                return;
            }

            if (rePassword.isEmpty() || !password.equals(rePassword)) {
                JOptionPane.showMessageDialog(registration, "Passwords do not match.");
                return;
            }

            if (panNumber.isEmpty()) {
                JOptionPane.showMessageDialog(registration, "Security answer is required.");
                return;
            }

            // All validations passed
            JOptionPane.showMessageDialog(registration, "All fields are validated successfully!");

            UserData userData = new UserData(fullname, lastName, address, email, password, contactNumber, securityAnswer);

            try {
                boolean success = UserDao.registration(userData);

                if (success) {
                    JOptionPane.showMessageDialog(registration, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    LoginForm loginView = new LoginForm();
                    LoginController loginController = new LoginController(loginView);
                    loginController.open();
                    close();
                } else {
                    JOptionPane.showMessageDialog(registration, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(registration, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }
}
    class BackLogin implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           System.out.println("Navigating");
           LoginForm login=new LoginForm();
           LoginController loginController=new LoginController(login);
           loginController.open();
           close();
        }
        
    }
    public void open() {
        this.registration.setVisible(true);
    }

    public void close() {
        this.registration.dispose();
    }
}
