package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

import Utils.BookStatus;
import Utils.SerializationUtils;
import Utils.Utils;

public class Rent implements java.io.Serializable {
    private String username;
    private String isbn;
    private BookStatus status;
    private LocalDateTime dateTime;
    private static List<Rent> allRents = new ArrayList<>();

    private static final long serialVersionUID = -7380031418880201916L;

    // Constructor
    public Rent(Book b, User u) {
        this.username = u.getUsername();
        this.isbn = b.getISBN();
        this.status = BookStatus.PENDING;
    }

    // approve a rent
    public boolean approveRent() {
        if (!this.getStatus().equals("PENDING")) {
            Utils.showErrorPopUp("Select a pending rent.");
            return false;
        } else {
            status = BookStatus.OPEN;
            dateTime = LocalDateTime.now();
            return true;
        }
    }

    // delete a rent
    public static void deleteRent(Rent r) {
        Book b = Book.getBook(r.isbn);
        b.setNumOfAvailableCopies(1);
        User u = User.getUserByUsername(r.username);
        u.removeFromRentedBooks(b);
        Rent.allRents.remove(r);
    }

    // check if a rent is gg before we create it
    public static boolean isRentGG(User user, Book book) {
        Book[] bl = user.getRentedBooks();
        if ((bl[0] == null || bl[1] == null) && book.getNumOfAvailableCopies() > 0) {
            book.setNumOfAvailableCopies(-1);
            user.addToRentedBooks(book);
            return true;
        }
        if (user.getRentedBooks().length == 2)
            Utils.showErrorPopUp("Already have 2 books.");
        if (book.getNumOfAvailableCopies() == 0)
            Utils.showErrorPopUp("There are not any anailable books.");
        return false;
    }

    // check if a rent has expired
    public static void checkIfExpired() {
        LocalDateTime time = LocalDateTime.now();
        for (Rent r : allRents) {
            if (r.dateTime != null) {
                if (time.isAfter(r.dateTime.plusDays(5))) {
                    r.status = BookStatus.EXPIRED;
                }
            }
        }
    }

    // get username
    public String getUsername() {
        return this.username;
    }

    // get isbn
    public String getISBN() {
        return this.isbn;
    }

    // get status
    public String getStatus() {
        return this.status.name();
    }

    // get rent date
    public String getDateAndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTime.format(formatter);
    }

    // get return date
    public String getReturnDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTime.plusDays(5).format(formatter);
    }

    // add a rent to list
    public static void addToList(Rent b) {
        Rent.allRents.add(b);
    }

    // get all rents
    public static List<Rent> getAllRents() {
        return allRents;
    }

    // get a rent by book isbn
    public static Rent getARentByISBN(String isbn) {
        for (Rent r : allRents) {
            if (r.isbn.equals(isbn)) {
                return r;
            }
        }
        return null;
    }

    // delete all rents of a book
    public static void deleteRentsOfABook(String isbn) {
        List<Rent> toRemove = new ArrayList<>();
        for (Rent r : allRents) {
            if (r.isbn.equals(isbn)) {
                toRemove.add(r);
            }
        }
        for (Rent r : toRemove) {
            deleteRent(r);
        }
    }

    /*
     * set expired date
     * public void setExpiredDate() {
     * this.dateTime = LocalDateTime.now().minusDays(7);
     * }
     */

    // Serialize
    public static void serialize() {
        SerializationUtils.serializeAll(Rent.allRents, Rent.class);
    }

    // Deserialize
    public static void deserialize() {
        Rent.allRents = SerializationUtils.deserializeAll(Rent.class);
    }

}