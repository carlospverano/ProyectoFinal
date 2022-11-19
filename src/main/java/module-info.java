module com.example.iniciosesion {
    requires javafx.controls;
    requires javafx.fxml;

    // requires org.controlsfx.controls;
    // requires java.mail;

    opens com.example.iniciosesion to javafx.fxml;
    exports com.example.iniciosesion;

    opens model to javafx.fxml;
    exports model;
}