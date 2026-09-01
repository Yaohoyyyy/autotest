package utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL Driver not found", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Метод для вставки данных
    public void insertUser(String firstName, String lastName, String email) {
        String sql = "INSERT INTO users (first_name, last_name, email, created_at, updated_at) VALUES (?, ?, ?, NOW(), null)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.executeUpdate();

            System.out.println("✅ Succesfull user insert: " + lastName + firstName);

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + lastName + firstName, e);
        }
    }

    // Метод для удаления данных (чистка после теста)
    public void deleteUserByEmail(String email) {
        String sql = "DELETE FROM users WHERE email = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            int deletedCount = stmt.executeUpdate();
            System.out.println("🗑️ Deleted records: " + deletedCount + " for email: " + email);

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user: " + email, e);
        }
    }

    // Метод для проверки существования пользователя в БД
    public boolean userExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException("Error checking user: " + email, e);
        }
    }

    // Метод для получения пользователя из БД
    public Map<String, Object> getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Map<String, Object> user = new HashMap<>();
                user.put("id", rs.getInt("id"));
                user.put("email", rs.getString("email"));
                user.put("first_name", rs.getString("first_name"));
                user.put("last_name", rs.getString("last_name"));
                user.put("created_at", rs.getTimestamp("created_at"));
                user.put("updated_at", rs.getTimestamp("updated_at"));
                return user;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error getting user: " + email, e);
        }
    }

    // Выполнение произвольного SQL-запроса
    public void executeSql(String sql) {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("✅ Executed SQL: " + sql);

        } catch (SQLException e) {
            throw new RuntimeException("Error executing SQL: " + sql, e);
        }
    }
}