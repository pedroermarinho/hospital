/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller;

import io.github.pedroermarinho.hospital.Dados;
import io.github.pedroermarinho.hospital.Model.Client.Client.ClientModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class ClientsController implements Initializable {
    private final Dados data = new Dados();

    private ClientModel cliente;
//    @FXML
//    private Button btnPesquisar;
//
//    @FXML
//    private Button btnSelecionar;
//
//    @FXML
//    private Button btnCancelar;

    @FXML
    private TableView<ClientModel> ClientesView;

    @FXML
    private TableColumn<ClientModel, String> IDColumn;

    @FXML
    private TableColumn<ClientModel, String> NomeColumn;

    @FXML
    private TextField PesquisarField;
    private Stage dialogStage;

    @FXML
    void OnPesquisar() {
        if (!PesquisarField.getText().equals("")) {
            ClientesView.setItems(findItems());
        } else {
            ClientesView.setItems(data.getClientData());
        }
    }

    private ObservableList<ClientModel> findItems() {
        ObservableList<ClientModel> itensEncontrados = FXCollections.observableArrayList();
        Integer ID;
        try {
            ID = Integer.parseInt(PesquisarField.getText());

        } catch (NumberFormatException a) {
            ID = null;

        }
        for (ClientModel itens : data.getClientData()) {

            //itens.getID().contains(Integer.valueOf( PesquisaField.getText())
            if (ID != null) {
                if (itens.getIdClient() == ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getCpf().contains(PesquisarField.getText()) || itens.getCartaoSUS().equalsIgnoreCase(PesquisarField.getText()) || itens.getNome().contains(PesquisarField.getText())) {
                    itensEncontrados.add(itens);

                }
            }
        }
        return itensEncontrados;
    }

    @FXML
    void OnSelecionar() {
        cliente = ClientesView.getSelectionModel().getSelectedItem();
        dialogStage.close();
    }

    @FXML
    void OnCancelar() {
        dialogStage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.ClientesView.setItems(data.getClientData());
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_cliente"));
        NomeColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
