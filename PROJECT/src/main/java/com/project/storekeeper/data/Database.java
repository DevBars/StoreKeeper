package com.project.storekeeper.data;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import com.project.storekeeper.Main;
import com.project.storekeeper.container.*;
import com.project.storekeeper.texts.SQLTexts;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;

public class Database {
    private static Connection connection = null;
    private static Statement statement = null;

    public static void connect(String url, String user, String password) {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement == null) {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void disconnect() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void initDatabase(boolean initData, String imagesPath) {
        String DBDriver = "org.postgresql.Driver";
        String DBUrl = Main.getDBUrl() + "postgres";
        String DBUser = Main.getDBUser();
        String DBPassword = Main.getDBPassword();
        try {
            Class.forName(DBDriver);
            Connection connection = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
            Statement statement = connection.createStatement();
            statement.execute("create database storekeeper;");
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        DBUrl = Main.getDBUrl() + "storekeeper";
        try {
            Connection connection = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
            Statement statement = connection.createStatement();
            statement.execute(SQLTexts.createTables);
            if (initData)
                statement.execute(SQLTexts.initData.replaceAll("%s", imagesPath));
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Database.connect(Main.getDBUrl() + "storekeeper", Main.getDBUser(), Main.getDBPassword());
    }

    public static ObservableList<Good> getGoods() {
        ObservableList<Good> goods = FXCollections.observableArrayList();
        String query = """
                SELECT G.id, G.name, M.name, G.volume, G.description\s
                FROM goods AS G\s
                LEFT JOIN makers AS M\s
                ON G.maker = M.id
                ORDER BY G.id;
                """;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Good good = new Good();
                good.setGoodID(rs.getInt(1));
                good.setGoodName(rs.getString(2));
                good.setGoodMaker(rs.getString(3));
                good.setGoodVolume(rs.getInt(4));
                good.setGoodDescription(rs.getString(5));
                goods.add(good);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    public static Image getImage(Integer goodID) {
        Image image = null;
        String query = "select image from goods where id = ?;";
        try {
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, goodID);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                InputStream imageFile = rs.getBinaryStream(1);
                if (imageFile != null) image = new Image(imageFile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static ObservableList<Contact> getContacts() {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        String query = """
                SELECT C.id, C.surname, C.name, C.patronymic,\s
                A.index, A.country, A.region, A.town, A.street, A.house, A.entrance, A.flat,\s
                C.phone, C.email, C.telegram\s
                FROM contacts AS C\s
                LEFT JOIN addresses AS A\s
                ON C.address = A.id\s
                ORDER BY C.id;
                """;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setContactID(rs.getInt(1));
                contact.setContactSurname(rs.getString(2));
                contact.setContactName(rs.getString(3));
                contact.setContactPatronymic(rs.getString(4));
                String address =
                        rs.getString(5) + ", " +
                        rs.getString(6) + ", " +
                        rs.getString(7) + ", " +
                        rs.getString(8) + ", " +
                        rs.getString(9) + ", " +
                        rs.getString(10) + ", " +
                        rs.getString(11) + ", " +
                        rs.getString(12);
                contact.setContactAddress(address);
                contact.setContactPhone(rs.getString(13));
                contact.setContactEmail(rs.getString(14));
                contact.setContactTelegram(rs.getString(15));
                contact.setContactFIO(
                        contact.getContactSurname() + " " +
                        contact.getContactName() + " " +
                        contact.getContactPatronymic());
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public static ObservableList<Delivery> getDeliveries() {
        ObservableList<Delivery> deliveries = FXCollections.observableArrayList();
        String query = """
                SELECT id, name, price, contact\s
                FROM deliveries\s
                ORDER BY id;
                """;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Delivery delivery = new Delivery();
                delivery.setDeliveryID(rs.getInt(1));
                delivery.setDeliveryName(rs.getString(2));
                String price = rs.getString(3)
                        .replace(",", ".")
                        .replace("?", "")
                        .replace(" ", "")
                        .replace("\u00a0", "");
                delivery.setDeliveryPrice(new BigDecimal(price));
                delivery.setDeliveryContact(rs.getInt(4));
                deliveries.add(delivery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    public static ObservableList<Maker> getMakers() {
        ObservableList<Maker> makers = FXCollections.observableArrayList();
        String query = """
                SELECT id, name\s
                FROM makers\s
                ORDER BY id;
                """;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Maker maker = new Maker();
                maker.setMakerID(rs.getInt(1));
                maker.setMakerName(rs.getString(2));
                makers.add(maker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return makers;
    }

    public static Integer setAddress(String index,
                                     String country,
                                     String region,
                                     String town,
                                     String street,
                                     String house,
                                     String entrance,
                                     String flat) {
        int newID = 0;
        String query = """
                insert into addresses (index, country, region, town, street, house, entrance, flat) values\s
                ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s') RETURNING id;
                """.formatted(index, country, region, town, street, house, entrance, flat);
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                newID = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newID;
    }

    public static void setContact(String surname,
                                  String name,
                                  String patronymic,
                                  Integer address,
                                  String phone,
                                  String email,
                                  String telegram) {
        String query = """
                insert into contacts (surname, name, patronymic, address, phone, email, telegram) values\s
                ('%s', '%s', '%s', %s, '%s', '%s', '%s');
                """.formatted(surname, name, patronymic, address, phone, email, telegram);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setMaker(String name) {
        String query = """
                insert into makers (name) values ('%s');
                """.formatted(name);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkContact(Integer id) {
        int ID = 0;
        String query = """
                select count(*) from contacts where id = %s;
                """.formatted(id);
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                ID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ID == 1;
    }

    public static void setDelivery(String name,
                                   BigDecimal price,
                                   Integer contact) {
        String query = """
                insert into deliveries (name, price, contact) values ('%s', %s, %s);
                """.formatted(name, price, contact);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer setNewGood(String name, Integer maker, String description) {
        int newID = 0;
        String query = """
                insert into goods (name, maker, description) values ('%s', %s, '%s') returning id;
                """.formatted(name, maker, description);
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                newID = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newID;
    }

    public static void setGoodImage(Integer ID, String path) {
        String query = """
                update goods set image = pg_read_binary_file('%s') where id = %s;
                """.formatted(path, ID);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static BigDecimal getDeliveryPrice(Integer id) {
        BigDecimal price = new BigDecimal(0);
        String query = """
                select price from deliveries where id = %s;
                """.formatted(id);
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String priceStr = rs.getString(1)
                        .replace(",", ".")
                        .replace("?", "")
                        .replace(" ", "")
                        .replace("\u00a0", "");
                price = new BigDecimal(priceStr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

    public static void setBuyMovement(Integer good,
                                      Integer volume,
                                      BigDecimal price,
                                      Integer contact,
                                      Integer delivery) {
        LocalDate startDate = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        String query = """
                insert into movements\s
                (status, good, volume, price, contact, delivery, startdate, starttime)\s
                values ('buy', %s, %s, %s, %s, %s, '%s', '%s');
                """.formatted(good, volume, price, contact, delivery, startDate, startTime);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Movement> getMovementsBuy() {
        ObservableList<Movement> movements = FXCollections.observableArrayList();
        String query = """
                SELECT id, good, volume, price, contact, startdate, starttime\s
                FROM movements\s
                WHERE status = 'buy' AND active = 'true'\s
                ORDER BY id;
                """;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Movement movement = new Movement();
                movement.setMovementID(rs.getInt(1));
                movement.setMovementGood(rs.getInt(2));
                movement.setMovementVolume(rs.getInt(3));
                String priceStr = rs.getString(4)
                        .replace(",", ".")
                        .replace("?", "")
                        .replace(" ", "")
                        .replace("\u00a0", "");
                movement.setMovementPrice(new BigDecimal(priceStr));
                movement.setMovementContact(rs.getInt(5));
                movement.setMovementStartDate(LocalDate.parse(rs.getString(6)));
                movement.setMovementStartTime(LocalTime.parse(rs.getString(7)));
                movements.add(movement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movements;
    }

    public static void closeMovementBuy(Integer ID) {
        LocalDate endDate = LocalDate.now();
        LocalTime endTime = LocalTime.now();
        String query = """
                update movements\s
                set active = 'false', enddate = '%s', endtime = '%s'\s
                where id = %s;
                """.formatted(endDate, endTime, ID);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer getGoodsVolume(Integer ID) {
        int currentVolume = 0;
        String query = """
                select volume from goods where id = %s;
                """.formatted(ID);
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                currentVolume = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentVolume;
    }

    public static void addGoodsVolume(Integer ID, Integer volume) {
        int currentVolume = getGoodsVolume(ID);
        currentVolume = currentVolume + volume;
        String query = """
                update goods\s
                set volume = %s\s
                where id = %s;
                """.formatted(currentVolume, ID);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
