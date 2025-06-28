package javaproject.view;

import java.awt.*;
import javax.swing.*;

/**
 * VehicleCardPanel displays individual vehicle info as a card,
 * and opens BookingView in a new window if available.
 */
public class VehicleCardPanel extends JPanel {

    private final String vehicleId;
    private final String vehicleName;
    private final String vehiclePrice;
    private final String vehicleStatus;
    private final ImageIcon vehicleImage;
    private final JPanel mainContentPanel;

    public VehicleCardPanel(String vehicleId, String name, String price, String status, ImageIcon image, JPanel mainContentPanel) {
        this.vehicleId = vehicleId;
        this.vehicleName = name;
        this.vehiclePrice = price;
        this.vehicleStatus = status;
        this.vehicleImage = image;
        this.mainContentPanel = mainContentPanel;

        initCardUI();
    }

    private void initCardUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(350, 250));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        setBackground(Color.WHITE);

        // Top: Vehicle image
        JLabel imageLabel = new JLabel(vehicleImage);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(350, 150));
        add(imageLabel, BorderLayout.NORTH);

        // Center: Vehicle info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        infoPanel.add(new JLabel("ID: " + vehicleId));
        infoPanel.add(new JLabel("Model: " + vehicleName));
        infoPanel.add(new JLabel("Price: Rs. " + vehiclePrice + "/day"));
        infoPanel.add(new JLabel("Status: " + vehicleStatus));

        add(infoPanel, BorderLayout.CENTER);

        // Bottom: Book Now button
        JButton bookButton = new JButton("Book Now");
        bookButton.setBackground(new Color(0, 120, 215));
        bookButton.setForeground(Color.WHITE);
        bookButton.setFocusPainted(false);
        bookButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Disable button if not available
        if (!"Available".equalsIgnoreCase(vehicleStatus)) {
            bookButton.setEnabled(false);
            bookButton.setToolTipText("Vehicle is not available for booking.");
        }

        // Show BookingView in a new window
        bookButton.addActionListener(e -> openBookingWindow());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(bookButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Opens the booking screen as a separate window.
     */
    private void openBookingWindow() {
        BookingView bookingView = new BookingView();

        bookingView.getVehicleInfo().setText(vehicleName);
        bookingView.getTotalAmountField().setText("Rs " + vehiclePrice);
        bookingView.setVisible(true);
    }
}
