import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.time.LocalDate;

public class UserDaoExample {

    private final DatabaseConnection databaseConnection;

    public UserDaoExample() {
        this.databaseConnection = new DatabaseConnection();
    }

    public void createUser(User user) {
        try (Connection con = databaseConnection.getCon()) {
            String query = "INSERT INTO users (id, username, email, password, isAdmin) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setBoolean(5, user.isAdmin());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email) {
        User user = null;
        try (Connection con = databaseConnection.getCon()) {
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean isAdmin = rs.getBoolean("isAdmin");

                user = new User(id, username, email, password, isAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addHealthData(HealthData healthData) {
        // Implement the logic to add health data to the database
    }

    public List<HealthData> getHealthDataByUserId(int userId) {
        List<HealthData> healthDataList = new ArrayList<>();
        try (Connection con = databaseConnection.getCon()) {
            String query = "SELECT * FROM health_data WHERE user_id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                double weight = rs.getDouble("weight");
                double height = rs.getDouble("height");
                int steps = rs.getInt("steps");
                int heartRate = rs.getInt("heart_rate");
                LocalDate date = rs.getDate("date").toLocalDate();

                HealthData healthData = new HealthData(id, userId, weight, height, steps, heartRate, date);
                healthDataList.add(healthData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return healthDataList;
    }

    public boolean verifyPassword(User user, String password) {
        return user.getPassword().equals(password);
    }
}
