package com.project.storekeeper.control;

import com.project.storekeeper.container.Maker;
import com.project.storekeeper.data.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MakersController {

    @FXML
    private Button buttonCheck;

    @FXML
    private Button buttonCreate;

    @FXML
    private TableColumn<Maker, Integer> columnID;

    @FXML
    private TableColumn<Maker, String> columnName;

    @FXML
    private Label labelStatus;

    @FXML
    private TableView<Maker> table;

    @FXML
    private TextField textName;

    ObservableList<Maker> makers = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        makers = Database.getMakers();
        columnID.setCellValueFactory(new PropertyValueFactory<>(Maker.techMakerID));
        columnName.setCellValueFactory(new PropertyValueFactory<>(Maker.techMakerName));
        table.setItems(makers);
    }

    @FXML
    void onBack(ActionEvent event) {
        SwapScene.swapScene(event, "/com/project/storekeeper/fxml/main.fxml");
    }

    @FXML
    void onCheck(ActionEvent event) {
        if (textName.getText().equals("")) {
            labelStatus.setText("Название не указано!");
            return;
        }
        labelStatus.setText("Данные одобрены!");
        buttonCreate.setDisable(false);
        buttonCheck.setDisable(true);
        textName.setDisable(true);
    }

    @FXML
    void onCreate(ActionEvent event) {
        Database.setMaker(textName.getText());
        labelStatus.setText("Данные сохранены!");
        buttonCreate.setDisable(true);
        buttonCheck.setDisable(false);
        textName.setDisable(false);
        textName.setText("");
        makers = Database.getMakers();
        table.setItems(makers);
    }

}
