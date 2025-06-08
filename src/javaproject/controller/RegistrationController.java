/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaproject.view.RegistrationView;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class RegistrationController {
    private RegistrationView registration = new RegistrationView();

    public RegistrationController(RegistrationView registration) {
        this.registration = registration;
        registration.RegistrationUser(new RegistrationUser());
    }

    public void open() {
        this.registration.setVisible(true);
    }

    public void close() {
        this.registration.dispose();
    }

    class RegistrationUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = registration.getFirstNameTextField().getText();
            String lastName = registration.getLastNameTextField().getText();
            String address = registration.getAddressTextField().getText();
            String email = registration.getEmailTextField().getText();
            String phoneNumber = registration.getPhoneNumberTextField().getText();
            String password = String.valueOf(registration.getPasswordTextField().getPassword());
            String rePassword = String.valueOf(registration.getRePasswordTextField().getPassword());
            String securityAnswer = registration.getSecurityAnswerTextField().getText();

            // Regex patterns
            String namePattern = "^[a-zA-Z]+$";
            String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
            String phoneNumberPattern = "^\\d{10}$";

            if (!firstName.isEmpty()) {
                if (firstName.matches(namePattern)) {
                    if (!lastName.isEmpty()) {
                        if (lastName.matches(namePattern)) {
                            if (!address.isEmpty()) {
                                if (!email.isEmpty()) {
                                    if (email.matches(emailPattern)) {
                                        if (!phoneNumber.isEmpty()) {
                                            if (phoneNumber.matches(phoneNumberPattern)) {
                                                if (!password.isEmpty()) {
                                                    if (password.length() >= 6) {
                                                        if (!rePassword.isEmpty()) {
                                                            if (password.equals(rePassword)) {
                                                                if (!securityAnswer.isEmpty()) {
                                                                    JOptionPane.showMessageDialog(registration, "All fields are validated successfully!");
                                                                    // Proceed to save data
                                                                } else {
                                                                    JOptionPane.showMessageDialog(registration, "Security answer is required.");
                                                                }
                                                            } else {
                                                                JOptionPane.showMessageDialog(registration, "Passwords do not match.");
                                                            }
                                                        } else {
                                                            JOptionPane.showMessageDialog(registration, "Please re-enter your password.");
                                                        }
                                                    } else {
                                                        JOptionPane.showMessageDialog(registration, "Password must be at least 6 characters long.");
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(registration, "Password is required.");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(registration, "Phone number must be exactly 10 digits and contain only numbers.");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(registration, "Phone number is required.");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(registration, "Invalid email format.");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(registration, "Email is required.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(registration, "Address is required.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(registration, "Last name should only contain letters.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(registration, "Last name is required.");
                    }
                } else {
                    JOptionPane.showMessageDialog(registration, "First name should only contain letters.");
                }
            } else {
                JOptionPane.showMessageDialog(registration, "First name is required.");
            }
        }
    }
}
