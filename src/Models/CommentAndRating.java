package Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Utils.SerializationUtils;
import Utils.Utils;

public class CommentAndRating implements java.io.Serializable {

    private String username;
    private String isbn;
    private String comment;
    private int rating;
    private static List<CommentAndRating> allCommentsAndRatings = new ArrayList<>();

    private static final long serialVersionUID = 2319305233683271995L;

    // Constructor
    public CommentAndRating(String username, String isbn, String comment, int rating) {
        this.username = username;
        this.isbn = isbn;
        this.rating = rating;
        if (comment == null || comment.trim().isEmpty()) {
            switch (rating) {
                case 1:
                    this.comment = "Awful.";
                    break;
                case 2:
                    this.comment = "Just a normal book.";
                    break;
                case 3:
                    this.comment = "Nice book.";
                    break;
                case 4:
                    this.comment = "Great book. I reccomend it.";
                    break;
                case 5:
                    this.comment = "Amazing Book! I definetly reccomend it!";
                    break;
            }
        } else {
            this.comment = comment;
        }
        Book b = Book.getBook(this.isbn);
        b.setRating(this.rating);
    }

    // Delete CommentsAndRatings of a book
    public static void deleteCommentAndRatingOfABook(String isbn) {
        Iterator<CommentAndRating> iterator = allCommentsAndRatings.iterator();
        while (iterator.hasNext()) {
            CommentAndRating c = iterator.next();
            if (isbn.equals(c.isbn)) {
                iterator.remove(); // Use iterator's remove method
            }
        }
    }

    // Check if a review is GG
    public static boolean isReviewGG(String username, String isbn, int rating) {
        if (rating == 0) {
            Utils.showErrorPopUp("Add a rating.");
            return false;
        }
        if (allCommentsAndRatings.isEmpty())
            return true;
        for (CommentAndRating s : CommentAndRating.allCommentsAndRatings) {
            if (username.equals(s.username) && isbn.equals(s.getISBN())) {
                Utils.showErrorPopUp("Already reviewed that Book.");
                return false;
            }
        }
        return true;
    }

    // get username
    public String getUsername() {
        return this.username;
    }

    // get isbn
    public String getISBN() {
        return this.isbn;
    }

    // get comment
    public String getComment() {
        return this.comment;
    }

    // get rating
    public int getRating() {
        return this.rating;
    }

    // get CommentAndRating for a book
    public static List<CommentAndRating> getCommentAndRating(String isbn) {
        List<CommentAndRating> comandrat = new ArrayList<>();
        for (CommentAndRating b : allCommentsAndRatings) {
            if (b.isbn == isbn)
                allCommentsAndRatings.add(b);
        }
        return comandrat;
    }

    // get all comments and ratings
    public static List<CommentAndRating> getAllCommentAndRating() {
        return allCommentsAndRatings;
    }

    // get CommentsAndRating as a String for a book
    public static List<String> getComments(String isbn) {
        List<String> comments = new ArrayList<>();
        for (CommentAndRating b : allCommentsAndRatings) {
            if (b.isbn.equals(isbn)) {
                String ratingAndComment = "Rating: " + b.getRating() + "/5" + " Comment: " + b.getComment();
                comments.add(ratingAndComment);
            }
        }
        return comments;
    }

    // add to list
    public static void addToList(CommentAndRating newCommentAndRating) {
        CommentAndRating.allCommentsAndRatings.add(newCommentAndRating);
    }

    // remove from list
    public static void removeFromList(CommentAndRating commentAndRating) {
        CommentAndRating.allCommentsAndRatings.remove(commentAndRating);
    }

    // Serialize
    public static void serialize() {
        SerializationUtils.serializeAll(CommentAndRating.allCommentsAndRatings, CommentAndRating.class);
    }

    // Deserialize
    public static void deserialize() {
        CommentAndRating.allCommentsAndRatings = SerializationUtils.deserializeAll(CommentAndRating.class);
    }

}
