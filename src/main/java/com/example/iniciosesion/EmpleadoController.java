package com.example.iniciosesion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import static com.example.iniciosesion.AppController.INSTANCE;
import model.*;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class EmpleadoController implements Initializable {
    Propiedad propiedadSeleccionado;

    private ObservableList<Propiedad> propiedades = FXCollections.observableArrayList();
    @FXML
    private TextField nombre;

    @FXML
    private TextField id;

    FincaRaiz finca = INSTANCE.getModel();

   Empleado empleado1 = new Empleado("Aleja", "124567", "aleja@mail.com", "tgvijk2mqo5",true);


    @FXML
    private TextField direccion;
    @FXML
    private TextField valor;
    @FXML
    private TextField area;
    @FXML
    private ComboBox <Propietario> combopropietario;

    @FXML
    private TableView<Propiedad> tablaPropiedades;

    @FXML
    private TableColumn<Propiedad,  Double> columnaArea;

    @FXML
    private TableColumn<Propiedad, String> columnaDireccion;

    @FXML
    private TableColumn<Disponibilidad, Enum> columnaDisponibilidad;

    @FXML
    private TableColumn<Propiedad, Propietario> columnaPropietario;

    @FXML
    private TableColumn<Propiedad, Double> columnaValor;



    @FXML
    private void comboboxEvents(ActionEvent e){}

    public void eventKey(KeyEvent keyEvent) {
    }
    public void eventAction(ActionEvent actionEvent) {
    }
    public void registrar() {


        String direccionRegistrada = direccion.getText();

        String valorPropiedad = (valor.getText());

        String areaPropiedad = area.getText();

        Propietario propietarioP= combopropietario.getValue();

        Propiedad propiedad= new Propiedad(direccionRegistrada,Double.parseDouble(valorPropiedad),Double.parseDouble(areaPropiedad),propietarioP,Disponibilidad.DISPONIBLE);
        propiedades.add(propiedad);
        tablaPropiedades.setItems(propiedades);
        tablaPropiedades.refresh(); //Actualiza la tabla

        try {
            finca.registrarPropiedad(propiedad);

        }
        catch (Exception e){
            e.getMessage();
        }
    }

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
        this.columnaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        this.columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        this.columnaArea.setCellValueFactory(new PropertyValueFactory<>("area"));
        this.columnaPropietario.setCellValueFactory(new PropertyValueFactory<>("propietario"));
        this.columnaDisponibilidad.setCellValueFactory(new PropertyValueFactory<>("disponibilidad"));

        }

    private void llenarTabla(List<Propiedad> propiedadList) {
        tablaPropiedades.setItems(FXCollections.observableArrayList(propiedadList));
        tablaPropiedades.refresh();
    }
    }
