/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaproject;

import javaproject.controller.RegistrationController;
import javaproject.view.RegistrationView;



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
  