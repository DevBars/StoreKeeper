module com.project.storekeeper {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires org.postgresql.jdbc;
    requires java.sql;
    exports com.project.storekeeper;
    opens com.project.storekeeper to javafx.fxml;
    exports com.project.storekeeper.container;
    opens com.project.storekeeper.container to javafx.fxml;
    exports com.project.storekeeper.control;
    opens com.project.storekeeper.control to javafx.fxml;
    exports com.project.storekeeper.data;
    opens com.project.storekeeper.data to javafx.fxml;
}