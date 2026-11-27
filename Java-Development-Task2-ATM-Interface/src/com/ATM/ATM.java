package com.ATM;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner scanner;

    private Account currentAccount;

    private ArrayList<Transaction> transactions;

    public ATM(Bank bank) {

        this.bank = bank;

        scanner = new Scanner(System.in);

        transactions = new ArrayList<>();
    }

    // Start ATM application
    public void start() {

        System.out.println("================================");
        System.out.println("       WELCOME TO ATM");
        System.out.println("================================");

        if (!login()) {
            System.out.println("Too many incorrect attempts.");
            System.out.println("Your access has been denied.");
            return;
        }

        System.out.println("\nLogin successful!");

        System.out.println(
                "Welcome, " + currentAccount.getAccountHolder()
        );

        showMenu();
    }

    // User login
    private boolean login() {

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("\nEnter User ID: ");
            String userId = scanner.nextLine();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            currentAccount = bank.authenticate(userId, pin);

            if (currentAccount != null) {
                return true;
            }

            attempts++;

            System.out.println("Invalid User ID or PIN.");

            System.out.println(
                    "Remaining attempts: " + (3 - attempts)
            );
        }

        return false;
    }

    // Display main menu
    private void showMenu() {

        boolean running = true;

        while (running) {

            System.out.println("\n================================");
            System.out.println("            ATM MENU");
            System.out.println("================================");

            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.println("================================");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    showTransactionHistory();
                    break;

                case "2":
                    withdraw();
                    break;

                case "3":
                    deposit();
                    break;

                case "4":
                    transfer();
                    break;

                case "5":
                    System.out.println(
                            "\nThank you for using our ATM!"
                    );

                    System.out.println("Goodbye!");

                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }

    // Transaction history
    private void showTransactionHistory() {

        System.out.println("\n===== TRANSACTION HISTORY =====");

        if (transactions.isEmpty()) {

            System.out.println("No transactions available.");

            return;
        }

        for (Transaction transaction : transactions) {

            System.out.println(transaction);
        }

        System.out.println("================================");
    }

    // Withdraw money
    private void withdraw() {

        System.out.println("\n===== WITHDRAW =====");

        double amount = readAmount();

        if (amount <= 0) {

            System.out.println("Invalid withdrawal amount.");

            return;
        }

        // Check balance before withdrawal
        if (currentAccount.getBalance() < amount) {

            System.out.println("Insufficient Funds");

            return;
        }

        // Withdraw money
        boolean success = currentAccount.withdraw(amount);

        if (success) {

            transactions.add(
                    new Transaction(
                            "Withdrawal",
                            amount,
                            "Cash withdrawn"
                    )
            );

            System.out.println("Withdrawal successful!");

            System.out.printf(
                    "Remaining Balance: ₹%.2f%n",
                    currentAccount.getBalance()
            );
        }
    }

    // Deposit money
    private void deposit() {

        System.out.println("\n===== DEPOSIT =====");

        double amount = readAmount();

        if (amount <= 0) {

            System.out.println("Invalid deposit amount.");

            return;
        }

        // Deposit money
        currentAccount.deposit(amount);

        transactions.add(
                new Transaction(
                        "Deposit",
                        amount,
                        "Cash deposited"
                )
        );

        System.out.println("Deposit successful!");

        System.out.printf(
                "Updated Balance: ₹%.2f%n",
                currentAccount.getBalance()
        );
    }

    // Transfer money
    private void transfer() {

        System.out.println("\n===== TRANSFER =====");

        System.out.print("Enter recipient account ID: ");

        String receiverId = scanner.nextLine();

        double amount = readAmount();

        if (amount <= 0) {

            System.out.println("Invalid transfer amount.");

            return;
        }

        // Check recipient account
        Account receiver = bank.getAccount(receiverId);

        if (receiver == null) {

            System.out.println("Recipient account not found.");

            return;
        }

        // Prevent transfer to the same account
        if (receiverId.equals(currentAccount.getAccountId())) {

            System.out.println(
                    "Cannot transfer to your own account."
            );

            return;
        }

        // Check balance before transfer
        if (currentAccount.getBalance() < amount) {

            System.out.println("Insufficient Funds");

            return;
        }

        // Perform transfer
        boolean success = bank.transfer(
                currentAccount,
                receiverId,
                amount
        );

        if (success) {

            transactions.add(
                    new Transaction(
                            "Transfer",
                            amount,
                            "Transferred to account " + receiverId
                    )
            );

            System.out.println("Transfer successful!");

            System.out.printf(
                    "Remaining Balance: ₹%.2f%n",
                    currentAccount.getBalance()
            );
        }
    }

    // Read amount from user
    private double readAmount() {

        while (true) {

            System.out.print("Enter amount: ₹");

            String input = scanner.nextLine();

            try {

                return Double.parseDouble(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }
}
