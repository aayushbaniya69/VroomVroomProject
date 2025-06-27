/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaproject.view.ForgotPasswordView;
import javaproject.view.LoginForm;
import javaproject.view.ResetView;

/**
 *
 * @author ACER
 */
public class ForgotPasswordController {
    ResetView forgotPassword=new ResetView();
    public ForgotPasswordController(ResetView forgotPassword){
        this.forgotPassword=forgotPassword;
        BackLogin backLogin=new BackLogin();
        this.forgotPassword.loginBack(backLogin);
    }
    public void open(){
       forgotPassword.setVisible(true); 
    }
    public void close(){
        forgotPassword.dispose();
    }
    
    class BackLogin implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginForm login = new LoginForm();
            LoginController loginController = new LoginController(login);
            loginController.open();
            close();
        }
    }
}
