package Controllers.AdminPages;

import java.io.IOException;

import Models.Book;
import Models.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddBook {

    @FXML
    private AnchorPane addBook;

    @FXML
    private TextField authorText;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    @FXML
    private TextField isbnText;

    @FXML
    private TextField numberOfCopiesText;

    @FXML
    private TextField publishYearText;

    @FXML
    private TextField publisherText;

    @FXML
    private TextField titleText;

    @FXML
    void btnAddBookClicked(ActionEvent event) {
        try {
            String title = titleText.getText();
            String publisher = publisherText.getText();
            int publishYear = Integer.parseInt(publishYearText.getText());
            int numberOfCopies = Integer.parseInt(numberOfCopiesText.getText());
            String isbn = isbnText.getText();
            String author = authorText.getText();
            String category = categoryChoiceBox.getValue();

            boolean isValid = Book.isBookGG(isbn, title, author, publisher, publishYear, category, numberOfCopies,
                    numberOfCopies, 'n');

            if (isValid) {
                Book newBook = new Book(isbn, title, author, publisher, publishYear, category, numberOfCopies);
                Book.addToList(newBook);
                // Clear the text fields
                titleText.clear();
                isbnText.clear();
                authorText.clear();
                publishYearText.clear();
                publisherText.clear();
                numberOfCopiesText.clear();
                categoryChoiceBox.setValue(Category.getAllCategoriesString().get(0));

            }
        } catch (NumberFormatException e) {
            Utils.Utils.showErrorPopUp("Publish year or number of copies is not a valid number.");
        } catch (Exception e) {
            Utils.Utils.showErrorPopUp("An error occurred: " + e.getMessage());
        }
    }

    @FXML
    void btnBackClicked(ActionEvent event) {
        try {
            // Load the new content
            Node newContent = FXMLLoader.load(getClass().getResource("../../Views/ManageBooks.fxml"));

            // Clear existing content if necessary
            addBook.getChildren().clear();

            // Add new content
            AnchorPane.setTopAnchor(newContent, 0.0);
            AnchorPane.setRightAnchor(newContent, 0.0);
            AnchorPane.setBottomAnchor(newContent, 0.0);
            AnchorPane.setLeftAnchor(newContent, 0.0);
            addBook.getChildren().add(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initialize() {
        categoryChoiceBox.getItems().addAll(Category.getAllCategoriesString());
        // Set a default value
        categoryChoiceBox.setValue(Category.getAllCategoriesString().get(0));
    }

}