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


import java.lang.ref.Cleaner;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class EmpleadoController implements Initializable {
    Propiedad propiedadSeleccionado;
    @FXML
    private ObservableList<Propiedad> propiedades = FXCollections.observableArrayList(); //Lista para mostrar en la tabla
    @FXML
    private ObservableList<Propietario> propietarios = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    @FXML
    private TextField nombrePropietario;
    @FXML
    private TextField idPropietario;

    FincaRaiz finca = INSTANCE.getModel();

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
    private void comboboxEvents(ActionEvent e){}

    public void eventKey(KeyEvent keyEvent) {
    }
    public void eventAction(ActionEvent actionEvent) {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
    }
    public void registrarPropiedad() {

        String direccionRegistrada = direccion.getText();
        String valorPropiedad = (valor.getText());
        String areaPropiedad = area.getText();
        Propietario propietarioP= combopropietario.getValue();
        String tipoPropiedad = cbTipoPropiedad.getValue();

        try {
            Propiedad propiedadRegistrada = finca.registrarPropiedad(tipoPropiedad, direccionRegistrada, Double.parseDouble(valorPropiedad), Double.parseDouble(areaPropiedad), propietarioP);
            propiedades.add(propiedadRegistrada);
            tablaPropiedades.setItems(propiedades);
            tablaPropiedades.refresh(); //Actualiza la tabla
            limpiarCampos();
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
                    tablaPropietarios.setItems(propietarios);
                    tablaPropietarios.refresh();
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
                    tablaClientes.setItems(clientes);
                    tablaClientes.refresh(); //Actualiza la tabla

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



    private void llenarTabla(List<Propiedad> propiedadList) {
        tablaPropiedades.setItems(FXCollections.observableArrayList(propiedadList));
        tablaPropiedades.refresh();
    }
    private void limpiarCampos() {
        //nombre.setText("");
        valor.setText("");
        area.setText("");
        cbTipoPropiedad.setValue(null);
        combopropietario.setValue(null);

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

    public void limpiarCamposPropietario(){
        nombrePropietario.setText("");
        idPropietario.setText("");
    }
    public void limpiarCamposCliente(){
        nombreCliente.setText("");
        idCliente.setText("");
    }
}
