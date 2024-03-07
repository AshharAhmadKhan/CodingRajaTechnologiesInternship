public class Library {

    private Connection connection; // Database connection

    public void addBook(Book book) throws SQLException {
        // Prepare and execute INSERT statement using connection to add book to database
    }

    public List<Book> searchBook(String criteria) throws SQLException {
        // Prepare and execute SELECT statement based on criteria (title, author, genre)
        // Return a list of Book objects
    }

    public void borrowBook(Book book, Patron patron) throws SQLException {
        if (book.isAvailable()) {
            // Update book availability in database (set to false)
            BorrowingRecord record = new BorrowingRecord(book, patron, new Date());
            // Insert borrowing record into database
            book.setAvailability(false);
        } else {
            System.out.println("Book currently unavailable.");
        }
    }

    // Similar methods for other functionalities (addPatron, searchPatron, returnBook, generateReports)
}
