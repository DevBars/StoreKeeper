package com.project.storekeeper.control;

import com.project.storekeeper.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class EntryController {
    @FXML
    private PasswordField fieldPassword;

    @FXML
    void onEntry(ActionEvent event) {
        String realPassword = Main.getMainPassword();
        String userPassword = fieldPassword.getText();
        if (userPassword.equals(realPassword)) {
            SwapScene.swapScene(event, "/com/project/storekeeper/fxml/main.fxml");
        } else {
            fieldPassword.setText("");
            fieldPassword.setPromptText("Неверный пароль!");
        }
    }
}
