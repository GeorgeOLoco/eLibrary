package Controllers.AdminPages;

import java.io.IOException;

import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EditUser {

    private static User currentUser;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public void initialize() {
        usernameText.setText(currentUser.getUsername());
        nameText.setText(currentUser.getName());
        surnameText.setText(currentUser.getSurname());
        mobileText.setText(currentUser.getMobile());
        emailText.setText(currentUser.getEmail());
        idText.setText(currentUser.getID());

    }

    @FXML
    private AnchorPane addUser;

    @FXML
    private TextField emailText;

    @FXML
    private TextField idText;

    @FXML
    private TextField mobileText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField surnameText;

    @FXML
    private TextField usernameText;

    @FXML
    void btnBackClicked(ActionEvent event) {
        try {
            // Load the new content
            Node newContent = FXMLLoader.load(getClass().getResource("../../Views/ManageUsers.fxml"));

            // Clear existing content if necessary
            addUser.getChildren().clear();

            // Add new content
            AnchorPane.setTopAnchor(newContent, 0.0);
            AnchorPane.setRightAnchor(newContent, 0.0);
            AnchorPane.setBottomAnchor(newContent, 0.0);
            AnchorPane.setLeftAnchor(newContent, 0.0);
            addUser.getChildren().add(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnEditUserClicked(ActionEvent event) {
        try {
            String username = usernameText.getText();
            String name = nameText.getText();
            String surname = surnameText.getText();
            String id = idText.getText();
            String email = emailText.getText();
            String mobile = mobileText.getText();

            boolean isValid;

            isValid = User.isUserGG(currentUser, username, currentUser.getPassword(), currentUser.getPassword(), name,
                    surname, id,
                    email, mobile, 'u');

            if (isValid) {
                currentUser.editUser(username, name, surname, id, email, mobile);
                initialize();
            }
        } catch (Exception e) {
            Utils.Utils.showErrorPopUp("An error occurred: " + e.getMessage());
        }
    }

}
