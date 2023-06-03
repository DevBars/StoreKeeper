package com.project.storekeeper;

import com.project.storekeeper.data.Database;
import com.project.storekeeper.data.Metadata;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Main extends Application {
    private static String startPath = "/com/project/storekeeper/fxml/entry.fxml";
    private static BigDecimal mainBalance = null;
    private static String mainPassword = null;
    private static String DBUrl = null;
    private static String DBUser = null;
    private static String DBPassword = null;

    public static void main(String[] args) {
        launch();
    }

    public static void setParams(BigDecimal mainBalance,
                                 String mainPassword,
                                 String DBUrl,
                                 String DBUser,
                                 String DBPassword) {
        Main.mainBalance = mainBalance;
        Main.mainPassword = mainPassword;
        Main.DBUrl = DBUrl;
        Main.DBUser = DBUser;
        Main.DBPassword = DBPassword;
    }

    public static String getMainPassword() {
        return mainPassword;
    }

    public static String getMainBalance() {
        DecimalFormat formatBalance = new DecimalFormat("#0.00");
        return formatBalance.format(Main.mainBalance);
    }

    public static String getDBUrl() {
        return DBUrl;
    }

    public static String getDBUser() {
        return DBUser;
    }

    public static String getDBPassword() {
        return DBPassword;
    }

    public static boolean changeCurrentBalance(boolean isProfit, BigDecimal balance) {
        balance = balance.abs();
        balance = balance.setScale(2, RoundingMode.HALF_EVEN);
        if (!isProfit) {
            if (balance.compareTo(Main.mainBalance) > 0) return false;
            balance = balance.multiply(BigDecimal.valueOf(-1));
        }
        Main.mainBalance = Main.mainBalance.add(balance);
        Metadata.updateValues();
        return true;
    }

    @Override
    public void init() {
        System.out.println("Проверка установки...");
        boolean setupNeeded = Metadata.checkSetupNeeded();
        if (setupNeeded) startPath = "/com/project/storekeeper/fxml/setup.fxml";
        else {
            System.out.println("Чтение метаданных...");
            Metadata.getValues();
            System.out.println("Подключение базы данных...");
            Database.connect(DBUrl + "storekeeper", DBUser, DBPassword);
        }
        try {
            super.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(startPath));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("StoreKeeper");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.out.println("Отключение базы данных...");
        Database.disconnect();
        try {
            super.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}