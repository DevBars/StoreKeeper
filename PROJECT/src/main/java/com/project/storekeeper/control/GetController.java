package com.project.storekeeper.control;

import com.project.storekeeper.Main;
import com.project.storekeeper.container.Movement;
import com.project.storekeeper.data.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class GetController {

    @FXML
    private Label labelBalance;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonGet;

    @FXML
    private Label labelID;

    @FXML
    private TableView<Movement> table;
    @FXML
    private TableColumn<Movement, Integer> columnContact;
    @FXML
    private TableColumn<Movement, LocalDate> columnDate;
    @FXML
    private TableColumn<Movement, Integer> columnGood;
    @FXML
    private TableColumn<Movement, Integer> columnID;
    @FXML
    private TableColumn<Movement, BigDecimal> columnPrice;
    @FXML
    private TableColumn<Movement, LocalTime> columnTime;
    @FXML
    private TableColumn<Movement, Integer> columnVolume;

    ObservableList<Movement> movements = FXCollections.observableArrayList();
    Movement currentMovement = null;

    @FXML
    void onBack(ActionEvent event) {
        SwapScene.swapScene(event, "/com/project/storekeeper/fxml/main.fxml");
    }

    @FXML
    private void initialize() {
        labelBalance.setText("Баланс: " + Main.getMainBalance());

        movements = Database.getMovementsBuy();
        columnID.setCellValueFactory(new PropertyValueFactory<>(Movement.techMovementID));
        columnGood.setCellValueFactory(new PropertyValueFactory<>(Movement.techMovementGood));
        columnVolume.setCellValueFactory(new PropertyValueFactory<>(Movement.techMovementVolume));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>(Movement.techMovementPrice));
        columnContact.setCellValueFactory(new PropertyValueFactory<>(Movement.techMovementContact));
        columnDate.setCellValueFactory(new PropertyValueFactory<>(Movement.techMovementStartDate));
        columnTime.setCellValueFactory(new PropertyValueFactory<>(Movement.techMovementStartTime));
        table.setItems(movements);

        if (movements.size() > 0) {
            labelID.setText(movements.get(0).getMovementID().toString());
        }

        TableView.TableViewSelectionModel<Movement> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((value, oldValue, newValue) -> {
            if(newValue != null) {
                labelID.setText(newValue.getMovementID().toString());
            }
        });
    }

    @FXML
    void onCancel(ActionEvent event) {
        getCurrentData();
        if (currentMovement == null) return;
        BigDecimal cost =
                currentMovement.getMovementPrice().multiply(BigDecimal.valueOf(currentMovement.getMovementVolume()));
        if (Main.changeCurrentBalance(true, cost)) {
            Database.closeMovementBuy(currentMovement.getMovementID());
        }
        updateCurrentData();
    }

    @FXML
    void onGet(ActionEvent event) {
        getCurrentData();
        if (currentMovement == null) return;
        Database.closeMovementBuy(currentMovement.getMovementID());
        Database.addGoodsVolume(currentMovement.getMovementGood(), currentMovement.getMovementVolume());
        updateCurrentData();
    }

    private void getCurrentData() {
        for (Movement movement : movements) {
            if (movement.getMovementID().toString().equals(labelID.getText())) {
                currentMovement = new Movement();
                currentMovement.setMovementID(movement.getMovementID());
                currentMovement.setMovementVolume(movement.getMovementVolume());
                currentMovement.setMovementPrice(movement.getMovementPrice());
                currentMovement.setMovementGood(movement.getMovementGood());
                break;
            }
        }
    }

    private void updateCurrentData() {
        labelBalance.setText("Баланс: " + Main.getMainBalance());
        movements = Database.getMovementsBuy();
        table.setItems(movements);
        if (movements.size() > 0) {
            labelID.setText(movements.get(0).getMovementID().toString());
        } else labelID.setText("0");
    }
}
