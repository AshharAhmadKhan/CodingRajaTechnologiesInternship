import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Main {
    private int id;
    private String name;
    private String pinHash;

    public Main(int id, String name, String pinHash) {
        this.id = id;
        this.name = name;
        this.pinHash = pinHash;
    }

    public int getId() {
        return id;
    }

    // Other getters and setters omitted for brevity

    public static Main login(String name, String pin, DatabaseConnection connection) {
        String hashedPin = hashPin(pin);
        String query = "SELECT id FROM Users WHERE name = ? AND pin_hash = ?";
        Main main = null;
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, hashedPin);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    main = new Main(resultSet.getInt("id"), name, hashedPin);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }
        return main;
    }

    private static String hashPin(String pin) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pin.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing the PIN.", e);
        }
    }
}
