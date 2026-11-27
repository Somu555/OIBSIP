# ATM Interface – Java Console Application

## Oasis Infobyte Java Development Internship (OIBSIP)

**Project Name:** ATM Interface  
**Task:** Task 2  
**Internship Track:** Java Development  
**Programming Language:** Java  
**JDK Version:** 21  
**IDE:** Eclipse IDE  
**Project Type:** Console-Based Application  

---

## 1. Project Overview

The ATM Interface is a console-based banking application developed using Java and Object-Oriented Programming (OOP).

This application simulates basic ATM operations. Users can log in using their User ID and PIN and perform different banking transactions through a menu-driven interface.

The project demonstrates Java concepts such as classes, objects, constructors, encapsulation, ArrayList, HashMap, methods, and switch-case statements.

## 2. Project Objective

The main objective is to develop a simple ATM simulation that allows users to:

- Authenticate using User ID and PIN.
- Access the ATM menu after successful login.
- View transaction history.
- Withdraw money.
- Deposit money.
- Transfer money between accounts.
- Exit the application.

## 3. Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application development |
| JDK 21 | Java development |
| Eclipse IDE | Code development |
| ArrayList | Store transaction history |
| HashMap | Manage bank accounts |
| Scanner | Accept user input |
| LocalDateTime | Store transaction date and time |
| Git | Version control |
| GitHub | Source code hosting |

## 4. Features

### 4.1 User Authentication
- Login using User ID and PIN.
- Maximum three incorrect login attempts.
- Access denied after three unsuccessful attempts.

### 4.2 Transaction History
- Displays successful transactions.
- Stores transaction type and amount.
- Displays transaction date and time.
- Uses ArrayList to maintain transaction records.

### 4.3 Withdraw Money
- Allows users to withdraw money.
- Validates the withdrawal amount.
- Checks available account balance.
- Displays Insufficient Funds when the balance is too low.
- Updates the account balance after successful withdrawal.

### 4.4 Deposit Money
- Allows users to deposit money.
- Validates the deposit amount.
- Updates the account balance.
- Records successful deposits in transaction history.

### 4.5 Transfer Money
- Transfers money between bank accounts.
- Accepts recipient account ID and amount.
- Validates recipient account details.
- Checks sufficient balance.
- Updates both sender and receiver balances.
- Prevents transfers to the same account.

### 4.6 Quit
- Displays a goodbye message.
- Exits the ATM application.

## 5. Project Structure

```text
ATMInterface/
│
├── src/
│   └── com/
│       └── ATM/
│           │
│           ├── Main.java
│           ├── ATM.java
│           ├── Account.java
│           ├── Bank.java
│           └── Transaction.java
│
└── README.md
```

## 6. Java Classes and Responsibilities

| Class Name | Description |
|---|---|
| Main.java | Starts the ATM application. |
| ATM.java | Handles login, menu, deposits, withdrawals, transfers, and transaction history. |
| Account.java | Stores account details and manages account balances. |
| Bank.java | Manages bank accounts and money transfers. |
| Transaction.java | Stores transaction details, date, and time. |

## 7. OOP Concepts Used

**Encapsulation:** Private variables protect account information and provide controlled access through methods.

**Classes and Objects:** Five Java classes are used to organize the application.

**Constructors:** Initialize account details and application objects.

**Abstraction:** The ATM menu provides a simple interface while transaction operations are handled through separate methods.

**Polymorphism:** The `toString()` method is overridden in the Transaction class to display transaction information.

## 8. Sample Login Credentials

The application contains two sample accounts for testing.

| User ID | PIN | Account Holder | Initial Balance |
|---|---|---|---|
| 1001 | 1234 | Somanath | ₹10,000 |
| 1002 | 5678 | Rahul | ₹5,000 |

**Note:** These are sample credentials for educational purposes only.

## 9. ATM Main Menu

After successful login, the following menu is displayed:

```text
================================
          ATM MENU
================================

1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit

================================
Enter your choice:
```

## 10. Sample Application Output

### Successful Login

```text
WELCOME TO ATM

Enter User ID: 1001
Enter PIN: 1234

Login successful!
Welcome, Somanath
```

### Withdrawal


===== WITHDRAW =====

Enter amount: 2000

Withdrawal successful!
Remaining Balance: ₹8000.00


### Deposit


===== DEPOSIT =====

Enter amount: 5000

Deposit successful!
Updated Balance: ₹13000.00


### Transfer


===== TRANSFER =====

Enter recipient account ID: 1002
Enter amount: 3000

Transfer successful!
Remaining Balance: ₹10000.00


### Transaction History


===== TRANSACTION HISTORY =====

Withdrawal | ₹2000.00 | Cash withdrawn

Deposit | ₹5000.00 | Cash deposited

Transfer | ₹3000.00 | Transferred to account 1002

================================


The actual application also displays the date and time of each transaction.

## 11. Data Structures Used

### ArrayList

Used to store transaction history.


ArrayList<Transaction> transactions;


### HashMap

Used to store bank account objects using account IDs.

java
Map<String, Account> accounts;


## 12. Validation and Error Handling

The application handles:

- Incorrect User ID and PIN.
- Maximum three login attempts.
- Invalid withdrawal amounts.
- Invalid deposit amounts.
- Invalid transfer amounts.
- Insufficient account balance.
- Invalid recipient account ID.
- Transfers to the same account.
- Invalid menu selections.
- Non-numeric amount inputs.

## 13. How to Run the Project

### Prerequisites

- JDK 21
- Eclipse IDE or any Java IDE

### Steps

1. Download or clone the repository.
2. Open Eclipse IDE.
3. Select File → Import.
4. Select Existing Projects into Workspace.
5. Choose the ATMInterface project.
6. Open  Main.java.
7. Right-click and select Run As → Java Application.
8. The ATM application will start in the Eclipse Console.

## 14. Project Limitations

This is an educational ATM simulation and is not connected to a real banking system.

- Account details are stored in memory.
- Account balances reset when the application restarts.
- Transaction history is maintained only during the current session.
- Sample account credentials are hardcoded.
- No database integration is included.
- No real financial transactions are processed.

## 15. Future Enhancements

- MySQL or PostgreSQL database integration.
- Permanent transaction history.
- User registration and account creation.
- Improved authentication and PIN security.
- Graphical User Interface using Java Swing or JavaFX.
- Transaction receipts.
- Additional account management features.

## 16. Learning Outcomes

Through this project, I gained practical experience in:

- Java programming.
- Object-Oriented Programming.
- Encapsulation and constructors.
- Classes and objects.
- ArrayList and HashMap.
- Menu-driven console applications.
- User input handling.
- Banking transaction validation.
- Git and GitHub version control.

## 17. Author Information

**Name:** Somanath Chakali Mulinti  
**GitHub:** https://github.com/Somu555  
**Repository:** OIBSIP  
**Internship:** Oasis Infobyte Java Development Internship

## 18. Acknowledgment

I sincerely thank Oasis Infobyte for providing me with the opportunity to work on this Java Development Internship project.

This project helped me improve my Java programming skills and understand the practical implementation of Object-Oriented Programming concepts.

---

**Project Status:** Completed
