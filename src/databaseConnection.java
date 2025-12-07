
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author www
 */
public class databaseConnection {
    //C:\JavaPract\mysql-connector-java-8.0.26
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/SIMS";
    private static final String USER = "root";
    private static final String PASS = "";  // ✅ Empty string if no password

    public static Connection connection() {
        try {
            Class.forName(JDBC_DRIVER); // optional in JDBC 4+, but safe
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "JDBC Driver not found: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
            return null;
        }
    }
}
