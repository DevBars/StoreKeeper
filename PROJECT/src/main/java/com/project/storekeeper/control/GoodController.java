package com.project.storekeeper.control;

import com.project.storekeeper.container.Maker;
import com.project.storekeeper.data.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class GoodController {

    @FXML
    private Button buttonCheck;

    @FXML
    private Button buttonCreate;

    @FXML
    private CheckBox checkBox;

    @FXML
    private ImageView image;

    @FXML
    private Label labelDescr;

    @FXML
    private Label labelStatus;

    @FXML
    private TextField textDescr;

    @FXML
    private TextField textImage;

    @FXML
    private ChoiceBox<Integer> textMaker;

    @FXML
    private TextField textName;

    ObservableList<Maker> makers = FXCollections.observableArrayList();
    ObservableList<Integer> makerIDs = FXCollections.observableArrayList();
    String path = null;

    @FXML
    private void initialize() {
        textDescr.textProperty().addListener((observable, oldValue, newValue)->{
            labelDescr.setText(textDescr.getText());
        });
        makers = Database.getMakers();
        for (Maker maker : makers) {
            makerIDs.add(maker.getMakerID());
        }
        textMaker.setItems(makerIDs);
    }

    @FXML
    void onBack(ActionEvent event) {
        SwapScene.swapScene(event, "/com/project/storekeeper/fxml/main.fxml");
    }

    @FXML
    void onCheck(ActionEvent event) {
        if (textName.getText().equals("")) {
            labelStatus.setText("Наименование не указано!");
            return;
        }
        if (textMaker.getValue() == null) {
            labelStatus.setText("Производитель не указан!");
            return;
        }
        if (checkBox.isSelected()) {
            path = textImage.getText();
            path = path
                    .replace("\"", "")
                    .replace("\\", File.separator + File.separator);

            try {
                Image img = new Image(path);
                image.setImage(img);
            } catch (Exception e) {
                labelStatus.setText("Некорректный путь!");
                return;
            }
        }
        labelStatus.setText("Данные одобрены!");
        buttonCreate.setDisable(false);
        buttonCheck.setDisable(true);
        checkBox.setDisable(true);
        textName.setDisable(true);
        textMaker.setDisable(true);
        textDescr.setDisable(true);
        textImage.setDisable(true);
    }

    @FXML
    void onCreate(ActionEvent event) {
        Integer ID = Database.setNewGood(
                textName.getText(),
                textMaker.getValue(),
                textDescr.getText()
        );
        if (checkBox.isSelected()) {
            Database.setGoodImage(ID, path);
        }
        labelStatus.setText("Данные сохранены!");
        buttonCreate.setDisable(true);
        buttonCheck.setDisable(false);
        checkBox.setDisable(false);
        textName.setDisable(false);
        textMaker.setDisable(false);
        textDescr.setDisable(false);
        textImage.setDisable(false);
        textName.setText("");
        textMaker.setValue(null);
        image.setImage(null);
        textDescr.setText("");
        textImage.setText("");
    }

    @FXML
    void onSwitchImageMode(ActionEvent event) {
        textImage.setDisable(!checkBox.isSelected());
        image.setImage(null);
        textImage.setText("");
    }
}
