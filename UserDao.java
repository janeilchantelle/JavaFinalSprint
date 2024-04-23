import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class UserDao {

    public boolean createUser(User user) {
        String query = "INSERT INTO users (first_name, last_name, email, password, is_doctor) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setBoolean(5, user.isDoctor());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate key value violates unique constraint \"users_email_key\"")) {
                System.out.println("Email address already exists. Please use a different email.");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    public User getUserById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("is_doctor"), false
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("is_doctor"), false
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(User user) {
        String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ?, is_doctor = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setBoolean(5, user.isDoctor());
            statement.setInt(6, user.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verifyPassword(String email, String password) {
        String query = "SELECT password FROM users WHERE email = ?";
        try (Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Doctor getDoctorById(int doctorId) {
        String query = "SELECT * FROM users WHERE id = ? AND is_doctor = TRUE";
        try (Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, doctorId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Doctor(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("is_doctor"),
                        false, rs.getString("medical_license_number"),
                        rs.getString("specialization")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getPatientsByDoctorId(int doctorId) {
        List<User> patients = new ArrayList<>();
        String query = "SELECT u.* FROM users u JOIN doctor_patient dp ON u.id = dp.patient_id WHERE dp.doctor_id = ?";
        try (Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, doctorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                patients.add(new User(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getBoolean("is_doctor"), false
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public boolean scheduleAppointment(Appointment appointment) {
        String query = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query)) {
             
            // Check if patient_id exists in doctor_patient table
            if (!relationExists(appointment.getPatientId(), "patient_id")) {
                System.out.println("Invalid patient ID. Please enter a valid patient ID.");
                return false;
            }
            
            // Check if doctor_id exists in doctor_patient table
            if (!relationExists(appointment.getDoctorId(), "doctor_id")) {
                System.out.println("Invalid doctor ID. Please enter a valid doctor ID.");
                return false;
            }
    
            statement.setInt(1, appointment.getPatientId());
            statement.setInt(2, appointment.getDoctorId());
            
            // Convert Java's String date to java.sql.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse(appointment.getAppointmentDate());
            Date sqlDate = new Date(date.getTime());
            statement.setDate(3, sqlDate); // Use java.sql.Date here
            
            // Convert Java's String time to java.sql.Time
            SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
            java.util.Date time = stf.parse(appointment.getAppointmentTime());
            Time sqlTime = new Time(time.getTime());
            statement.setTime(4, sqlTime); // Use java.sql.Time here
            
            return statement.executeUpdate() > 0;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean relationExists(int userId, String column) {
        String query = "SELECT COUNT(*) FROM doctor_patient WHERE " + column + " = ?";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    private boolean relationExists(int userId) {
        String query = "SELECT COUNT(*) FROM doctor_patient WHERE patient_id = ? OR doctor_id = ?";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    

    public boolean createReminder(Reminder reminder) {
        String query = "INSERT INTO reminders (user_id, description, reminder_date) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, reminder.getUserId());
            statement.setString(2, reminder.getDescription());
            statement.setString(3, reminder.getReminderDate());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Reminder> getRemindersByUserId(int userId) {
        List<Reminder> reminders = new ArrayList<>();
        String query = "SELECT * FROM reminders WHERE user_id = ?";
        try (Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                reminders.add(new Reminder(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("description"),
                        rs.getString("reminder_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reminders;
    }
}
    

