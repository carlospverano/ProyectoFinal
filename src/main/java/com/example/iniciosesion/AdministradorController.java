package com.example.iniciosesion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Empleado;
import model.Genero;

import java.util.List;

import static com.example.iniciosesion.AppController.INSTANCE;

public class AdministradorController {
    @FXML
    private TextField tfNumeroIdentificacion;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfContrasena;
    @FXML
    private ComboBox cbGenero;
    @FXML
    private Button botRegistrar;
    @FXML
    private Button botBloquear;
    @FXML
    private Button botActualizar;
    @FXML
    private TableColumn<Empleado, String> colNumeroIdentificacion;
    @FXML
    private  TableColumn <Empleado, String >colNombre;
    @FXML
    private TableColumn <Empleado, Genero > colGenero;
    @FXML
    private TableColumn <Empleado, String > colCorreo;
    @FXML
    private TableView<Empleado> tbEmpleados;
    @FXML
    private ObservableList<Empleado> empleados;
    @FXML
    private Label labelMensaje;


    Empleado empleadoSeleccionado;

    @FXML
    public void initialize() {

        llenarTabla(INSTANCE.getModel().getEmpleados());
        colNumeroIdentificacion.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));

        cbGenero.setItems(FXCollections.observableArrayList(Genero.values()));

        tbEmpleados.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> llenarCampos(newValue));


    }

    public void onRegistrarClick() throws Exception {
        String nombre = tfNombre.getText();
        String id = tfNumeroIdentificacion.getText();
        String correo = tfCorreo.getText();
        String contrasena = tfContrasena.getText();
        Genero genero = (Genero) cbGenero.getValue();

        Empleado empleadoRegistrado = INSTANCE.getModel().registrarEmpleado(new Empleado(nombre,correo,id,contrasena, genero));

        if(empleadoRegistrado instanceof Empleado){

            llenarTabla(INSTANCE.getModel().getEmpleados());
            limpiarCampos();
        }else{
            labelMensaje.setText("EL EMPLEADO YA ESTA REGISTRADO.");
            limpiarCampos();
        }

    }

    public void onRemoverClick() {

    }

    public void onBuscarClick() {

    }

    public void adicionarNota() {

    }

    private void llenarTabla(List<Empleado> empleados) {

        tbEmpleados.setItems(FXCollections.observableArrayList(empleados));
        tbEmpleados.refresh();
    }
    private void limpiarCampos() {
        tfNumeroIdentificacion.setText("");
        tfNombre.setText("");
        tfCorreo.setText("");
        tfContrasena.setText("");
        cbGenero.setValue(null);

    }
    private void llenarCampos(Empleado empleado) {
        empleadoSeleccionado = empleado;
        if (empleado != null) {
            tfNumeroIdentificacion.setText(empleado.getId());
            tfNombre.setText(empleado.getNombre());
            tfCorreo.setText(empleado.getEmail());
            cbGenero.setValue(empleado.getGenero());

        }
    }
}
