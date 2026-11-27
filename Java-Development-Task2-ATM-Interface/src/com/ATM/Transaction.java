package com.ATM;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    // Private fields
    private String type;
    private double amount;
    private String details;
    private LocalDateTime dateTime;

    // Constructor
    public Transaction(String type, double amount, String details) {

        this.type = type;
        this.amount = amount;
        this.details = details;
        this.dateTime = LocalDateTime.now();
    }

    // Getter for transaction type
    public String getType() {
        return type;
    }

    // Getter for transaction amount
    public double getAmount() {
        return amount;
    }

    // Getter for transaction details
    public String getDetails() {
        return details;
    }

    // Getter for transaction date and time
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // Display transaction details
    @Override
    public String toString() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return "Date & Time: " + dateTime.format(formatter)
                + " | Type: " + type
                + " | Amount: ₹" + String.format("%.2f", amount)
                + " | Details: " + details;
    }
}
