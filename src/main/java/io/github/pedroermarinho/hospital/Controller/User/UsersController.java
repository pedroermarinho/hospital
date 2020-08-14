/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller.User;

import io.github.pedroermarinho.hospital.Controller.Util.SexoEnum;
import io.github.pedroermarinho.hospital.Dados;
import io.github.pedroermarinho.hospital.Model.Usuario.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static io.github.pedroermarinho.hospital.Util.MsgErro.MessagemErroFormulario;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class UsersController implements Initializable {
    private final Dados data = new Dados();
    private UserModel modificaoUser;
    @FXML
    private TableView<UserModel> PessoaTable;
    @FXML
    private TableColumn<UserModel, Integer> IDColumn;
    @FXML
    private TableColumn<UserModel, String> UsuarioColumn;
    @FXML
    private TableColumn<UserModel, String> NomeColumn;
    @FXML
    private MenuItem btnDetalhes;
//    @FXML
//    private Button btNovo;
    @FXML
    private Button btEditar;
    @FXML
    private Button btDeletar;
    @FXML
    private TextField PrimeiroNomeField;
    @FXML
    private TextField SobrenomeField;
    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private DatePicker dpData;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;
    @FXML
    private TextField EmailField;
    @FXML
    private ComboBox<SexoEnum> SexoBox;



    @FXML
    void OnCancelar() {
        LimparCampo();
        On_Off_Button(true);
    }

    @FXML
    void OnSalvar() {
        if (modificaoUser == null) {
            modificaoUser = new UserModel();
        }
        if (isInputValid()) {
            modificaoUser.setDataNascimento(java.sql.Date.valueOf(dpData.getValue()));
            modificaoUser.setEmail(EmailField.getText());
            modificaoUser.setUserName(usuarioField.getText());
            modificaoUser.setNome(PrimeiroNomeField.getText());
            modificaoUser.setSenha(senhaField.getText());
            modificaoUser.setSobrenome(SobrenomeField.getText());
            modificaoUser.setSexo(SexoBox.getValue().getDescricao());
            modificaoUser.save();
            LimparCampo();
            data.getUserData();
            On_Off_Button(true);

        }
    }

    @FXML
    void DeletarPessoa() {
        UserModel selected = PessoaTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.delete();
            data.getUserData();
        }
    }

    @FXML
    void EditarPessoa() {
        LimparCampo();
        On_Off_Button(false);
        modificaoUser = PessoaTable.getSelectionModel().getSelectedItem();
        PrimeiroNomeField.setText(modificaoUser.getNome());
        SobrenomeField.setText(modificaoUser.getSobrenome());
        usuarioField.setText(modificaoUser.getUserName());
        senhaField.setText(modificaoUser.getSenha());
        dpData.setValue(modificaoUser.getDataNascimento().toLocalDate());
        EmailField.setText(modificaoUser.getEmail());
        SexoBox.setValue(SexoEnum.valueOf(modificaoUser.getSexo().toUpperCase()));
    }

    @FXML
    void NovaPessoa() {
        LimparCampo();
        On_Off_Button(false);
    }

    @FXML
    void OnDetalhes() {
        LimparCampo();
        On_Off_Button(true);
        modificaoUser = PessoaTable.getSelectionModel().getSelectedItem();
        PrimeiroNomeField.setText(modificaoUser.getNome());
        SobrenomeField.setText(modificaoUser.getSobrenome());
        usuarioField.setText(modificaoUser.getUserName());
        senhaField.setText(modificaoUser.getSenha());
        dpData.setValue(modificaoUser.getDataNascimento().toLocalDate());
        EmailField.setText(modificaoUser.getEmail());
        SexoBox.setValue(SexoEnum.valueOf(modificaoUser.getSexo().toUpperCase()));

    }

    private void On_Off_Button(boolean es) {
        PrimeiroNomeField.setDisable(es);
        SobrenomeField.setDisable(es);
        usuarioField.setDisable(es);
        senhaField.setDisable(es);
        dpData.setDisable(es);
        SexoBox.setDisable(es);
        btnSalvar.setDisable(es);
        btnCancelar.setDisable(es);
        EmailField.setDisable(es);
    }

    private void LimparCampo() {
        PrimeiroNomeField.setText("");
        EmailField.setText("");
        SobrenomeField.setText("");
        usuarioField.setText("");
        senhaField.setText("");
        dpData.setValue(null);
        SexoBox.setValue(null);
        modificaoUser = null;

    }

    private boolean isInputValid() {
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
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        On_Off_Button(true);
        PessoaTable.setItems(data.getUserData());
        SexoBox.getItems().addAll(SexoEnum.values());
        btnDetalhes.disableProperty().bind(PessoaTable.getSelectionModel().selectedItemProperty().isNull());
        btEditar.disableProperty().bind(PessoaTable.getSelectionModel().selectedItemProperty().isNull());
        btDeletar.disableProperty().bind(PessoaTable.getSelectionModel().selectedItemProperty().isNull());

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        UsuarioColumn.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
        NomeColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));
    }

}
