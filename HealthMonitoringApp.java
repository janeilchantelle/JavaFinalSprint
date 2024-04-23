import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class HealthMonitoringApp {

    private static UserDaoExample userDao = new UserDaoExample();
    private static MedicineReminderManager medicineReminderManager = new MedicineReminderManager();
    private static DoctorPortalDao doctorPortalDao = new DoctorPortalDao();

    /**
     * Test the following functionalities within the Main Application
     *  1. Register a new user
     *  2. Log in the user
     *  3. Add health data
     *  4. Generate recommendations
     *  5. Add a medicine reminder
     *  6. Get reminders for a specific user
     *  7. Get due reminders for a specific user
     *  8. Test doctor portal
     */
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        // 1. Register a new user
        User user1 = new User(5, "Dalilah", "Dalilah", "dalilah@gmail.com", "guggu", false);
        userDao.createUser(user1);

        // 2. Log in the user
        testLoginUser(user1.getEmail(), user1.getPassword());

        // 3. Add health data (For demonstration, I'm adding dummy health data)
        HealthData healthData = new HealthData(1, user1.getId(), 70.5, 165.0, 8000, 75, LocalDate.now());
        userDao.addHealthData(healthData);

        // 4. Generate recommendations
        // Assuming you have a method in UserDaoExample to fetch health data
        List<HealthData> userHealthData = userDao.getHealthDataByUserId(user1.getId());
        List<String> recommendations = new RecommendationSystem().generateRecommendations(userHealthData.get(0)); // Assuming we take the latest health data

        for (String recommendation : recommendations) {
            System.out.println(recommendation);
        }

        // 5. Add a medicine reminder
        MedicineReminder reminder = new MedicineReminder(1, user1.getId(), "Aspirin", "1 pill", "Morning", "2024-04-22", "2024-04-30");
        medicineReminderManager.addReminder(reminder);

        // 6. Get reminders for a specific user
        List<MedicineReminder> userReminders = medicineReminderManager.getRemindersForUser(user1.getId());
        System.out.println("Reminders for User " + user1.getId() + ": " + userReminders);

        // 7. Get due reminders for a specific user
        List<MedicineReminder> dueReminders = medicineReminderManager.getDueReminders(user1.getId());
        System.out.println("Due Reminders for User " + user1.getId() + ": " + dueReminders);

        // 8. Test doctor portal
        testDoctorPortal();
    }

    public static boolean loginUser(String email, String password) {
        User user = userDao.getUserByEmail(email);

        if (user != null && userDao.verifyPassword(user, password)) {
            return true;
        }

        return false;
    }

    public static void testDoctorPortal() {
        int doctorId = 1;

        // Fetch the doctor by ID
        Doctor doctor = doctorPortalDao.getDoctorById(doctorId);
        System.out.println("Fetched Doctor: " + doctor);

        // Fetch patients associated with the doctor
        List<User> patients = doctorPortalDao.getPatientsByDoctorId(doctorId);
        System.out.println("Patients associated with Doctor " + doctorId + ": " + patients);

        // Fetch health data for the patient
        int patientId = patients.get(0).getId(); // Assuming there's at least one patient
        List<HealthData> healthDataList = doctorPortalDao.getHealthDataByPatientId(patientId);
        System.out.println("Health data for Patient " + patientId + ": " + healthDataList);
    }

    public static void testLoginUser(String email, String password) {
        boolean loginSuccess = loginUser(email, password);

        if (loginSuccess) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Incorrect email or password. Please try again.");
        }
    }
}
