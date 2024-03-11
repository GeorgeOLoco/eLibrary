package Controllers.AdminPages;

import java.util.List;
import java.io.IOException;

import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class ManageUsers {

    public void initialize() {
        List<User> userList = User.getAllUsers();
        username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));

        usersTable.getItems().setAll(userList);
    }

    private void loadNewContentToLeftPane(String path) {
        try {
            // Load the new content
            Node newContent = FXMLLoader.load(getClass().getResource(path));

            // Clear existing content if necessary
            manageUsers.getChildren().clear();

            // Add new content
            AnchorPane.setTopAnchor(newContent, 0.0);
            AnchorPane.setRightAnchor(newContent, 0.0);
            AnchorPane.setBottomAnchor(newContent, 0.0);
            AnchorPane.setLeftAnchor(newContent, 0.0);
            manageUsers.getChildren().add(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private User selectedUser;

    @FXML
    private TableColumn<User, String> email;

    @FXML
    private TableColumn<User, String> id;

    @FXML
    private AnchorPane manageUsers;

    @FXML
    private BorderPane manageUsersBorder;

    @FXML
    private TableColumn<User, String> mobile;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, String> surname;

    @FXML
    private TableColumn<User, String> username;

    @FXML
    private TableView<User> usersTable;

    @FXML
    void UserSelected(MouseEvent event) {
        selectedUser = usersTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    void btnDeleteUserClicked(ActionEvent event) {
        selectedUser = usersTable.getSelectionModel().getSelectedItem();
        boolean check = User.deleteUser(selectedUser);
        if (selectedUser != null)
            initialize();
        if (check)
            selectedUser = null;
    }

    @FXML
    void btnEditUserClicked(ActionEvent event) {
        if (selectedUser != null) {
            EditUser.setCurrentUser(selectedUser);
            loadNewContentToLeftPane("../../Views/EditUser.fxml");
        } else {
            Utils.Utils.showErrorPopUp("Select a User.");
        }
    }

}
