package javaproject.view;

import java.awt.*;
import java.io.File;
import javaproject.controller.VehicleController;
import javaproject.model.Vehicle;
import javax.swing.*;

/**
 * UserVehiclePanel displays a scrollable list of vehicles as cards for the user dashboard.
 */
public class UserVehiclePanel extends JPanel {

    private JScrollPane scrollPane;
    private JPanel vehicleListPanel;

    public UserVehiclePanel() {
        initComponents();
        setUpVehicleListPanel();
        loadVehicles();
    }

    /**
     * Initializes layout and scrollPane container.
     */
    private void initComponents() {
        scrollPane = new JScrollPane();

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Sets up the panel that holds all vehicle cards.
     */
    private void setUpVehicleListPanel() {
        vehicleListPanel = new JPanel();
        vehicleListPanel.setLayout(new BoxLayout(vehicleListPanel, BoxLayout.Y_AXIS));
        vehicleListPanel.setBackground(Color.WHITE);

        scrollPane.setViewportView(vehicleListPanel);
    }

    /**
     * Loads all vehicles from the controller and adds their cards to the list.
     */
    private void loadVehicles() {
        vehicleListPanel.removeAll();

        for (Vehicle vehicle : VehicleController.getInstance().getAllVehicles()) {
            ImageIcon icon = loadImage(vehicle.getImagePath());
            VehicleCardPanel card = new VehicleCardPanel(
                    vehicle.getVehicleId(),
                    vehicle.getName(),
                    String.valueOf(vehicle.getPrice()),
                    vehicle.getStatus(),
                    icon
            );
            vehicleListPanel.add(card);
        }

        vehicleListPanel.revalidate();
        vehicleListPanel.repaint();
    }

    /**
     * Refreshes the UI with updated list of vehicles.
     * Call this after adding/removing vehicles.
     */
    public void refreshVehicleCards() {
        loadVehicles();  // loadVehicles already handles clearing and repainting
    }

    /**
     * Loads and scales image from the given path.
     * If image doesn't exist, falls back to default.
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
     * Loads a default placeholder image in case actual image fails.
     */
    private ImageIcon getDefaultImageIcon() {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/Dashboard/images/default.png"));
            Image scaled = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } catch (Exception e) {
            return new ImageIcon(); // blank icon fallback
        }
    }
}
