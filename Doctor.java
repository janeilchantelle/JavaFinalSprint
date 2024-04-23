public class Doctor extends User {
    private String medicalLicenseNumber;
    private String specialization;

    public Doctor(int id, String firstName, String lastName, String email, String password, String medicalLicenseNumber, String specialization) {
        super(id, firstName, lastName, email, password, true);  // All doctors are indeed doctors, so setting isDoctor to true
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialization = specialization;
    }
    

    // Getters and setters for the new properties
    public String getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    public void setMedicalLicenseNumber(String medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
