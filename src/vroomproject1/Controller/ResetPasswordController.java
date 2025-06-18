/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;

import vroomproject1.Dao.ResetDao;
import vroomproject1.Model.ResetData;
import vroomproject1.view.Reset;
import vroomproject1.Controller.Mail.SMTPSMailSender;

public class ResetPasswordController {

    private Reset view;
    private ResetDao dao;
    private String generatedOTP;

    public ResetPasswordController(Reset view) {
        this.view = view;
        this.dao = new ResetDao();

        this.view.sendOtpListener(new SendOTP());
        this.view.VerifyOtpListener(new VerifyOTP());
        this.view.resetUser(new ResetUser());
        this.view.loginBack(new BackToLogin());
    }

    class SendOTP implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmailTextField().getText().trim();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter your email.");
                return;
            }

            if (!dao.checkEmail(email)) {
                JOptionPane.showMessageDialog(null, "Email not registered.");
                return;
            }

            generatedOTP = String.format("%06d", new Random().nextInt(999999));
            if (dao.saveOtp(email, generatedOTP)) {
                boolean sent = SMTPSMailSender.sendMail(email, "Your OTP", "Your OTP is: " + generatedOTP);
                if (sent) {
                    JOptionPane.showMessageDialog(null, "OTP sent successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to send OTP.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to generate OTP.");
            }
        }
    }

    class VerifyOTP implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmailTextField().getText().trim();
            String otp = view.getOTPTextField().getText().trim();

            if (otp.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter the OTP.");
                return;
            }

            if (dao.verifyOtp(email, otp)) {
                JOptionPane.showMessageDialog(null, "OTP verified.");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid or expired OTP.");
            }
        }
    }

    class ResetUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmailTextField().getText().trim();
            String newPassword = new String(view.getNewPasswordField().getPassword());
            String confirmPassword = new String(view.getConfirmPasswordField().getPassword());

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match.");
                return;
            }

            if (newPassword.length() < 8) {
                JOptionPane.showMessageDialog(null, "Password must be at least 8 characters.");
                return;
            }

            ResetData data = new ResetData();
            data.setEmail(email);
            data.setNewPassword(newPassword);

            if (dao.resetPassword(data)) {
                dao.deleteOtp(email);
                JOptionPane.showMessageDialog(null, "Password reset successful.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to reset password.");
            }
        }
    }

    class BackToLogin implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Returning to login screen...");
            // Add code here if needed to switch views
        }
    }
}
