package Controllers.LoginLogout;

import java.io.IOException;

import Models.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import javafx.scene.layout.AnchorPane;

public class StartSceneController {

    @FXML
    private AnchorPane left;

    @FXML
    private AnchorPane topFiveBooks;

    @FXML
    private TableColumn<Book, String> author;

    @FXML
    private TableColumn<Book, String> rating;

    @FXML
    private TableColumn<Book, String> title;

    @FXML
    private TableColumn<Book, String> numOfRatings;

    @FXML
    private TableColumn<Book, String> isbn;

    @FXML
    private TableView<Book> topFives;

    public void initialize() {
        List<Book> bookList = Book.getTopFiveBooks();
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        numOfRatings.setCellValueFactory(new PropertyValueFactory<>("numOfRatings"));
        isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        topFives.getItems().setAll(bookList);
        loadNewContentToLeftPane();
    }

    private void loadNewContentToLeftPane() {
        try {
            // Load the new content
            Node newContent = FXMLLoader.load(getClass().getResource("../../Views/SignIn.fxml"));

            // Clear existing content if necessary
            left.getChildren().clear();

            // Add new content
            AnchorPane.setTopAnchor(newContent, 0.0);
            AnchorPane.setRightAnchor(newContent, 0.0);
            AnchorPane.setBottomAnchor(newContent, 0.0);
            AnchorPane.setLeftAnchor(newContent, 0.0);
            left.getChildren().add(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
