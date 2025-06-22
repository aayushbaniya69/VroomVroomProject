/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaproject.model.UserData;
import javaproject.view.BookingView;
import javaproject.view.DashboardView;
import javaproject.view.LoginForm;

/**
 *
 * @author ACER
 */
public class DashboardController {

    DashboardView view;
    UserData user;
    public DashboardController(DashboardView view,UserData user){
        this.view=view;
        this.user=user;
        //this.firstName=firstname;
        //this.view.getWelcomeLable().setText("Welcome ");
        BackLogin backButton=new BackLogin();
        this.view.BackButton(backButton);
        Booking booking=new Booking();
        this.view.bookings(booking);
    }
    public DashboardController(DashboardView view){
        this.view=view;
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
            LoginForm login = new LoginForm();
            LoginController loginController = new LoginController(login);
            loginController.open();
            close();
        }
    }
    class Booking implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Booking button");
            BookingView booking = new BookingView();
            BookingController bookingController = new BookingController(booking);
            bookingController.open();
            close();
        }
    }
}             

