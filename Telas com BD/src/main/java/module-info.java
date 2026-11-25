module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.j;

    opens org.example to javafx.fxml;
    exports org.example;
    exports org.example.entidades;
    opens org.example.entidades to javafx.fxml;
    exports org.example.controllers;
    opens org.example.controllers to javafx.fxml;
}
