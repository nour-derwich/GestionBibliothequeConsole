package model;

public class Historique {
    private long timestamp;
    private User user;
    private Book book;
    private String action;

    // Constructor with all fields
    public Historique(long timestamp, User user, Book book, String action) {
        this.timestamp = timestamp;
        this.user = user;
        this.book = book;
        this.action = action;
    }

    // Getters and Setters
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Historique{" +
                "timestamp=" + timestamp +
                ", user=" + user +
                ", book=" + book +
                ", action='" + action + '\'' +
                '}';
    }
}
