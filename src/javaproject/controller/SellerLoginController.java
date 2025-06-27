/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaproject.dao.SellerDao;
import javaproject.model.LoginRequest;
import javaproject.model.SellerData;
import javaproject.view.AdminDashboardView;
import javaproject.view.LoginForm;
import javaproject.view.ResetView;
import javaproject.view.SellerRegistration;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class SellerLoginController {
    LoginForm view=new LoginForm();
    public SellerLoginController(LoginForm view){
        this.view=view;
        LoginSeller loginUser=new LoginSeller();
        this.view.LoginUser(loginUser);
        ForgotPassword forgotPassword=new ForgotPassword();
        this.view.forgotPassword(forgotPassword) ;
        Register registerPage=new Register();
        this.view.registerPage(registerPage);
    }
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    class LoginSeller implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        String email=view.getEmailTextField().getText();
        String password=String.valueOf(view.getPasswordField().getPassword());
        if(email.isEmpty()||password.isEmpty()){
            JOptionPane.showMessageDialog(view,"Fill all the field.");
        }
        else{
            LoginRequest loginData=new LoginRequest(email,password);
            System.out.println("hi");
            SellerDao sellerDao=new SellerDao();
            SellerData seller=sellerDao.loginSeller(loginData);
            System.out.println("hi guys");
            if(seller==null){
                JOptionPane.showMessageDialog(view,"Login failed");
            }
            else{
                JOptionPane.showMessageDialog(view, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                AdminDashboardView dashboardView=new AdminDashboardView();
                SellerDashboardController dashboardController=new SellerDashboardController(dashboardView,seller);
                dashboardController.open();
                close();
            }
        }
    }
    }
         
class ForgotPassword implements MouseListener{

     @Override
        public void mouseClicked(MouseEvent e) {
            ResetView forgotPassword=new ResetView();
            ForgotPasswordController forgotPasswordController=new ForgotPasswordController(forgotPassword);
            forgotPasswordController.open();
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
class Register implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            SellerRegistration reisterView = new SellerRegistration();
            SellerRegistrationController registerController = new SellerRegistrationController(reisterView);
            registerController.open();
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
}
