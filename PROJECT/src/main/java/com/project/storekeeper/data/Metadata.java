package com.project.storekeeper.data;

import com.project.storekeeper.Main;
import org.json.*;
import java.io.*;
import java.math.BigDecimal;

public class Metadata {

    public static void runSetup() {
        String path = System.getProperty("user.home") +
                File.separator + "StoreKeeper" + File.separator + "metadata.json";
        File file = new File(path);
        try {
            if (file.createNewFile()) System.out.println("Файл создан!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateValues();
    }

    public static void updateValues() {
        String path = System.getProperty("user.home") +
                File.separator + "StoreKeeper" + File.separator + "metadata.json";

        JSONObject json = new JSONObject();
        try {
            json.put("DBUrl", Main.getDBUrl());
            json.put("DBUser", Main.getDBUser());
            json.put("DBPassword", Main.getDBPassword());
            json.put("mainBalance", Main.getMainBalance());
            json.put("mainPassword", Main.getMainPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write(json.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getValues() {
        String path = System.getProperty("user.home") +
                File.separator + "StoreKeeper" + File.separator + "metadata.json";
        String jsonString = null;
        try (FileReader reader = new FileReader(path)) {
            BufferedReader bufReader = new BufferedReader(reader);
            jsonString = bufReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonString != null) {
            JSONObject json = new JSONObject(jsonString);
            Main.setParams(new BigDecimal(((String) json.get("mainBalance")).replace(",", ".")),
                    (String) json.get("mainPassword"),
                    (String) json.get("DBUrl"),
                    (String) json.get("DBUser"),
                    (String) json.get("DBPassword"));
        }
    }

    public static boolean checkSetupNeeded() {
        boolean setupNeeded = false;
        String path = System.getProperty("user.home") + File.separator + "StoreKeeper";
        File dir = new File(path);
        boolean dirExists = dir.exists() && dir.isDirectory();
        if (!dirExists) {
            if (dir.mkdirs()) System.out.println("Директория создана!");
        }
        path = path + File.separator + "metadata.json";
        File file = new File(path);
        boolean fileExists = file.exists() && file.isFile();
        if (!fileExists) {
            setupNeeded = true;
        }
        return setupNeeded;
    }
}
