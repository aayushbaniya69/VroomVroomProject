package javaproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaproject.dao.SellerDao;
import javaproject.model.SellerData;
import javaproject.view.LoginForm;
import javaproject.view.SellerRegistration;
import javax.swing.JOptionPane;

public class SellerRegistrationController {
    private final SellerRegistration view;

    public SellerRegistrationController(SellerRegistration registration) {
        this.view = registration;

        // Attach action listeners
        RegistrationUser register = new RegistrationUser();
        this.view.sellerRegisterUser(register);
        BackLogin backLogin = new BackLogin();
        this.view.backToLogin(backLogin);
    }

    class RegistrationUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("=== SELLER REGISTRATION CONTROLLER ===");
            
            // Fetch data from form
            String fullName = view.getFullName().getText().trim();
            String email = view.getEmail().getText().trim();
            String location = view.getLocations().getText().trim();
            String contactNumber = view.getContactNumber().getText().trim();
            String password = String.valueOf(view.getPassword().getPassword());
            String rePassword = String.valueOf(view.getRePassword().getPassword());
            String panNumber = view.getPanNumber().getText().trim();

            System.out.println("Form data collected:");
            System.out.println("  FullName: '" + fullName + "'");
            System.out.println("  Email: '" + email + "'");
            System.out.println("  Location: '" + location + "'");
            System.out.println("  ContactNumber: '" + contactNumber + "'");
            System.out.println("  PanNumber: '" + panNumber + "'");
            System.out.println("  Password length: " + password.length());

            // Regex patterns
            String namePattern = "^[a-zA-Z ]+$";
            String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
            String phoneNumberPattern = "^\\d{10}$";
            String panPattern = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$"; // Standard PAN format

            // Validate fields
            if (fullName.isEmpty() || !fullName.matches(namePattern)) {
                JOptionPane.showMessageDialog(view, "Full name must contain only letters and spaces.");
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

            // Optional: Validate PAN format
            if (!panNumber.matches(panPattern)) {
                int choice = JOptionPane.showConfirmDialog(view, 
                    "PAN number format seems incorrect. Standard format is ABCDE1234F.\nDo you want to continue anyway?", 
                    "PAN Format Warning", 
                    JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            // Check if email already exists
            SellerDao sellerDao = new SellerDao();
            if (sellerDao.emailExists(email)) {
                JOptionPane.showMessageDialog(view, "Email already exists. Please use a different email.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // All validations passed - create SellerData with 7 parameters
            SellerData sellerData = new SellerData(fullName, email, location, contactNumber, password, rePassword, panNumber);

            System.out.println("SellerData object created:");
            System.out.println("  FullName: '" + sellerData.getFullName() + "'");
            System.out.println("  Email: '" + sellerData.getEmail() + "'");
            System.out.println("  Location: '" + sellerData.getLocation() + "'");
            System.out.println("  ContactNumber: '" + sellerData.getContactNumber() + "'");
            System.out.println("  PanNumber: '" + sellerData.getPanNumber() + "'");

            try {
                System.out.println("Attempting to register seller...");
                boolean success = sellerDao.registerSeller(sellerData);

                if (success) {
                    JOptionPane.showMessageDialog(view, "Registration Successful!\nPAN Number: " + panNumber, "Success", JOptionPane.INFORMATION_MESSAGE);
                    LoginForm loginView = new LoginForm();
                    LoginController sellerLoginController = new LoginController(loginView);
                    sellerLoginController.open();
                    close();
                } else {
                    JOptionPane.showMessageDialog(view, "Registration failed! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.err.println("Exception during registration: " + ex.getMessage());
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        this.view.setVisible(true);
    }

    public void close() {
        this.view.dispose();
    }
}