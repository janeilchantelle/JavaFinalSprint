
# Health Monitoring System

## Overview

The Health Monitoring System is designed to manage patient and doctor information within a healthcare environment. This system facilitates the creation, retrieval, and management of user profiles, patient records, and doctor details. The application is built using Java and utilizes PostgreSQL as the backend database.

## Features

- **User Management:** Create, update, delete, and retrieve user profiles.
- **Patient Management:** Assign patients to doctors and retrieve a list of patients for a specific doctor.
- **Doctor Management:** Retrieve doctor details, including medical license numbers and specializations.
- **Password Verification:** Securely verify user passwords during login.
- **Basic CLI Interface:** A command-line interface to interact with the system.

## Class Diagram

Here's a simplified class diagram illustrating the associations between classes:

```
    +----------------+       +-----------+       +-------------+
    |     User       |<------|  Doctor   |------>|  Patient    |
    +----------------+       +-----------+       +-------------+
    | - id: int      |       | - id: int |       | - id: int   |
    | - firstName    |       | - firstName |     | - doctor_id |
    | - lastName     |       | - lastName  |     | - user_id   |
    | - email        |       | - email     |     +-------------+
    | - password     |      | - password  |
    | - isDoctor     |      | - isDoctor  |
    +----------------+      +-------------+
```

## User Documentation

### Application Overview

The Health Monitoring System is designed to streamline the process of managing healthcare professionals and their patients. 

### Classes and Their Workings

- **User:** Represents a general user in the system. It can be either a doctor or a patient.
- **Doctor:** Inherits from User and represents a healthcare professional with additional attributes like medical license number and specialization.
- **Patient:** Represents a patient associated with a specific doctor.

### How to Start/Access the Application

1. Compile the project using:
    ```
    javac -cp ".:postgresql-42.6.0.jar" *.java
    ```
2. Run the application:
    ```
    java -cp ".:postgresql-42.6.0.jar" HealthMonitoringApp
    ```

## Development Documentation

### Javadocs

Javadoc comments are added to the source code to provide detailed explanations of each class, method, and variable. To generate Javadocs:

```
javadoc -d docs *.java

```

### Build Process

1. Ensure `postgresql-42.6.0.jar` is in the project directory.
2. Compile the project using the provided command.
3. Run the application as instructed above.

### Compiler Time Dependencies

- Java Development Kit (JDK)
- PostgreSQL JDBC Driver (`postgresql-42.6.0.jar`)

### Development Standards

The project follows the standard Java coding conventions and uses JDBC for database connectivity.

### Database Setup

1. Install PostgreSQL.
2. Create a new database and tables using the provided SQL schema.
3. Update `DatabaseConnection.java` with your database details.

### Getting the Source Code

Clone the repository from GitHub:

```
git clone https://github.com/janeilchantelle/JavaFinalSprint.git
```

## Deployment Documentation

### Installation Steps

1. Clone the repository from GitHub.
2. Set up the PostgreSQL database and import the provided SQL schema.
3. Compile and run the application using the provided commands.

---
