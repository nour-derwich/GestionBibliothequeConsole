package service;

import model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book addBook(String title, String author, String genre);
    Optional<Book> getBookById(Long id);
    List<Book> searchBooks(String query);
    void updateBook(Long id, String title, String author, String genre);
    void deleteBook(Long id);
    List<Book> getAllBooks(); // Add this line
    Book addSpecializedBook(Book book);

}
