/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Controller;

import java.awt.event.ActionEvent;
import vroomproject1.view.Reset;
import vroomproject1.Model.ResetData;
import java.awt.event.ActionListener;



public class ResetPasswordController {
    private Reset view;
    private ResetData model;
//    private String generatedOtp;
//    private ResetDao dao;
    private String generatedOtp = "";
    private boolean isOtpVerified =false;
    
    //constructor
    public ResetPasswordController(Reset view, ResetData model) {
        this.view = view;
        this.model = new ResetData();
//        this.dao=new ResetDao();
        
        //Attach listeners
        this.view.sendOtpListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sendOtp();
            }  

            private void sendOtp() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            
        });
        
        this.view.VerifyOtpListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyOtp();
            }

            private void verifyOtp() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            
        });
        
        this.view.loginBack(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPassword();
            } 
        });
    }
    private void resetPassword(){
        if(!isOtpVerified){
            view.showMessage("Please verify your otp first.");
            return;
        }
        
        String password = new String(view.getNewPasswordField().getPassword());
        String confirm = new String(view.getConfirmPasswordField().getPassword());
        String email = view.getEmailTextField().getText().trim();
        
        if (password.length() < 8){
            view.showMessage("Password must be at least 8 letters.");
            return;
        }
        if (!password.equals(confirm)){
            view.showMessage("Passwords do not match.");
            return;
        }
        
        boolean updated = model.resetData(email,password);
        if ((updated)){
            view.showMessage("Your password has been reset successfully.");
        } else {
            view.showMessage("Failed to reset password.");
        }
    }
        
    private void backToLogin(){
        view.showMessage("Going back to login.");
        //Implement back to login action
    }
    
}
    
//    ForgetUser resetUser= new ForgetUser();
//    this.view.resetUser(resetUser);
//    
//    LoginBack loginBack= new LoginBack();
//    this.view.loginBack(loginBack);
//    
//    this.view.sendOtpListener(new SendOtpHandler());
//    this.view.VerifyOtpListener(new VerifyOtpHandler());
//        
//    }
//    public void open(){
//        view.setVisible(true);
//    }
//    public void close(){
//        view.dispose();
//    }
//    
//    private class SendOtpHandler implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String email= view.getEmail().getText();
//            if
//        }
//    }
//    
//
////    SubmitOtp SendOtpHandler = new SubmitOtp();
////    this.view.SubmitOtp(SendOtpHandler);
////    
////    ResetAction resetPasswordHandler= new ResetAction();
////    this.view.resetPasswordAction(resetPasswordHandler);
//
//}
//    //Send Otp to email
//    class ForgetUser implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String email= view.getEmailTextField(.getText();
//            if (!dao.checkEmail(email)){
//                view.showMessage("Email not registered.");
//                return;
//            }
//            generatedOtp = dao.generateOtp(email);
//            view.showMessage("Otp has been sent to your email.");
//        }
//    }
//    //Verify OTP
//    class SubmitOtp implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String enteredOtp = view.getOtpsendButton();
//            String email = view.getEmail();
//            
//            boolean isValid = dao.verifyOtp(email, enteredOtp);
//            
//            if (isValid){
//                view.showMessage("Otp verified. Plese enter your new password.");
//            } else {
//                view.showMessage("Invalid OTP.");
//            }
//        }
//    }
//    //Reset Password
//    class ResetAction implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String email= view.getEmail();
//            String newPassword= view.getNewPassword();
//            String confirmPassword = view.getConfirmPassword();
//            
//            if (!newPassword.equals(confirmPassword)){
//                view.showMessage("Passwords do not match.");
//                return;
//            }
//            if (newPassword.length() < 8){
//                view.showMessage("Password must be at least 8 characters.");
//                return;
//            }
//            
//            ResetData data = new ResetData();
//            data.setEmail(email);
//            data.setNewPassword(newPassword);
//            
//            boolean success = dao.resetPassword(data);
//            
//            if (success){
//                dao.deleteOtp(email); //optional cleanup
//                view.showMessage("Password reset successful.");
//                
//            }else{
//                view.showMessage("Failed to reset Password.");
//            }
//        }
//    }
//    
//    //Back to login
//    class LoginBack implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            view.backToLogin();
//        }
//    }


