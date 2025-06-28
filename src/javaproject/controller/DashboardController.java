/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaproject.model.UserData;
import javaproject.view.BookingView;
import javaproject.view.DashboardView;
import javaproject.view.LoginForm;
import javaproject.view.user;
import javax.swing.JOptionPane;

public class DashboardController {

    DashboardView view;
    UserData user;
    private String email;
    
    // Main constructor - FIXED to extract email from UserData
    public DashboardController(DashboardView view, UserData user){
        this.view = view;
        this.user = user;
        this.email = user != null ? user.getEmail() : null; // FIX: Extract email from user
        
        BackLogin BackButton = new BackLogin();
        this.view.BackButton(BackButton);
        UserProfile userProfile = new UserProfile();
        this.view.userProfiles(userProfile);
    }
    
    public DashboardController(DashboardView view){
        this.view = view;
    }
    
    public DashboardController(DashboardView view, String email){
        this.view = view;
        this.email = email;
    }
    
    public void open(){
       view.setVisible(true); 
    }
    
    public void close(){
        view.dispose();
    }
    
    class BackLogin implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Back button");
            LoginForm login = new LoginForm();
            LoginController loginController = new LoginController(login);
            loginController.open();
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
    
    class Booking implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Booking button");
            BookingView booking = new BookingView();
            BookingController bookingController = new BookingController(booking);
            bookingController.open();
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
    
    class UserProfile implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("UserProfile button clicked");
            
            // FIX: Better email validation and extraction
            String userEmail = email;
            if (userEmail == null || userEmail.trim().isEmpty()) {
                if (user != null && user.getEmail() != null) {
                    userEmail = user.getEmail();
                } else {
                    JOptionPane.showMessageDialog(view, 
                        "User email not found. Please login again.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            System.out.println("Opening user profile for email: " + userEmail);
            user userProfile = new user();
            UserProfileController userProfileController = new UserProfileController(userProfile, userEmail);
            userProfileController.open();
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
}