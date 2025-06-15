/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import javaproject.view.ForgotPasswordView;

/**
 *
 * @author ACER
 */
public class ForgotPasswordController {
    ForgotPasswordView forgotPassword=new ForgotPasswordView();
    public ForgotPasswordController(ForgotPasswordView forgotPassword){
        this.forgotPassword=forgotPassword;
    }
    public void open(){
       forgotPassword.setVisible(true); 
    }
    public void close(){
        forgotPassword.dispose();
    }
}
