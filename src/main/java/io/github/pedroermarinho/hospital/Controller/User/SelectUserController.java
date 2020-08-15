/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller.User;

import io.github.pedroermarinho.hospital.Dados;
import io.github.pedroermarinho.hospital.Model.Usuario.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho
 * <pedro.marinho238@gmail.com & https://github.com/TECFlyingCommunity>
 */
public class SelectUserController implements Initializable {
    private final Dados data = new Dados();
    private UserModel userModel;
//    @FXML
//    private Button btnPesquisar;

    @FXML
    private TextField PesquisarField;

//    @FXML
//    private Button btnSelecionar;
//
//    @FXML
//    private Button btnCancelar;

    @FXML
    private TableView<UserModel> ClientesView;

    @FXML
    private TableColumn<UserModel, String> IDColumn;

    @FXML
    private TableColumn<UserModel, String> NomeColumn;
    private Stage dialogStage;

    @FXML
    void OnCancelar(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void OnPesquisar(ActionEvent event) {
        if (!PesquisarField.getText().equals("")) {
            ClientesView.setItems(findItems());
        } else {
            ClientesView.setItems(data.getUserData());
        }
    }

    @FXML
    void OnSelecionar(ActionEvent event) {
        userModel = ClientesView.getSelectionModel().getSelectedItem();
        dialogStage.close();
    }


    public UserModel getUserModel() {
        return userModel;
    }

    private ObservableList<UserModel> findItems() {
        ObservableList<UserModel> itensEncontrados = FXCollections.observableArrayList();
        Integer ID;
        try {
            ID = Integer.parseInt(PesquisarField.getText());

        } catch (NumberFormatException a) {
            ID = null;

        }
        for (UserModel itens : data.getUserData()) {

            //itens.getID().contains(Integer.valueOf( PesquisaField.getText())
            if (ID != null) {
                if (itens.getIdUser() == ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getNome().contains(PesquisarField.getText()) || itens.getSobrenome().equalsIgnoreCase(PesquisarField.getText()) || itens.getUserName().contains(PesquisarField.getText()) || itens.getEmail().contains(PesquisarField.getText())) {
                    itensEncontrados.add(itens);

                }
            }
        }
        return itensEncontrados;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.ClientesView.setItems(data.getUserData());
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_usuario"));
        NomeColumn.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
