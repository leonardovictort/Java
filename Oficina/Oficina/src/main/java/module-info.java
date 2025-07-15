module org.example.oficina {
    requires javafx.controls;
    requires javafx.fxml;
    opens org.example.oficina.controller to javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens org.example.oficina to javafx.fxml;
    exports org.example.oficina;
}