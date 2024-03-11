package Controllers.PopUpScreens;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ErrorController {

    @FXML
    private Label errorMessage;

    @FXML
    private Pane errorScreen;

    public void setErrorMessage(String message) {
        errorMessage.setText(message);
    }

}
