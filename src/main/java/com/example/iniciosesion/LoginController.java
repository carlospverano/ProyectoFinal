package com.example.iniciosesion;
//import model.FincaRaiz;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.FincaRaiz;

import model.Administrador;


import java.util.List;

public class LoginController {
    @FXML
    private Label result;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    FincaRaiz modelo = new FincaRaiz();


    @FXML
    protected void iniciarSesion() {

        List<Administrador> administradores = modelo.getAdministradores();
        String email = emailField.getText();
        String password = passwordField.getText();

        if(email.equals("") || password.equals("")){
            result.setText("*Ingrese sus datos.");
        }else{

            Administrador emailEncontrado = administradores.stream().filter( (admin)-> admin.getEmail().equals(email)).findFirst().orElse(null);
            if(emailEncontrado == null){
                result.setText("Usuario no registrado");
            }else{
                if(emailEncontrado.getPassword().equals(password)){
                    result.setText("Inicio de sesion exitoso");
                    emailField.setText("");
                    passwordField.setText("");
                }else{
                    result.setText("Contraseña invalida");
                }
            }

        }

    }
    public void recuperarCuenta(MouseEvent mouseEvent) {
        result.setText("recuperacion de cuenta.");
    }
}