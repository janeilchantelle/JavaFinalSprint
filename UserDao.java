import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDao {
    public boolean createUser(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        String query = "INSERT INTO users (first_name, last_name, email, password, is_doctor) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, hashedPassword);
            statement.setBoolean(5, user.isDoctor());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserById(int id) {
        // Similar to UserDaoExample
    }

    public User getUserByEmail(String email) {
        // Similar to UserDaoExample
    }

    public boolean updateUser(User user) {
        // Similar to UserDaoExample
    }

    public boolean deleteUser(int id) {
        // Similar to UserDaoExample
    }

    public boolean verifyPassword(String email, String password) {
        // Similar to UserDaoExample
    }
}
