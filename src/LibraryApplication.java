import model.*;
import service.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LibraryApplication {
    private static UserService userService = new UserServiceImpl();
    private static BookService bookService = new BookServiceImpl();
    private static HistoriqueService historiqueService = new HistoriqueServiceImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        while (true) {
            if (currentUser == null) {
                // Display menu options for non-logged-in users
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        currentUser = registerUser(scanner);
                        break;
                    case 2:
                        currentUser = loginUser(scanner);
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                // Display menu options for logged-in users
                System.out.println("1. Search Books");
                System.out.println("2. Add Book");
                System.out.println("3. Update Book");
                System.out.println("4. Delete Book");
                System.out.println("5. View All Books");
                System.out.println("6. Edit User Info");
                System.out.println("7. View History");
                System.out.println("8. Logout");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        searchBooks(scanner, currentUser);
                        break;
                    case 2:
                        addBook(scanner, currentUser);
                        break;
                    case 3:
                        updateBook(scanner, currentUser);
                        break;
                    case 4:
                        deleteBook(scanner, currentUser);
                        break;
                    case 5:
                        viewAllBooks();
                        break;
                    case 6:
                        editUserInfo(scanner, currentUser);
                        break;
                    case 7:
                        viewHistory(currentUser);
                        break;
                    case 8:
                        currentUser = null; // Log out
                        System.out.println("Logged out.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }

    private static User registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        User user = userService.registerUser(username, password, email);
        System.out.println("User registered: " + user);
        return user;
    }

    private static User loginUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Optional<User> user = userService.loginUser(username, password);
        if (user.isPresent()) {
            System.out.println("Login successful: " + user.get());
            return user.get();
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }

    private static void searchBooks(Scanner scanner, User user) {
        System.out.print("Enter search query: ");
        String query = scanner.nextLine();

        List<Book> books = bookService.searchBooks(query);
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            books.forEach(System.out::println);
        }

        books.forEach(book -> historiqueService.logAction(user, book, "Searched"));
    }

    private static void addBook(Scanner scanner, User user) {
        System.out.println("Choose type of book to add: ");
        System.out.println("1. Regular Book");
        System.out.println("2. Article");
        System.out.println("3. Magazine");
        System.out.println("4. Livre");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        Book book;
        switch (choice) {
            case 1:
                book = new Book(null, title, author, genre);
                book = bookService.addSpecializedBook(book);
                break;
            case 2:
                System.out.print("Enter journal name: ");
                String journalName = scanner.nextLine();
                book = new Article(null, title, author, genre, journalName);
                book = bookService.addSpecializedBook(book);
                break;
            case 3:
                System.out.print("Enter issue: ");
                String issue = scanner.nextLine();
                book = new Magazine(null, title, author, genre, issue);
                book = bookService.addSpecializedBook(book);
                break;
            case 4:
                System.out.print("Enter number of pages: ");
                int numberOfPages = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                book = new Livre(null, title, author, genre, numberOfPages);
                book = bookService.addSpecializedBook(book);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Book added: " + book);
        historiqueService.logAction(user, book, "Added");
    }

    private static void updateBook(Scanner scanner, User user) {
        System.out.print("Enter book ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Optional<Book> bookOptional = bookService.getBookById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            System.out.print("Enter new title (current: " + book.getTitle() + "): ");
            String title = scanner.nextLine();
            System.out.print("Enter new author (current: " + book.getAuthor() + "): ");
            String author = scanner.nextLine();
            System.out.print("Enter new genre (current: " + book.getGenre() + "): ");
            String genre = scanner.nextLine();

            bookService.updateBook(id, title, author, genre);
            System.out.println("Book updated: " + book);
            historiqueService.logAction(user, book, "Updated");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void deleteBook(Scanner scanner, User user) {
        System.out.print("Enter book ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Optional<Book> bookOptional = bookService.getBookById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            bookService.deleteBook(id);
            System.out.println("Book deleted: " + book);
            historiqueService.logAction(user, book, "Deleted");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void viewAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void editUserInfo(Scanner scanner, User user) {
        System.out.print("Enter new username (current: " + user.getUsername() + "): ");
        String username = scanner.nextLine();
        System.out.print("Enter new email (current: " + user.getEmail() + "): ");
        String email = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();

        userService.updateUser(user.getId(), username, email, password);
        System.out.println("User info updated.");
    }

    private static void viewHistory(User user) {
        List<Historique> history = historiqueService.getHistoryByUser(user);
        if (history.isEmpty()) {
            System.out.println("No history found.");
        } else {
            history.forEach(System.out::println);
        }
    }
}
