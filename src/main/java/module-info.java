module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example.demo1.DBConnect;
    opens com.example.demo1.DBConnect to javafx.fxml;
    exports com.example.demo1.Modules;
    opens com.example.demo1.Modules to javafx.fxml;
}