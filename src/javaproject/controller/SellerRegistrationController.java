package javaproject.controller;

import javaproject.view.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaproject.dao.SellerDao;
import javaproject.model.SellerData;
import javaproject.view.SellerRegistration;
import javax.swing.JOptionPane;

public class SellerRegistrationController {
    private final SellerRegistration registration;

    public SellerRegistrationController(SellerRegistration registration) {
        this.registration = registration;

        // Attach action listeners
        RegistrationUser register=new RegistrationUser();
        this.registration.sellerRegisterUser(register);
        BackLogin backLogin=new BackLogin();
        this.registration.backToLogin(backLogin);
    }

    class RegistrationUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Fetch data from 
            String fullName = registration.getFullName().getText().trim();
            String email = registration.getEmail().getText().trim();
            String location = registration.getLocations().getText().trim();
            String contactNumber = registration.getContactNumber().getText().trim();
            String password = new String(registration.getPassword().getPassword()).trim();
            String rePassword = new String(registration.getRePassword().getPassword()).trim();
            String panNumber = registration.getPanNumber().getText().trim();

            // Regex patterns
            String namePattern = "^[a-zA-Z ]+$";
            String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
            String phoneNumberPattern = "^\\d{10}$";

            // Validate fields
            if (fullName.isEmpty() || !fullName.matches(namePattern)) {
                JOptionPane.showMessageDialog(registration, "Full name must contain only letters.");
                return;
            }

            if (location.isEmpty()) {
                JOptionPane.showMessageDialog(registration, "Location is required.");
                return;
            }

            if (email.isEmpty() || !email.matches(emailPattern)) {
                JOptionPane.showMessageDialog(registration, "A valid email is required.");
                return;
            }

            if (contactNumber.isEmpty() || !contactNumber.matches(phoneNumberPattern)) {
                JOptionPane.showMessageDialog(registration, "Contact number must be exactly 10 digits.");
                return;
            }

            if (password.isEmpty() || password.length() < 6) {
                JOptionPane.showMessageDialog(registration, "Password must be at least 6 characters long.");
                return;
            }

            if (!password.equals(rePassword)) {
                JOptionPane.showMessageDialog(registration, "Passwords do not match.");
                return;
            }

            if (panNumber.isEmpty()) {
                JOptionPane.showMessageDialog(registration, "PAN number is required.");
                return;
            }

            // All validations passed
            SellerData sellerData = new SellerData(fullName, email, location, contactNumber, password, panNumber);

            try {
                SellerDao sellerDao=new SellerDao();
                boolean success = sellerDao.sellerRegister(sellerData);

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

    class BackLogin implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginForm login = new LoginForm();
            LoginController loginController = new LoginController(login);
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
