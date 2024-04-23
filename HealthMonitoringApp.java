import java.util.List;
import java.util.Scanner;

public class HealthMonitoringApp {

    private static UserDao userDao = new UserDao();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Create User");
            System.out.println("2. Get User by ID");
            System.out.println("3. Get User by Email");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Verify Password");
            System.out.println("7. Get Doctor by ID");
            System.out.println("8. Get Patients by Doctor ID");
            System.out.println("9. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createUser(scanner);
                    break;
                case 2:
                    getUserById(scanner);
                    break;
                case 3:
                    getUserByEmail(scanner);
                    break;
                case 4:
                    updateUser(scanner);
                    break;
                case 5:
                    deleteUser(scanner);
                    break;
                case 6:
                    verifyPassword(scanner);
                    break;
                case 7:
                    getDoctorById(scanner);
                    break;
                case 8:
                    getPatientsByDoctorId(scanner);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createUser(Scanner scanner) {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();

        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        System.out.println("Is the user a doctor? (true/false):");
        boolean isDoctor = scanner.nextBoolean();

        User newUser = new User(1, firstName, lastName, email, password, false, isDoctor);
        userDao.createUser(newUser);
        System.out.println("User created successfully.");
    }

    private static void getUserById(Scanner scanner) {
        System.out.println("Enter user ID:");
        int id = scanner.nextInt();
        User user = userDao.getUserById(id);
        if (user != null) {
            System.out.println("User found: " + user.getFirstName() + " " + user.getLastName());
        } else {
            System.out.println("User not found.");
        }
    }

    private static void getUserByEmail(Scanner scanner) {
        System.out.println("Enter user email:");
        String email = scanner.nextLine();
        User user = userDao.getUserByEmail(email);
        if (user != null) {
            System.out.println("User found: " + user.getFirstName() + " " + user.getLastName());
        } else {
            System.out.println("User not found.");
        }
    }

    private static void updateUser(Scanner scanner) {
        System.out.println("Enter user ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();

        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        System.out.println("Is the user a doctor? (true/false):");
        boolean isDoctor = scanner.nextBoolean();

        User updatedUser = new User(id, firstName, lastName, email, password, false, isDoctor);
        boolean success = userDao.updateUser(updatedUser);
        if (success) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("Failed to update user.");
        }
    }

    private static void deleteUser(Scanner scanner) {
        System.out.println("Enter user ID:");
        int id = scanner.nextInt();
        boolean success = userDao.deleteUser(id);
        if (success) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Failed to delete user.");
        }
    }

    private static void verifyPassword(Scanner scanner) {
        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        boolean isValid = userDao.verifyPassword(email, password);
        if (isValid) {
            System.out.println("Password is correct.");
        } else {
            System.out.println("Password is incorrect.");
        }
    }

    private static void getDoctorById(Scanner scanner) {
        System.out.println("Enter doctor ID:");
        int id = scanner.nextInt();
        Doctor doctor = userDao.getDoctorById(id);
        if (doctor != null) {
            System.out.println("Doctor found: " + doctor.getFirstName() + " " + doctor.getLastName());
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private static void getPatientsByDoctorId(Scanner scanner) {
        System.out.println("Enter doctor ID:");
        int id = scanner.nextInt();
        List<User> patients = userDao.getPatientsByDoctorId(id);
        if (!patients.isEmpty()) {
            System.out.println("Patients for doctor with ID " + id + ":");
            for (User patient : patients) {
                System.out.println(patient.getFirstName() + " " + patient.getLastName());
            }
        } else {
            System.out.println("No patients found for doctor with ID " + id + ".");
        }
    }
}
