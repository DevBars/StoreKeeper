package com.project.storekeeper.control;

import com.project.storekeeper.Main;
import com.project.storekeeper.container.Good;
import com.project.storekeeper.data.Database;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class MainController {
    @FXML
    private ImageView imageGood;
    @FXML
    private Label labelBalance;
    @FXML
    private Label labelDescr;
    @FXML
    private Label labelID;
    @FXML
    private Label labelMaker;
    @FXML
    private Label labelName;
    @FXML
    private Label labelVolume;
    @FXML
    private TableView<Good> tableGoods;
    @FXML
    private TableColumn<Good, Integer> columnID;
    @FXML
    private TableColumn<Good, String> columnName;
    @FXML
    private TableColumn<Good, Integer> columnMaker;
    @FXML
    private TableColumn<Good, Integer> columnVolume;

    @FXML
    private void initialize() {
        labelBalance.setText("Баланс: " + Main.getMainBalance());
        ObservableList<Good> goods = Database.getGoods();

        columnID.setCellValueFactory(new PropertyValueFactory<>(Good.techGoodID));
        columnName.setCellValueFactory(new PropertyValueFactory<>(Good.techGoodName));
        columnMaker.setCellValueFactory(new PropertyValueFactory<>(Good.techGoodMaker));
        columnVolume.setCellValueFactory(new PropertyValueFactory<>(Good.techGoodVolume));
        tableGoods.setItems(goods);

        if (goods.size() > 0) {
            labelID.setText("Артикул: " + goods.get(0).getGoodID());
            labelName.setText(goods.get(0).getGoodName());
            labelMaker.setText((goods.get(0).getGoodMaker()));
            labelVolume.setText("В наличии: " + goods.get(0).getGoodVolume());
            labelDescr.setText(goods.get(0).getGoodDescription());
            imageGood.setImage(Database.getImage(goods.get(0).getGoodID()));
        }

        TableView.TableViewSelectionModel<Good> selectionModel = tableGoods.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((value, oldValue, newValue) -> {
            if(newValue != null) {
                labelID.setText("Артикул: " + newValue.getGoodID());
                labelName.setText(newValue.getGoodName());
                labelMaker.setText((newValue.getGoodMaker()));
                labelVolume.setText("В наличии: " + newValue.getGoodVolume());
                labelDescr.setText(newValue.getGoodDescription());
                imageGood.setImage(Database.getImage(newValue.getGoodID()));
            }
        });
    }
    @FXML
    void onBalance(ActionEvent event) {
        SwapScene.swapScene(event, "/com/project/storekeeper/fxml/balance.fxml");
    }
    @FXML
    void onBuy(ActionEvent event) {
        SwapScene.swapScene(event, "/com/project/storekeeper/fxml/buy.fxml");
    }
    @FXML
    void onContacts(ActionEvent event) {
        SwapScene.swapScene(event, "/com/project/storekeeper/fxml/contacts.fxml");
    }
    @FXML
    void onDelivery(ActionEvent event) {
        SwapScene.swapScene(event, "/com/project/storekeeper/fxml/delivery.fxml");
    }
    @FXML
    void onGet(ActionEvent event) {
        SwapScene.swapScene(event, "/com/project/storekeeper/fxml/get.fxml");
    }
    @FXML
    void onGood(ActionEvent event) {
        SwapScene.swapScene(event, "/com/project/storekeeper/fxml/good.fxml");
    }
    @FXML
    void onMaker(ActionEvent event) {
        SwapScene.swapScene(event, "/com/project/storekeeper/fxml/makers.fxml");
    }
    @FXML
    void onSell(ActionEvent event) {
        SwapScene.swapScene(event, "/com/project/storekeeper/fxml/sell.fxml");
    }
}
