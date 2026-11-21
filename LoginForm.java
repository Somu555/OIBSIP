package com.oasisinfobyte;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.security.MessageDigest;

public class LoginForm extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginForm() {

        setTitle("Online Reservation System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel(
                "ONLINE RESERVATION SYSTEM",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 18));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        panel.add(new JLabel());
        panel.add(loginButton);

        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {

        String username = usernameField.getText().trim();

        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Please enter username and password."
            );
            return;
        }

        String sql = "SELECT username FROM users "
                   + "WHERE username = ? AND password_hash = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, hashPassword(password));

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    JOptionPane.showMessageDialog(
                            this, "Login Successful!"
                    );
                    dispose();
                    new ReservationDashboard();

                    // Reservation screen will be opened in the next step.

                } else {
                    JOptionPane.showMessageDialog(
                            this, "Invalid username or password!"
                    );
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this, "Database Error: " + e.getMessage()
            );
            e.printStackTrace();
        }
    }

    private String hashPassword(String password) throws Exception {

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hash = md.digest(password.getBytes("UTF-8"));

        StringBuilder hex = new StringBuilder();

        for (byte b : hash) {
            hex.append(String.format("%02x", b));
        }

        return hex.toString();
    }
}