package service;

import model.Book;

import java.util.List;

public interface BookService {
    Book addBook(String title, String author, String genre);
    List<Book> searchBooks(String query);
}
