_## Online Train Reservation System

The Online Train Reservation System is a Java desktop application that allows users to manage train ticket reservations through a simple graphical interface. It is built using Java Swing and connects to a MySQL database using JDBC.

Users can log in, book tickets, retrieve booking details using a PNR number, cancel tickets, and view all bookings. The application also includes a logout feature that returns users to the login page.

This project demonstrates the use of Java, Swing, JDBC, and MySQL to build a database-driven desktop application.
## Features
- Login to the application
- Book train tickets
- Retrieve ticket details using a PNR number
- Cancel tickets using a PNR number
- View all bookings
- Logout and return to the login screen

## Technologies Used
- Java
- Java Swing
- JDBC
- MySQL
- Eclipse IDE
- Maven

## Project Structure
OnlineReservationSystem/
├── src/main/java/com/oasisinfobyte/
│   ├── DBConnection.java
│   ├── LoginForm.java
│   ├── Main.java
│   ├── ReservationDashboard.java
│   ├── ReservationForm.java
│   └── TestConnection.java
├── screenshots
├── pom.xml
└── README.md
##Requirements
- JDK 21
- MySQL Server
- Eclipse IDE or another Java IDE
Setup and Run
1. Download or clone this repository.
2. Import the project into Eclipse as an existing Maven project.
3. Create the MySQL database and reservation table required by the application.
4. Update DBConnection.java with your local database name, username, and password.
5. Start MySQL Server.
6. Run Main.java as a Java Application.
##Demo Login
. USERNAME: admin
. PASSWORD: admin
.These are demo credentials for testing only.
##Screenshots
8.Screenshots of the login page, dashboard, booking form, and reservation features are available in the screenshots/ folder.
##NOTES:-
9.This is a Java desktop application. It runs on a computer with Java and MySQL installed; it does not run as a desktop GUI directly in a web browser
