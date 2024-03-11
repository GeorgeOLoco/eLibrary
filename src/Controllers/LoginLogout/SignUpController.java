package Controllers.LoginLogout;

import Models.User;
import Utils.CurrentStuff;
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

public class SignUpController {

    @FXML
    private AnchorPane SignUp;

    @FXML
    private PasswordField confirmpasswordText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField idText;

    @FXML
    private TextField mobileText;

    @FXML
    private TextField nameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField surnameText;

    @FXML
    private TextField usernameText;

    @FXML
    void btnSignInClicked(ActionEvent event) {
        try {
            // Load the new content
            Node newContent = FXMLLoader.load(getClass().getResource("../../Views/SignIn.fxml"));

            // Clear the existing content
            SignUp.getChildren().clear();

            // Add the new content
            SignUp.getChildren().add(newContent);

            // Optionally, set the AnchorPane constraints if needed
            AnchorPane.setTopAnchor(newContent, 0.0);
            AnchorPane.setRightAnchor(newContent, 0.0);
            AnchorPane.setBottomAnchor(newContent, 0.0);
            AnchorPane.setLeftAnchor(newContent, 0.0);

            SignUp.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSignUpClicked(ActionEvent event) {
        String username = usernameText.getText();
        String password = passwordText.getText();
        String confirmPassword = confirmpasswordText.getText();
        String name = nameText.getText();
        String surname = surnameText.getText();
        String uid = idText.getText();
        String email = emailText.getText();
        String mobile = mobileText.getText();

        if (User.isUserGG(null, username, password, confirmPassword, name, surname, uid, email, mobile, 'n')) {
            User user = new User(username, confirmPassword, name, surname, uid, email, mobile);
            User.addToList(user);

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

}
