package javaproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaproject.dao.SellerDao;
import javaproject.model.SellerData;
import javaproject.view.SellerLoginForm;
import javaproject.view.SellerRegistration;
import javax.swing.JOptionPane;

public class SellerRegistrationController {
    private final SellerRegistration view;

    public SellerRegistrationController(SellerRegistration registration) {
        this.view = registration;

        // Attach action listeners
        RegistrationUser register=new RegistrationUser();
        this.view.sellerRegisterUser(register);
        BackLogin backLogin=new BackLogin();
        this.view.backToLogin(backLogin);
    }

    class RegistrationUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Fetch data from 
            String fullName = view.getFullName().getText().trim();
            String email = view.getEmail().getText().trim();
            String location = view.getLocations().getText().trim();
            String contactNumber = view.getContactNumber().getText().trim();
            String password =  String.valueOf(view.getPassword().getPassword());
            String rePassword = String.valueOf(view.getRePassword().getPassword());
            String panNumber = view.getPanNumber().getText().trim();

            // Regex patterns
            String namePattern = "^[a-zA-Z ]+$";
            String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
            String phoneNumberPattern = "^\\d{10}$";

            // Validate fields
            if (fullName.isEmpty() || !fullName.matches(namePattern)) {
                JOptionPane.showMessageDialog(view, "Full name must contain only letters.");
                return;
            }

            if (location.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Location is required.");
                return;
            }

            if (email.isEmpty() || !email.matches(emailPattern)) {
                JOptionPane.showMessageDialog(view, "A valid email is required.");
                return;
            }

            if (contactNumber.isEmpty() || !contactNumber.matches(phoneNumberPattern)) {
                JOptionPane.showMessageDialog(view, "Contact number must be exactly 10 digits.");
                return;
            }

            if (password.isEmpty() || password.length() < 6) {
                JOptionPane.showMessageDialog(view, "Password must be at least 6 characters long.");
                return;
            }

            if (!password.equals(rePassword)) {
                JOptionPane.showMessageDialog(view, "Passwords do not match.");
                return;
            }

            if (panNumber.isEmpty()) {
                JOptionPane.showMessageDialog(view, "PAN number is required.");
                return;
            }

            // All validations passed
            SellerData sellerData = new SellerData(fullName, email, location, contactNumber, password,rePassword, panNumber);

            try {
                System.out.println("hello");
                SellerDao sellerDao=new SellerDao();
                boolean success = sellerDao.registerSeller(sellerData);

                if (success) {
                    JOptionPane.showMessageDialog(view, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    SellerLoginForm loginView = new SellerLoginForm();
                    SellerLoginController sellerLoginController = new SellerLoginController(loginView);
                    sellerLoginController.open();
                    close();
                } else {
                    JOptionPane.showMessageDialog(view, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class BackLogin implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SellerLoginForm login = new SellerLoginForm();
            SellerLoginController loginController = new SellerLoginController(login);
            loginController.open();
            close();
        }
    }

    public void open() {
        this.view.setVisible(true);
    }

    public void close() {
        this.view.dispose();
    }
}
