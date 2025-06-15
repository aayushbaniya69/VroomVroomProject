package javaproject.controller;

import javaproject.view.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaproject.dao.UserDao;
import javaproject.model.UserData;
import javaproject.view.RegistrationView;
import javax.swing.JOptionPane;

public class RegistrationController {
    private RegistrationView registration;

    public RegistrationController(RegistrationView registration) {
        this.registration = registration;
        RegistrationUser register=new RegistrationUser();
        this.registration.registeruser(register);
        BackLogin backLogin=new BackLogin();
        this.registration.backLogin(backLogin);
        
    }

    class RegistrationUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = registration.getFirstNameTextField().getText();
            String lastName = registration.getLastNameTextField().getText();
            String address = registration.getAddressTextField().getText();
            String email = registration.getEmailTextField().getText();
            String contactNumber = registration.getPhoneNumberTextField().getText();
            String password = String.valueOf(registration.getPasswordTextField().getPassword());
            String rePassword = String.valueOf(registration.getRePasswordTextField().getPassword());
            String securityAnswer = registration.getSecurityAnswerTextField().getText();

            // Regex patterns
            String namePattern = "^[a-zA-Z]+$";
            String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
            String phoneNumberPattern = "^\\d{10}$";

            // Validate inputs using guard clauses
            if (firstName.isEmpty() || !firstName.matches(namePattern)) {
                JOptionPane.showMessageDialog(registration, "First name is required and must contain only letters.");
                return;
            }

            if (lastName.isEmpty() || !lastName.matches(namePattern)) {
                JOptionPane.showMessageDialog(registration, "Last name is required and must contain only letters.");
                return;
            }

            if (address.isEmpty()) {
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

            if (securityAnswer.isEmpty()) {
                JOptionPane.showMessageDialog(registration, "Security answer is required.");
                return;
            }

            // All validations passed
            JOptionPane.showMessageDialog(registration, "All fields are validated successfully!");

            UserData userData = new UserData(firstName, lastName, address, email, password, contactNumber, securityAnswer);

            try {
                UserDao userDao=new UserDao();
                boolean success = userDao.Registration(userData);

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

