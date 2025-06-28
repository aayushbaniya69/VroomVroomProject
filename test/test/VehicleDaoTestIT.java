/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import javaproject.dao.VehicleDaoImpl;
import javaproject.model.Vehicle;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author your_name
 */
public class VehicleDaoTestIT {
    
    @Test
    public void testGetAllVehicles() {
        VehicleDaoImpl dao = new VehicleDaoImpl();
        List<Vehicle> vehicles = dao.getAllVehicles();
        
        Assert.assertNotNull("Vehicles list should not be null", vehicles);
        System.out.println("Found " + vehicles.size() + " vehicles");
    }
    
    @Test
    public void testAddVehicle() {
        VehicleDaoImpl dao = new VehicleDaoImpl();
        
        Vehicle vehicle = new Vehicle("TEST001", "Test Car", "Sedan", 100.0, "Available", "/test/image.jpg");
        boolean result = dao.addVehicle(vehicle);
        Assert.assertTrue("Vehicle addition should be successful", result);
    }
    
    @Test
    public void testUpdateVehicle() {
        VehicleDaoImpl dao = new VehicleDaoImpl();
        
        Vehicle vehicle = new Vehicle("TEST001", "Updated Car", "SUV", 150.0, "Available", "/test/updated.jpg");
        boolean result = dao.updateVehicle(vehicle);
        Assert.assertTrue("Vehicle update should be successful", result);
    }
    
    @Test
    public void testDeleteVehicle() {
        VehicleDaoImpl dao = new VehicleDaoImpl();
        boolean result = dao.deleteVehicle("TEST001");
        Assert.assertTrue("Vehicle deletion should be successful", result);
    }
}

