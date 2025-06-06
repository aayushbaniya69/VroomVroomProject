/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import LoginPage.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaproject.controller.mail.SMTPSMailSender;
import javaproject.dao.UserDao;
import javaproject.model.LoginRequest;
import javaproject.model.ResetPasswordRequest;
import javaproject.model.UserData;
import javaproject.view.DashboardView;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class LoginController {
    LoginForm view=new LoginForm();
    public LoginController(LoginForm view){
        this.view=view;
        LoginUser loginUser=new LoginUser();
        this.view.LoginUser(loginUser);
        ResetPassword forgotPassword=new ResetPassword();
        this.view.forgotPassword(forgotPassword) ;
    }
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    class LoginUser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        String email=view.getEmailTextField().getText();
        String password=String.valueOf(view.getPasswordField().getPassword());
        if(email.isEmpty()||password.isEmpty()){
            JOptionPane.showMessageDialog(view,"Fill all the field.");
        }
        else{
            LoginRequest loginData=new LoginRequest(email,password);
            UserDao userDao=new UserDao();
            UserData user=userDao.login(loginData);
            if(user==null){
                JOptionPane.showMessageDialog(view,"Login failed");
            }
            else{
                DashboardView dashboardView=new DashboardView();
                DashboardController dashboardController=new DashboardController(dashboardView,user);
                dashboardController.open();
                close();
            }
        }
    }
    }
         
class ResetPassword implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        String email=JOptionPane.showInputDialog(view,"Enter your email");
            if(email.trim().isEmpty()){
                JOptionPane.showMessageDialog(view,"Email field was empty");
            }
            else{
                // check the email exist or not
                UserDao userDao=new UserDao();
                boolean emailExists=userDao.checkEmail(email);
                if(!emailExists){
                    JOptionPane.showMessageDialog(view,"User does not exist");
            }
                else{
                    String otp="321456";
                    String title ="Reset Password Verification";
                    String body="The otp to reset your password is "+otp;
                    // send the otp in given email 
                    boolean emailSent=SMTPSMailSender.sendMail(email,title,body);
                    if(!emailSent){
                        JOptionPane.showMessageDialog(view, "Failed to sent opt. Try again later.");
                    }
                    else{
                        //Give the box to inpu the otp
                        String receivedOtp=JOptionPane.showInputDialog(view,"Enter the otp");
                        if(receivedOtp.trim().isEmpty()|| !otp.equals(receivedOtp)){
                            JOptionPane.showMessageDialog(view, "Invalid otp");
                        }
                        else{
                            //If otp is matched then give the field to input new password
                            String newPassword=JOptionPane.showInputDialog(view,"Enter the new password");
                            if(newPassword.trim().isEmpty()){
                                JOptionPane.showMessageDialog(view,"Password field was empty");
                            }
                            else{
                                //create object of resetpassord and run query of that class 
                                ResetPasswordRequest reset=new ResetPasswordRequest(email,newPassword);
                                boolean passwordChanged=userDao.resetPassword(reset);
                                if(!passwordChanged){
                                    JOptionPane.showMessageDialog(view,"Failed to reset password");
                                }
                                else{
                                    JOptionPane.showMessageDialog(view, "Password has been changed");
                                }
                            }
                        }
                    }
                }
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
}