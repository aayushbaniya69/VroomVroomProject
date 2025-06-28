package javaproject.view;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javaproject.controller.VehicleController;
import javaproject.model.Vehicle;

public class UserVehiclePanel extends JPanel {

    private JScrollPane scrollPane;
    private JPanel vehicleListPanel;
    

    public UserVehiclePanel() {

        initComponents();
        setUpVehicleListPanel();
        loadVehicles();
    }

    private void initComponents() {
        scrollPane = new JScrollPane();
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    private void setUpVehicleListPanel() {
        vehicleListPanel = new JPanel();
        vehicleListPanel.setLayout(new BoxLayout(vehicleListPanel, BoxLayout.Y_AXIS));
        vehicleListPanel.setBackground(Color.WHITE);
        vehicleListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setViewportView(vehicleListPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }

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


            card.setAlignmentX(Component.LEFT_ALIGNMENT);
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260));
            vehicleListPanel.add(card);
            vehicleListPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        vehicleListPanel.revalidate();
        vehicleListPanel.repaint();
    }

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

    private ImageIcon getDefaultImageIcon() {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/Dashboard/images/default.png"));
            Image scaled = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } catch (Exception e) {
            return new ImageIcon();
        }
    }
}
