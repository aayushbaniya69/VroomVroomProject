/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaproject;

import javaproject.controller.BookingController;
import javaproject.controller.LoginController;
import javaproject.controller.RegistrationController;
import javaproject.controller.SellerRegistrationController;
import javaproject.view.BookingView;
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
        RegistrationView registrationView=new RegistrationView();
        RegistrationController controller = new RegistrationController(registrationView);
        controller.open();
    }
}
  