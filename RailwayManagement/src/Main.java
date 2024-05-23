import javax.swing.*;
import java.awt.*;

public class Main {
    private static String selectedTrain;
    private static String paymentInfo;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createWelcomePage);
    }

    private static void createWelcomePage() {
        JFrame welcomeFrame = new JFrame("Welcome Page");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(470, 685);
        welcomeFrame.setLayout(null);

        JLabel welcomeMessage = new JLabel("Welcome To Railway Management System", SwingConstants.CENTER);
        welcomeMessage.setBounds(35, 230, 400, 60);
        welcomeMessage.setFont(new Font(welcomeMessage.getFont().getName(), Font.BOLD, 17));

        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(175, 380, 80, 30);
        enterButton.addActionListener(e -> {
            welcomeFrame.dispose();
            createLoginPage();
        });

        welcomeFrame.add(welcomeMessage);
        welcomeFrame.add(enterButton);
        welcomeFrame.setVisible(true);
    }

    private static void createLoginPage() {
        JFrame loginFrame = new JFrame("Login Page");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(470, 685);
        loginFrame.setLayout(null);

        JLabel headerLabel = new JLabel("Railway Management System", SwingConstants.CENTER);
        headerLabel.setBounds(35, 130, 400, 60);
        headerLabel.setFont(new Font(headerLabel.getFont().getName(), Font.PLAIN, 21));

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(120, 262, 80, 30);
        JTextField userField = new JTextField();
        userField.setBounds(200, 262, 200, 30);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(120, 326, 80, 30);
        JPasswordField passField = new JPasswordField();
        passField.setBounds(200, 326, 200, 30);

        JLabel messageLabel = new JLabel();
        messageLabel.setBounds(150, 400, 500, 30);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(175, 380, 80, 30);
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (isValidLogin(username, password)) {
                loginFrame.dispose();
                createTrainListPage();
            } else {
                messageLabel.setText("Invalid login");
            }
        });

        loginFrame.add(headerLabel);
        loginFrame.add(userLabel);
        loginFrame.add(userField);
        loginFrame.add(passLabel);
        loginFrame.add(passField);
        loginFrame.add(messageLabel);
        loginFrame.add(loginButton);
        loginFrame.setVisible(true);
    }

    private static boolean isValidLogin(String username, String password) {
        return "tasnia".equals(username) && "tasnia1234".equals(password);
    }

    private static void createTrainListPage() {
        JFrame trainListFrame = new JFrame("Train List");
        trainListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        trainListFrame.setSize(470, 685);
        trainListFrame.setLayout(null);

        JLabel trainListLabel = new JLabel("Available Trains", SwingConstants.CENTER);
        trainListLabel.setBounds(35, 20, 400, 60);

        String[][] data = {
            {"111", "Suborno Express", "1:20"},
            {"222", "Mohanagar Provati/Godhuli Express", "1:50"},
            {"333", "Ekota Express", "2:30"},
            {"444", "Tista Express", "3:50"},
            {"555", "Parabat Express", "4:30"},
            {"666", "Upakul Express", "5:10"},
            {"777", "Karatoya Express", "5:30"},
            {"888", "Jayantika Express", "6:00"},
            {"999", "Paharika Express", "6:30"},
            {"112", "Mohanagar Express", "6:50"},
            {"113", "Udayan Express", "7:30"},
            {"114", "Meghna Express", "7:50"},
            {"115", "Barendra Express", "8:20"},
            {"116", "Titumir Express", "9:30"},
            {"117", "Agnibina Express", "10:00"}
        };
        String[] columnNames = {"ID", "Train Name", "Time"};
        JTable trainListTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(trainListTable);
        scrollPane.setBounds(30, 100, 400, 300);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(175, 500, 80, 40);
        submitButton.addActionListener(e -> {
            int selectedRow = trainListTable.getSelectedRow();
            if (selectedRow != -1) {
                selectedTrain = String.format("Train ID: %s, Train Name: %s, Time: %s",
                        data[selectedRow][0], data[selectedRow][1], data[selectedRow][2]);
                trainListFrame.dispose();
                createPaymentPage();
            } else {
                JOptionPane.showMessageDialog(trainListFrame, "Please select a train.");
            }
        });

        trainListFrame.add(trainListLabel);
        trainListFrame.add(scrollPane);
        trainListFrame.add(submitButton);
        trainListFrame.setVisible(true);
    }

    private static void createPaymentPage() {
        JFrame paymentFrame = new JFrame("Payment Page");
        paymentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paymentFrame.setSize(470, 685);
        paymentFrame.setLayout(null);

        JLabel numberLabel = new JLabel("Number:");
        numberLabel.setBounds(60, 115, 80, 30);
        JTextField numberField = new JTextField();
        numberField.setBounds(140, 115, 200, 30);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(60, 170, 80, 30);
        JTextField amountField = new JTextField();
        amountField.setBounds(140, 170, 200, 30);

        JLabel refLabel = new JLabel("Reference:");
        refLabel.setBounds(60, 225, 80, 30);
        JTextField refField = new JTextField();
        refField.setBounds(140, 225, 200, 30);

        JLabel pinLabel = new JLabel("Pin:");
        pinLabel.setBounds(60, 280, 80, 30);
        JPasswordField pinField = new JPasswordField();
        pinField.setBounds(140, 280, 200, 30);

        JLabel paymentTypeLabel = new JLabel("Payment Type:");
        paymentTypeLabel.setBounds(60, 335, 100, 30);
        String[] paymentTypes = {"bKash", "nagad", "Visa Card", "MasterCard"};
        JComboBox<String> paymentTypeComboBox = new JComboBox<>(paymentTypes);
        paymentTypeComboBox.setBounds(170, 335, 170, 30);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(175, 480, 80, 30);
        submitButton.addActionListener(e -> {
            paymentInfo = String.format("Number: %s, Amount: %s, Payment Type: %s",
                    numberField.getText(), amountField.getText(), paymentTypeComboBox.getSelectedItem());
            paymentFrame.dispose();
            createThankYouPage();
        });

        paymentFrame.add(numberLabel);
        paymentFrame.add(numberField);
        paymentFrame.add(amountLabel);
        paymentFrame.add(amountField);
        paymentFrame.add(refLabel);
        paymentFrame.add(refField);
        paymentFrame.add(pinLabel);
        paymentFrame.add(pinField);
        paymentFrame.add(paymentTypeLabel);
        paymentFrame.add(paymentTypeComboBox);
        paymentFrame.add(submitButton);
        paymentFrame.setVisible(true);
    }

    private static void createThankYouPage() {
        JFrame thankYouFrame = new JFrame("Thank You Page");
        thankYouFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thankYouFrame.setSize(470, 685);
        thankYouFrame.setLayout(null);

        JLabel thankYouLabel = new JLabel("Thank You", SwingConstants.CENTER);
        thankYouLabel.setBounds(35, 200, 400, 60);
        thankYouLabel.setFont(new Font(thankYouLabel.getFont().getName(), Font.BOLD, 21));

        JLabel bookedInfoLabel = new JLabel("<html>Booked Ticket Info:<br>" + selectedTrain + "<br>" + paymentInfo + "</html>", SwingConstants.CENTER);
        bookedInfoLabel.setBounds(35, 280, 400, 100);

        thankYouFrame.add(thankYouLabel);
        thankYouFrame.add(bookedInfoLabel);
        thankYouFrame.setVisible(true);
    }
}
