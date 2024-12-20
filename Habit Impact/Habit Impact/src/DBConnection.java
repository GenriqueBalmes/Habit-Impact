import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/habit_impact"; // Replace with your database
    // name
    private static final String USERNAME = "genrique"; // Replace with your MySQL username
    private static final String PASSWORD = "GQBalmes423"; // Replace with your MySQL password

    public static Connection getConnection() {
        try {
            // Make sure to load the driver before connecting
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }
}
