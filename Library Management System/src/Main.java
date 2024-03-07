public class Main {

    private String title;
    private String author;
    private String genre;
    private String ISBN;
    private boolean available;

    // Getters and setters for all properties

    public String getDetails() {
        return "Title: " + title + ", Author: " + author + ", Genre: " + genre + ", ISBN: " + ISBN +
                (available ? ", Available" : ", Borrowed");
    }

    public void setAvailability(boolean available) {
        this.available = available;
    }
}
