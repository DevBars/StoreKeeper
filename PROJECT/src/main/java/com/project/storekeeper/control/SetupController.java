package com.project.storekeeper.control;

import com.project.storekeeper.Main;
import com.project.storekeeper.data.Database;
import com.project.storekeeper.data.Metadata;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.math.BigDecimal;

public class SetupController {

    @FXML
    private CheckBox checkInitData;

    @FXML
    private PasswordField textConfirm;

    @FXML
    private PasswordField textDBPassword;

    @FXML
    private TextField textImagesPath;

    @FXML
    private PasswordField textPassword;

    @FXML
    private TextField textURL;

    @FXML
    private TextField textUser;

    @FXML
    void onStart(ActionEvent event) {
        String password = textPassword.getText();
        String confirm = textConfirm.getText();
        if (!password.equals(confirm)) {
            textConfirm.setText("");
            textConfirm.setPromptText("Пароли не совпадают!");
            return;
        }

        boolean initData = checkInitData.isSelected();
        String imagesPath = null;
        if (initData) {
            imagesPath = textImagesPath.getText();
            imagesPath = imagesPath.replace("\"", "");
            imagesPath = imagesPath.replace("\\", File.separator + File.separator);
            File dir = new File(imagesPath);
            boolean dirExists = dir.exists() && dir.isDirectory();
            if (!dirExists) {
                textImagesPath.setText("");
                textImagesPath.setPromptText("Некорректный путь!");
                return;
            }
        }
        String DBUrl = textURL.getText();
        String DBUser = textUser.getText();
        String DBPassword = textDBPassword.getText();
        Main.setParams(BigDecimal.valueOf(0.0), password, DBUrl, DBUser, DBPassword);
        Metadata.runSetup();
        Database.initDatabase(initData, imagesPath);
        SwapScene.swapScene(event, "/com/project/storekeeper/fxml/entry.fxml");
    }
}
