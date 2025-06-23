package vroomproject1.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;
import javaproject.controller.LoginController;
import vroomproject1.dao.UserDao;
import javaproject.view.LoginForm;


import vroomproject1.Controller.mail.SMTPSMailSender;
import vroomproject1.view.ResetView;

public class ResetPasswordController {

    private ResetView view;
    private String generatedOtp;
    private UserDao dao;

    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.\\w{2,}$";
    private static final String PASSWORD_REGEX = 
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    public ResetPasswordController(ResetView view) {
        this.view = view;
        this.dao = new UserDao();

        this.view.sendOtpListener(new SendOtpHandler());
        this.view.VerifyOtpListener(new VerifyOtpHandler());
        this.view.ResetPassword(new ResetHandler());
        this.view.loginBack(new LoginBackHandler());
        this.view.getNPshow().addActionListener(new ToggleNewPasswordVisibility());
        this.view.getCPshow().addActionListener(new ToggleConfirmPasswordVisibility());
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }

    private String generateOtp() {
        Random rand = new Random();
        int otp = 100000 + rand.nextInt(900000);
        return String.valueOf(otp);
    }

    // ========== Inner Classes ==========

    private class SendOtpHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmailTextField().getText().trim();
            String question = (String) view.getSecurityQuestionComboBox().getSelectedItem();
            String answer =  view.getSecurityAnswerTextField().getText().trim();


            if (email.isEmpty() || answer.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill both email and answer");
                return;
            }

            boolean isValid = dao.validateSecurityAnswer(email, question, answer);
            if (!isValid) {
                JOptionPane.showMessageDialog(view, "Security answer does not match.");
                return;
            }

            generatedOtp = generateOtp();
            String subject = "Your OTP for Resetting Password";
            String message = "Your OTP is: " + generatedOtp;

            boolean sent = SMTPSMailSender.sendMail(email, subject, message);
            if (sent) {
                JOptionPane.showMessageDialog(view, "OTP has been sent to your email.");
                view.enableOTPInputs();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to send OTP.");
            }
        }
    }

    private class VerifyOtpHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredOtp = view.getOTPTextField().getText().trim();

            if (enteredOtp.equals(generatedOtp)) {
                JOptionPane.showMessageDialog(view, "OTP verified successfully.");
                view.enablePasswordInputs();
            } else {
                JOptionPane.showMessageDialog(view, "Incorrect OTP.");
            }
        }
    }

    private class ResetHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmailTextField().getText().trim();
            String answer =  view.getSecurityAnswerTextField().getText().trim();
            String newPass = new String(view.getNewPasswordField().getPassword()).trim();
            String confirmPass = new String(view.getConfirmPasswordField().getPassword()).trim();

            if (email.isEmpty() || answer.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.matches(EMAIL_REGEX)) {
                JOptionPane.showMessageDialog(view, "Invalid email format.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPass.matches(PASSWORD_REGEX)) {
                JOptionPane.showMessageDialog(view,
                        "Password must be at least 8 characters with uppercase, lowercase, digit, and special character.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(view, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean updated = dao.updatePassword(email, newPass);
            if (updated) {
                JOptionPane.showMessageDialog(view, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new LoginController(new LoginForm()).open();
                close();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class LoginBackHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginController(new LoginForm()).open();
            close();
        }
    }

    private class ToggleNewPasswordVisibility implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean show = view.getNPshow().isSelected();
            view.getNewPasswordField().setEchoChar(show ? (char) 0 : '•');
        }
    }

    private class ToggleConfirmPasswordVisibility implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean show = view.getCPshow().isSelected();
            view.getConfirmPasswordField().setEchoChar(show ? (char) 0 : '•');
        }
    }
}
