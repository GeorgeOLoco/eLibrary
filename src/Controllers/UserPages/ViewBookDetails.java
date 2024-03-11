package Controllers.UserPages;

import Models.Book;
import Models.CommentAndRating;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ViewBookDetails {

    public void initialize(Book currentBook) {
        titleLabel.setText(currentBook.getTitle());
        isbnLabel.setText(currentBook.getISBN());
        authorLabel.setText(currentBook.getAuthor());
        publisherLabel.setText(currentBook.getPublisher());
        publishYearLabel.setText(Integer.toString(currentBook.getPublishYear()));
        categoryLabel.setText(currentBook.getCategory());
        ratingLabel.setText(Double.toString(currentBook.getRating()));
        numOfRatingsLabel.setText(Integer.toString(currentBook.getNumOfRatings()));
        numOfAvailableCopiesLabel.setText(Integer.toString(currentBook.getNumOfAvailableCopies()));

        commentsList.setItems(FXCollections.observableArrayList(CommentAndRating.getComments(currentBook.getISBN())));
    }

    @FXML
    private Label authorLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private ListView<String> commentsList;

    @FXML
    private Label isbnLabel;

    @FXML
    private Label numOfAvailableCopiesLabel;

    @FXML
    private Label numOfRatingsLabel;

    @FXML
    private Label publishYearLabel;

    @FXML
    private Label publisherLabel;

    @FXML
    private Label ratingLabel;

    @FXML
    private Label titleLabel;

}
