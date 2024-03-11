package Controllers.LoginLogout;

import Models.User;
import Utils.CurrentStuff;
import Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignInController {

    @FXML
    private AnchorPane SignIn;

    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField usernameText;

    @FXML
    void btnSignInClicked(ActionEvent event) {
        String username = usernameText.getText();
        String password = passwordText.getText();
        User user = User.getUser(username, password);
        if (user == null) {
            usernameText.clear();
            passwordText.clear();
            Utils.showErrorPopUp("Wrong Credentials.");
        } else if (user.isAdmin()) {
            // Open MainAdmin.fxml in a new window
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Views/MainAdmin.fxml"));
                Parent root = loader.load();
                // Create a new stage and scene
                Stage stage = new Stage();
                Image icon = new Image(getClass().getResourceAsStream("../../icons/ScreenIcons/admin.png"));
                stage.getIcons().add(icon);
                stage.setScene(new Scene(root));
                stage.setTitle("Admin Page");
                stage.show();

                ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Open Main.fxml in a new window
            try {
                CurrentStuff.setUser(user);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Views/Main.fxml"));
                Parent root = loader.load();

                // Create a new stage and scene
                Stage stage = new Stage();
                Image icon = new Image(getClass().getResourceAsStream("../../icons/ScreenIcons/user.png"));
                stage.getIcons().add(icon);
                stage.setScene(new Scene(root));
                stage.setTitle("User Page");
                stage.show();

                ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnSignUpClicked(ActionEvent event) {
        try {
            // Load the new content
            Node newContent = FXMLLoader.load(getClass().getResource("../../Views/SignUp.fxml"));

            // Clear the existing content
            SignIn.getChildren().clear();

            // Add the new content
            SignIn.getChildren().add(newContent);

            // Optionally, set the AnchorPane constraints if needed
            AnchorPane.setTopAnchor(newContent, 0.0);
            AnchorPane.setRightAnchor(newContent, 0.0);
            AnchorPane.setBottomAnchor(newContent, 0.0);
            AnchorPane.setLeftAnchor(newContent, 0.0);

            SignIn.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
