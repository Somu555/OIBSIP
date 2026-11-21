package com.oasisinfobyte;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ReservationForm extends JFrame {

    private JTextField passengerField;
    private JTextField trainNumberField;
    private JTextField trainNameField;
    private JTextField dateField;
    private JTextField sourceField;
    private JTextField destinationField;
    private JButton logoutButton = new JButton("Logout");

    private JComboBox<String> classBox;

    public ReservationForm() {

        setTitle("Online Train Reservation System");
        setSize(550, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(12, 2, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        passengerField = new JTextField();
        trainNumberField = new JTextField();
        trainNameField = new JTextField();

        trainNameField.setEditable(false);

        classBox = new JComboBox<>(new String[]{
                "Sleeper",
                "AC 3 Tier",
                "AC 2 Tier",
                "AC First Class"
        });

        dateField = new JTextField();
        sourceField = new JTextField();
        destinationField = new JTextField();

        // Buttons
        JButton fetchButton = new JButton("Fetch Train");
        JButton bookButton = new JButton("Book Ticket");
        JButton fetchTicketButton = new JButton("Fetch Ticket");
        JButton cancelTicketButton = new JButton("Cancel Ticket");
        JButton viewBookingsButton = new JButton("View All Bookings");

        // Passenger Name
        panel.add(new JLabel("Passenger Name:"));
        panel.add(passengerField);

        // Train Number
        panel.add(new JLabel("Train Number:"));
        panel.add(trainNumberField);

        // Train Name
        panel.add(new JLabel("Train Name:"));
        panel.add(trainNameField);

        // Class Type
        panel.add(new JLabel("Class Type:"));
        panel.add(classBox);

        // Journey Date
        panel.add(new JLabel("Journey Date (YYYY-MM-DD):"));
        panel.add(dateField);

        // Source
        panel.add(new JLabel("Source:"));
        panel.add(sourceField);

        // Destination
        panel.add(new JLabel("Destination:"));
        panel.add(destinationField);

        // Buttons
        panel.add(fetchButton);
        panel.add(bookButton);

        // Fetch Ticket Button
        panel.add(new JLabel(""));
        panel.add(fetchTicketButton);

        add(panel);
        panel.add(new JLabel(""));
        panel.add(cancelTicketButton);
        
        panel.add(new JLabel(""));
        panel.add(viewBookingsButton);
        panel.add(new JLabel()); // empty space for alignment
        panel.add(logoutButton);
        
        // Button Actions
        fetchButton.addActionListener(e -> fetchTrain());

        bookButton.addActionListener(e -> bookTicket());

        fetchTicketButton.addActionListener(e -> fetchTicket());
        cancelTicketButton.addActionListener(e -> cancelTicket());
        viewBookingsButton.addActionListener(e -> viewAllBookings());
        logoutButton.addActionListener(e -> logout());

        setVisible(true);
    }

    // FETCH TRAIN METHOD

    private void fetchTrain() {

        String sql = "SELECT train_name FROM trains WHERE train_number = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1,
                    Integer.parseInt(trainNumberField.getText().trim()));

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    trainNameField.setText(rs.getString("train_name"));

                } else {

                    trainNameField.setText("");

                    JOptionPane.showMessageDialog(this,
                            "Train not found!");
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "Enter a valid train number.");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage());
        }
    }

    // BOOK TICKET METHOD

    private void bookTicket() {

        try {

            String passenger = passengerField.getText().trim();

            int trainNumber = Integer.parseInt(
                    trainNumberField.getText().trim());

            String trainName = trainNameField.getText().trim();

            String classType = classBox.getSelectedItem().toString();

            LocalDate journeyDate = LocalDate.parse(
                    dateField.getText().trim());

            String source = sourceField.getText().trim();

            String destination = destinationField.getText().trim();

            if (passenger.isEmpty() || trainName.isEmpty()
                    || source.isEmpty() || destination.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please fill all the data.");

                return;
            }

            if (!journeyDate.isAfter(LocalDate.now())) {

                JOptionPane.showMessageDialog(this,
                        "Choose a future journey date.");

                return;
            }

            if (source.equalsIgnoreCase(destination)) {

                JOptionPane.showMessageDialog(this,
                        "Source and destination cannot be the same.");

                return;
            }

            long pnr = generatePNR();

            String sql = """
                    INSERT INTO reservations
                    (pnr, passenger_name, train_number, train_name,
                     class_type, journey_date, source, destination)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setLong(1, pnr);
                ps.setString(2, passenger);
                ps.setInt(3, trainNumber);
                ps.setString(4, trainName);
                ps.setString(5, classType);
                ps.setDate(6, Date.valueOf(journeyDate));
                ps.setString(7, source);
                ps.setString(8, destination);

                int rows = ps.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(this,
                            "Ticket Booked Successfully!\nYour PNR: " + pnr);

                    clearFields();
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "Enter a valid train number.");

        } catch (java.time.format.DateTimeParseException e) {

            JOptionPane.showMessageDialog(this,
                    "Enter date in YYYY-MM-DD format.");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Booking Failed: " + e.getMessage());

            e.printStackTrace();
        }
    }

    // GENERATE PNR

    private long generatePNR() {

        return ThreadLocalRandom.current()
                .nextLong(1000000000L, 9999999999L);
    }

    // FETCH TICKET METHOD - NEWLY ADDED

    private void fetchTicket() {

        String input = JOptionPane.showInputDialog(
                this, "Enter Your PNR Number:");

        if (input == null || input.trim().isEmpty()) {
            return;
        }

        String sql = """
                SELECT pnr, passenger_name, train_number,
                       train_name, class_type, journey_date,
                       source, destination
                FROM reservations
                WHERE pnr = ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, Long.parseLong(input.trim()));

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    JOptionPane.showMessageDialog(this,

                            "TICKET DETAILS\n\n"

                            + "PNR: " + rs.getLong("pnr")

                            + "\nPassenger Name: "
                            + rs.getString("passenger_name")

                            + "\nTrain Number: "
                            + rs.getInt("train_number")

                            + "\nTrain Name: "
                            + rs.getString("train_name")

                            + "\nClass Type: "
                            + rs.getString("class_type")

                            + "\nJourney Date: "
                            + rs.getDate("journey_date")

                            + "\nSource: "
                            + rs.getString("source")

                            + "\nDestination: "
                            + rs.getString("destination"));

                } else {

                    JOptionPane.showMessageDialog(this,
                            "No Ticket Found for this PNR!");
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "Please enter a valid PNR number.");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(this,
                    "Database Error: " + e.getMessage());

            e.printStackTrace();
        }
    }

    // CLEAR FIELDS

    private void clearFields() {
    	// CANCEL TICKET METHOD
    }
    
    	private void cancelTicket() {

    	    // Ask the user for PNR
    	    String input = JOptionPane.showInputDialog(
    	            this,
    	            "Enter PNR Number to Cancel:"
    	    );

    	    // Stop if user clicks Cancel or enters nothing
    	    if (input == null || input.trim().isEmpty()) {
    	        return;
    	    }

    	    try {

    	        // Convert PNR into long
    	        long pnr = Long.parseLong(input.trim());

    	        // SQL query to check ticket
    	        String checkSql =
    	                "SELECT passenger_name, train_name "
    	                + "FROM reservations WHERE pnr = ?";

    	        try (Connection con = DBConnection.getConnection();
    	             PreparedStatement checkPs =
    	                     con.prepareStatement(checkSql)) {

    	            checkPs.setLong(1, pnr);

    	            try (ResultSet rs = checkPs.executeQuery()) {

    	                // Check whether ticket exists
    	                if (!rs.next()) {

    	                    JOptionPane.showMessageDialog(
    	                            this,
    	                            "Ticket not found for this PNR!"
    	                    );

    	                    return;
    	                }

    	                // Get passenger and train details
    	                String passenger = rs.getString("passenger_name");
    	                String train = rs.getString("train_name");

    	                // Confirm cancellation
    	                int choice = JOptionPane.showConfirmDialog(
    	                        this,
    	                        "Passenger: " + passenger
    	                        + "\nTrain: " + train
    	                        + "\nPNR: " + pnr
    	                        + "\n\nAre you sure you want to cancel?",
    	                        "Confirm Cancellation",
    	                        JOptionPane.YES_NO_OPTION
    	                );

    	                // Stop if user selects No
    	                if (choice != JOptionPane.YES_OPTION) {
    	                    return;
    	                }

    	                // SQL query to delete reservation
    	                String deleteSql =
    	                        "DELETE FROM reservations WHERE pnr = ?";

    	                try (PreparedStatement deletePs =
    	                             con.prepareStatement(deleteSql)) {

    	                    deletePs.setLong(1, pnr);

    	                    int rows = deletePs.executeUpdate();

    	                    if (rows > 0) {

    	                        JOptionPane.showMessageDialog(
    	                                this,
    	                                "Ticket Cancelled Successfully!\nPNR: " + pnr
    	                        );

    	                    } else {

    	                        JOptionPane.showMessageDialog(
    	                                this,
    	                                "Ticket cancellation failed!"
    	                        );
    	                    }
    	                }
    	            }
    	        }

    	    } catch (NumberFormatException e) {

    	        JOptionPane.showMessageDialog(
    	                this,
    	                "Please enter a valid PNR number!"
    	        );

    	    } catch (SQLException e) {

    	        JOptionPane.showMessageDialog(
    	                this,
    	                "Database Error: " + e.getMessage()
    	        );

    	        e.printStackTrace();
    	    }}
    	// VIEW ALL BOOKINGS METHOD

    	private void viewAllBookings() {

    	    String sql = """
    	            SELECT pnr, passenger_name, train_number,
    	                   train_name, class_type, journey_date,
    	                   source, destination
    	            FROM reservations
    	            ORDER BY journey_date
    	            """;

    	    try (Connection con = DBConnection.getConnection();
    	         PreparedStatement ps = con.prepareStatement(sql);
    	         ResultSet rs = ps.executeQuery()) {

    	        // Create table model
    	        javax.swing.table.DefaultTableModel model =
    	                new javax.swing.table.DefaultTableModel();

    	        // Add column names
    	        model.addColumn("PNR");
    	        model.addColumn("Passenger");
    	        model.addColumn("Train No");
    	        model.addColumn("Train Name");
    	        model.addColumn("Class");
    	        model.addColumn("Journey Date");
    	        model.addColumn("Source");
    	        model.addColumn("Destination");

    	        // Add database records
    	        while (rs.next()) {

    	            model.addRow(new Object[]{

    	                    rs.getLong("pnr"),
    	                    rs.getString("passenger_name"),
    	                    rs.getInt("train_number"),
    	                    rs.getString("train_name"),
    	                    rs.getString("class_type"),
    	                    rs.getDate("journey_date"),
    	                    rs.getString("source"),
    	                    rs.getString("destination")

    	            });
    	        }

    	        // Create JTable
    	        JTable table = new JTable(model);

    	        // Prevent editing table values
    	        table.setEnabled(false);

    	        // Add table to scroll pane
    	        JScrollPane scrollPane = new JScrollPane(table);

    	        // Create table window
    	        JFrame frame = new JFrame("All Bookings");

    	        frame.add(scrollPane);

    	        frame.setSize(1000, 400);

    	        frame.setLocationRelativeTo(this);

    	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    	        frame.setVisible(true);

    	    } catch (SQLException e) {

    	        JOptionPane.showMessageDialog(
    	                this,
    	                "Database Error: " + e.getMessage()
    	        );

    	        e.printStackTrace();
    	    }}
    	    private void logout() {
    	        int choice = JOptionPane.showConfirmDialog(
    	                this,
    	                "Are you sure you want to logout?",
    	                "Confirm Logout",
    	                JOptionPane.YES_NO_OPTION
    	        );

    	        if (choice == JOptionPane.YES_OPTION) {
    	            JOptionPane.showMessageDialog(
    	                    this,
    	                    "You have logged out successfully!"
    	            );

    	            dispose(); // Close the dash board

    	            LoginForm login = new LoginForm();
    	            login.setVisible(true); // Show the login screen
    	        }
    	    
    	
        passengerField.setText("");
        trainNumberField.setText("");
        trainNameField.setText("");
        dateField.setText("");
        sourceField.setText("");
        destinationField.setText("");

        classBox.setSelectedIndex(0);
    }
}