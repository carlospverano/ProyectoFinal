package com.example.iniciosesion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class EmpleadoController {
    @FXML
    private Label resultado;
    @FXML
    private TextField direccion;
    public void eventKey(KeyEvent keyEvent) {
    }

    public void eventAction(ActionEvent actionEvent) {
    }

    public void registrar() {
        resultado.setText("REGISTRADOOO");

        String direccionRegistrada = direccion.getText();
        resultado.setText(direccionRegistrada);

    }
}
