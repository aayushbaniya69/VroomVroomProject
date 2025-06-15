/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaproject;

import javaproject.controller.LoginController;
import javaproject.controller.RegistrationController;
import javaproject.controller.SellerRegistrationController;
import javaproject.view.LoginForm;
import javaproject.view.RegistrationView;
import javaproject.view.SellerRegistration;



/**
 *
 * @author ACER
 */
public class JavaProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SellerRegistration registrationView=new SellerRegistration();
        SellerRegistrationController controller = new SellerRegistrationController(registrationView);
        controller.open();
    }
}
  