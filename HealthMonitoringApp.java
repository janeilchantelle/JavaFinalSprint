import java.util.List;

public class HealthMonitoringApp {
    
    public static void main(String[] args) {
        
        UserDao userDao = new UserDao();
        
        User newUser = new User(1, "John", "Doe", "johndoe@example.com", "password123", false, false);
        userDao.createUser(newUser);
        
        List<User> patients = userDao.getPatientsByDoctorId(1);
        for (User patient : patients) {
            System.out.println("Patient Name: " + patient.getFirstName() + " " + patient.getLastName());
        }
    }
}
