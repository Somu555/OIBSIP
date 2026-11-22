_## Online Train Reservation System

The Online Train Reservation System is a Java desktop application that allows users to manage train ticket reservations through a simple graphical interface. It is built using Java Swing and connects to a MySQL database using JDBC.

Users can log in, book tickets, retrieve booking details using a PNR number, cancel tickets, and view all bookings. The application also includes a logout feature that returns users to the login page.

This project demonstrates the use of Java, Swing, JDBC, and MySQL to build a database-driven desktop application.

## Requirements

- JDK 21
- MySQL Server
- MySQL Workbench
- Eclipse IDE or another Java IDE

## Setup and Run

1. Download or clone this repository.
2. Import the project into Eclipse as an existing Maven project.
3. Use MySQL Workbench to create the database and reservation table required by the application.
4. Update DBConnection.java with your local database name, username, and password.
5. Start MySQL Server.
6. Run Main.java as a Java Application.

## Demo Login

- Username: "admin"
- Password: "admin"

These are demo credentials for testing only.

## Screenshots

Screenshots of the login page, dashboard, booking form, and reservation features are available in the `screenshots/` folder.

## Notes

This is a Java desktop application. It runs on a computer with Java and MySQL installed; it does not run as a desktop GUI directly in a web browser.
