package model;

public class Article extends Book {
    private String journalName;

    public Article(Long id, String title, String author, String genre, String journalName) {
        super(id, title, author, genre);
        this.journalName = journalName;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    @Override
    public String toString() {
        return "Article{" +
                "journalName='" + journalName + '\'' +
                ", id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", genre='" + getGenre() + '\'' +
                '}';
    }
}