package Models;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import Controllers.PopUpScreens.AreYouSureController;
import Utils.SerializationUtils;
import Utils.Utils;
import java.text.DecimalFormat;

public class Book implements java.io.Serializable {

    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private int publishYear;
    private String category;
    private int numOfCopies;
    private int numOfAvailableCopies;
    private double rating;
    private int numOfRatings;
    private static List<Book> allBooks = new ArrayList<>();

    private static final long serialVersionUID = 391415479423617747L;

    // Constructor
    public Book(String isbn, String title, String author, String publisher, int publishYear, String category,
            int numOfCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.category = category;
        this.numOfCopies = numOfCopies;
        this.numOfAvailableCopies = numOfCopies;
        this.rating = 0.0;
        this.numOfRatings = 0;
    }

    // Edit Book
    public void editBook(String isbn, String title, String author, String publisher, int publishYear, String category,
            int numOfCopies, int numOfAvailableCopies) {
        if (isbn != null && !isbn.trim().isEmpty())
            this.isbn = isbn;
        if (title != null && !title.trim().isEmpty())
            this.title = title;
        if (author != null && !author.trim().isEmpty())
            this.author = author;
        if (publisher != null && !publisher.trim().isEmpty())
            this.publisher = publisher;
        if (publishYear > 0)
            this.publishYear = publishYear;
        if (category != null && !category.trim().isEmpty())
            this.category = category;
        if (numOfCopies >= 0)
            this.numOfCopies = numOfCopies;
        if (numOfAvailableCopies >= 0 && numOfAvailableCopies <= numOfCopies)
            this.numOfAvailableCopies = numOfAvailableCopies;
    }

    // Delete book
    public static boolean deleteBook(Book b) {
        boolean result = false;
        if (b != null) {
            Utils.areYouSurePopUp();
            if (AreYouSureController.getAreYouSure()) {
                String bookISBN = b.getISBN();
                CommentAndRating.deleteCommentAndRatingOfABook(bookISBN);
                Rent.deleteRentsOfABook(bookISBN);
                removeFromList(b);
                result = AreYouSureController.getAreYouSure();
                AreYouSureController.setAreYouSure();
            }
        } else {
            Utils.showErrorPopUp("Select a book.");
            result = false;
        }
        return result;

    }

    // check if book data are gg
    public static boolean isBookGG(String isbn, String title, String author, String publisher, int publishYear,
            String category,
            int numOfCopies, int numOfAvailableCopies, char w) {

        for (Book b : allBooks) {
            if (b.isbn.equals(isbn)) {
                Utils.showErrorPopUp("ISBN already exists.");
                return false;
            }
        }
        if (w == 'n') {

            boolean checked = (isbn == null || isbn.trim().isEmpty()) ||
                    (title == null || title.trim().isEmpty()) ||
                    (author == null || author.trim().isEmpty()) ||
                    (publisher == null || publisher.trim().isEmpty()) ||
                    (publishYear <= 0) || (publishYear > java.time.Year.now().getValue()) ||
                    (category == null || category.trim().isEmpty()) || !Category.findCategory(category) ||
                    (numOfCopies < 0) ||
                    (numOfAvailableCopies < 0 || numOfAvailableCopies > numOfCopies);

            if (checked) {
                Utils.showErrorPopUp("Please ensure all fields are correctly filled.");
                return false;
            } else {
                Utils.showSuccessfullPopUp();
                return true;
            }
        }

        if (w == 'u') {
            if ((publishYear <= 0) || (publishYear > java.time.Year.now().getValue()) ||
                    (numOfCopies < 0) || (numOfAvailableCopies < 0 ||
                            numOfAvailableCopies > numOfCopies)
                    || !Category.findCategory(category)) {
                Utils.showErrorPopUp("Please ensure all fields are correctly filled.");
                return false;
            } else {
                Utils.showSuccessfullPopUp();
                return true;
            }

        }

        Utils.showErrorPopUp("Something went wrong.");
        return false;
    }

    // get a book
    public static Book getBook(String isbn) {
        for (Book b : allBooks) {
            if (isbn.equals(b.isbn))
                return b;
        }
        return null;
    }

    // add to list
    public static void addToList(Book book) {
        Book.allBooks.add(book);
    }

    // remove from list
    public static void removeFromList(Book book) {
        // remove book from list
        Book.allBooks.remove(book);
    }

    // return top five books
    public static List<Book> getTopFiveBooks() {
        return allBooks.stream()
                .sorted(Comparator.comparingDouble(Book::getRating).reversed()) // Sort books by rating in descending
                                                                                // order
                .limit(5) // Limit to top 5
                .collect(Collectors.toList()); // Collect the results into a list
    }

    // return allbooks list
    public static List<Book> getAllBooks() {
        return Book.allBooks;
    }

    // return allbooks with a specific category list
    public static List<Book> getAllBooksByCategory(String category) {
        List<Book> books = new ArrayList<>();
        for (Book b : allBooks) {
            if (b.category.equals(category)) {
                books.add(b);
            }
        }
        return books;
    }

    // Get rating
    public double getRating() {
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        return Double.parseDouble(decimalFormat.format(this.rating));
    }

    // Get isbn
    public String getISBN() {
        return this.isbn;
    }

    // Get Number of Copies
    public Integer getNumOfCopies() {
        return this.numOfCopies;
    }

    // Get Number of Available Copies
    public Integer getNumOfAvailableCopies() {
        return this.numOfAvailableCopies;
    }

    // Get title
    public String getTitle() {
        return this.title;
    }

    // Get author
    public String getAuthor() {
        return author;
    }

    // Get publisher
    public String getPublisher() {
        return publisher;
    }

    // Get category
    public String getCategory() {
        return category;
    }

    // Get publishYear
    public int getPublishYear() {
        return publishYear;
    }

    // Get number of ratings
    public int getNumOfRatings() {
        return numOfRatings;
    }

    // Set the rating
    public void setRating(double rating) {
        this.rating = (rating + this.numOfRatings * this.rating) / (numOfRatings + 1);
        this.numOfRatings++;
    }

    // Set number of available copies
    public void setNumOfAvailableCopies(int i) {
        this.numOfAvailableCopies += i;
    }

    // return the filtered list
    public static List<Book> getAllBooksBySearch(String title, String author, int publishYear) {
        List<Book> filteredBooks = new ArrayList<>(); // Initialize the list to avoid NullPointerException

        for (Book b : Book.allBooks) {
            boolean matchesSearch = true;

            // Check each field only if a search parameter is provided and make comparison
            // case-insensitive
            if (author != null && !author.isEmpty() && !b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                matchesSearch = false;
            }
            if (title != null && !title.isEmpty() && !b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchesSearch = false;
            }
            // Assuming a publishYear of 0 or negative is not valid and should not be used
            // as a filter
            if (publishYear > 0 && b.getPublishYear() != publishYear) {
                matchesSearch = false;
            }

            if (matchesSearch) {
                filteredBooks.add(b);
            }
        }
        return filteredBooks;
    }

    // Serialize
    public static void serialize() {
        SerializationUtils.serializeAll(Book.allBooks, Book.class);
    }

    // Deserialize
    public static void deserialize() {
        Book.allBooks = SerializationUtils.deserializeAll(Book.class);
    }

}