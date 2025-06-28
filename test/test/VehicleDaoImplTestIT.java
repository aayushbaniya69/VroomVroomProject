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
public class VehicleDaoImplTestIT {
    
    String testVehicleId = "T001";  // Shorter ID
    String testName = "Test Toyota Camry";
    String testType = "Sedan";
    double testPrice = 150.0;
    String testStatus = "Available";
    String testImagePath = "/images/test_car.jpg";
    
    @Test
    public void testGetAllVehicles() {
        VehicleDaoImpl dao = new VehicleDaoImpl();
        List<Vehicle> vehicles = dao.getAllVehicles();
        
        Assert.assertNotNull("Vehicles list should not be null. Check database connection and vehicles table.", vehicles);
        Assert.assertFalse("Vehicles list should not be empty. Please add vehicles to database.", vehicles.isEmpty());
    }
    
    @Test
    public void testAddVehicle() {
        VehicleDaoImpl dao = new VehicleDaoImpl();
        Vehicle vehicle = new Vehicle(testVehicleId, testName, testType, testPrice, testStatus, testImagePath);
        
        boolean result = dao.addVehicle(vehicle);
        Assert.assertTrue("Vehicle addition should be successful for ID: " + testVehicleId + 
                         ". Check database connection and vehicles table structure.", result);
    }
    
    @Test
    public void testUpdateVehicle() {
        VehicleDaoImpl dao = new VehicleDaoImpl();
        
        // First add the vehicle that we want to update
        Vehicle originalVehicle = new Vehicle(testVehicleId, testName, testType, testPrice, testStatus, testImagePath);
        boolean added = dao.addVehicle(originalVehicle);
        Assert.assertTrue("Vehicle should be added before update test", added);
        
        // Now create updated vehicle with same ID
        Vehicle vehicle = new Vehicle(testVehicleId, "Updated Car Name", "SUV", 200.0, "Available", "/images/updated.jpg");
        
        boolean result = dao.updateVehicle(vehicle);
        Assert.assertTrue("Vehicle update should be successful for ID: " + testVehicleId + 
                         ". Ensure vehicle with ID '" + testVehicleId + "' exists in vehicles table.", result);
    }
    
    @Test
    public void testDeleteVehicle() {
        VehicleDaoImpl dao = new VehicleDaoImpl();
        
        // First add a vehicle to delete - use shorter ID
        Vehicle vehicle = new Vehicle("D001", "Delete Test Car", "Sedan", 100.0, "Available", "/test/delete.jpg");
        boolean added = dao.addVehicle(vehicle);
        Assert.assertTrue("Vehicle should be added before deletion test. Check database connection.", added);
        
        // Now delete it
        boolean result = dao.deleteVehicle("D001");
        Assert.assertTrue("Vehicle deletion should be successful for ID: D001. " +
                         "Check if vehicle exists in database.", result);
    }
    
    @Test
    public void testVehicleDataIntegrity() {
        VehicleDaoImpl dao = new VehicleDaoImpl();
        
        // Add a vehicle - use shorter ID
        Vehicle originalVehicle = new Vehicle("I001", "Integrity Test Car", "Hatchback", 120.0, "Available", "/test/integrity.jpg");
        boolean added = dao.addVehicle(originalVehicle);
        Assert.assertTrue("Vehicle should be added for integrity test", added);
        
        // Get all vehicles and find our test vehicle
        List<Vehicle> vehicles = dao.getAllVehicles();
        Vehicle foundVehicle = null;
        for (Vehicle v : vehicles) {
            if ("I001".equals(v.getVehicleId())) {
                foundVehicle = v;
                break;
            }
        }
        
        Assert.assertNotNull("Added vehicle should be found in database", foundVehicle);
        Assert.assertEquals("Vehicle name should match", "Integrity Test Car", foundVehicle.getName());
        Assert.assertEquals("Vehicle type should match", "Hatchback", foundVehicle.getType());
        Assert.assertEquals("Vehicle price should match", 120.0, foundVehicle.getPrice(), 0.01);
        Assert.assertEquals("Vehicle status should match", "Available", foundVehicle.getStatus());
        
        // Clean up
        dao.deleteVehicle("I001");
    }
}
