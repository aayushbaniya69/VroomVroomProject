import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    public LoginPage() {
        // Frame settings
        setTitle("VroomVroom - Login");
        setSize(400, 500);
        setLocationRelativeTo(null); // center
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(30, 31, 38)); // #1E1F26

        // Title Label
        JLabel titleLabel = new JLabel("VroomVroom", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(90, 30, 220, 40);
        add(titleLabel);

        // Logo (Optional)
        // JLabel logo = new JLabel(new ImageIcon("car-icon.png")); // Add if you have an icon
        // logo.setBounds(170, 70, 64, 64);
        // add(logo);

        // Login Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(new Color(44, 47, 54)); // #2C2F36
        loginPanel.setBounds(50, 120, 300, 250);
        add(loginPanel);

        // Email Label
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 20, 80, 25);
        emailLabel.setForeground(Color.WHITE);
        loginPanel.add(emailLabel);

        // Email Field
        emailField = new JTextField();
        emailField.setBounds(20, 45, 260, 30);
        emailField.setBackground(new Color(51, 56, 64)); // #333840
        emailField.setForeground(Color.WHITE);
        emailField.setCaretColor(Color.WHITE);
        emailField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        loginPanel.add(emailField);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 85, 100, 25);
        passwordLabel.setForeground(Color.WHITE);
        loginPanel.add(passwordLabel);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(20, 110, 260, 30);
        passwordField.setBackground(new Color(51, 56, 64));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        loginPanel.add(passwordField);

        // Login Button
        loginButton = new JButton("Log In");
        loginButton.setBounds(20, 160, 260, 35);
        loginButton.setBackground(new Color(0, 168, 120)); // #00A878
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginPanel.add(loginButton);

        // Forgot Password
        JLabel forgotPassword = new JLabel("Forgot password?");
        forgotPassword.setBounds(85, 205, 150, 20);
        forgotPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        forgotPassword.setForeground(new Color(176, 176, 176)); // #B0B0B0
        loginPanel.add(forgotPassword);

        // Status Label
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBounds(50, 390, 300, 20);
        add(statusLabel);

        // Action Listener
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (email.equals("admin") && password.equals("1234")) {
                    statusLabel.setForeground(new Color(0, 255, 128));
                    statusLabel.setText("Login Successful!");
                } else {
                    statusLabel.setForeground(Color.RED);
                    statusLabel.setText("Invalid email or password.");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        // Set a modern flat look and feel (optional)
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Look and Feel not set.");
        }

        new LoginPage();
    }
}

