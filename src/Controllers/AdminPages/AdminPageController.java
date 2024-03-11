package Controllers.AdminPages;

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

public class AdminPageController {

        public void initialize() {
                ManageBooks.setStyle(
                                "-fx-background-color: #ddd1e7; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: black;");
                loadNewContentToLeftPane("../../Views/ManageBooks.fxml");
        }

        private void loadNewContentToLeftPane(String path) {
                try {
                        // Load the new content
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
        private Button ManageBooks;

        @FXML
        private Button ManageCategories;

        @FXML
        private Button ManageRents;

        @FXML
        private Button ManageUsers;

        @FXML
        private AnchorPane left;

        @FXML
        void btnLogOutClicked(ActionEvent event) {
                ManageUsers.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 2 2; -fx-text-fill: white;");
                ManageBooks.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: white;");
                ManageRents.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: white;");
                ManageCategories.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: white;");

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
                                stage.setScene(new Scene(root));
                                Image icon = new Image(
                                                getClass().getResourceAsStream("../../icons/ScreenIcons/user.png"));
                                stage.getIcons().add(icon);
                                stage.setTitle("eLibrary");
                                stage.show();
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
        }

        @FXML
        void btnManageBooksClicked(ActionEvent event) {
                ManageBooks.setStyle(
                                "-fx-background-color: #ddd1e7; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: black;");
                ManageCategories.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: white;");
                ManageRents.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: white;");
                ManageUsers.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 2 2; -fx-text-fill: white;");

                loadNewContentToLeftPane("../../Views/ManageBooks.fxml");

        }

        @FXML
        void btnManageCategoriesClicked(ActionEvent event) {
                ManageCategories.setStyle(
                                "-fx-background-color: #ddd1e7; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: black;");
                ManageBooks.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: white;");
                ManageRents.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: white;");
                ManageUsers.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 2 2; -fx-text-fill: white;");

                loadNewContentToLeftPane("../../Views/ManageCategories.fxml");

        }

        @FXML
        void btnManageRentsClicked(ActionEvent event) {
                ManageRents.setStyle(
                                "-fx-background-color: #ddd1e7; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: black;");
                ManageCategories.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: white;");
                ManageBooks.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: white;");
                ManageUsers.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 2 2; -fx-text-fill: white;");

                loadNewContentToLeftPane("../../Views/ManageRents.fxml");
        }

        @FXML
        void btnManageUsersClicked(ActionEvent event) {
                ManageUsers.setStyle(
                                "-fx-background-color: #ddd1e7; -fx-border-color: black; -fx-border-width: 2 2 2 2; -fx-text-fill: black;");
                ManageBooks.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: white;");
                ManageCategories.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: white;");
                ManageRents.setStyle(
                                "-fx-background-color: indigo; -fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-text-fill: white;");

                loadNewContentToLeftPane("../../Views/ManageUsers.fxml");
        }

}