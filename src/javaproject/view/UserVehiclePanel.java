package javaproject.view;

import java.awt.*;
import java.io.File;
import javax.swing.*;

import javaproject.controller.VehicleController;
import javaproject.model.Vehicle;

/**
 * UserVehiclePanel displays a scrollable list of vehicles as cards for the user dashboard.
 */
public class UserVehiclePanel extends JPanel {

    private JScrollPane scrollPane;
    private JPanel vehicleListPanel;
    private DashboardView dashboardView;


    public UserVehiclePanel(DashboardView aThis) {
        this.dashboardView = dashboardView;
        initComponents();
        setUpVehicleListPanel();
        loadVehicles();
    }

    /**
     * Initializes layout and adds scrollPane to the main panel.
     */
    private void initComponents() {
        scrollPane = new JScrollPane();
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Sets up the internal panel that holds vehicle cards vertically.
     */
    private void setUpVehicleListPanel() {
        vehicleListPanel = new JPanel();
        vehicleListPanel.setLayout(new BoxLayout(vehicleListPanel, BoxLayout.Y_AXIS));
        vehicleListPanel.setBackground(Color.WHITE);
        vehicleListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // spacing

        scrollPane.setViewportView(vehicleListPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smooth scroll
    }

    /**
     * Loads all vehicles from the controller and adds their cards to the list.
     */
    private void loadVehicles() {
        vehicleListPanel.removeAll();

        for (Vehicle vehicle : VehicleController.getInstance().getAllVehicles()) {
            ImageIcon icon = loadImage(vehicle.getImagePath());

            // Pass this (main content panel) to the card for booking redirection
            VehicleCardPanel card = new VehicleCardPanel(
            vehicle.getVehicleId(),
            vehicle.getName(),
            String.valueOf(vehicle.getPrice()),
            vehicle.getStatus(),
            icon,
            dashboardView // ✅ correct
            );


            card.setAlignmentX(Component.LEFT_ALIGNMENT);
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260)); // keep uniform
            vehicleListPanel.add(card);
            vehicleListPanel.add(Box.createRigidArea(new Dimension(0, 10))); // spacing
        }

        vehicleListPanel.revalidate();
        vehicleListPanel.repaint();
    }

    /**
     * Public method to refresh the panel content after add/delete.
     */
    public void refreshVehicleCards() {
        loadVehicles();
    }

    /**
     * Loads image from file and scales it; uses default if missing.
     */
    private ImageIcon loadImage(String imagePath) {
        try {
            File imgFile = new File(imagePath);
            if (imgFile.exists()) {
                ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());
                Image scaled = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            }
        } catch (Exception ignored) {}

        return getDefaultImageIcon();
    }

    /**
     * Returns default placeholder image.
     */
    private ImageIcon getDefaultImageIcon() {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/Dashboard/images/default.png"));
            Image scaled = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } catch (Exception e) {
            return new ImageIcon(); // fallback blank icon
        }
    }
}
