package javaproject.view;

import java.awt.*;
import javax.swing.*;

/**
 * VehicleCardPanel displays individual vehicle info as a card.
 */
public class VehicleCardPanel extends JPanel {
    private final String vehicleId;
    private final String vehicleName;
    private final String vehiclePrice;
    private final String vehicleStatus;
    private final ImageIcon vehicleImage;

    public VehicleCardPanel(String vehicleId, String name, String price, String status, ImageIcon image) {
        this.vehicleId = vehicleId;
        this.vehicleName = name;
        this.vehiclePrice = price;
        this.vehicleStatus = status;
        this.vehicleImage = image;

        setupCard();
    }

    private void setupCard() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(350, 250));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        setBackground(Color.WHITE);

        // Image at the top
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setIcon(vehicleImage);
        imageLabel.setPreferredSize(new Dimension(350, 150));
        add(imageLabel, BorderLayout.NORTH);

        // Info panel at the center
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        infoPanel.add(new JLabel("ID: " + vehicleId));
        infoPanel.add(new JLabel("Model: " + vehicleName));
        infoPanel.add(new JLabel("Price: Rs. " + vehiclePrice + "/day"));
        infoPanel.add(new JLabel("Status: " + vehicleStatus));

        add(infoPanel, BorderLayout.CENTER);

        // Book Now button at bottom
        JButton bookButton = new JButton("Book Now");
        bookButton.setBackground(new Color(0, 120, 215));
        bookButton.setForeground(Color.WHITE);
        bookButton.setFocusPainted(false);
        bookButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Action: open BookingView
        bookButton.addActionListener(e -> openBookingView());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(bookButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void openBookingView() {
        BookingView bookingView = new BookingView();
        bookingView.getVehicleInfo().setText(vehicleName);
        bookingView.getTotalAmountField().setText("Rs " + vehiclePrice);
        bookingView.setVisible(true);
    }
}
