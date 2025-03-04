import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class back {

    public static void main(String[] args) {
        // Παράμετροι σύνδεσης - Οι τιμές πρέπει να αντικατασταθούν
        final String DB_URL = "jdbc:mysql://localhost:3306/DnD?useSSL=false&serverTimezone=UTC";
        final String USER = "root";
        final String PASSWORD = ""; // Προσθήκη κωδικού πρόσβασης
        
        Connection connection = null;

        try {
            // 1. Φόρτωση JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Δημιουργία σύνδεσης
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            
            // 3. Έλεγχος σύνδεσης
            if (connection != null && !connection.isClosed()) {
                System.out.println("✅ Επιτυχής σύνδεση στη βάση δεδομένων!");
                
                // Προσθήκη λειτουργιών βάσης εδώ...
            }
            
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Δεν βρέθηκε ο JDBC Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Αποτυχία σύνδεσης:");
            System.err.println("Πιθανές αιτίες:");
            System.err.println("- Ο server MySQL δεν είναι ενεργός");
            System.err.println("- Λάθος όνομα βάσης/χρήστη/κωδικού");
            System.err.println("- Δεν υπάρχει η βάση 'DnD'");
            e.printStackTrace();
        } finally {
            // 4. Κλείσιμο σύνδεσης
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("✅ Σύνδεση κλειστή.");
                }
            } catch (SQLException e) {
                System.err.println("❌ Σφάλμα κατά το κλείσιμο σύνδεσης");
                e.printStackTrace();
            }
        }
    }
}