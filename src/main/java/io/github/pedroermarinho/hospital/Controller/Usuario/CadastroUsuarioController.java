/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller.Usuario;

import io.github.pedroermarinho.hospital.MainApp;
import io.github.pedroermarinho.hospital.Model.Usuario.model_usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static io.github.pedroermarinho.hospital.Util.MsgErro.MessagemErroFormulario;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho
 * <pedro.marinho238@gmail.com & https://github.com/TECFlyingCommunity>
 */
public class CadastroUsuarioController implements Initializable {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField PrimeiroNomeField;

    @FXML
    private TextField SobrenomeField;

    @FXML
    private TextField usuarioField;

    @FXML
    private TextField EmailField;

    @FXML
    private PasswordField senhaField;

    @FXML
    private DatePicker dpData;

    @FXML
    private ComboBox<String> SexoBox;

    private MainApp mainapp;
    private model_usuario modificao_usuario = null;
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * @param mainapp
     */
    public void setMainApp(MainApp mainapp) {
        this.mainapp = mainapp;
    }

    @FXML
    void OnCancelar(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void OnSalvar(ActionEvent event) {
        if (modificao_usuario == null) {
            modificao_usuario = new model_usuario(mainapp);
        }
        if (isInputValid()) {
            modificao_usuario.setDataNascimento(java.sql.Date.valueOf(dpData.getValue()));
            modificao_usuario.setEmail(EmailField.getText());
            modificao_usuario.setUsuario(usuarioField.getText());
            modificao_usuario.setNome(PrimeiroNomeField.getText());
            modificao_usuario.setSenha(senhaField.getText());
            modificao_usuario.setSobrenome(SobrenomeField.getText());
            modificao_usuario.save();

            mainapp.getDadosData().getUsuariosData();
            dialogStage.close();

        }
    }

    public model_usuario getUsuario() {
        return modificao_usuario;
    }



    private boolean isInputValid() {
        System.out.println("isInputValid");
        String errorMessage = "";

        if (PrimeiroNomeField.getText() == null || PrimeiroNomeField.getText().length() == 0) {
            errorMessage += "Usuario inválido!\n";
            PrimeiroNomeField.setStyle("-fx-border-color:red");
        } else {
            PrimeiroNomeField.setStyle("");
        }
        if (SobrenomeField.getText() == null || SobrenomeField.getText().length() == 0) {
            errorMessage += "Cliente inválido!\n";
            SobrenomeField.setStyle("-fx-border-color:red");
        } else {
            SobrenomeField.setStyle("");
        }
        if (usuarioField.getText() == null || usuarioField.getText().length() == 0) {
            errorMessage += "Clinica inválido!\n";
            usuarioField.setStyle("-fx-border-color:red");
        } else {
            usuarioField.setStyle("");
        }
        if (senhaField.getText() == null || senhaField.getText().length() == 0) {
            errorMessage += "Observação inválido!\n";
            senhaField.setStyle("-fx-border-color:red");
        } else {
            senhaField.setStyle("");
        }

        if (dpData.getValue() == null || dpData.getValue().toString().length() == 0) {
            errorMessage += "Hora inválido!\n";
            dpData.setStyle("-fx-border-color:red");
        } else {
            dpData.setStyle("");
        }
        if (SexoBox.getValue() == null || SexoBox.getValue().toString().length() == 0) {
            errorMessage += "Data inválido!\n";
            SexoBox.setStyle("-fx-border-color:red");
        } else {
            SexoBox.setStyle("");
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            MessagemErroFormulario(errorMessage);
//            System.out.println(errorMessage);
//           
            return false;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
