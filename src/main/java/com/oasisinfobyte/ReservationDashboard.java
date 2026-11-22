package com.oasisinfobyte;

import javax.swing.*;
import java.awt.*;

public class ReservationDashboard extends JFrame {

    public ReservationDashboard() {

        setTitle("Online Reservation System - Dashboard");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel(
                "ONLINE RESERVATION SYSTEM",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 20));

        JButton bookButton = new JButton("BOOK TICKET");
        JButton cancelButton = new JButton("CANCEL TICKET");
        JButton logoutButton = new JButton("LOGOUT");
        
        bookButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel panel = new JPanel(new GridLayout(3, 1, 15, 15));

        panel.setBorder(
                BorderFactory.createEmptyBorder(30, 80, 30, 80)
        );

        panel.add(bookButton);
        panel.add(cancelButton);
        panel.add(logoutButton);

        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        bookButton.addActionListener(e -> {
            new ReservationForm().setVisible(true);
        });

        cancelButton.addActionListener(e -> {
            new ReservationForm().setVisible(true);
        });
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginForm();
        });

        setVisible(true);
    }
}