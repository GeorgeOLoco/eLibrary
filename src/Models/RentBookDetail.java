package Models;

public class RentBookDetail {
    private String title;
    private String isbn;
    private String returnDate;
    private String rentDate;

    // Constructor
    public RentBookDetail(Rent r, Book b) {
        this.title = b.getTitle();
        this.isbn = r.getISBN();
        this.rentDate = r.getDateAndTime();
        this.returnDate = r.getReturnDate();
    }

    public String getTitle() {
        return this.title;
    }

    public String getISBN() {
        return this.isbn;
    }

    public String getRentDate() {
        return this.rentDate;
    }

    public String getReturnDate() {
        return this.returnDate;
    }
}
