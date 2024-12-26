module com.example.observers {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.observers to javafx.fxml;
    exports com.example.observers;
}