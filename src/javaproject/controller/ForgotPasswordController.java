package javaproject.controller;

import javaproject.dao.UserDao;
import javaproject.dao.SellerDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javaproject.view.ResetView;
import javaproject.view.LoginForm;
import java.util.Random;

/**
 * Fixed ForgotPasswordController - Simplified password validation (min 6 characters)
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
        
        // Clear password fields
        view.getNewPasswordField().setText("");
        view.getConfirmPasswordField().setText("");
        
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
        view.setLocationRelativeTo(null);
    }

    public void close() {
        view.dispose();
    }
    
    private static final String email_Regex = "^[\\w.-]+@[\\w.-]+\\.\\w{2,}$";
    // ✅ SIMPLIFIED: Only check minimum length of 6 characters
    private static final String password_Regex = ".{6,}";

    private class SendOtpHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmailTextField().getText().trim();
            
            System.out.println("🔍 Checking email: " + email);
            
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter your email address", "Missing Email", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (!email.matches(email_Regex)) {
                JOptionPane.showMessageDialog(view, "Invalid email format", "Invalid Email", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Check if email exists in database
            boolean emailFound = checkEmailExists(email);
            
            if (!emailFound) {
                System.out.println("❌ Email not found: " + email);
                JOptionPane.showMessageDialog(view, 
                    "Email address not found in our system.\n\nPlease check your email or register first.", 
                    "Email Not Found", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Email found - generate OTP
            currentEmail = email;
            generatedOtp = generateOtp();
            
            // 🎯 DISPLAY OTP IN CONSOLE/OUTPUT PANEL
            System.out.println("=".repeat(50));
            System.out.println("📧 EMAIL FOUND: " + email);
            System.out.println("🔐 GENERATED OTP: " + generatedOtp);
            System.out.println("⏰ OTP is valid for 10 minutes");
            System.out.println("=".repeat(50));
            
            // Show success message to user
            JOptionPane.showMessageDialog(view, 
                "✅ Email found in system!\n\n" +
                "🔐 Your OTP: " + generatedOtp + "\n\n" +
                "⚠️ Check the console/output panel for your OTP\n" +
                "This OTP is valid for 10 minutes only.", 
                "OTP Generated Successfully", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Enable OTP input
            view.enableOTPInputs();
            
            // Disable email field
            view.getEmailTextField().setEnabled(false);
            view.getOtpsendButton().setEnabled(false);
        }
    }
    
    private class VerifyOtpHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredOtp = view.getOTPTextField().getText().trim();
            
            System.out.println("🔐 Verifying OTP: " + enteredOtp + " against: " + generatedOtp);
            
            if (enteredOtp.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter the OTP", "Missing OTP", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (enteredOtp.length() != 6) {
                JOptionPane.showMessageDialog(view, "OTP must be 6 digits", "Invalid OTP Length", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (enteredOtp.equals(generatedOtp)) {
                System.out.println("✅ OTP verified successfully!");
                JOptionPane.showMessageDialog(view, "✅ OTP verified successfully!\n\nYou can now set your new password.", "OTP Verified", JOptionPane.INFORMATION_MESSAGE);
                view.enablePasswordInputs();
                
                // Disable OTP fields
                view.getOTPTextField().setEnabled(false);
                view.getEnterButton().setEnabled(false);
                
                // Enable password fields and show buttons
                view.getNPshow().setEnabled(true);
                view.getCPshow().setEnabled(true);
            } else {
                System.out.println("❌ Invalid OTP entered: " + enteredOtp);
                JOptionPane.showMessageDialog(view, "❌ Incorrect OTP. Please try again.", "Invalid OTP", JOptionPane.ERROR_MESSAGE);
                view.getOTPTextField().setText(""); // Clear wrong OTP
            }
        }
    }
    
    private class ResetPasswordHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newPass = String.valueOf(view.getNewPasswordField().getPassword()).trim();
            String confirmpass = String.valueOf(view.getConfirmPasswordField().getPassword()).trim();

            if (newPass.isEmpty() || confirmpass.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill all password fields", "Missing Password", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ✅ SIMPLIFIED: Only check minimum length of 6 characters
            if (!newPass.matches(password_Regex)) {
                JOptionPane.showMessageDialog(view, 
                    "Password must be at least 6 characters long.", 
                    "Password Too Short", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPass.equals(confirmpass)) {
                JOptionPane.showMessageDialog(view, "Passwords do not match", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update password in appropriate table
            boolean updated = false;
            if (isUserAccount) {
                updated = userDao.resetPassword(currentEmail, newPass);
                System.out.println("🔄 Updating user password for: " + currentEmail);
            } else {
                updated = sellerDao.updatePassword(currentEmail, newPass);
                System.out.println("🔄 Updating seller password for: " + currentEmail);
            }

            if (updated) {
                System.out.println("✅ Password updated successfully for: " + currentEmail);
                JOptionPane.showMessageDialog(view, 
                    "✅ Password changed successfully!\n\nYou can now login with your new password.", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                LoginForm loginForm = new LoginForm();
                LoginController loginController = new LoginController(loginForm);
                loginController.open();
                close();
            } else {
                System.out.println("❌ Password update failed for: " + currentEmail);
                JOptionPane.showMessageDialog(view, 
                    "❌ Failed to update password. Please try again.", 
                    "Update Failed", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private String generateOtp() {
        Random rand = new Random();
        int otp = 100000 + rand.nextInt(900000);
        return String.valueOf(otp);
    }
    
    /**
     * Check if email exists in either User or Seller table
     */
    private boolean checkEmailExists(String email) {
        System.out.println("🔍 Checking email in User table: " + email);
        
        // Check in user table first
        if (userDao.checkEmail(email)) {
            isUserAccount = true;
            System.out.println("✅ Found in User table");
            return true;
        }
        
        System.out.println("🔍 Checking email in Seller table: " + email);
        
        // Check in seller table
        if (sellerDao.emailExists(email)) {
            isUserAccount = false;
            System.out.println("✅ Found in Seller table");
            return true;
        }
        
        System.out.println("❌ Email not found in either table");
        return false;
    }

    class LoginBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = JOptionPane.showConfirmDialog(
                view,
                "Are you sure you want to go back to login?\n\nAny progress will be lost.",
                "Confirm Back to Login",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (choice == JOptionPane.YES_OPTION) {
                LoginForm loginForm = new LoginForm();
                LoginController loginController = new LoginController(loginForm);
                loginController.open();
                ForgotPasswordController.this.close();
            }
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