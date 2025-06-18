/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import LoginPage.ForgotPasswordForm;
import LoginPage.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaproject.dao.UserDao;
import javaproject.model.LoginRequest;
import javaproject.model.UserData;
import javaproject.view.DashboardView;
import javaproject.view.RegistrationView;
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
                JOptionPane.showMessageDialog(view, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
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
            view.dispose();
            ForgotPasswordForm forgotpassword = new ForgotPasswordForm();
            ForgotPasswordController forgotController = new ForgotPasswordController(forgotpassword);
            forgotController.open();
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
class Register implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            view.dispose();
            RegistrationView reisterView = new RegistrationView();
            RegistrationController registerController = new RegistrationController(reisterView);
            registerController.open();
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
    
}
