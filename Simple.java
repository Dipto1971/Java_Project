import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Simple {

    public static void main(String[] args) {
        welcomePage();
    }

    private static void welcomePage() {
        JFrame f = new JFrame("Welcome Page");
        final JLabel label = new JLabel();

        JLabel l3 = new JLabel(" Welcome to Pharmacy Management System ");
        l3.setBounds(65, 130, 400, 60);

        Font labelFont = new Font(l3.getFont().getName(), Font.PLAIN, 24);
        l3.setFont(labelFont);

        JButton b = new JButton("Click");
        b.setBounds(175, 380, 80, 30);

        f.add(l3);
        f.add(b);
        f.setSize(470, 685);
        f.setLayout(null);
        f.setVisible(true);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginPage();
                f.dispose();
            }
        });
    }

    private static void loginPage() {
        JFrame f = new JFrame("Login Page");
        final JLabel label = new JLabel();
        label.setBounds(150, 190, 500, 500);
        final JPasswordField value = new JPasswordField();
        value.setBounds(200, 326, 100, 30);
        JLabel l1 = new JLabel("Username:");
        l1.setBounds(120, 20, 300, 510);
        JLabel l2 = new JLabel("Password:");
        l2.setBounds(120, 75, 300, 531);
        JLabel l3 = new JLabel(" Pharmacy Management System ");
        l3.setBounds(65, 130, 400, 60);

        Font labelFont = new Font(l3.getFont().getName(), Font.PLAIN, 24);
        l3.setFont(labelFont);
        JButton b = new JButton("Login");
        b.setBounds(175, 380, 80, 30);

        final JTextField text = new JTextField();
        text.setBounds(200, 262, 100, 30);

        f.add(value);
        f.add(l1);
        f.add(label);
        f.add(l2);
        f.add(l3);
        f.add(b);
        f.add(text);
        f.setSize(470, 685);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = text.getText();
                String password = new String(value.getPassword());
                int t = 1;

                if (t == 1) {
                    f.dispose();
                    medicineSelectionPage();
                } else {
                    label.setText("Invalid login");
                }
            }
        });
    }

    private static void medicineSelectionPage() {
        JFrame a = new JFrame("Main Page");
        JLabel l1 = new JLabel("What Type Of Problem You Are Facing?");
        l1.setBounds(120, 20, 300, 60);

        JButton headaceButton = new JButton("headache");
        headaceButton.setBounds(160, 130, 100, 50);

        JButton feverButton = new JButton("fever");
        feverButton.setBounds(160, 200, 100, 50);

        JButton allergyButton = new JButton("allergy");
        allergyButton.setBounds(160, 270, 100, 50);

        a.add(l1);
        a.add(headaceButton);
        a.add(feverButton);
        a.add(allergyButton);

        a.setSize(470, 685);
        a.setLayout(null);
        a.setVisible(true);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        headaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                a.dispose();
                selectMedicine("Headache");
            }
        });

        feverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                a.dispose();
                selectMedicine("Fever");
            }
        });

        allergyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                a.dispose();
                selectMedicine("Allergy");
            }
        });
    }

    private static void selectMedicine(String problem) {
        JFrame c = new JFrame("Medicine Page");
        JLabel l1 = new JLabel("Available medicine List ");
        l1.setBounds(150, 20, 300, 60);
        JLabel l2 = new JLabel("Select Your medicine SERIAL");
        l2.setBounds(150, 390, 300, 60);
        JLabel l3 = new JLabel("Price");
        l3.setBounds(150, 390, 300, 60);

        c.add(l2);
        c.add(l1);
        String data[][] = {
                { "1", "Square", "Ace", "500", "350" },
                { "2", "Square", "Adiva", "600", "400" },
                { "3", "Square", "Alatrol", "5", "20" },
                { "4", "Square", "Angilock", "100", "50" },
                { "5", "Square", "Bisocor", "5", "20" },
                { "6", "Beximco", "D-Rise ", "2000", "500" },
                { "7", "Beximco", "Arline", "400", "70" },
                { "8", "Beximco", "Diavix", "300", "60" },
                { "9", "Beximco", "Evo", "750", "100" },
                { "10", "Beximco", "Intracef", "125", "50" },
                { "11", "Popular", "Avona", "8", "15" },
                { "12", "Popular", "Fexoral", "180", "80" },
                { "13", "Popular", "M-beg", "50", "40" },
                { "14", "Popular", "Pofol", "200", "45" },
                { "15", "Popular", "TPS", "500", "150" },
        };

        String column[] = { "SERIAL", "COMPANY NAME", "MEDICINE NAME", "POWER IN MG", "Price" };

        JTable jt = new JTable(data, column);
        jt.setBounds(30, 100, 400, 250);

        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(30, 100, 400, 300);
        JButton b = new JButton("Submit");
        b.setBounds(175, 500, 80, 40);

        c.getContentPane().add(sp);

        String[] medicineTypes = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" };
        JComboBox<String> medicineComboBox = new JComboBox<>(medicineTypes);
        medicineComboBox.setBounds(30, 445, 150, 30);
        c.getContentPane().add(medicineComboBox);
        c.add(b);

        c.setSize(470, 685);
        c.setLayout(null);
        c.setVisible(true);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.dispose();
                showPaymentPage(problem, medicineComboBox.getSelectedItem().toString(), data);
            }
        });
    }

    private static void showPaymentPage(String problem, String selectedMedicine, String[][] medicineData) {
        JFrame paymentFrame = new JFrame("Payment Page");
        JLabel l1 = new JLabel("Selected Medicine " + selectedMedicine + " for " + problem);
        l1.setBounds(150, 20, 300, 60);

        JLabel l2 = new JLabel("Selected Medicine: " + selectedMedicine);
        l2.setBounds(50, 100, 400, 60);

        // Find the price for the selected medicine
        String price = "";
        for (String[] row : medicineData) {
            if (row[0].equals(selectedMedicine)) {
                price = row[4];
                break;
            }
        }

        JLabel l3 = new JLabel("Price: $" + price);
        l3.setBounds(50, 150, 400, 60);

        paymentFrame.add(l1);
        paymentFrame.add(l2);
        paymentFrame.add(l3);

        paymentFrame.setSize(400, 300);
        paymentFrame.setLayout(null);
        paymentFrame.setVisible(true);
        paymentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
