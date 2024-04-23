import java.util.List;


public class HealthMonitoringApp {
    
    public static void main(String[] args) {
        // Your main application logic here
        
        // Example:
        UserDao userDao = new UserDao();
        
        User newUser = new User(1, "JohnDoe", "johndoe@example.com", "password123", false);
        userDao.createUser(newUser);
        
        List<User> patients = userDao.getPatientsByDoctorId(1);
        for (User patient : patients) {
            System.out.println("Patient Name: " + patient.getUsername());
        }
    }
}
