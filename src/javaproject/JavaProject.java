/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaproject;

import javaproject.controller.LoginController;
import javaproject.view.LoginForm;



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
        LoginForm registrationView=new LoginForm();
        LoginController controller = new LoginController(registrationView);
        controller.open();
    }
}
  