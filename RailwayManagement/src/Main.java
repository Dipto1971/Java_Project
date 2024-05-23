import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

class Station {
    String name;
    List<Track> tracks = new ArrayList<>();

    Station(String name) {
        this.name = name;
    }
}

class Track {
    Station from;
    Station to;
    int distance; // not used currently but can be expanded

    Track(Station from, Station to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
}

class Train {
    String id;
    String name;
    Map<Station, String[]> schedule = new LinkedHashMap<>();
    int delay; // delay in minutes

    Train(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Booking {
    Train train;
    String from;
    String to;
    String date;
    String passengerName;
    String seat;
    String paymentInfo;

    Booking(Train train, String from, String to, String date, String passengerName, String seat, String paymentInfo) {
        this.train = train;
        this.from = from;
        this.to = to;
        this.date = date;
        this.passengerName = passengerName;
        this.seat = seat;
        this.paymentInfo = paymentInfo;
    }
}

public class Main {
    private static Map<String, Station> stations = new HashMap<>();
    private static Map<String, Train> trains = new HashMap<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static Train selectedTrain;
    private static String fromStation;
    private static String toStation;
    private static String travelDate;

    public static void main(String[] args) {
        setupData();
        SwingUtilities.invokeLater(Main::createWelcomePage);
    }

    private static void setupData() {
        Station dhaka = new Station("Dhaka");
        Station chittagong = new Station("Chittagong");
        Station sylhet = new Station("Sylhet");
        Station rajshahi = new Station("Rajshahi");

        stations.put("Dhaka", dhaka);
        stations.put("Chittagong", chittagong);
        stations.put("Sylhet", sylhet);
        stations.put("Rajshahi", rajshahi);

        dhaka.tracks.add(new Track(dhaka, chittagong, 245));
        chittagong.tracks.add(new Track(chittagong, dhaka, 245));
        dhaka.tracks.add(new Track(dhaka, sylhet, 286));
        sylhet.tracks.add(new Track(sylhet, dhaka, 286));
        dhaka.tracks.add(new Track(dhaka, rajshahi, 343));
        rajshahi.tracks.add(new Track(rajshahi, dhaka, 343));

        Train subornoExpress = new Train("111", "Suborno Express");
        subornoExpress.schedule.put(dhaka, new String[]{"06:00", "06:15"});
        subornoExpress.schedule.put(chittagong, new String[]{"10:30", "10:45"});
        trains.put("111", subornoExpress);

        Train mohanagarExpress = new Train("222", "Mohanagar Express");
        mohanagarExpress.schedule.put(dhaka, new String[]{"07:00", "07:15"});
        mohanagarExpress.schedule.put(sylhet, new String[]{"11:30", "11:45"});
        trains.put("222", mohanagarExpress);
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
                createSearchPage();
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

    private static void createSearchPage() {
        JFrame searchFrame = new JFrame("Search Trains");
        searchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchFrame.setSize(470, 685);
        searchFrame.setLayout(null);

        JLabel headerLabel = new JLabel("Search for Trains", SwingConstants.CENTER);
        headerLabel.setBounds(35, 50, 400, 60);
        headerLabel.setFont(new Font(headerLabel.getFont().getName(), Font.PLAIN, 21));

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setBounds(60, 150, 100, 30);
        JTextField fromField = new JTextField();
        fromField.setBounds(140, 150, 200, 30);

        JLabel toLabel = new JLabel("To:");
        toLabel.setBounds(60, 200, 100, 30);
        JTextField toField = new JTextField();
        toField.setBounds(140, 200, 200, 30);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(60, 250, 100, 30);
        JTextField dateField = new JTextField();
        dateField.setBounds(140, 250, 200, 30);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(175, 300, 100, 30);
        searchButton.addActionListener(e -> {
            fromStation = fromField.getText();
            toStation = toField.getText();
            travelDate = dateField.getText();
            searchFrame.dispose();
            createTrainListPage();
        });

        searchFrame.add(headerLabel);
        searchFrame.add(fromLabel);
        searchFrame.add(fromField);
        searchFrame.add(toLabel);
        searchFrame.add(toField);
        searchFrame.add(dateLabel);
        searchFrame.add(dateField);
        searchFrame.add(searchButton);
        searchFrame.setVisible(true);
    }

    private static void createTrainListPage() {
        JFrame trainListFrame = new JFrame("Train List");
        trainListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        trainListFrame.setSize(470, 685);
        trainListFrame.setLayout(null);

        JLabel trainListLabel = new JLabel("Available Trains", SwingConstants.CENTER);
        trainListLabel.setBounds(35, 20, 400, 60);

        String[][] data = getTrainData(fromStation, toStation);
        String[] column = {"ID", "Train Name", "Arrival", "Departure"};
        JTable trainListTable = new JTable(data, column);
        trainListTable.setBounds(30, 100, 400, 250);
        JScrollPane sp = new JScrollPane(trainListTable);
        sp.setBounds(30, 100, 400, 250);

        JButton bookButton = new JButton("Book");
        bookButton.setBounds(175, 380, 100, 30);
        bookButton.addActionListener(e -> {
            int selectedRow = trainListTable.getSelectedRow();
            if (selectedRow >= 0) {
                String selectedTrainId = (String) trainListTable.getValueAt(selectedRow, 0);
                selectedTrain = trains.get(selectedTrainId);
                trainListFrame.dispose();
                createBookingPage();
            }
        });

        trainListFrame.add(trainListLabel);
        trainListFrame.add(sp);
        trainListFrame.add(bookButton);
        trainListFrame.setVisible(true);
    }

    private static String[][] getTrainData(String from, String to) {
        List<String[]> trainData = new ArrayList<>();
        for (Train train : trains.values()) {
            if (train.schedule.containsKey(stations.get(from)) && train.schedule.containsKey(stations.get(to))) {
                String[] scheduleFrom = train.schedule.get(stations.get(from));
                String[] scheduleTo = train.schedule.get(stations.get(to));
                trainData.add(new String[]{train.id, train.name, scheduleFrom[0], scheduleTo[1]});
            }
        }
        return trainData.toArray(new String[0][0]);
    }

    private static void createBookingPage() {
        JFrame bookingFrame = new JFrame("Booking Details");
        bookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bookingFrame.setSize(470, 685);
        bookingFrame.setLayout(null);

        JLabel headerLabel = new JLabel("Passenger Details", SwingConstants.CENTER);
        headerLabel.setBounds(35, 20, 400, 60);
        headerLabel.setFont(new Font(headerLabel.getFont().getName(), Font.PLAIN, 21));

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(60, 100, 100, 30);
        JTextField nameField = new JTextField();
        nameField.setBounds(140, 100, 200, 30);

        JLabel seatLabel = new JLabel("Seat:");
        seatLabel.setBounds(60, 150, 100, 30);
        JTextField seatField = new JTextField();
        seatField.setBounds(140, 150, 200, 30);

        JLabel paymentLabel = new JLabel("Payment Info:");
        paymentLabel.setBounds(60, 200, 100, 30);
        JTextField paymentField = new JTextField();
        paymentField.setBounds(140, 200, 200, 30);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBounds(175, 300, 100, 30);
        confirmButton.addActionListener(e -> {
            String passengerName = nameField.getText();
            String seat = seatField.getText();
            String paymentInfo = paymentField.getText();
            Booking booking = new Booking(selectedTrain, fromStation, toStation, travelDate, passengerName, seat, paymentInfo);
            bookings.add(booking);
            bookingFrame.dispose();
            createThankYouPage(booking);
        });

        bookingFrame.add(headerLabel);
        bookingFrame.add(nameLabel);
        bookingFrame.add(nameField);
        bookingFrame.add(seatLabel);
        bookingFrame.add(seatField);
        bookingFrame.add(paymentLabel);
        bookingFrame.add(paymentField);
        bookingFrame.add(confirmButton);
        bookingFrame.setVisible(true);
    }

    private static void createThankYouPage(Booking booking) {
        JFrame thankYouFrame = new JFrame("Thank You");
        thankYouFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thankYouFrame.setSize(470, 685);
        thankYouFrame.setLayout(null);

        JLabel messageLabel = new JLabel("Thank you for booking!", SwingConstants.CENTER);
        messageLabel.setBounds(35, 100, 400, 60);
        messageLabel.setFont(new Font(messageLabel.getFont().getName(), Font.BOLD, 21));

        JLabel bookingDetailsLabel = new JLabel("<html>Booking Details:<br>"
                + "Train: " + booking.train.name + "<br>"
                + "From: " + booking.from + "<br>"
                + "To: " + booking.to + "<br>"
                + "Date: " + booking.date + "<br>"
                + "Passenger: " + booking.passengerName + "<br>"
                + "Seat: " + booking.seat + "<br>"
                + "Payment: " + booking.paymentInfo + "</html>", SwingConstants.CENTER);
        bookingDetailsLabel.setBounds(35, 200, 400, 200);

        thankYouFrame.add(messageLabel);
        thankYouFrame.add(bookingDetailsLabel);
        thankYouFrame.setVisible(true);
    }
}
