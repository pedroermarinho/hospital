/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller.Settings;

import io.github.pedroermarinho.hospital.Dados;
import io.github.pedroermarinho.hospital.Model.Configuracao_Local.DataBaseModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
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
public class RegisterDataBaseController implements Initializable {
    private final Dados data = new Dados();
    @FXML
    private TextField HostField;

    @FXML
    private TextField UserField;

    @FXML
    private TextField PasswordField;

    @FXML
    private TextField DataBaseField;

    @FXML
    private TextField PrefixField;

    @FXML
    private TextField PortField;
//
//    @FXML
//    private Button bntCancelar;

    @FXML
    private Button btnSalvar;

    @FXML
    private ToggleButton bntOffline;

    @FXML
    private ToggleButton bntOnline;
    private Stage dialogStage;

    @FXML
    void OnCancelar() {
        dialogStage.close();
    }

    @FXML
    void OnOffline() {
        DisableBnt(true);
//        boolean value = false;
        DataBaseField.setDisable(false);
        btnSalvar.setDisable(false);
        bntOffline.setSelected(true);
        bntOnline.setSelected(false);
        PrefixField.setText("jdbc:sqlite:");
        PrefixField.setDisable(true);

    }

    @FXML
    void OnOnline() {
        DisableBnt(false);
        bntOffline.setSelected(true);
        bntOnline.setSelected(false);
        PrefixField.setText("jdbc:mysql:");
        PrefixField.setDisable(true);
    }

    @FXML
    void OnSalvar() {
        if (isInputValid()) {
            DataBaseModel dataBaseModel = new DataBaseModel();
            dataBaseModel.setDataBase(DataBaseField.getText());
            dataBaseModel.setHost(HostField.getText());
            dataBaseModel.setPassword(PasswordField.getText());
            dataBaseModel.setPorts(Integer.parseInt(PortField.getText()));
            dataBaseModel.setPrefix(PrefixField.getText());
            dataBaseModel.setUser(UserField.getText());
            dataBaseModel.save();
            data.getDataBaseData();
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (DataBaseField.getText() == null || DataBaseField.getText().length() == 0) {
            errorMessage += "DataBase inválido!\n";
            DataBaseField.setStyle("-fx-border-color:red");
        } else {
            DataBaseField.setStyle("");
        }
        if (HostField.getText() == null || HostField.getText().length() == 0) {
            errorMessage += "Host inválido!\n";
            HostField.setStyle("-fx-border-color:red");
        } else {
            HostField.setStyle("");
        }
        if (PasswordField.getText() == null) {
            PasswordField.setText("");
            PasswordField.setStyle("-fx-border-color:red");
        } else {
            PasswordField.setStyle("");
        }
        if (PrefixField.getText() == null || PrefixField.getText().length() == 0) {
            errorMessage += "Prefix inválido!\n";
            PrefixField.setStyle("-fx-border-color:red");
        } else {
            PrefixField.setStyle("");
        }
        if (UserField.getText() == null || UserField.getText().length() == 0) {
            errorMessage += "User inválido!\n";
            UserField.setStyle("-fx-border-color:red");
        } else {
            UserField.setStyle("");
        }
        if (PortField.getText() == null || PortField.getText().length() == 0) {
            errorMessage += "Port inválido!\n";
            PortField.setStyle("-fx-border-color:red");
        } else {
            try {

                Integer.valueOf(PortField.getText());
                PortField.setStyle("");
            } catch (NumberFormatException e) {
                errorMessage += "Port inválido!\n";
                PortField.setStyle("-fx-border-color:red");
            }

        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            MessagemErroFormulario(errorMessage);

            return false;
        }
    }

    private void DisableBnt(boolean value) {
        DataBaseField.setDisable(value);
        HostField.setDisable(value);
        PasswordField.setDisable(value);
        PortField.setDisable(value);
        PrefixField.setDisable(value);
        UserField.setDisable(value);
        btnSalvar.setDisable(value);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DisableBnt(true);
        bntOnline.setDisable(true);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
