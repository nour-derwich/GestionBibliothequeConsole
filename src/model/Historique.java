package model;

public class Historique {
    private Long id;
    private User user;
    private Book book;
    private String action;
    private String timestamp; // Assuming timestamp as String for simplicity

    public Historique(Long id, User user, Book book, String action, String timestamp) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.action = action;
        this.timestamp = timestamp;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Historique{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", action='" + action + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
