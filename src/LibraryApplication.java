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
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Search Books");
            System.out.println("4. Add Book");
            System.out.println("5. View History");
            System.out.println("6. Exit");
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
                    if (currentUser != null) {
                        searchBooks(scanner, currentUser);
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;
                case 4:
                    if (currentUser != null) {
                        addBook(scanner, currentUser);
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;
                case 5:
                    if (currentUser != null) {
                        viewHistory(currentUser);
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
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
                book = bookService.addBook(title, author, genre);
                break;
            case 2:
                System.out.print("Enter journal name: ");
                String journalName = scanner.nextLine();
                book = new Article(null, title, author, genre, journalName);
                break;
            case 3:
                System.out.print("Enter issue: ");
                String issue = scanner.nextLine();
                book = new Magazine(null, title, author, genre, issue);
                break;
            case 4:
                System.out.print("Enter number of pages: ");
                int numberOfPages = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                book = new Livre(null, title, author, genre, numberOfPages);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        bookService.addBook(book.getTitle(), book.getAuthor(), book.getGenre());
        System.out.println("Book added: " + book);
        historiqueService.logAction(user, book, "Added");
    }

    private static void viewHistory(User user) {
        List<Historique> historiqueList = historiqueService.getHistoriqueByUser(user);
        if (historiqueList.isEmpty()) {
            System.out.println("No history found.");
        } else {
            historiqueList.forEach(System.out::println);
        }
    }
}
