package service;

import model.Book;

import java.util.ArrayList;
import java.util.List;
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
}
