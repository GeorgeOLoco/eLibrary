package Controllers.AdminPages;

import java.io.IOException;
import java.util.List;

import Models.Book;
import Models.Category;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ManageBooks {

    private Book selectedBook;

    @FXML
    private BorderPane manageBooksBorder;

    @FXML
    private TableColumn<Book, String> author;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> category;

    @FXML
    private ListView<String> categoryList;

    @FXML
    private TableColumn<Book, String> isbn;

    @FXML
    private AnchorPane manageBooks;

    @FXML
    private TableColumn<Book, String> publishYear;

    @FXML
    private TableColumn<Book, String> publisher;

    @FXML
    private TableColumn<Book, String> rating;

    @FXML
    private TableColumn<Book, String> title;

    public void initialize() {
        List<Book> bookList = Book.getAllBooks();
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        publishYear.setCellValueFactory(new PropertyValueFactory<>("PublishYear"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("Publisher"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        // numOfRatings.setCellValueFactory(new PropertyValueFactory<>("numOfRatings"));
        isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        bookTable.getItems().setAll(bookList);

        // List<String> categories = Category.getAllCategoriesString(); // This method
        // must return a List<String>
        categoryList.setItems(FXCollections.observableArrayList(Category.getAllCategoriesString()));

    }

    @FXML
    void btnAddBookClicked(ActionEvent event) {
        loadNewContentToLeftPane("../../Views/AddBook.fxml");

    }

    @FXML
    void btnDeleteBookClicked(ActionEvent event) {
        selectedBook = bookTable.getSelectionModel().getSelectedItem();
        boolean check = Book.deleteBook(selectedBook);
        if (selectedBook != null && categoryList.getSelectionModel().getSelectedItem() != null)
            initializeList();
        if (categoryList.getSelectionModel().getSelectedItem() == null)
            initialize();
        if (check)
            selectedBook = null;
    }

    @FXML
    void btnEditBookClicked(ActionEvent event) {
        if (selectedBook != null) {
            EditBook.setCurrentBook(selectedBook);
            loadNewContentToLeftPane("../../Views/EditBook.fxml");
        } else {
            Utils.Utils.showErrorPopUp("Choose a Book.");
        }
    }

    @FXML
    void BookSelected(MouseEvent event) {
        selectedBook = bookTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    void CategorySelected(MouseEvent event) {
        initializeList();
    }

    public void initializeList() {
        String selectedCategory = categoryList.getSelectionModel().getSelectedItem();
        List<Book> bookList = Book.getAllBooksByCategory(selectedCategory);
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        publishYear.setCellValueFactory(new PropertyValueFactory<>("PublishYear"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("Publisher"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        bookTable.getItems().setAll(bookList);
    }

    private void loadNewContentToLeftPane(String path) {
        try {
            // Load the new content
            Node newContent = FXMLLoader.load(getClass().getResource(path));

            // Clear existing content if necessary
            manageBooks.getChildren().clear();

            // Add new content
            AnchorPane.setTopAnchor(newContent, 0.0);
            AnchorPane.setRightAnchor(newContent, 0.0);
            AnchorPane.setBottomAnchor(newContent, 0.0);
            AnchorPane.setLeftAnchor(newContent, 0.0);
            manageBooks.getChildren().add(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
