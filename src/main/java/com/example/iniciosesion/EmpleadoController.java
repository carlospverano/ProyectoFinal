package com.example.iniciosesion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;


public class EmpleadoController implements Initializable {

    FincaRaiz finca = new FincaRaiz();

   Empleado empleado1 = new Empleado("Aleja", "124567", "aleja@mail.com", "tgvijk2mqo5",true);

    @FXML
    private Label resultado3;
    @FXML
    private TextField direccion;
    @FXML
    private TextField valor;
    @FXML
    private TextField area;
    @FXML
    private ComboBox <Propietario> combopropietario;




    public void eventKey(KeyEvent keyEvent) {
    }
    public void eventAction(ActionEvent actionEvent) {
    }
    public void registrar() {

        String direccionRegistrada = direccion.getText();

        String valorPropiedad = (valor.getText());

        String areaPropiedad = area.getText();

        //String propietarioP= propietario.get;
        resultado3.setText(propietarioP);

        Propiedad propiedad= new Propiedad(direccionRegistrada,Double.parseDouble(valorPropiedad),Double.parseDouble(areaPropiedad),new Propietario(propietarioP,"1456"),Disponibilidad.DISPONIBLE);

        resultado3.setText(propiedad.toString());
        try {
            finca.registrarPropiedad(propiedad, new Empleado("Aleja", "124567", "aleja@mail.com", "tgvijk2mqo5",true));
            final String[] lista = {""};
            finca.getPropiedades().forEach(propiedad1 -> lista[0] = lista[0] +propiedad1.toString()+"\n");
            resultado3.setText(lista[0]);

        }
        catch (Exception e){
            e.getMessage();
        }
    }

    @FXML
    private TextField nombre;

    @FXML
    private TextField id;
    public void registrarPropietario () throws Exception {



        String nombrePropietario = nombre.getText();
        String identificacionPropietario= id.getText();

        Propietario propietario1 = new Propietario(nombrePropietario,identificacionPropietario);
        try {
            finca.registrarPropietario(propietario1, empleado1);
        }
        catch (Exception e){
            e.getMessage();
        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        combopropietario.getItems().addAll(finca.getPropietarios());

    }
}
