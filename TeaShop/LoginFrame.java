package TeaShop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public LoginFrame() {
        setTitle("Juju & Jhumu Tea Shop - Login & Order!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        initComponents();
        setSize(400, 250);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));
        setVisible(true);
    }

    private void initComponents() {
        JLabel titleLabel = new JLabel("Tea Shop Management System");
        titleLabel.setBounds(50, 10, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 80, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 150, 30);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 90, 80, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 90, 150, 30);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 130, 80, 30);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
        add(loginButton);

        statusLabel = new JLabel();
        statusLabel.setBounds(50, 170, 250, 30);
        add(statusLabel);
    }

    private void authenticateUser() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();

        // Replace the following line with your authentication logic
        // For demo purposes, let's assume a simple username and password
        if ("JUJU".equals(username) && "Jhumu".equals(new String(password))) {
            statusLabel.setText("Login Successful!");
            openHomePage();
        } else {
            statusLabel.setText("Invalid username or password. Try again.");
        }

        // Clear fields after attempting login
        usernameField.setText("");
        passwordField.setText("");
    }

    private void openHomePage() {
        // Replace this with the code to open the home page or switch to a new class
        // (HomePage)
        // For demonstration, let's show a JOptionPane
        JOptionPane.showMessageDialog(this, "Welcome to the Tea Shop Management System!", "Login Successful",
                JOptionPane.INFORMATION_MESSAGE);

        // Redirect to HomePage class or create a new instance of it
        new HomePage();
        // Close the current login frame
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
