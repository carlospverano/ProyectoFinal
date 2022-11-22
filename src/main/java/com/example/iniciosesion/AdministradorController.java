package com.example.iniciosesion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Empleado;
import model.Genero;

import java.io.IOException;
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
    private Label labelMensaje;
    @FXML
    private Button btnCerrarSesion;
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

    public void onCerrarSesionClick( ) throws IOException {
        Parent parent = FXMLLoader.load(MainApp.class.getResource("login.fxml"));
        Scene scene = new Scene(parent, 600, 600);
        Stage stage = new Stage();
        stage.setTitle("ADMINISTRADOR");
        stage.setScene(scene);
        stage.initOwner(btnCerrarSesion.getScene().getWindow());
        btnCerrarSesion.getScene().getWindow().hide();
        stage.show();
    }
}
