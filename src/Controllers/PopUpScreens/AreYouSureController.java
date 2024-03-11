package Controllers.PopUpScreens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Node;

public class AreYouSureController {

    private static boolean areYouSure = false;

    @FXML
    void btnNoClicked(ActionEvent event) {
        closeStage(event);
    }

    @FXML
    void btnYesClicked(ActionEvent event) {
        AreYouSureController.areYouSure = true;
        closeStage(event);
    }

    private void closeStage(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public static boolean getAreYouSure() {
        return AreYouSureController.areYouSure;
    }

    public static void setAreYouSure() {
        AreYouSureController.areYouSure = false;
    }
}
