package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {

    // SECURITY ISSUE: Hardcoded credentials
    private String password = "admin123";

    // VULNERABILITY: SQL Injection
    public void findUser(String username) throws Exception {

        Connection conn =
            DriverManager.getConnection("jdbc:mysql://localhost/db",
                    "root", password);

        Statement st = conn.createStatement();

        String query =
            "SELECT * FROM users WHERE name = '" + username + "'";

        st.executeQuery(query);
    }

    // SMELL: Unused method
    public void notUsed() {
        System.out.println("I am never called");
    }

    // EVEN WORSE: another SQL injection
    public void deleteUser(String username) throws SQLException {
    String query = "DELETE FROM users WHERE name = ?";
    String url = "jdbc:mysql://localhost/db";

    // Try-with-resources automatically closes Connection and PreparedStatement
    try (Connection conn = DriverManager.getConnection(url, "root", password);
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setString(1, username);
        pstmt.executeUpdate();
        
    } catch (SQLException e) {
        // Log the error or rethrow a custom exception
        throw new SQLException("Error deleting user: " + username, e);
    }
}

}
