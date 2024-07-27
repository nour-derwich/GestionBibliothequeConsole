package model;

public class Livre extends Book {
    private int numberOfPages;

    public Livre(Long id, String title, String author, String genre, int numberOfPages) {
        super(id, title, author, genre);
        this.numberOfPages = numberOfPages;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "numberOfPages=" + numberOfPages +
                ", id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", genre='" + getGenre() + '\'' +
                '}';
    }
}
