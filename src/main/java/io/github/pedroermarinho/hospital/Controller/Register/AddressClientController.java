/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller.Register;

import io.github.pedroermarinho.hospital.Dados;
import io.github.pedroermarinho.hospital.Model.Client.Address.AddressClientModel;
import io.github.pedroermarinho.hospital.Model.Client.Client.ClientModel;
import io.github.pedroermarinho.hospital.Util.MsgErro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class AddressClientController implements Initializable {
    private final Dados data = new Dados();

    private AddressClientModel modificaoAddress;
    private ClientModel cliente;
    @FXML
    private TextField paisField;
    @FXML
    private TextField estadoField;
    @FXML
    private TextField cidadeField;
    @FXML
    private TextField bairroField;
    @FXML
    private TextField ruaField;
    @FXML
    private TextField casaField;

    @FXML
    private TextArea observacaoField;
    @FXML
    private Button bntSalva;
    @FXML
    private Button BtnCancelar;
    //    @FXML
//    private Button btnPesquisar;
    @FXML
    private TableView<ClientModel> registrosView;
    @FXML
    private TableColumn<ClientModel, Integer> IDColumn;
    @FXML
    private TableColumn<ClientModel, String> ClienteColumn;
    @FXML
    private MenuItem bntDetalhes;
    @FXML
    private Button btnEditar;
    @FXML
    private TextField PesquisarField;

    @FXML
    void OnPesquisar() {
        if (!PesquisarField.getText().equals("")) {
            registrosView.setItems(findItems());
        } else {
            registrosView.setItems(data.getClientData());
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
    void OnCancelar() {
        LimparCampo();
        On_Off_Button(true);
    }

    @FXML
    void OnDetalhes() {
        On_Off_Button(true);
        cliente = registrosView.getSelectionModel().getSelectedItem();
        modificaoAddress = AddressClientModel.find(cliente.getIdClient());
        if (modificaoAddress != null) {

            casaField.setText(String.valueOf(modificaoAddress.getNumeroCasa()));
            observacaoField.setText(modificaoAddress.getComplemento());
            ruaField.setText(modificaoAddress.getRua());
            bairroField.setText(modificaoAddress.getBairro());
            cidadeField.setText(modificaoAddress.getCidade());
            estadoField.setText(modificaoAddress.getEstado());
            paisField.setText(modificaoAddress.getPais());
        }
    }


    @FXML
    void OnEditar() {
        On_Off_Button(false);
        cliente = registrosView.getSelectionModel().getSelectedItem();

        modificaoAddress = AddressClientModel.find(cliente.getIdClient());
        if (modificaoAddress != null) {
            casaField.setText(String.valueOf(modificaoAddress.getNumeroCasa()));
            observacaoField.setText(modificaoAddress.getComplemento());

            ruaField.setText(modificaoAddress.getRua());
            bairroField.setText(modificaoAddress.getBairro());
            cidadeField.setText(modificaoAddress.getCidade());
            estadoField.setText(modificaoAddress.getEstado());
            paisField.setText(modificaoAddress.getPais());
        }
    }

    @FXML
    void OnSalvar() {
        if (modificaoAddress == null) {
            modificaoAddress = new AddressClientModel();
        }
        if (isInputValid()) {

            modificaoAddress.setPais(paisField.getText());
            modificaoAddress.setEstado(estadoField.getText());
            modificaoAddress.setCidade(cidadeField.getText());
            modificaoAddress.setBairro(bairroField.getText());
            modificaoAddress.setRua(ruaField.getText());
            modificaoAddress.setNumeroCasa(casaField.getText());

            modificaoAddress.setComplemento(observacaoField.getText());
            modificaoAddress.setIdClient(cliente.getIdClient());

            modificaoAddress.save();
            LimparCampo();
            data.getAddressClientData();
            On_Off_Button(true);
        }

    }

    private void LimparCampo() {
        casaField.setText("");

        observacaoField.setText("");
        paisField.setText("");
        estadoField.setText("");
        cidadeField.setText("");
        bairroField.setText("");
        ruaField.setText("");

        cliente = null;
        modificaoAddress = null;
    }


    private void On_Off_Button(boolean es) {
        modificaoAddress = null;
        casaField.setDisable(es);

        observacaoField.setDisable(es);
        paisField.setDisable(es);
        estadoField.setDisable(es);
        cidadeField.setDisable(es);
        bairroField.setDisable(es);
        ruaField.setDisable(es);

        bntSalva.setDisable(es);
        BtnCancelar.setDisable(es);
    }

    private boolean isInputValid() {
        String errorMessage = "";



        if(!isValidPaisField()){
            errorMessage += "País inválido!\n";
        }
        if (!isValidEstadoField()){
            errorMessage += "Estado inválido!\n";
        }

        if (!isValidCidadeField()){
            errorMessage += "Cidade inválido!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            MsgErro.MessagemErroFormulario(errorMessage);

            return false;
        }
    }




    public boolean isValidPaisField() {
        if (paisField.getText() == null || paisField.getText().length() == 0) {
            paisField.setStyle("-fx-border-color:red");
            return false;
        } else {
            paisField.setStyle("");
            return true;
        }
    }

    public boolean isValidEstadoField() {
        if (estadoField.getText() == null || estadoField.getText().length() == 0) {
            estadoField.setStyle("-fx-border-color:red");
            return false;
        } else {
            estadoField.setStyle("");
            return true;
        }
    }

    public boolean isValidCidadeField() {
        if (cidadeField.getText() == null || cidadeField.getText().length() == 0) {
            cidadeField.setStyle("-fx-border-color:red");
            return false;
        } else {
            cidadeField.setStyle("");
            return true;
        }
    }

    public boolean isValidBairroField() {
        if (bairroField.getText() == null || bairroField.getText().length() == 0) {
            bairroField.setStyle("-fx-border-color:red");
            return false;
        } else {
            bairroField.setStyle("");
            return true;
        }

    }

    public boolean isValidRuaField() {
        if (ruaField.getText() == null || ruaField.getText().length() == 0) {
            ruaField.setStyle("-fx-border-color:red");
            return false;
        } else {
            ruaField.setStyle("");
            return true;
        }
    }

    public boolean isValidNCasaField() {
        if (casaField.getText() == null || casaField.getText().length() == 0) {
            casaField.setStyle("-fx-border-color:red");
            return false;
        } else {
            casaField.setStyle("");
            return true;
        }
    }

    public boolean isValidObservacaoField() {
        if (observacaoField.getText() == null || observacaoField.getText().length() == 0) {
            observacaoField.setStyle("-fx-border-color:red");
            return false;
        } else {
            observacaoField.setStyle("");
            return true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        On_Off_Button(true);
        registrosView.setItems(data.getClientData());
        bntDetalhes.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnEditar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("cartaoSUS"));
        ClienteColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }
}
