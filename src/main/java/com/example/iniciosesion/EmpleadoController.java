package com.example.iniciosesion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.*;

public class EmpleadoController {

    FincaRaiz finca = new FincaRaiz();

    @FXML
    private Label resultado;

    @FXML
    private Label resultado1;

    @FXML
    private Label resultado2;

    @FXML
    private Label resultado3;
    @FXML
    private TextField direccion;
    @FXML
    private TextField valor;
    @FXML
    private TextField area;
    @FXML
    private TextField propietario;



    public void eventKey(KeyEvent keyEvent) {
    }
    public void eventAction(ActionEvent actionEvent) {
    }

    public void registrar() {

        resultado.setText("REGISTRADOOO");

        String direccionRegistrada = direccion.getText();
        resultado.setText(direccionRegistrada);

        String valorPropiedad = (valor.getText());
        resultado1.setText(valorPropiedad);

        String areaPropiedad = area.getText();
        resultado2.setText(areaPropiedad);

        String propietarioP= propietario.getText();
        resultado3.setText(propietarioP);

        Propiedad propiedad= new Propiedad(direccionRegistrada,Double.parseDouble(valorPropiedad),Double.parseDouble(areaPropiedad),new Propietario(propietarioP,"1456"),Disponibilidad.DISPONIBLE);

        resultado3.setText(propiedad.toString());
        try {
            finca.registrarPropiedad(propiedad, new Usuario("Aleja", "124567", "aleja@mail.com", "tgvijk2mqo5"));
            final String[] lista = {""};
            finca.getPropiedades().forEach(propiedad1 -> lista[0] = lista[0] +propiedad1.toString()+"\n");

        }
        catch (Exception e){
            e.getMessage();
        }
    }
}
