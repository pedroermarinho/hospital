/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller.Util;

import io.github.pedroermarinho.hospital.MainApp;
import io.github.pedroermarinho.hospital.Model.Usuario.UserModel;
import io.github.pedroermarinho.hospital.Util.Senha;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class SenhaController implements Initializable {

    @FXML
    private Label lbUsuario;
    @FXML
    private PasswordField senha;
    private MainApp mainapp;
    private UserModel pessoa;
    private Stage dialogStage;
    private boolean senhabool = false;

    public void setDialogStage(Stage dialogStage) {
        System.out.println("setDialogStage");
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainapp, UserModel pessoa) {
        System.out.println("setMainApp");
        this.mainapp = mainapp;
        this.pessoa = pessoa;
        lbUsuario.setText(pessoa.getUsuario());
    }

    @FXML
    private void ok() {
        if ((Senha.Cripto(senha.getText().toUpperCase()).equals(pessoa.getSenha()))) {
            senhabool = true;
            senha();
            dialogStage.close();
        }

    }

    public boolean senha() {
        return senhabool;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
