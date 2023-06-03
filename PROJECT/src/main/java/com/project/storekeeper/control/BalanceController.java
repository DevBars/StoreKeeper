package com.project.storekeeper.control;

import com.project.storekeeper.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class BalanceController {

    @FXML
    private Label labelBalance;

    @FXML
    private TextField textSum;

    @FXML
    private void initialize() {
        labelBalance.setText("Баланс: " + Main.getMainBalance());
    }

    @FXML
    void onBack(ActionEvent event) {
        SwapScene.swapScene(event, "/com/project/storekeeper/fxml/main.fxml");
    }

    @FXML
    void onReceive() {
        onBalance(true);
    }

    @FXML
    void onSend() {
        onBalance(false);
    }

    private void onBalance(boolean isProfit) {
        BigDecimal newBalance;
        try {
            newBalance = new BigDecimal(textSum.getText());
        } catch (Exception e) {
            textSum.setText("");
            textSum.setPromptText("Некорректное значение!");
            return;
        }
        if (newBalance.compareTo(new BigDecimal("0.0")) <= 0) {
            textSum.setText("");
            textSum.setPromptText("Значение меньше или равно нуля!");
            return;
        }
        if (!Main.changeCurrentBalance(isProfit, newBalance)) {
            textSum.setText("");
            textSum.setPromptText("Недостаточно средств!");
            return;
        }
        labelBalance.setText("Баланс: " + Main.getMainBalance());
        textSum.setText("");
        textSum.setPromptText("Введите сумму");
    }
}
