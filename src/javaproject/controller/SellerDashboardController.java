/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaproject.model.SellerData;
import javaproject.view.AdminDashboardView;
import javaproject.view.BookingView;
import javaproject.view.SellerLoginForm;

/**
 *
 * @author ACER
 */
public class SellerDashboardController {
    AdminDashboardView view;
    SellerData seller;
    public SellerDashboardController(AdminDashboardView view,SellerData seller){
        this.view=view;
        this.seller=seller;
        BackLogin backLogin=new BackLogin();
        this.view.BackLogin(backLogin);
    }
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    
   class BackLogin implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Back button");
            SellerLoginForm login = new SellerLoginForm();
            SellerLoginController loginController = new SellerLoginController(login);
            loginController.open();
            close();
        }
    }
  class Booking implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Booking Page");
            BookingView login = new BookingView();
            BookingController loginController = new BookingController(login);
            loginController.open();
            close();
        }
    }
  class ManageUsers implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Back button");
            SellerLoginForm login = new SellerLoginForm();
            SellerLoginController loginController = new SellerLoginController(login);
            loginController.open();
            close();
        }
    }
  class PaymentHistory implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Back button");
            SellerLoginForm login = new SellerLoginForm();
            SellerLoginController loginController = new SellerLoginController(login);
            loginController.open();
            close();
        }
    }
}
