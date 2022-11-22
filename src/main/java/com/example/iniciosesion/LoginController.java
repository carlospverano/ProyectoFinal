package com.example.iniciosesion;
//import model.FincaRaiz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import static com.example.iniciosesion.AppController.INSTANCE;

import javafx.stage.Stage;
import model.Administrador;
import model.Empleado;
import model.Usuario;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.List;

public class LoginController {
    @FXML
    private Label result;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button btnLogin;

    @FXML
    protected void onLoginButtonClick() throws IOException {

        String email = emailField.getText();
        String password = passwordField.getText();

        Usuario userLogin = INSTANCE.getModel().autenticar(email, password);

        if(userLogin == null){
            emailField.setText("");
            passwordField.setText("");
            result.setText("CREDENCIALES INCORRECTAS");
        }else if(userLogin instanceof Empleado){
            Parent parent = FXMLLoader.load(MainApp.class.getResource("empleado-view.fxml"));
            Scene scene = new Scene(parent, 900, 700);
            Stage stage = new Stage();
            stage.setTitle("Principal");
            stage.setScene(scene);
            stage.show();
        }else{
            Parent parent = FXMLLoader.load(MainApp.class.getResource("administrador-view.fxml"));
            Scene scene = new Scene(parent, 800, 600);
            Stage stage = new Stage();
            stage.setTitle("ADMINISTRADOR");
            stage.setScene(scene);
            stage.initOwner(btnLogin.getScene().getWindow());
            btnLogin.getScene().getWindow().hide();
            stage.show();
        }

    }
    public void recuperarCuenta(MouseEvent mouseEvent) {
        result.setText("recuperacion de cuenta.");
    }
}