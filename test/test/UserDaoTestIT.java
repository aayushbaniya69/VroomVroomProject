/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER
 */
package test;

import javaproject.dao.UserDao;
import javaproject.model.LoginRequest;
import javaproject.model.UserData;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author your_name
 */
public class UserDaoTestIT {
    
    String correctEmail = "bibash@gmail.com";
    String correctPassword = "654321";
    String newPassword = "123456";
    
    @Test
    public void testCheckEmail() {
        UserDao dao = new UserDao();
        boolean result = dao.checkEmail(correctEmail);
        
        System.out.println("=== testCheckEmail DEBUG ===");
        System.out.println("Email: " + correctEmail);
        System.out.println("Result: " + result);
        
        Assert.assertTrue("Email '" + correctEmail + "' should exist in Registration table. " +
                         "Please add this user to database or use existing email.", result);
    }
    
    @Test
    public void testResetPassword() throws SQLException {
        UserDao dao = new UserDao();
        
        System.out.println("=== testResetPassword DEBUG ===");
        System.out.println("Email: " + correctEmail);
        System.out.println("New Password: " + newPassword);
        
        // First check if email exists
        boolean emailExists = dao.checkEmail(correctEmail);
        System.out.println("Email exists: " + emailExists);
        Assert.assertTrue("Email '" + correctEmail + "' must exist before password reset.", emailExists);
        
        boolean result = dao.resetPassword(correctEmail, newPassword);
        System.out.println("Reset password result: " + result);
        Assert.assertTrue("Password reset should be successful for email: " + correctEmail, result);
        
        // Optional: Test login with new password
        LoginRequest login = new LoginRequest(correctEmail, newPassword);
        UserData user = dao.login(login);
        System.out.println("Login after reset - User object: " + (user != null ? "Found" : "NULL"));
        if (user != null) {
            System.out.println("User email from object: " + user.getEmail());
        }
        Assert.assertNotNull("User should log in with updated password for email: " + correctEmail, user);
    }
    
    @Test
    public void testUserLogin() {
        UserDao dao = new UserDao();
        LoginRequest login = new LoginRequest(correctEmail, correctPassword);
        
        System.out.println("=== testUserLogin DEBUG ===");
        System.out.println("Login Email: " + correctEmail);
        System.out.println("Login Password: " + correctPassword);
        
        UserData user = dao.login(login);
        
        System.out.println("Login result: " + (user != null ? "SUCCESS" : "FAILED"));
        if (user != null) {
            System.out.println("Returned user email: " + user.getEmail());
            System.out.println("Returned user firstName: " + user.getFirstName());
        } else {
            System.out.println("❌ Login returned NULL - check UserData constructor");
        }
        
        Assert.assertNotNull("User login should be successful for email: " + correctEmail + 
                           ". User exists but login returns null - check UserData constructor in UserDao.login()", user);
    }
}
