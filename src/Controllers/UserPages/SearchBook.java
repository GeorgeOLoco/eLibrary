package Controllers.UserPages;

import java.util.List;
import java.io.IOException;

import Models.Book;
import Models.Rent;
import Models.User;
import Utils.CurrentStuff;
import Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchBook {

    private Book selectedBook;

    private static User currentUser;

    public void initialize() {
        currentUser = CurrentStuff.getCurrentUser();
        List<Book> bookList = Book.getAllBooks();
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        numberOfRatings.setCellValueFactory(new PropertyValueFactory<>("numOfRatings"));
        isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        bookTable.getItems().setAll(bookList);
    }

    public void initializeList() {
        String titleS = searchTitle.getText();
        String authorS = searchAuthor.getText();
        int py = 0;
        try {
            if (searchPublishYear.getText() != null && !searchPublishYear.getText().trim().isEmpty()) {
                py = Integer.parseInt(searchPublishYear.getText().trim());
            }
        } catch (NumberFormatException e) {
            Utils.showErrorPopUp("Publish Year is not a valid number");
        }
        List<Book> bookList = Book.getAllBooksBySearch(titleS, authorS, py);
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        numberOfRatings.setCellValueFactory(new PropertyValueFactory<>("numOfRatings"));
        isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        bookTable.getItems().setAll(bookList);
    }

    @FXML
    private TableColumn<Book, String> author;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> isbn;

    @FXML
    private TableColumn<Book, String> numberOfRatings;

    @FXML
    private TableColumn<Book, String> rating;

    @FXML
    private AnchorPane searchBook;

    @FXML
    private BorderPane searchBookBorder;

    @FXML
    private TextField searchTitle;

    @FXML
    private TextField searchAuthor;

    @FXML
    private TextField searchPublishYear;

    @FXML
    private TableColumn<Book, String> title;

    @FXML
    void BookSelected(MouseEvent event) {
        selectedBook = bookTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    void btnRentBookClicked(ActionEvent event) {
        if (selectedBook != null) {
            if (Rent.isRentGG(currentUser, selectedBook)) {
                Rent r = new Rent(selectedBook, currentUser);
                Rent.addToList(r);
                Utils.showSuccessfullPopUp();
            }
        } else {
            Utils.showErrorPopUp("Choose a Book.");
        }
    }

    @FXML
    void btnViewDetailsClicked(ActionEvent event) {
        if (selectedBook != null) {
            try {
                // Load the detail view FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Views/ViewDetails.fxml"));
                Parent root = loader.load();

                // Get the controller of the detail view and pass the selected book
                ViewBookDetails controller = loader.getController();
                controller.initialize(selectedBook);

                Stage stage = new Stage();
                Image icon = new Image(getClass().getResourceAsStream("../../icons/ScreenIcons/user.png"));
                stage.getIcons().add(icon);
                stage.setTitle("Book Details");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                Utils.showErrorPopUp("Failed to open book details.");
            }

            // create a new window
        } else {
            Utils.showErrorPopUp("Choose a Book.");
        }
    }

    @FXML
    void changedSearchText(KeyEvent event) {
        initializeList();
    }

}
