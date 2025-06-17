/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Controller;


import vroomproject1.view.Reset;

import vroomproject1.Dao.ResetDao;
import vroomproject1.Model.ResetData;

public class ResetPasswordController {
    Reset view;

    public ResetPasswordController(Reset view) {
         ResetDao resetDao = new ResetDao();

    // Send OTP to user email
    public String sendOtpToEmail(String email) {
        boolean emailExists = resetDao.checkEmail(email);
        if (!emailExists) {
            return "Email not registered.";
        }

        String otp = resetDao.generateOtp();

        // Save OTP to DB
        boolean saved = resetDao.saveOtp(email, otp);
        if (saved) {
            // TODO: Replace this with actual email sending logic (e.g., JavaMail)
            System.out.println("OTP for " + email + " is: " + otp); // For testing
            return "OTP sent to your email.";
        } else {
            return "Failed to send OTP. Try again.";
        }
    }

    // Verify the OTP entered by user
    public boolean verifyOtp(String email, String enteredOtp) {
        return resetDao.verifyOtp(email, enteredOtp);
    }

    // Reset the password
    public String resetPassword(String email, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            return "Passwords do not match.";
        }

        if (newPassword.length() < 8) {
            return "Password must be at least 8 characters.";
        }

        ResetData resetReq = new ResetData();
        resetReq.setEmail(email);
        resetReq.setPassword(newPassword);

        boolean success = restDao.resetPassword(resetReq);
        if (success) {
            ResetDao.deleteOtp(email); // Clean up OTP
            return "Password reset successful.";
        } else {
            return "Failed to reset password.";
        }
    }
}

