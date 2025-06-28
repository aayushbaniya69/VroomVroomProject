/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import javaproject.dao.AdminProfileDao;
import javaproject.model.SellerData;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author your_name
 */
public class AdminProfileDaoTestIT {
    
    String testEmail = "bibash9@gmail.com";
    
    @Test
    public void testGetUserByEmail() {
        AdminProfileDao dao = new AdminProfileDao();
        SellerData seller = dao.getUserByEmail(testEmail);
        // May return null if user doesn't exist
        System.out.println("Seller found: " + (seller != null));
    }
    
    @Test
    public void testUpdateSellerProfile() {
        AdminProfileDao dao = new AdminProfileDao();
        
        // First get existing seller
        SellerData seller = dao.getUserByEmail(testEmail);
        if (seller != null) {
            seller.setLocation("Updated Location");
            boolean result = dao.updateSellerProfile(seller);
            Assert.assertTrue("Profile update should be successful", result);
        }
    }
    
    @Test
    public void testChangePassword() {
        AdminProfileDao dao = new AdminProfileDao();
        
        SellerData seller = dao.getUserByEmail(testEmail);
        if (seller != null) {
            seller.setPassword("newpassword123");
            boolean result = dao.changePassword(seller);
            Assert.assertTrue("Password change should be successful", result);
        }
    }
}

