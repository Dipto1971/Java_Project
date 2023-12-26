package TeaShop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class HomePage extends JFrame {
    private JTextArea orderTextArea;
    private JLabel totalCostLabel;
    private double totalCost;

    public HomePage() {
        setTitle("Tea Shop - Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initComponents();
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(176, 240, 240));
        setVisible(true);
    }

    private void initComponents() {
        JLabel menuLabel = new JLabel("Menu");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 20));
        menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(menuLabel, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(menuPanel, BorderLayout.CENTER);

        // Tea items
        addMenuItem(menuPanel, "Tea", "Chamomile Tea", 120,
                "Experience tranquility in a cup with our soothing Chamomile Tea. Infused with the delicate essence of chamomile flowers, this tea is a remedy for relaxation. Let the subtle floral notes wash away the stress of the day, leaving you in a state of calm and comfort.");

        addMenuItem(menuPanel, "Tea", "Peppermint Tea", 140,
                "Invigorate your senses with our Peppermint Tea. A refreshing blend of peppermint leaves, this tea is a burst of coolness with every sip. Perfect for rejuvenating your mind and body, it's an ideal choice for those seeking a revitalizing and minty flavor.");

        addMenuItem(menuPanel, "Tea", "Ginger Tea", 80,
                "Spice up your tea time with our invigorating Ginger Tea. The zesty warmth of ginger creates a comforting brew, perfect for those looking to add a touch of spice to their day. Enjoy the rich, aromatic flavor that ginger brings to this delightful cup of tea.");

        addMenuItem(menuPanel, "Tea", "Hibiscus Tea", 90,
                "Immerse yourself in the vibrant and refreshing taste of our Hibiscus Tea. This floral infusion delights the palate with its tart and fruity notes. Packed with antioxidants, it not only tantalizes your taste buds but also offers a healthful experience in every sip.");

        addMenuItem(menuPanel, "Tea", "Earl Grey Tea", 110,
                "Savor the sophistication of our Earl Grey Tea. A classic blend of black tea infused with bergamot, this tea offers a fragrant and citrusy experience.");

        addMenuItem(menuPanel, "Tea", "Green Tea", 100,
                "Embrace the health benefits of our Green Tea. Rich in antioxidants, this tea offers a delicate and refreshing flavor that revitalizes your senses.");

        // Coffee items
        addMenuItem(menuPanel, "Coffee", "Iced Coffee", 90,
                "Cool down with our Iced Coffee. Perfectly brewed and served over ice, it's a delightful way to enjoy coffee on a warm day.");

        addMenuItem(menuPanel, "Coffee", "Mocha", 150,
                "Indulge in the decadence of our Mocha. A harmonious blend of coffee, chocolate, and steamed milk, this drink is a treat for your taste buds.");

        // Cookies
        addMenuItem(menuPanel, "Cookies", "Chocolate Chip Cookie", 50,
                "Indulge in the classic goodness of our Chocolate Chip Cookie. Loaded with chocolate chips, it's a cookie lover's delight.");

        addMenuItem(menuPanel, "Cookies", "Oatmeal Raisin Cookie", 45,
                "Enjoy the wholesome flavor of our Oatmeal Raisin Cookie. Packed with oats and plump raisins, it's a deliciously chewy treat.");

        // Brownies
        addMenuItem(menuPanel, "Brownies", "Walnut Brownie", 160,
                "Experience the nutty goodness of our Walnut Brownie. Loaded with walnuts, this brownie offers a delightful crunch.");

        addMenuItem(menuPanel, "Brownies", "Peanut Butter Swirl Brownie", 180,
                "Satisfy your sweet and savory cravings with our Peanut Butter Swirl Brownie. A perfect blend of rich chocolate and creamy peanut butter.");

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BorderLayout());
        add(orderPanel, BorderLayout.SOUTH);

        orderTextArea = new JTextArea();
        orderTextArea.setEditable(false);
        orderPanel.add(new JScrollPane(orderTextArea), BorderLayout.CENTER);

        totalCostLabel = new JLabel("Total Cost: 0 TK");
        orderPanel.add(totalCostLabel, BorderLayout.SOUTH);

        JButton orderButton = new JButton("Place Order");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeOrder();
            }
        });
        orderPanel.add(orderButton, BorderLayout.NORTH);
    }

    private void addMenuItem(JPanel panel, String category, String itemName, double price, String description) {
        JButton orderButton = new JButton("Order");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToOrder(category, itemName, price);
            }
        });

        JButton detailsButton = new JButton("Details");
        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showItemDescription(itemName, description);
            }
        });

        JLabel itemLabel = new JLabel(itemName + " - " + price + " TK");
        itemLabel.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = panel.getComponentCount() / 3;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(itemLabel, gbc);

        panel.add(orderButton, gbc);

        panel.add(detailsButton, gbc);
    }

    private void addToOrder(String category, String itemName, double price) {
        totalCost += price;
        orderTextArea.append(category + ": " + itemName + " - " + price + " TK\n");
        totalCostLabel.setText("Total Cost: " + totalCost + " TK");
    }

    private void placeOrder() {
        JOptionPane.showMessageDialog(this, "Order placed successfully!\nTotal Cost: " + totalCost + " TK",
                "Order Placed", JOptionPane.INFORMATION_MESSAGE);

    }

    private void showItemDescription(String itemName, String description) {
        JFrame descriptionFrame = new JFrame(itemName + " Description");
        descriptionFrame.setLayout(new BorderLayout());
        descriptionFrame.setSize(400, 300);

        JTextArea descriptionTextArea = new JTextArea(description);
        descriptionTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(descriptionTextArea);

        descriptionFrame.add(scrollPane, BorderLayout.CENTER);

        descriptionFrame.setLocationRelativeTo(this);
        descriptionFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage());
    }
}
