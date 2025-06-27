package javaproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaproject.dao.UserDao;
import javaproject.dao.SellerDao;
import javaproject.model.LoginRequest;
import javaproject.model.UserData;
import javaproject.model.SellerData;
import javaproject.view.DashboardView;
import javaproject.view.AdminDashboardView;
import javaproject.view.LoginForm;
import javaproject.view.RegistrationView;
import javaproject.view.ResetView;
import javaproject.view.SellerRegistration;
import javax.swing.JOptionPane;

public class LoginController {
    private LoginForm view;
    
    public LoginController(LoginForm view) {
        this.view = view;
        
        // Attach listeners
        LoginUser loginUser = new LoginUser();
        this.view.LoginUser(loginUser);
        
        ForgotPassword forgotPassword = new ForgotPassword();
        this.view.forgotPassword(forgotPassword);
        
        UserRegister userRegisterPage = new UserRegister();
        this.view.registerPage(userRegisterPage);
        
        AdminRegister adminRegisterPage = new AdminRegister();
        this.view.adminRegisterPage(adminRegisterPage);
    }
    
    public void open() {
        view.setVisible(true);
    }
    
    public void close() {
        view.dispose();
    }
    
    // UNIFIED LOGIN CLASS - Handles both User and Admin login
    class LoginUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("=== UNIFIED LOGIN CONTROLLER ===");
            
            String email = view.getEmailTextField().getText().trim();
            String password = String.valueOf(view.getPasswordField().getPassword());
            
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill all fields.");
                return;
            }
            
            System.out.println("Attempting login for: " + email);
            LoginRequest loginData = new LoginRequest(email, password);
            
            // STEP 1: Try User Login First
            System.out.println("Checking user database...");
            UserDao userDao = new UserDao();
            UserData user = userDao.login(loginData);
            
            if (user != null) {
                // USER LOGIN SUCCESSFUL
                System.out.println("User login successful!");
                JOptionPane.showMessageDialog(view, "Welcome User: " + user.getFirstName()+" "+user.getLastName(), "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                
                // Navigate to User Dashboard
                DashboardView dashboardView = new DashboardView();
                DashboardController dashboardController = new DashboardController(dashboardView, user);
                dashboardController.open();
                close();
                return;
            }
            
            // STEP 2: Try Admin/Seller Login
            System.out.println("User not found, checking admin database...");
            SellerDao sellerDao = new SellerDao();
            SellerData seller = sellerDao.loginSeller(loginData);
            
            if (seller != null) {
                // ADMIN LOGIN SUCCESSFUL
                System.out.println("Admin login successful!");
                JOptionPane.showMessageDialog(view, "Welcome Admin: " + seller.getFullName(), "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                
                // Navigate to Admin Dashboard
                AdminDashboardView adminDashboardView = new AdminDashboardView();
                SellerDashboardController adminDashboardController = new SellerDashboardController(adminDashboardView, seller);
                adminDashboardController.open();
                close();
                return;
            }
            
            // BOTH LOGIN ATTEMPTS FAILED
            System.out.println("Login failed for both user and admin");
            JOptionPane.showMessageDialog(view, "Invalid email or password!\nPlease check your credentials.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // USER REGISTRATION LINK
    class UserRegister implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Navigating to User Registration");
            RegistrationView registrationView = new RegistrationView();
            RegistrationController registrationController = new RegistrationController(registrationView);
            registrationController.open();
            close();
        }
        
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    }
    
    // ADMIN REGISTRATION LINK
    class AdminRegister implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Navigating to Admin Registration");
            SellerRegistration sellerRegistration = new SellerRegistration();
            SellerRegistrationController sellerRegistrationController = new SellerRegistrationController(sellerRegistration);
            sellerRegistrationController.open();
            close();
        }
        
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    }
    
    // FORGOT PASSWORD LINK
    class ForgotPassword implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            ResetView forgotPassword = new ResetView();
            ForgotPasswordController forgotPasswordController = new ForgotPasswordController(forgotPassword);
            forgotPasswordController.open();
            close();
        }
        
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    }
}