import java.time.LocalDate;
import java.util.List;

public class HealthMonitoringApp {

    private static UserDaoExample userDao = new UserDaoExample();
    private static MedicineReminderManager medicineReminderManager = new MedicineReminderManager();
    private static DoctorPortalDao doctorPortalDao = new DoctorPortalDao();

    public static void main(String[] args) {
        User user1 = new User(5, "Dalilah", "dalilah@gmail.com", "guggu", false);
        userDao.createUser(user1);

        testLoginUser(user1.getEmail(), user1.getPassword());

        HealthData healthData = new HealthData(1, user1.getId(), 70.5, 165.0, 8000, 75, LocalDate.now());
        userDao.addHealthData(healthData);

        List<HealthData> userHealthData = userDao.getHealthDataByUserId(user1.getId());
        List<String> recommendations = new RecommendationSystem().generateRecommendations(userHealthData.get(0));

        for (String recommendation : recommendations) {
            System.out.println(recommendation);
        }

        MedicineReminder reminder = new MedicineReminder(1, user1.getId(), "Aspirin", "1 pill", "Morning", "2024-04-22", "2024-04-30");
        medicineReminderManager.addReminder(reminder);

        List<MedicineReminder> userReminders = medicineReminderManager.getRemindersForUser(user1.getId());
        System.out.println("Reminders for User " + user1.getId() + ": " + userReminders);

        List<MedicineReminder> dueReminders = medicineReminderManager.getDueReminders(user1.getId());
        System.out.println("Due Reminders for User " + user1.getId() + ": " + dueReminders);

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

        Doctor doctor = doctorPortalDao.getDoctorById(doctorId);
        System.out.println("Fetched Doctor: " + doctor);

        List<User> patients = doctorPortalDao.getPatientsByDoctorId(doctorId);
        System.out.println("Patients associated with Doctor " + doctorId + ": " + patients);

        int patientId = patients.get(0).getId();
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
