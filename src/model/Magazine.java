package model;

public class Magazine extends Book {
    private String issue;

    public Magazine(Long id, String title, String author, String genre, String issue) {
        super(id, title, author, genre);
        this.issue = issue;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "issue='" + issue + '\'' +
                ", id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", genre='" + getGenre() + '\'' +
                '}';
    }
}