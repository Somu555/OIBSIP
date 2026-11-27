package com.ATM;

public class Account {

    // Private variables
    private String accountId;
    private String accountHolder;
    private String pin;
    private double balance;

    // Constructor
    public Account(String accountId, String accountHolder,
                   String pin, double balance) {

        this.accountId = accountId;
        this.accountHolder = accountHolder;
        this.pin = pin;
        this.balance = balance;
    }

    // Get account ID
    public String getAccountId() {
        return accountId;
    }

    // Get account holder name
    public String getAccountHolder() {
        return accountHolder;
    }

    // Check PIN
    public boolean checkPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    // Get account balance
    public double getBalance() {
        return balance;
    }

    // Deposit money
    public void deposit(double amount) {

        if (amount > 0) {
            balance += amount;
        }
    }

    // Withdraw money
    public boolean withdraw(double amount) {

        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;

        return true;
    }

    // Receive money during a transfer
    public void receiveTransfer(double amount) {

        if (amount > 0) {
            balance += amount;
        }
    }
}