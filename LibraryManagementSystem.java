import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Book class representing a book in the library
class Book {
    private String title;
    private String author;
    private String genre;
    private int bookId;
    private boolean isAvailable;

    public Book(String title, String author, String genre, int bookId) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = true; // Book is available by default
    }

    public void displayBookInfo() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Borrowed"));
        System.out.println("----------------------");
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("You have borrowed the book: " + title);
        } else {
            System.out.println("Sorry, this book is already borrowed.");
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("You have returned the book: " + title);
        } else {
            System.out.println("This book was not borrowed.");
        }
    }
}

// Library class to manage the collection of books
class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("\nLibrary Books:");
            for (Book book : books) {
                book.displayBookInfo();
            }
        }
    }

    public void searchByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.displayBookInfo();
                return;
            }
        }
        System.out.println("Book with title '" + title + "' not found.");
    }

    public void searchByGenre(String genre) {
        boolean found = false;
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                book.displayBookInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found in the genre: " + genre);
        }
    }

    public void removeBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                books.remove(book);
                System.out.println("Book removed successfully!");
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found!");
    }

    public void borrowBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                book.borrowBook();
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found!");
    }

    public void returnBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                book.returnBook();
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found!");
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library myLibrary = new Library();

        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Search Books by Genre");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Remove Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();

                    Book newBook = new Book(title, author, genre, bookId);
                    myLibrary.addBook(newBook);
                    break;

                case 2:
                    myLibrary.displayBooks();
                    break;

                case 3:
                    System.out.print("Enter Book Title to Search: ");
                    String searchTitle = scanner.nextLine();
                    myLibrary.searchByTitle(searchTitle);
                    break;

                case 4:
                    System.out.print("Enter Genre to Search: ");
                    String searchGenre = scanner.nextLine();
                    myLibrary.searchByGenre(searchGenre);
                    break;

                case 5:
                    System.out.print("Enter Book ID to Borrow: ");
                    int borrowId = scanner.nextInt();
                    myLibrary.borrowBook(borrowId);
                    break;

                case 6:
                    System.out.print("Enter Book ID to Return: ");
                    int returnId = scanner.nextInt();
                    myLibrary.returnBook(returnId);
                    break;

                case 7:
                    System.out.print("Enter Book ID to Remove: ");
                    int removeId = scanner.nextInt();
                    myLibrary.removeBook(removeId);
                    break;

                case 8:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}
