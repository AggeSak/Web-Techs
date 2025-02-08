import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class back {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/DnD?useSSL=false"; // Your database URL with SSL disabled
        String user = "root"; // Your MySQL username
        String password = ""; // Your MySQL password

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to database!");

            // Close the connection
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver Not Found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Connection Failed!");
            e.printStackTrace();
        }
    }
}
