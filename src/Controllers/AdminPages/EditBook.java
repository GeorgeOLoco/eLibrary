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

public class EditBook {

    private static Book currentBook;

    public static void setCurrentBook(Book book) {
        currentBook = book;
    }

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
    void btnEditBookClicked(ActionEvent event) {
        try {
            String title = titleText.getText();
            String publisher = publisherText.getText();
            int publishYear = Integer.parseInt(publishYearText.getText());
            int numberOfCopies = Integer.parseInt(numberOfCopiesText.getText());
            String isbn = isbnText.getText();
            String author = authorText.getText();
            String category = categoryChoiceBox.getValue();

            boolean isValid;

            if (isbn.equals(currentBook.getISBN())) {
                isValid = Book.isBookGG(null, title, author, publisher, publishYear, category, numberOfCopies,
                        numberOfCopies, 'u');
            } else {
                isValid = Book.isBookGG(isbn, title, author, publisher, publishYear, category, numberOfCopies,
                        numberOfCopies, 'u');
            }

            if (isValid) {
                // Edit the book
                currentBook.editBook(isbn, title, author, publisher, publishYear, category, numberOfCopies,
                        numberOfCopies);
                // Update the text fields
                initialize();
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
        // categoryChoiceBox.setValue(Category.getAllCategoriesString().get(0));
        titleText.setText(currentBook.getTitle());
        isbnText.setText(currentBook.getISBN());
        authorText.setText(currentBook.getAuthor());
        publishYearText.setText(Integer.toString(currentBook.getPublishYear()));
        publisherText.setText(currentBook.getPublisher());
        numberOfCopiesText.setText(Integer.toString(currentBook.getNumOfCopies()));
        categoryChoiceBox.setValue(currentBook.getCategory());
    }

}