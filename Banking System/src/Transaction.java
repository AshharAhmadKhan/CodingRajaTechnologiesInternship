// Transaction.java
import java.sql.*;

public class Transaction {
    private int userId;
    private long timestamp;
    private double amount;
    private String sourceAccount;
    private String destinationAccount;
    private TransactionStatus status;

    public enum TransactionStatus {
        PENDING, COMPLETED
    }

    public Transaction(int userId, long timestamp, double amount, String sourceAccount, String destinationAccount) {
        this.userId = userId;
        this.timestamp = timestamp;
        this.amount = amount;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.status = TransactionStatus.PENDING;
    }

    // Other getters and setters omitted for brevity

    public static void performTransaction(Main main, String sourceAccount, String destinationAccount, double amount, DatabaseConnection connection) {
        Transaction transaction = new Transaction(main.getId(), System.currentTimeMillis(), amount, sourceAccount, destinationAccount);
        String query = "INSERT INTO Transactions (user_id, timestamp, amount, source_account, destination_account, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, transaction.getUserId());
            statement.setLong(2, transaction.getTimestamp());
            statement.setDouble(3, transaction.getAmount());
            statement.setString(4, transaction.getSourceAccount());
            statement.setString(5, transaction.getDestinationAccount());
            statement.setString(6, transaction.getStatus().name()); // Use name() to get the enum name
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        transaction.setId(generatedKeys.getInt(1));
                        // Status is already set to PENDING in the Transaction constructor
                    }
                }
            }
            // Update local account balances in the application (not shown here)
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }
    }

    private void setId(int id) {
    }

    private TransactionStatus getStatus() {
        return status;
    }

    private String getDestinationAccount() {
        return destinationAccount;
    }

    private String getSourceAccount() {
        return sourceAccount;
    }

    private double getAmount() {
        return amount;
    }

    private long getTimestamp() {
        return timestamp;
    }

    private int getUserId() {
        return userId;
    }
}
