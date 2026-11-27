package com.ATM;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<String, Account> accounts;

    public Bank() {

        accounts = new HashMap<>();

        // Sample accounts
        accounts.put(
                "1001",
                new Account("1001", "Somanath", "1234", 10000.00)
        );

        accounts.put(
                "1002",
                new Account("1002", "Rahul", "5678", 25000.00)
        );
        accounts.put("1003",
        		new Account("1003","Sampath kurmar","1122",15000.00));
    }

    // Find account using account ID
    public Account getAccount(String accountId) {

        return accounts.get(accountId);
    }

    // Authenticate user
    public Account authenticate(String accountId, String pin) {

        Account account = accounts.get(accountId);

        if (account != null && account.checkPin(pin)) {
            return account;
        }

        return null;
    }

    // Transfer money between accounts
    public boolean transfer(Account sender,
                            String receiverId,
                            double amount) {

        // Validate amount
        if (amount <= 0) {
            return false;
        }

        // Find receiver account
        Account receiver = accounts.get(receiverId);

        // Check receiver account
        if (receiver == null) {
            return false;
        }

        // Prevent transferring to the same account
        if (sender.getAccountId().equals(receiverId)) {
            return false;
        }

        // Check sender balance
        if (sender.getBalance() < amount) {
            return false;
        }

        // Withdraw from sender
        sender.withdraw(amount);

        // Deposit into receiver
        receiver.receiveTransfer(amount);

        return true;
    }
}
