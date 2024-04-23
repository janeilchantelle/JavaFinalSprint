import java.util.List;
import java.util.Scanner;

public class HealthMonitoringApp {
    private static final UserDao userDao = new UserDao();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to Health Monitoring System!");
            System.out.println("1. Create User");
            System.out.println("2. Login");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. View Reminders");
            System.out.println("5. Create Reminder");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    scheduleAppointment();
                    break;
                case 4:
                    viewReminders();
                    break;
                case 5:
                    createReminder();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    private static void createUser() {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();

        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        System.out.println("Are you a doctor? (true/false)");
        boolean isDoctor = scanner.nextBoolean();

        System.out.println("Are you an admin? (true/false)");
        boolean isAdmin = scanner.nextBoolean();

        User newUser = new User(0, firstName, lastName, email, password, isAdmin, isDoctor);
        userDao.createUser(newUser);
        System.out.println("User created successfully!");
    }

    private static void login() {
        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (userDao.verifyPassword(email, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password. Try again.");
        }
    }

    private static void scheduleAppointment() {
        System.out.println("Enter patient ID:");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter doctor ID:");
        int doctorId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter appointment date (YYYY-MM-DD):");
        String appointmentDate = scanner.nextLine();

        System.out.println("Enter appointment time (HH:MM):");
        String appointmentTime = scanner.nextLine();

        Appointment appointment = new Appointment(0, patientId, doctorId, appointmentDate, appointmentTime);
        if (userDao.scheduleAppointment(appointment)) {
            System.out.println("Appointment scheduled successfully!");
        } else {
            System.out.println("Failed to schedule appointment. Try again.");
        }
    }

    private static void viewReminders() {
        System.out.println("Enter user ID:");
        int userId = scanner.nextInt();
        scanner.nextLine();

        List<Reminder> reminders = userDao.getRemindersByUserId(userId);
        if (reminders.isEmpty()) {
            System.out.println("No reminders found.");
        } else {
            System.out.println("Reminders:");
            for (Reminder reminder : reminders) {
                System.out.println(reminder.toString());
            }
        }
    }

    private static void createReminder() {
        System.out.println("Enter user ID:");
        int userId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.println("Enter reminder description:");
        String description = scanner.nextLine();

        System.out.println("Enter reminder date (YYYY-MM-DD):");
        String reminderDate = scanner.nextLine();

        Reminder reminder = new Reminder(0, userId, description, reminderDate);
        if (userDao.createReminder(reminder)) {
            System.out.println("Reminder created successfully!");
        } else {
            System.out.println("Failed to create reminder. Try again.");
        }
    }
}
