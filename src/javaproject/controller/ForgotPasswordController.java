/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import javaproject.controller.mail.SMTPSMailSender;
import javaproject.dao.UserDao;
import javaproject.dao.SellerDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javaproject.view.ResetView;
import javaproject.view.LoginForm;
import java.util.Random;

/**
 *
 * @author ACER
 */
public class ForgotPasswordController {
    private ResetView view;
    private String generatedOtp;
    private UserDao userDao;
    private SellerDao sellerDao;
    private String currentEmail;
    private boolean isUserAccount;

    public ForgotPasswordController(ResetView view) {
        this.view = view;
        this.userDao = new UserDao();
        this.sellerDao = new SellerDao();
        
        // Initially disable OTP and password fields
        view.getOTPTextField().setEnabled(false);
        view.getEnterButton().setEnabled(false);
        view.getNewPasswordField().setEnabled(false);
        view.getConfirmPasswordField().setEnabled(false);
        view.getResetPasswordButton().setEnabled(false);
        view.getNPshow().setEnabled(false);
        view.getCPshow().setEnabled(false);
        
        // Attach listeners
        view.sendOtpListener(new SendOtpHandler());
        view.VerifyOtpListener(new VerifyOtpHandler());
        view.resetUser(new ResetPasswordHandler());
        view.loginBack(new LoginBack());
        
        // Password toggle listeners
        view.addPasswordToggleListeners(new ToggleNewPassword(), new ToggleConfirmPassword());
    }
    
    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }
    
    private static final String email_Regex = "^[\\w.-]+@[\\w.-]+\\.\\w{2,}$";
    private static final String password_Regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    private class SendOtpHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmailTextField().getText().trim();
            String security_question = (String) view.getSecurityQuestionComboBox().getSelectedItem();
            String security_answer = view.getSecurityAnswerTextField().getText().trim();
            
            if (email.isEmpty() || security_answer.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill both email and security answer");
                return;
            }
            
            if (!email.matches(email_Regex)) {
                JOptionPane.showMessageDialog(view, "Invalid email format");
                return;
            }
            
            // Check if email exists and validate security answer
            boolean isValid = validateSecurityAnswer(email, security_question, security_answer);
            
            if (!isValid) {
                JOptionPane.showMessageDialog(view, "Email not found or security answer doesn't match");
                return;
            }
            
            currentEmail = email;
            generatedOtp = generateOtp();
            String subject = "Your OTP for Resetting Password - BuySmart";
            String message = "Dear User,\n\nYour OTP for password reset is: " + generatedOtp + 
                           "\n\nThis OTP is valid for 10 minutes only.\nPlease do not share this OTP with anyone." +
                           "\n\nIf you did not request this, please ignore this email." +
                           "\n\nBest regards,\nBuySmart Team";
            
            boolean sent = SMTPSMailSender.send(email, subject, message);
            if (sent) {
                JOptionPane.showMessageDialog(view, "OTP has been sent to your email");
                view.enableOTPInputs();
                
                // Disable email and security fields
                view.getEmailTextField().setEnabled(false);
                view.getSecurityQuestionComboBox().setEnabled(false);
                view.getSecurityAnswerTextField().setEnabled(false);
                view.getOtpsendButton().setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(view, "Failed to send OTP. Please try again.");
            }
        }
    }
    
    private class VerifyOtpHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredOtp = view.getOTPTextField().getText().trim();
            
            if (enteredOtp.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter the OTP");
                return;
            }
            
            if (enteredOtp.length() != 6) {
                JOptionPane.showMessageDialog(view, "OTP must be 6 digits");
                return;
            }
            
            if (enteredOtp.equals(generatedOtp)) {
                JOptionPane.showMessageDialog(view, "OTP verified successfully!");
                view.enablePasswordInputs();
                
                // Disable OTP fields
                view.getOTPTextField().setEnabled(false);
                view.getEnterButton().setEnabled(false);
                
                // Enable password fields and show buttons
                view.getNPshow().setEnabled(true);
                view.getCPshow().setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(view, "Incorrect OTP. Please try again.");
            }
        }
    }
    
    private class ResetPasswordHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newPass = String.valueOf(view.getNewPasswordField().getPassword()).trim();
            String confirmpass = String.valueOf(view.getConfirmPasswordField().getPassword()).trim();

            if (newPass.isEmpty() || confirmpass.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill all password fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPass.matches(password_Regex)) {
                JOptionPane.showMessageDialog(view, "Password must be 8+ characters with uppercase, lowercase, digit, and special character", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPass.equals(confirmpass)) {
                JOptionPane.showMessageDialog(view, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update password in appropriate table
            boolean updated = false;
            if (isUserAccount) {
                updated = userDao.resetPassword(currentEmail, newPass);
            } else {
                updated = sellerDao.resetPassword(currentEmail, newPass);
            }

            if (updated) {
                JOptionPane.showMessageDialog(view, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                LoginForm loginForm = new LoginForm();
                LoginController loginController = new LoginController(loginForm);
                loginController.open();
                close();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to update password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private String generateOtp() {
        Random rand = new Random();
        int otp = 100000 + rand.nextInt(900000);
        return String.valueOf(otp);
    }
    
    private boolean validateSecurityAnswer(String email, String question, String answer) {
        // Check in user table first
        if (userDao.validateSecurityAnswer(email, question, answer)) {
            isUserAccount = true;
            return true;
        }
        
        // Check in seller table (for sellers, we'll just check if email exists)
        if (sellerDao.validateSecurityAnswer(email, question, answer)) {
            isUserAccount = false;
            return true;
        }
        
        return false;
    }

    class LoginBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginForm loginForm = new LoginForm();
            LoginController loginController = new LoginController(loginForm);
            loginController.open();
            ForgotPasswordController.this.close();
        }
    }
    
    class ToggleNewPassword implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean show = view.getNPshow().getText().equals("Hide");
            if (show) {
                view.getNewPasswordField().setEchoChar('•');
                view.getNPshow().setText("Show");
            } else {
                view.getNewPasswordField().setEchoChar((char) 0);
                view.getNPshow().setText("Hide");
            }
        }
    }
    
    class ToggleConfirmPassword implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean show = view.getCPshow().getText().equals("Hide");
            if (show) {
                view.getConfirmPasswordField().setEchoChar('•');
                view.getCPshow().setText("Show");
            } else {
                view.getConfirmPasswordField().setEchoChar((char) 0);
                view.getCPshow().setText("Hide");
            }
        }
    }
}