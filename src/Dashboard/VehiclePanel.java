/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



// Mock Vehicle class for demo (replace with your actual Vehicle model)
class Vehicle {
    private String name, type, status;
    private String imagePath;
    private double pricePerDay;

    public Vehicle(String name, String type, double pricePerDay, String status, String imagePath) {
        this.name = name;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.status = status;
        this.imagePath = imagePath;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public double getPricePerDay() { return pricePerDay; }
    public String getStatus() { return status; }
    public String getImagePath() { return imagePath; }
}

public class VehiclePanel extends JPanel {

    private JComboBox<String> vehicleTypeCombo;
    private JPanel vehicleListPanel;
    private JScrollPane scrollPane;

    // Mock data method: Replace with your controller logic
    private ArrayList<Vehicle> getAllVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("Honda Civic", "Car", 50, "Available", "/images/civic.png"));
        vehicles.add(new Vehicle("Yamaha R15", "Bike", 30, "Booked", "/images/r15.png"));
        vehicles.add(new Vehicle("Toyota Corolla", "Car", 55, "Available", "/images/corolla.png"));
        vehicles.add(new Vehicle("Kawasaki Ninja", "Bike", 70, "Available", "/images/ninja.png"));
        // Add more vehicles as needed
        return vehicles;
    }

    public VehiclePanel() {
        setLayout(new BorderLayout(10,10));
        setBorder(new EmptyBorder(10,10,10,10));

        // Top filter panel
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Select Vehicle Type:"));

        vehicleTypeCombo = new JComboBox<>(new String[]{"All", "Car", "Bike", "Truck"});
        filterPanel.add(vehicleTypeCombo);
        add(filterPanel, BorderLayout.NORTH);

        // Vehicle list panel inside scroll pane
        vehicleListPanel = new JPanel();
        vehicleListPanel.setLayout(new GridLayout(0, 3, 15, 15)); // 3 cards per row with gaps

        scrollPane = new JScrollPane(vehicleListPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // Load all vehicles initially
        loadVehicleList(getAllVehicles());

        // Combo box listener to filter vehicles
        vehicleTypeCombo.addActionListener(e -> {
            String selectedType = (String) vehicleTypeCombo.getSelectedItem();
            if (selectedType.equals("All")) {
                loadVehicleList(getAllVehicles());
            } else {
                loadVehicleList(filterVehiclesByType(selectedType));
            }
        });
    }

    // Filter vehicles by type (mock logic)
    private ArrayList<Vehicle> filterVehiclesByType(String type) {
        ArrayList<Vehicle> filtered = new ArrayList<>();
        for (Vehicle v : getAllVehicles()) {
            if (v.getType().equalsIgnoreCase(type)) {
                filtered.add(v);
            }
        }
        return filtered;
    }

    // Load vehicles and create cards
    private void loadVehicleList(ArrayList<Vehicle> vehicles) {
        vehicleListPanel.removeAll();

        for (Vehicle v : vehicles) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
            card.setBackground(Color.WHITE);

            // Image label (scaled)
            ImageIcon icon = new ImageIcon(getClass().getResource(v.getImagePath()));
            Image img = icon.getImage().getScaledInstance(200, 120, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(img));
            imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
            card.add(imgLabel, BorderLayout.NORTH);

            // Details panel
            JPanel detailsPanel = new JPanel(new GridLayout(0,1));
            detailsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            detailsPanel.add(new JLabel("<html><b>" + v.getName() + "</b></html>"));
            detailsPanel.add(new JLabel("Type: " + v.getType()));
            detailsPanel.add(new JLabel(String.format("Price: $%.2f/day", v.getPricePerDay())));
            detailsPanel.add(new JLabel("Status: " + v.getStatus()));

            card.add(detailsPanel, BorderLayout.CENTER);

            // Book button panel
            JButton bookBtn = new JButton("Book Now");
            bookBtn.setEnabled(v.getStatus().equalsIgnoreCase("Available"));
            JPanel btnPanel = new JPanel();
            btnPanel.add(bookBtn);
            card.add(btnPanel, BorderLayout.SOUTH);

            vehicleListPanel.add(card);
        }

        vehicleListPanel.revalidate();
        vehicleListPanel.repaint();
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 746, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
