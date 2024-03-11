package Controllers.AdminPages;

import java.util.List;

import Models.Rent;
import Utils.Utils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;

public class ManageRents {
    private Rent selectedRent;

    public void initialize() {

        statusBox.getItems().addAll("PENDING", "OPEN", "EXPIRED");
        statusBox.setValue("OPEN");

        statusBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                initializeList();
            }
        });

        initializeList();
    }

    public void initializeList() {
        Rent.checkIfExpired();
        selectedRent = null;
        List<Rent> rentList = new ArrayList<>();
        for (Rent r : Rent.getAllRents()) {
            if (r.getStatus().equals(statusBox.getValue())) {
                rentList.add(r);
            }
        }

        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        rentsTable.getItems().setAll(rentList);
    }

    @FXML
    private TableColumn<Rent, String> isbn;

    @FXML
    private AnchorPane manageRents;

    @FXML
    private BorderPane manageRentsBorder;

    @FXML
    private TableView<Rent> rentsTable;

    @FXML
    private TableColumn<Rent, String> status;

    @FXML
    private ChoiceBox<String> statusBox;

    @FXML
    private TableColumn<Rent, String> username;

    @FXML
    void RentSelected(MouseEvent event) {
        selectedRent = rentsTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    void btnRentBookClicked(ActionEvent event) {
        if (selectedRent != null) {
            if (selectedRent.approveRent()) {
                // check
                // selectedRent.setExpiredDate();
                // System.out.println(selectedRent.getDateAndTime());
                initializeList();
                Utils.showSuccessfullPopUp();
            }

        } else {
            Utils.showErrorPopUp("Select a Rent.");
        }
    }

    @FXML
    void btnReturnBookClicked(ActionEvent event) {
        if (selectedRent != null) {
            Rent.deleteRent(selectedRent);
            initializeList();
            Utils.showSuccessfullPopUp();

        } else {
            Utils.showErrorPopUp("Select a Rent.");
        }
    }

}
