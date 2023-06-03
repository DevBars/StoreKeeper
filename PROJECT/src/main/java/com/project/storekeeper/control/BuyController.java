package com.project.storekeeper.control;

import com.project.storekeeper.Main;
import com.project.storekeeper.container.Contact;
import com.project.storekeeper.container.Delivery;
import com.project.storekeeper.container.Good;
import com.project.storekeeper.data.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

public class BuyController {

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonBuy;

    @FXML
    private Button buttonCheck;

    @FXML
    private Label labelBalance;

    @FXML
    private Label labelCost;

    @FXML
    private Label labelStatus;

    @FXML
    private ChoiceBox<Integer> textContact;

    @FXML
    private ChoiceBox<Integer> textDelivery;

    @FXML
    private ChoiceBox<Integer> textGood;

    @FXML
    private TextField textPrice;

    @FXML
    private TextField textVolume;
    ObservableList<Good> goods = FXCollections.observableArrayList();
    ObservableList<Integer> goodIDs = FXCollections.observableArrayList();
    ObservableList<Contact> contacts = FXCollections.observableArrayList();
    ObservableList<Integer> contactIDs = FXCollections.observableArrayList();
    ObservableList<Delivery> deliveries = FXCollections.observableArrayList();
    ObservableList<Integer> deliveryIDs = FXCollections.observableArrayList();
    BigDecimal price = null;
    Integer volume = null;
    BigDecimal cost = new BigDecimal(0);

    @FXML
    private void initialize() {
        labelBalance.setText("Баланс: " + Main.getMainBalance());

        goods = Database.getGoods();
        contacts = Database.getContacts();
        deliveries = Database.getDeliveries();
        for (Good good : goods) {
            goodIDs.add(good.getGoodID());
        }
        for (Contact contact : contacts) {
            contactIDs.add(contact.getContactID());
        }
        for (Delivery delivery : deliveries) {
            deliveryIDs.add(delivery.getDeliveryID());
        }
        textGood.setItems(goodIDs);
        textContact.setItems(contactIDs);
        textDelivery.setItems(deliveryIDs);
    }

    @FXML
    void onBack(ActionEvent event) {
        SwapScene.swapScene(event, "/com/project/storekeeper/fxml/main.fxml");
    }

    @FXML
    void onCheck(ActionEvent event) {
        if (textGood.getValue() == null) {
            labelStatus.setText("Артикул не указан!");
            return;
        }
        if (textVolume.getText().equals("")) {
            labelStatus.setText("Количество не указано!");
            return;
        }
        try {
            volume = Integer.parseInt(textVolume.getText());
        } catch (Exception e) {
            textVolume.setText("");
            labelStatus.setText("Некорректное количество!");
            return;
        }
        if (volume <= 0) {
            textVolume.setText("");
            labelStatus.setText("Количество меньше или равно нуля!");
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
        if (textContact.getValue() == null) {
            labelStatus.setText("Продавец не указан!");
            return;
        }
        if (textDelivery.getValue() == null) {
            labelStatus.setText("Доставка не указана!");
            return;
        }
        BigDecimal deliveryPrice = Database.getDeliveryPrice(textDelivery.getValue());
        DecimalFormat formatBalance = new DecimalFormat("#0.00");
        cost = price.multiply(BigDecimal.valueOf(volume));
        cost = cost.add(deliveryPrice);
        labelCost.setText(formatBalance.format(cost));
        if (cost.compareTo(new BigDecimal(Main.getMainBalance().replace(",", "."))) > 0) {
            labelStatus.setText("Недостаточно баланса!");
            return;
        }
        labelStatus.setText("Данные одобрены!");
        buttonBuy.setDisable(false);
        buttonCheck.setDisable(true);
        textGood.setDisable(true);
        textContact.setDisable(true);
        textDelivery.setDisable(true);
        textVolume.setDisable(true);
        textPrice.setDisable(true);
    }

    @FXML
    void onBuy(ActionEvent event) {
        if (Main.changeCurrentBalance(false, cost)) {
            Database.setBuyMovement(
                    textGood.getValue(),
                    volume,
                    price,
                    textContact.getValue(),
                    textDelivery.getValue());
        }
        labelBalance.setText("Баланс: " + Main.getMainBalance());
        labelStatus.setText("Данные сохранены!");
        labelCost.setText("0,00");
        buttonBuy.setDisable(true);
        buttonCheck.setDisable(false);
        textGood.setDisable(false);
        textContact.setDisable(false);
        textDelivery.setDisable(false);
        textPrice.setDisable(false);
        textVolume.setDisable(false);
        textGood.setValue(null);
        textContact.setValue(null);
        textDelivery.setValue(null);
        textPrice.setText("");
        textVolume.setText("");
    }
}
