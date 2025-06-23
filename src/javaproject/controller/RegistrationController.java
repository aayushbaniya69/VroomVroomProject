package javaproject.controller;

import javaproject.view.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaproject.dao.UserDao;
import javaproject.model.UserData;
import javaproject.view.RegistrationView;
import javax.swing.JOptionPane;

public class RegistrationController {
    private RegistrationView view;

    public RegistrationController(RegistrationView registration) {
        this.view = registration;
        RegistrationUser register=new RegistrationUser();
        this.view.registeruser(register);
        BackLogin backLogin=new BackLogin();
        this.view.backLogin(backLogin);
        
    }

    class RegistrationUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = view.getFirstNameTextField().getText();
            String lastName = view.getLastNameTextField().getText();
            String address = view.getAddressTextField().getText();
            String email = view.getEmailTextField().getText();
            String contactNumber = view.getPhoneNumberTextField().getText();
            String password = String.valueOf(view.getPasswordTextField().getPassword());
            String rePassword = String.valueOf(view.getRePasswordTextField().getPassword());
            String securityAnswer = view.getSecurityAnswerTextField().getText();

            // Regex patterns
            String namePattern = "^[a-zA-Z]+$";
            String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
            String phoneNumberPattern = "^\\d{10}$";

            // Validate inputs using guard clauses
            if (firstName.isEmpty() || !firstName.matches(namePattern)) {
                JOptionPane.showMessageDialog(view, "First name is required and must contain only letters.");
                return;
            }

            if (lastName.isEmpty() || !lastName.matches(namePattern)) {
                JOptionPane.showMessageDialog(view, "Last name is required and must contain only letters.");
                return;
            }

            if (address.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Address is required.");
                return;
            }

            if (email.isEmpty() || !email.matches(emailPattern)) {
                JOptionPane.showMessageDialog(view, "A valid email is required.");
                return;
            }

            if (contactNumber.isEmpty() || !contactNumber.matches(phoneNumberPattern)) {
                JOptionPane.showMessageDialog(view, "Phone number must be exactly 10 digits.");
                return;
            }

            if (password.isEmpty() || password.length() < 6) {
                JOptionPane.showMessageDialog(view, "Password must be at least 6 characters long.");
                return;
            }

            if (rePassword.isEmpty() || !password.equals(rePassword)) {
                JOptionPane.showMessageDialog(view, "Passwords do not match.");
                return;
            }

            if (securityAnswer.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Security answer is required.");
                return;
            }

            // All validations passed
            JOptionPane.showMessageDialog(view, "All fields are validated successfully!");

            UserData userData = new UserData(firstName, lastName, address, email,contactNumber, password,rePassword, securityAnswer);

            try {
                UserDao userDao=new UserDao();
                boolean success = userDao.Registration(userData);

                if (success) {
                    JOptionPane.showMessageDialog(view, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    LoginForm loginView = new LoginForm();
                    LoginController loginController = new LoginController(loginView);
                    loginController.open();
                    close();
                } else {
                    JOptionPane.showMessageDialog(view, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }
} 
    class BackLogin implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
           System.out.println("Navigating");
           LoginForm login=new LoginForm();
           LoginController loginController=new LoginController(login);
           loginController.open();
           close();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
        
    }
    public void open() {
        this.view.setVisible(true);
    }

    public void close() {
        this.view.dispose();
    }
}

