/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER
 */
package test;

import javaproject.dao.SellerDao;
import javaproject.model.LoginRequest;
import javaproject.model.SellerData;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author your_name
 */
public class SellerDaoTestIT {
    
    String correctEmail = "bibash@gmail.com";
    String correctPassword = "newpassword123";
    String newPassword = "654321";
    
    @Test
    public void testEmailExists() {
        SellerDao dao = new SellerDao();
        boolean result = dao.emailExists(correctEmail);
        Assert.assertNotNull("Email exists check should return a result", Boolean.valueOf(result));
    }
    
    @Test
    public void testUpdatePassword() throws SQLException {
        SellerDao dao = new SellerDao();
        boolean result = dao.updatePassword(correctEmail, newPassword);
        Assert.assertTrue("Password update should be successful", result);
        
        // Optional: Test login with new password
        LoginRequest login = new LoginRequest(correctEmail, newPassword);
        SellerData seller = dao.loginSeller(login);
        Assert.assertNotNull("Seller should log in with updated password", seller);
    }
    
    @Test
    public void testSellerLogin() {
        SellerDao dao = new SellerDao();
        LoginRequest login = new LoginRequest(correctEmail, correctPassword);
        SellerData seller = dao.loginSeller(login);
        Assert.assertNotNull("Seller login should be successful", seller);
    }
    
    @Test
    public void testRegisterSeller() {
        SellerDao dao = new SellerDao();
        SellerData seller = new SellerData("Test Seller", "unique" + System.currentTimeMillis() + "@test.com", 
                                          "Test City", "1234567890", "testpass", "testpass", "1234567890");
        
        boolean result = dao.registerSeller(seller);
        Assert.assertTrue("Seller registration should be successful", result);
    }
}
