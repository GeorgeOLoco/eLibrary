/**
 * In order to work go in the directory Models and at the CommentAndReview.java make comments the lines 64 and 71
 */

package creator;

import Models.Book;
import Models.CommentAndRating;
import Models.User;

import java.util.Random;

public class CreateAndSerializeCommentsAndRatings implements java.io.Serializable {

    public static void main(String[] args) {
        // Assume Books and Users have been deserialized previously in the application
        // lifecycle
        Book.deserialize();
        User.deserialize();
        CommentAndRating.deserialize();

        Random random = new Random();
        // Iterate over each book to generate comments and ratings
        for (Book book : Book.getAllBooks()) {
            // Generate a random number of comments for each book, between 2 and 10
            int numberOfComments = 2 + random.nextInt(9);
            for (int i = 0; i < numberOfComments; i++) {
                User user = User.getAllUsers().get(random.nextInt(User.getAllUsers().size()));
                int rating = 1 + random.nextInt(5); // Ratings between 1 and 5
                String comment = null; // Null to let the constructor handle the default comment based on rating
                if (CommentAndRating.isReviewGG(user.getUsername(), book.getISBN(), rating)) {
                    CommentAndRating newCommentAndRating = new CommentAndRating(user.getUsername(), book.getISBN(),
                            comment, rating);
                    CommentAndRating.addToList(newCommentAndRating);
                }
            }
        }

        // Serialize comments and ratings after generation
        CommentAndRating.serialize();
        Book.serialize();
        System.out.println("Comments and ratings have been created and serialized successfully.");
    }
}
