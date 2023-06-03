package com.project.storekeeper.control;

import com.project.storekeeper.container.Delivery;
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

import java.math.BigDecimal;

public class DeliveryController {
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonCheck;
    @FXML
    private Button buttonCreate;
    @FXML
    private Label labelStatus;
    @FXML
    private TextField textContact;
    @FXML
    private TextField textName;
    @FXML
    private TextField textPrice;

    @FXML
    private TableView<Delivery> table;
    @FXML
    private TableColumn<Delivery, Integer> columnID;
    @FXML
    private TableColumn<Delivery, String> columnName;
    @FXML
    private TableColumn<Delivery, BigDecimal> columnPrice;
    @FXML
    private TableColumn<Delivery, Integer> columnContact;

    ObservableList<Delivery> deliveries = FXCollections.observableArrayList();
    BigDecimal price = null;
    Integer contact = null;

    @FXML
    private void initialize() {
        deliveries = Database.getDeliveries();
        columnID.setCellValueFactory(new PropertyValueFactory<>(Delivery.techDeliveryID));
        columnName.setCellValueFactory(new PropertyValueFactory<>(Delivery.techDeliveryName));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>(Delivery.techDeliveryPrice));
        columnContact.setCellValueFactory(new PropertyValueFactory<>(Delivery.techDeliveryContact));
        table.setItems(deliveries);
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
        if (textPrice.getText().equals("")) {
            labelStatus.setText("Цена не указана!");
            return;
        }
        try {
            price = new BigDecimal(textPrice.getText());
        } catch (Exception e) {
            textPrice.setText("");
            labelStatus.setText("Некорректная цена!");
            return;
        }
        if (price.compareTo(new BigDecimal("0.0")) < 0) {
            textPrice.setText("");
            labelStatus.setText("Цена меньше нуля!");
            return;
        }
        if (textContact.getText().equals("")) {
            labelStatus.setText("Системный номер контакта не указан!");
            return;
        }

        try {
            contact = Integer.parseInt(textContact.getText());
        }
        catch (NumberFormatException e) {
            textContact.setText("");
            labelStatus.setText("Некорректный системный номер контакта!");
            return;
        }
        if (contact < 1) {
            textContact.setText("");
            labelStatus.setText("Некорректный системный номер контакта!");
            return;
        }
        if (!Database.checkContact(contact)) {
            textContact.setText("");
            labelStatus.setText("Такого контакта не существует!");
            return;
        }
        labelStatus.setText("Данные одобрены!");
        buttonCreate.setDisable(false);
        buttonCheck.setDisable(true);
        textName.setDisable(true);
        textPrice.setDisable(true);
        textContact.setDisable(true);
    }

    @FXML
    void onCreate(ActionEvent event) {
        Database.setDelivery(textName.getText(),
                             price,
                             contact);
        labelStatus.setText("Данные сохранены!");
        buttonCreate.setDisable(true);
        buttonCheck.setDisable(false);

        textName.setDisable(false);
        textPrice.setDisable(false);
        textContact.setDisable(false);
        textName.setText("");
        textPrice.setText("");
        textContact.setText("");
        deliveries = Database.getDeliveries();
        table.setItems(deliveries);
    }

}
