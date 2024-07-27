package service;

import model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    private List<Book> books = new ArrayList<>();
    private long bookIdCounter = 1;

    @Override
    public Book addBook(String title, String author, String genre) {
        Book book = new Book(bookIdCounter++, title, author, genre);
        books.add(book);
        return book;
    }
    @Override
    public List<Book> searchBooks(String query) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase())
                        || book.getAuthor().toLowerCase().contains(query.toLowerCase())
                        || book.getGenre().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    @Override
    public void updateBook(Long id, String title, String author, String genre) {
        Optional<Book> bookOptional = getBookById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);
        }
    }

    @Override
    public void deleteBook(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(books); // Return a copy of the book list
    }
    @Override
    public Book addSpecializedBook(Book book) {
        book.setId(bookIdCounter++);
        books.add(book);
        return book;
    }
}
