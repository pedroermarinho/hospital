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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static io.github.pedroermarinho.hospital.Util.MsgErro.MessagemErroFormulario;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class SettingsDataBaseController implements Initializable {
    private final Dados data = new Dados();
    private DataBaseModel dataBaseModel;
    @FXML
    private ListView<DataBaseModel> BancosView;
    @FXML
    private MenuItem bntDetalhes;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnDeletar;
    //    @FXML
//    private Button btnNovo;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;
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


    @FXML
    void OnCancelar() {
        On_Off_Button(true);

        LimparCampo();
    }

    @FXML
    void OnDeletar() {
        DataBaseModel selected = BancosView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.delete();
            data.getDataBaseData();
        }
    }

    @FXML
    void OnSalvar() {
        if (dataBaseModel == null) {
            dataBaseModel = new DataBaseModel();
        }
        if (isInputValid()) {
            dataBaseModel = new DataBaseModel();
            dataBaseModel.setDataBase(DataBaseField.getText());
            dataBaseModel.setHost(HostField.getText());
            dataBaseModel.setPassword(PasswordField.getText());
            dataBaseModel.setPorts(Integer.parseInt(PortField.getText()));
            dataBaseModel.setPrefix(PrefixField.getText());
            dataBaseModel.setUser(UserField.getText());
            dataBaseModel.save();
            LimparCampo();
            On_Off_Button(true);
            data.getDataBaseData();

        }

    }

    @FXML
    void OnDetalhes() {
        On_Off_Button(true);
        dataBaseModel = BancosView.getSelectionModel().getSelectedItem();
        DataBaseField.setText(dataBaseModel.getDataBase());
        HostField.setText(dataBaseModel.getHost());
        PasswordField.setText(dataBaseModel.getPassword());
        PortField.setText(String.valueOf(dataBaseModel.getPorts()));
        PrefixField.setText(dataBaseModel.getPrefix());
        UserField.setText(dataBaseModel.getUser());
    }

    @FXML
    void OnEditar() {
        On_Off_Button(false);
        DataBaseField.setText(dataBaseModel.getDataBase());
        HostField.setText(dataBaseModel.getHost());
        PasswordField.setText(dataBaseModel.getPassword());
        PortField.setText(String.valueOf(dataBaseModel.getPorts()));
        PrefixField.setText(dataBaseModel.getPrefix());
        UserField.setText(dataBaseModel.getUser());
    }

    @FXML
    void OnNovo() {
        On_Off_Button(false);
        LimparCampo();
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

    private void On_Off_Button(boolean es) {
        HostField.setDisable(es);
        UserField.setDisable(es);
        PasswordField.setDisable(es);
        DataBaseField.setDisable(es);
        PrefixField.setDisable(es);
        PortField.setDisable(es);
        btnSalvar.setDisable(es);
        btnCancelar.setDisable(es);
    }

    private void LimparCampo() {
        HostField.setText("");
        UserField.setText("");
        PasswordField.setText("");
        DataBaseField.setText("");
        PrefixField.setText("");
        PortField.setText("");
        dataBaseModel = null;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        On_Off_Button(true);
        BancosView.setItems(data.getDataBaseData());
        // TODO
        bntDetalhes.disableProperty().bind(BancosView.getSelectionModel().selectedItemProperty().isNull());
        btnEditar.disableProperty().bind(BancosView.getSelectionModel().selectedItemProperty().isNull());
        btnDeletar.disableProperty().bind(BancosView.getSelectionModel().selectedItemProperty().isNull());
    }

}
