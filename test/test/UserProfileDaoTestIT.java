/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import javaproject.dao.UserProfileDao;
import javaproject.model.UserData;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author your_name
 */
public class UserProfileDaoTestIT {
    
    String testEmail = "bibash@gmail.com";
    
    @Test
    public void testGetUserByEmail() {
        UserProfileDao dao = new UserProfileDao();
        UserData user = dao.getUserByEmail(testEmail);
        // May return null if user doesn't exist
        System.out.println("User found: " + (user != null));
    }
    
    @Test
    public void testUpdateUser() {
        UserProfileDao dao = new UserProfileDao();
        
        UserData user = dao.getUserByEmail(testEmail);
        if (user != null) {
            user.setAddress("Updated Address");
            boolean result = dao.updateUser(user);
            Assert.assertTrue("User update should be successful", result);
        }
    }
    
    @Test
    public void testChangePassword() {
        UserProfileDao dao = new UserProfileDao();
        
        UserData user = dao.getUserByEmail(testEmail);
        if (user != null) {
            user.setPassword("newuserpass123");
            boolean result = dao.changePassword(user);
            Assert.assertTrue("Password change should be successful", result);
        }
    }
}
