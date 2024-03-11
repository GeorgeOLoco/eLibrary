package Utils;

import Controllers.PopUpScreens.ErrorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Utils {
    public static void showErrorPopUp(String errorMessage) {
        try {
            FXMLLoader loader = new FXMLLoader(Utils.class.getResource("../Views/ErrorScene.fxml"));
            Parent root = loader.load();

            // Get the controller and set the error message
            ErrorController controller = loader.getController();
            controller.setErrorMessage(errorMessage);

            // Create a new stage for the pop-up
            Stage stage = new Stage();
            Image icon = new Image(Utils.class.getResourceAsStream("../icons/ScreenIcons/warning.png"));
            stage.getIcons().add(icon);
            stage.initModality(Modality.APPLICATION_MODAL); // Block input events until the pop-up is closed
            // stage.setTitle("Error");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSuccessfullPopUp() {
        try {
            FXMLLoader loader = new FXMLLoader(Utils.class.getResource("../Views/SuccessfullOperationScene.fxml"));
            Parent root = loader.load();

            // Create a new stage for the pop-up
            Stage stage = new Stage();
            Image icon = new Image(Utils.class.getResourceAsStream("../icons/ScreenIcons/checked.png"));
            stage.getIcons().add(icon);
            stage.initModality(Modality.APPLICATION_MODAL); // Block input events until the pop-up is closed
            // stage.setTitle("Successful");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void areYouSurePopUp() {
        try {
            FXMLLoader loader = new FXMLLoader(Utils.class.getResource("../Views/AreYouSureScene.fxml"));
            Parent root = loader.load();

            // Create a new stage for the pop-up
            Stage stage = new Stage();
            Image icon = new Image(Utils.class.getResourceAsStream("../icons/ScreenIcons/question-mark.png"));
            stage.getIcons().add(icon);
            stage.initModality(Modality.APPLICATION_MODAL); // Block input events until the pop-up is closed
            // stage.setTitle("Are you Sure?");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
