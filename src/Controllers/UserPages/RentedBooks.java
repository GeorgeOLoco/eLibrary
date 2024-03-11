package Controllers.UserPages;

import java.util.ArrayList;
import java.util.List;

import Models.Book;
import Models.CommentAndRating;
import Models.Rent;
import Models.RentBookDetail;
import Models.User;
import Utils.CurrentStuff;
import Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class RentedBooks {

    private RentBookDetail selectedRent;
    private int rating;

    private User currentUser = CurrentStuff.getCurrentUser();

    @FXML
    void initialize() {
        rating = 0;
        initializeStars();
        initializeList();
    }

    public void initializeList() {
        selectedRent = null;
        List<RentBookDetail> userRentsDetails = new ArrayList<>();

        // found user's rents
        for (Rent r : Rent.getAllRents()) {
            if (r.getUsername().equals(currentUser.getUsername()) && r.getStatus() != "PENDING") {
                Book b = Book.getBook(r.getISBN());
                RentBookDetail rbd = new RentBookDetail(r, b);
                userRentsDetails.add(rbd);
            }
        }

        if (!userRentsDetails.isEmpty()) {
            isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            title.setCellValueFactory(new PropertyValueFactory<>("title"));
            rentDate.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
            returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
            rentTable.getItems().setAll(userRentsDetails);

        }
    }

    private void initializeStars() {
        setStar(star1, false);
        setStar(star2, false);
        setStar(star3, false);
        setStar(star4, false);
        setStar(star5, false);
    }

    private void setStar(Button button, boolean filled) {
        Image image = new Image(getClass()
                .getResourceAsStream(filled ? "../../icons/rate_star_filled.png" : "../../icons/rate_star.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
    }

    private void updateStars(int clickedStar) {
        if (selectedRent != null) {
            Button[] stars = { star1, star2, star3, star4, star5 };
            for (int i = 0; i < stars.length; i++) {
                setStar(stars[i], i < clickedStar);
            }
        } else {
            Utils.showErrorPopUp("Select a Book.");
        }
    }

    @FXML
    private TextArea commentText;

    @FXML
    private TableColumn<RentBookDetail, String> isbn;

    @FXML
    private TableColumn<RentBookDetail, String> rentDate;

    @FXML
    private TableView<RentBookDetail> rentTable;

    @FXML
    private AnchorPane rentedBooks;

    @FXML
    private BorderPane rentedBooksBorder;

    @FXML
    private TableColumn<RentBookDetail, String> returnDate;

    @FXML
    private Button star1;

    @FXML
    private Button star2;

    @FXML
    private Button star3;

    @FXML
    private Button star4;

    @FXML
    private Button star5;

    @FXML
    private TableColumn<RentBookDetail, String> title;

    @FXML
    void btnAddReviewClicked(ActionEvent event) {
        if (selectedRent != null) {

            String comment = commentText.getText();

            if (CommentAndRating.isReviewGG(currentUser.getUsername(), selectedRent.getISBN(), rating)) {
                CommentAndRating review = new CommentAndRating(currentUser.getUsername(), selectedRent.getISBN(),
                        comment,
                        rating);
                CommentAndRating.addToList(review);
                commentText.clear();
                initialize();
                Utils.showSuccessfullPopUp();
            } else {
                commentText.clear();
                initialize();
            }
        } else {
            Utils.showErrorPopUp("Select a Book.");
            commentText.clear();
        }

    }

    @FXML
    void btnSrar1Clicked(ActionEvent event) {
        rating = 1;
        updateStars(1);
    }

    @FXML
    void btnSrar2Clicked(ActionEvent event) {
        rating = 2;
        updateStars(2);
    }

    @FXML
    void btnSrar3Clicked(ActionEvent event) {
        rating = 3;
        updateStars(3);
    }

    @FXML
    void btnSrar4Clicked(ActionEvent event) {
        rating = 4;
        updateStars(4);
    }

    @FXML
    void btnSrar5Clicked(ActionEvent event) {
        rating = 5;
        updateStars(5);
    }

    @FXML
    void rentSelected(MouseEvent event) {
        selectedRent = rentTable.getSelectionModel().getSelectedItem();
    }

}
