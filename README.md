Features

User Management
User registration and login
Password hashing and verification
User profile management (update details, delete account)
Doctor-Specific Features
Doctor registration and profile management
Patient assignment and tracking
Viewing patient health records
Patient-Specific Features
Patient registration and profile management
Health data input (e.g., blood pressure, heart rate)
Viewing personal health history
Appointment Management
Scheduling and managing appointments between doctors and patients
Reminders for upcoming appointments
Health Data Analytics
Data visualization of patient health metrics
Health trend analysis for doctors
Security
Role-based access control (Admin, Doctor, Patient)
Data encryption for sensitive information
Requirements

Java 8 or higher
PostgreSQL database
JDBC driver (included in the repository as postgresql-42.6.0.jar)
Setup

Clone the Repository

git clone https://github.com/janeilchantelle/JavaFinalSprint.git
Navigate to the Project Directory

cd JavaFinalSprint

Compile the Code

javac -cp ".:postgresql-42.6.0.jar" *.java

Run the Application

java -cp ".:postgresql-42.6.0.jar" HealthMonitoringApp

Usage

Upon running the application, you'll be presented with a command-line interface (CLI) that allows you to:

Register new users and assign roles
Log in to the system based on the assigned role (Admin, Doctor, Patient)
Perform role-specific actions such as managing appointments, viewing health data, etc.
Update and delete user profiles
Schedule, manage, and view appointments
Analyze health data for patients

Contributing

Feel free to fork the repository and submit pull requests with improvements or additional features.

License

This project is licensed under the MIT License. See the LICENSE file for details.