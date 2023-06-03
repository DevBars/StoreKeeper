package com.project.storekeeper.control;

import com.project.storekeeper.container.Contact;
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

public class ContactsController {
    @FXML
    private Button buttonCheck;
    @FXML
    private Button buttonCreate;
    @FXML
    private Label labelStatus;
    @FXML
    private TextField textCountry;
    @FXML
    private TextField textEmail;
    @FXML
    private TextField textEntrance;
    @FXML
    private TextField textFlat;
    @FXML
    private TextField textHouse;
    @FXML
    private TextField textIndex;
    @FXML
    private TextField textName;
    @FXML
    private TextField textPatronymic;
    @FXML
    private TextField textPhone;
    @FXML
    private TextField textRegion;
    @FXML
    private TextField textStreet;
    @FXML
    private TextField textSurname;
    @FXML
    private TextField textTelegram;
    @FXML
    private TextField textTown;

    @FXML
    private TableView<Contact> table;
    @FXML
    private TableColumn<Contact, Integer> columnID;
    @FXML
    private TableColumn<Contact, String> columnFIO;
    @FXML
    private TableColumn<Contact, String> columnAddress;
    @FXML
    private TableColumn<Contact, String> columnPhone;
    @FXML
    private TableColumn<Contact, String> columnEmail;
    @FXML
    private TableColumn<Contact, String> columnTelegram;

    ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        contacts = Database.getContacts();
        columnID.setCellValueFactory(new PropertyValueFactory<>(Contact.techContactID));
        columnFIO.setCellValueFactory(new PropertyValueFactory<>(Contact.techContactFIO));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>(Contact.techContactAddress));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>(Contact.techContactPhone));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>(Contact.techContactEmail));
        columnTelegram.setCellValueFactory(new PropertyValueFactory<>(Contact.techContactTelegram));
        table.setItems(contacts);
    }

    @FXML
    void onBack(ActionEvent event) {
        SwapScene.swapScene(event, "/com/project/storekeeper/fxml/main.fxml");
    }

    @FXML
    void onCheck(ActionEvent event) {
        if (textName.getText().equals("")) {
            labelStatus.setText("Имя не указано!");
            return;
        }
        if (textIndex.getText().equals("")) {
            labelStatus.setText("Почтовый индекс не указан!");
            return;
        }
        if (textCountry.getText().equals("")) {
            labelStatus.setText("Страна не указана!");
            return;
        }
        labelStatus.setText("Данные одобрены!");
        buttonCreate.setDisable(false);
        buttonCheck.setDisable(true);
        textCountry.setDisable(true);
        textEmail.setDisable(true);
        textEntrance.setDisable(true);
        textFlat.setDisable(true);
        textHouse.setDisable(true);
        textIndex.setDisable(true);
        textSurname.setDisable(true);
        textName.setDisable(true);
        textPatronymic.setDisable(true);
        textPhone.setDisable(true);
        textRegion.setDisable(true);
        textStreet.setDisable(true);
        textTelegram.setDisable(true);
        textTown.setDisable(true);
    }

    @FXML
    void onCreate(ActionEvent event) {
        Integer newAddressID = Database.setAddress(
                textIndex.getText(),
                textCountry.getText(),
                textRegion.getText(),
                textTown.getText(),
                textStreet.getText(),
                textHouse.getText(),
                textEntrance.getText(),
                textFlat.getText()
        );
        Database.setContact(
                textSurname.getText(),
                textName.getText(),
                textPatronymic.getText(),
                newAddressID,
                textPhone.getText(),
                textEmail.getText(),
                textTelegram.getText()
        );
        labelStatus.setText("Данные сохранены!");
        buttonCreate.setDisable(true);
        buttonCheck.setDisable(false);
        textCountry.setDisable(false);
        textEmail.setDisable(false);
        textEntrance.setDisable(false);
        textFlat.setDisable(false);
        textHouse.setDisable(false);
        textIndex.setDisable(false);
        textSurname.setDisable(false);
        textName.setDisable(false);
        textPatronymic.setDisable(false);
        textPhone.setDisable(false);
        textRegion.setDisable(false);
        textStreet.setDisable(false);
        textTelegram.setDisable(false);
        textTown.setDisable(false);

        textCountry.setText("");
        textEmail.setText("");
        textEntrance.setText("");
        textFlat.setText("");
        textHouse.setText("");
        textIndex.setText("");
        textSurname.setText("");
        textName.setText("");
        textPatronymic.setText("");
        textPhone.setText("");
        textRegion.setText("");
        textStreet.setText("");
        textTelegram.setText("");
        textTown.setText("");

        contacts = Database.getContacts();
        table.setItems(contacts);
    }
}
