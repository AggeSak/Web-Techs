import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    // Authenticate the user by checking username and password
    public static boolean authenticate(String Username, String Password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Get connection from the reusable method in DatabaseConnection class
            connection = back.getConnection();

            // SQL query to check for the username and password
            String sql = "SELECT * FROM Users WHERE Name = ? AND Password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, Password);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // If a result is found, the login is successful
            if (resultSet.next()) {
                System.out.println("✅ Login successful.");
                return true; // Login successful
            } else {
                System.out.println("❌ Invalid ");
                return false; // Invalid login
            }

        } catch (SQLException e) {
            System.err.println("❌ Database error!");
            e.printStackTrace();
            return false; // Error in authentication
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                // Reuse the closeConnection method from DatabaseConnection class
                back.closeConnection(connection);
            } catch (SQLException e) {
                System.err.println("❌ Error closing resources.");
                e.printStackTrace();
            }
        }
    }

    // Main method for testing the login functionality
    public static void main(String[] args) {
        // Example username and password
        String Username = "john";  // Replace with the actual username to test
        String Password = "password123";  // Replace with the actual password to test

        // Authenticate the user
        if (authenticate(Username, Password)) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Login failed.");
        }
    }
}
