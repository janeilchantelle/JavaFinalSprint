import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for HealthData.
 */
public class HealthDataDao {

    /**
     * Creates a new health data record in the database.
     *
     * @param healthData The HealthData object to be inserted.
     * @return true if the insertion was successful, false otherwise.
     */
    public boolean createHealthData(HealthData healthData) {
        String query = "INSERT INTO health_data (user_id, weight, height, steps, heart_rate, date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, healthData.getUserId());
            statement.setDouble(2, healthData.getWeight());
            statement.setDouble(3, healthData.getHeight());
            statement.setInt(4, healthData.getSteps());
            statement.setInt(5, healthData.getHeartRate());
            statement.setDate(6, Date.valueOf(healthData.getDate()));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a health data record by its ID.
     *
     * @param id The ID of the health data record.
     * @return The HealthData object if found, null otherwise.
     */
    public HealthData getHealthDataById(int id) {
        HealthData healthData = null;
        String query = "SELECT * FROM health_data WHERE id = ?";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                healthData = new HealthData(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getDouble("weight"),
                        rs.getDouble("height"),
                        rs.getInt("steps"),
                        rs.getInt("heart_rate"),
                        rs.getDate("date").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return healthData;
    }

    /**
     * Retrieves all health data records associated with a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of HealthData objects representing the user's health data.
     */
    public List<HealthData> getHealthDataByUserId(int userId) {
        List<HealthData> healthDataList = new ArrayList<>();
        String query = "SELECT * FROM health_data WHERE user_id = ?";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                HealthData healthData = new HealthData(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getDouble("weight"),
                        rs.getDouble("height"),
                        rs.getInt("steps"),
                        rs.getInt("heart_rate"),
                        rs.getDate("date").toLocalDate()
                );
                healthDataList.add(healthData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return healthDataList;
    }

    /**
     * Updates an existing health data record in the database.
     *
     * @param healthData The HealthData object containing updated data.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateHealthData(HealthData healthData) {
        String query = "UPDATE health_data SET weight = ?, height = ?, steps = ?, heart_rate = ?, date = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setDouble(1, healthData.getWeight());
            statement.setDouble(2, healthData.getHeight());
            statement.setInt(3, healthData.getSteps());
            statement.setInt(4, healthData.getHeartRate());
            statement.setDate(5, Date.valueOf(healthData.getDate()));
            statement.setInt(6, healthData.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a health data record from the database by its ID.
     *
     * @param id The ID of the health data record to be deleted.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteHealthData(int id) {
        String query = "DELETE FROM health_data WHERE id = ?";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
