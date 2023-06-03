package com.project.storekeeper.control;

import com.project.storekeeper.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.EventObject;

public class SwapScene {
    public static void swapScene(EventObject event, String path) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(Main.class.getResource(path));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlloader.load(), stage.getWidth() - 16, stage.getHeight() - 39);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
