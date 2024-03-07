public class BorrowingRecord {

    private Book book;
    private Patron patron;
    private Date borrowDate;
    private Date returnDate;

    // Constructor with parameters

    public int calculateFine(int overdueDays) {
        // Implement logic to calculate fine based on overdue days and a per-day rate
        return fineAmount;
    }

    public void markReturned() {
        this.returnDate = new Date();
    }
}
