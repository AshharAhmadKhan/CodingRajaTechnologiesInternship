// DatabaseConnection.java
import java.sql.*;

class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection(String databasePath) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
    }

    public PreparedStatement prepareStatement(String sql, int returnGeneratedKeys) throws SQLException {
        return connection.prepareStatement(sql);
    }

    // Other methods for executing queries, updates, and closing connections can be added here
}
