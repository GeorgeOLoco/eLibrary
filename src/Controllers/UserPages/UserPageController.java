package Controllers.UserPages;

import java.io.IOException;

import Controllers.PopUpScreens.AreYouSureController;
import Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class UserPageController {

    public void initialize() {

        SearchBook.setStyle(
                "-fx-background-color: #ddd1e7; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: black;");

        loadNewContentToLeftPane("../../Views/SearchBook.fxml");
    }

    private void loadNewContentToLeftPane(String path) {
        try {
            Node newContent = FXMLLoader.load(getClass().getResource(path));

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

    @FXML
    private Button RentedBooks;

    @FXML
    private Button SearchBook;

    @FXML
    private AnchorPane left;

    @FXML
    void btnLogOutClicked(ActionEvent event) {
        RentedBooks.setStyle("-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 2 2;");
        SearchBook.setStyle("-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 0 2;");

        Utils.areYouSurePopUp();
        if (AreYouSureController.getAreYouSure()) {
            Window currentWindow = ((Node) event.getSource()).getScene().getWindow();
            if (currentWindow instanceof Stage) {
                ((Stage) currentWindow).close();
            }

            // Open Main.fxml in a new window
            try {
                // Load the new content
                Parent root = FXMLLoader.load(getClass().getResource("../../Views/StartScene.fxml"));

                // Create a new stage and scene
                Stage stage = new Stage();
                Image icon = new Image(getClass().getResourceAsStream("../../icons/ScreenIcons/user.png"));
                stage.getIcons().add(icon);
                stage.setScene(new Scene(root));
                stage.setTitle("eLibrary");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnRentedBooksClicked(ActionEvent event) {
        RentedBooks.setStyle(
                "-fx-background-color: #ddd1e7; -fx-border-color: black; -fx-border-width: 2 2 2 2; -fx-text-fill: black;");
        SearchBook.setStyle(
                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: white;");

        loadNewContentToLeftPane("../../Views/RentedBooks.fxml");
    }

    @FXML
    void btnSearchBookClicked(ActionEvent event) {
        RentedBooks.setStyle(
                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 2 2; -fx-text-fill: white;");
        SearchBook.setStyle(
                "-fx-background-color: #ddd1e7; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: black;");

        loadNewContentToLeftPane("../../Views/SearchBook.fxml");
    }

}
