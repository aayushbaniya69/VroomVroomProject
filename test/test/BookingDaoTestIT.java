/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author ACER
 */

import javaproject.dao.BookingDao;
import javaproject.model.Booking;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author your_name
 */
public class BookingDaoTestIT {
    
    @Test
    public void testInsertBooking() {
        BookingDao dao = new BookingDao();
        
        Booking booking = new Booking();
        booking.setUserId(1);
        booking.setVehicleInfo("Test Vehicle - Toyota Camry");
        booking.setStartDate("2024-02-01");
        booking.setEndDate("2024-02-05");
        booking.setNumberOfVehicles(1);
        booking.setTotalAmount(200.0);
        
        boolean result = dao.insertBooking(booking);
        Assert.assertTrue("Booking insertion should be successful", result);
    }
}

