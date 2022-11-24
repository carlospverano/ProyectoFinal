package com.example.iniciosesion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import static com.example.iniciosesion.AppController.INSTANCE;

import javafx.stage.Stage;
import model.*;


import java.io.IOException;
import java.lang.ref.Cleaner;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class EmpleadoController implements Initializable {
    @FXML
    private ObservableList<Propiedad> propiedades = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Propietario> propietarios = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    @FXML
    private TextField nombrePropietario;
    @FXML
    private TextField idPropietario;
    @FXML
    private TextField nombreCliente;
    @FXML
    private TextField idCliente;
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
    private TableColumn<Propiedad, String> colTipo;
    @FXML
    private TableView<Propietario> tablaPropietarios;
    @FXML
    private TableColumn<Propietario, String> columnaName;
    @FXML
    private TableColumn<Propietario, String> columnaIdPropietario;
    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente,  String> columnaNameCliente;
    @FXML
    private TableColumn<Cliente, String> columnaIdentificacionCliente;
    @FXML
    private ComboBox<String> cbTipoPropiedad;
    @FXML
    private ComboBox<Cliente> cbClientes;
    @FXML
    private Button btnCerrarSesion;
    FincaRaiz finca = INSTANCE.getModel();
    Propiedad propiedadSeleccionada;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        llenarTabla(INSTANCE.getModel().getPropiedades());
        llenarTablaCliente(INSTANCE.getModel().getClientes());
        llenarTablaPropietario(INSTANCE.getModel().getPropietarios());
        combopropietario.getItems().addAll(finca.getPropietarios());
        cbTipoPropiedad.getItems().addAll(finca.getTipoPropiedaes());
        cbClientes.getItems().addAll(finca.getClientes());
        this.columnaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        this.columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        this.columnaArea.setCellValueFactory(new PropertyValueFactory<>("area"));
        this.columnaPropietario.setCellValueFactory(new PropertyValueFactory<>("propietario"));
        this.columnaDisponibilidad.setCellValueFactory(new PropertyValueFactory<>("disponibilidad"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoPropiedad"));

        this.columnaName.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaIdPropietario.setCellValueFactory(new PropertyValueFactory<>("id"));

        this.columnaNameCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaIdentificacionCliente.setCellValueFactory(new PropertyValueFactory<>("id"));

        tablaPropiedades.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> llenarCampos(newValue));
    }
    public void registrarPropiedad() {

        String direccionRegistrada = direccion.getText();
        String valorPropiedad = (valor.getText());
        String areaPropiedad = area.getText();
        Propietario propietarioP= combopropietario.getValue();
        String tipoPropiedad = cbTipoPropiedad.getValue();

        try {
            if(!direccionRegistrada.equals("") && !valorPropiedad.equals("") && !areaPropiedad.equals("") && !propietarioP.equals(null) && !tipoPropiedad.equals("")){
                Propiedad propiedad = INSTANCE.getModel().registrarPropiedad(tipoPropiedad,direccionRegistrada,Double.parseDouble(valorPropiedad),Double.parseDouble(areaPropiedad), propietarioP);
                llenarTabla(INSTANCE.getModel().getPropiedades());
                limpiarCamposPropiedad();
            }else{
                mostrarMensajeAdvertencia("LLENE TODOS LOS CAMPOS");
            }
        }
        catch (Exception e){
            e.getMessage();
        }
    }
    public void registrarPropietario () throws Exception {

        String nombre = nombrePropietario.getText();
        String id= idPropietario.getText();

        try {
            Propietario propietarioEncontrado = INSTANCE.getModel().getPropietarios().stream().filter((client)-> client.getId().equals(id)).findFirst().orElse(null);
            if(propietarioEncontrado instanceof Propietario) {
                mostrarMensajeAdvertencia("EL PROPIETARIO YA ESTA REGISTRADO");
                limpiarCamposPropietario();
            }else{
                Propietario propietario =  finca.registrarPropietario(new Propietario(nombre, id));
                if (propietario != null){
                    propietarios.add(propietario);
                    combopropietario.setItems(propietarios);
                    llenarTablaPropietario(INSTANCE.getModel().getPropietarios());
                    limpiarCamposPropietario();
                    mostrarMensajeInformacion("SE HA AÑADIDO CORRECTAMENTE EL PROPIETARIO.");
                }else{
                    mostrarMensajeAdvertencia("LLENE TODOS LOS CAMPOS.");
                }
            }
        }
        catch (Exception e){
            e.getMessage();
        }

    }
    public void registrarCliente () throws Exception {

        String nombre = nombreCliente.getText();
        String id= idCliente.getText();

        try {
            Cliente clienteEncontrado = INSTANCE.getModel().getClientes().stream().filter((client)-> client.getId().equals(id)).findFirst().orElse(null);

            if(clienteEncontrado instanceof Cliente){
                mostrarMensajeAdvertencia("EL CLIENTE YA ESTA REGISTRADO");
                limpiarCamposCliente();
            }else{
                Cliente cliente = finca.registrarCliente(new Cliente(nombre,id));
                limpiarCamposCliente();

                if (cliente != null){
                    clientes.add(cliente);
                    cbClientes.setItems(clientes);
                    llenarTablaCliente(INSTANCE.getModel().getClientes());
                    limpiarCamposCliente();
                    mostrarMensajeInformacion("SE AÑADIO CORRECTAMENTE EL CLIENTE");
                }else{
                    mostrarMensajeAdvertencia("LLENE TODOS LOS CAMPOS.");
                }
            }


        }
        catch (Exception e){
            e.getMessage();
        }

    }
    public void onVenderClick(){
        Cliente cliente = cbClientes.getValue();
        if( propiedadSeleccionada != null){
            if(cliente != null){
                Propietario nuevoPropietario = new Propietario(cliente.getNombre(), cliente.getId());
                propiedadSeleccionada.setDisponibilidad(Disponibilidad.NO_DISPONIBLE);
                propiedadSeleccionada.setPropietario(nuevoPropietario);
                tablaPropiedades.refresh();
                limpiarCamposPropiedad();
            }else{
                mostrarMensajeAdvertencia("ELIGA UN CLIENTE PARA LA VENTA");
            }
        }else {
            mostrarMensajeAdvertencia("ELIGA UNA PROPIEDAD PARA VENDER");
        }
    }
    public void onAlquilarClick(){
        Cliente cliente = cbClientes.getValue();
        if( propiedadSeleccionada != null){
            if(cliente != null){
                propiedadSeleccionada.setDisponibilidad(Disponibilidad.NO_DISPONIBLE);
                tablaPropiedades.refresh();
                limpiarCamposPropiedad();
            }else{
                mostrarMensajeAdvertencia("ELIGA UN CLIENTE PARA ALQUILARLE LA PROPIEDAD");
            }
        }else {
            mostrarMensajeAdvertencia("ELIGA UNA PROPIEDAD PARA ALQUILAR");
        }
    }
    public void onBuscarClick() {
        String tipoPropiedad = cbTipoPropiedad.getValue();
        if (tipoPropiedad != null) {
            llenarTabla(INSTANCE.getModel().buscarPropiedad(tipoPropiedad));
            cbTipoPropiedad.setValue(null);
        } else{
            mostrarMensajeAdvertencia("INGRESE UN VALOR EN TIPO DE PROPIEDAD PARA FILTRAR");
            llenarTabla(INSTANCE.getModel().getPropiedades());
        }


    }
    public void onRetirarClick(){
        if (propiedadSeleccionada != null){
            INSTANCE.getModel().retirarPropiedad(propiedadSeleccionada);
            List<Propiedad> listResult = (INSTANCE.getModel().getPropiedades());
            if(listResult != null){
                llenarTabla(listResult);
            }else{
                mostrarMensajeAdvertencia("NO SE HA ENCONTRADO NINGUNA PROPIEDAD");
                llenarTabla(INSTANCE.getModel().getPropiedades());
            }
        }else{
            mostrarMensajeAdvertencia("SELECCIONE UNA PROPIEDAD PARA ELIMINAR");
        }
    }
    public void onCerrarSesionClick() throws IOException {
        Parent parent = FXMLLoader.load(MainApp.class.getResource("login.fxml"));
        Scene scene = new Scene(parent, 600, 600);
        Stage stage = new Stage();
        stage.setTitle("ADMINISTRADOR");
        stage.setScene(scene);
        stage.initOwner(btnCerrarSesion.getScene().getWindow());
        btnCerrarSesion.getScene().getWindow().hide();
        stage.show();
    }
    private void mostrarMensajeAdvertencia(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void mostrarMensajeInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void limpiarCamposPropietario(){
        nombrePropietario.setText("");
        idPropietario.setText("");
    }
    private void limpiarCamposCliente(){
        nombreCliente.setText("");
        idCliente.setText("");
    }
    private void limpiarCamposPropiedad() {
        direccion.setText("");
        valor.setText("");
        area.setText("");
        cbTipoPropiedad.setValue(null);
        combopropietario.setValue(null);
        cbClientes.setValue(null);

    }
    private void llenarCampos(Propiedad propiedad) {
        propiedadSeleccionada = propiedad;
        if (propiedad != null) {
            direccion.setText(propiedad.getDireccion());
            valor.setText(String.valueOf(propiedad.getValor()));
            area.setText(String.valueOf(propiedad.getArea()));
            combopropietario.setValue(propiedad.getPropietario());
            cbTipoPropiedad.setValue(propiedad.getTipoPropiedad());
        }
    }
    public void onCancelarClick() {
        limpiarCamposPropiedad();
        tablaPropiedades.getSelectionModel().clearSelection();

    }
    private void llenarTabla(List<Propiedad> propiedades) {
        tablaPropiedades.setItems(FXCollections.observableArrayList(propiedades));
        tablaPropiedades.refresh();
    }
    private void llenarTablaPropietario(List<Propietario> propietarios){
        tablaPropietarios.setItems(FXCollections.observableArrayList(propietarios));
        tablaPropietarios.refresh();
    }
    private void llenarTablaCliente(List<Cliente> clientes){
        tablaClientes.setItems(FXCollections.observableArrayList(clientes));
        tablaClientes.refresh();
    }

}
