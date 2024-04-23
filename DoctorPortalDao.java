import java.util.List;

public class DoctorPortalDao {
    private UserDao userDao;
    private HealthDataDao healthDataDao;

    public DoctorPortalDao() {
        userDao = new UserDao();
        healthDataDao = new HealthDataDao();
    }

    /**
     * Retrieves a doctor by their ID.
     *
     * @param doctorId The ID of the doctor to retrieve.
     * @return The Doctor object if found, null otherwise.
     */
    public Doctor getDoctorById(int doctorId) {
        return userDao.getDoctorById(doctorId);
    }

    /**
     * Retrieves the list of patients associated with a doctor.
     *
     * @param doctorId The ID of the doctor.
     * @return A list of User objects representing the patients.
     */
    public List<User> getPatientsByDoctorId(int doctorId) {
        return userDao.getPatientsByDoctorId(doctorId);
    }

    /**
     * Retrieves the health data of a patient by their ID.
     *
     * @param patientId The ID of the patient.
     * @return A list of HealthData objects representing the patient's health data.
     */
    public List<HealthData> getHealthDataByPatientId(int patientId) {
        return healthDataDao.getHealthDataByUserId(patientId);
    }

}
