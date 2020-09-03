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

import static io.github.pedroermarinho.hospital.Util.Filtro.Cliente_para_Endereco;

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
    private TextField PaisText;
    @FXML
    private TextField EstadoText;
    @FXML
    private TextField CidadeText;
    @FXML
    private TextField BairroText;
    @FXML
    private TextField RuaText;
    @FXML
    private TextField NCasaText;

    @FXML
    private TextArea ObservacaoText;
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
        modificaoAddress = Cliente_para_Endereco(cliente.getIdClient());
        if (modificaoAddress != null) {

            NCasaText.setText(String.valueOf(modificaoAddress.getNumeroCasa()));
//            TelefoneText.setText(modificaoAddress.getTelefone());
            ObservacaoText.setText(modificaoAddress.getComplemento());
            RuaText.setText(modificaoAddress.getRua());
            BairroText.setText(modificaoAddress.getBairro());
            CidadeText.setText(modificaoAddress.getCidade());
            EstadoText.setText(modificaoAddress.getEstado());
            PaisText.setText(modificaoAddress.getPais());
        }
    }


    @FXML
    void OnEditar() {
        On_Off_Button(false);
        cliente = registrosView.getSelectionModel().getSelectedItem();

        modificaoAddress = Cliente_para_Endereco(cliente.getIdClient());
        if (modificaoAddress != null) {
            NCasaText.setText(String.valueOf(modificaoAddress.getNumeroCasa()));
//            TelefoneText.setText(modificaoAddress.getTelefone());
            ObservacaoText.setText(modificaoAddress.getComplemento());

            RuaText.setText(modificaoAddress.getRua());
            BairroText.setText(modificaoAddress.getBairro());
            CidadeText.setText(modificaoAddress.getCidade());
            EstadoText.setText(modificaoAddress.getEstado());
            PaisText.setText(modificaoAddress.getPais());
        }
    }

    @FXML
    void OnSalvar() {
        if (modificaoAddress == null) {
            modificaoAddress = new AddressClientModel();
        }
        if (isInputValid()) {

            modificaoAddress.setPais(PaisText.getText());
            modificaoAddress.setEstado(EstadoText.getText());
            modificaoAddress.setCidade(CidadeText.getText());
            modificaoAddress.setBairro(BairroText.getText());
            modificaoAddress.setRua(RuaText.getText());
            modificaoAddress.setNumeroCasa(Integer.parseInt(NCasaText.getText()));

            modificaoAddress.setComplemento(ObservacaoText.getText());
            modificaoAddress.setIdClient(cliente.getIdClient());

            modificaoAddress.save();
            LimparCampo();
            data.getAddressClientData();
            On_Off_Button(true);
        }

    }

    private void LimparCampo() {
        NCasaText.setText("");

        ObservacaoText.setText("");
        PaisText.setText("");
        EstadoText.setText("");
        CidadeText.setText("");
        BairroText.setText("");
        RuaText.setText("");

        cliente = null;
        modificaoAddress = null;
    }


    private void On_Off_Button(boolean es) {
        modificaoAddress = null;
        NCasaText.setDisable(es);

        ObservacaoText.setDisable(es);
        PaisText.setDisable(es);
        EstadoText.setDisable(es);
        CidadeText.setDisable(es);
        BairroText.setDisable(es);
        RuaText.setDisable(es);

        bntSalva.setDisable(es);
        BtnCancelar.setDisable(es);
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (NCasaText.getText() == null || NCasaText.getText().length() == 0) {
            errorMessage += "Nº Casa inválido!\n";
            NCasaText.setStyle("-fx-border-color:red");
        } else {
            try {
                Integer.valueOf(NCasaText.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Nº Casa inválido!\n";
                NCasaText.setStyle("-fx-border-color:red");
            }
            NCasaText.setStyle("");
        }

        if (ObservacaoText.getText() == null || ObservacaoText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            ObservacaoText.setStyle("-fx-border-color:red");
        } else {
            ObservacaoText.setStyle("");
        }

        if (PaisText.getText() == null || PaisText.getText().length() == 0) {
            errorMessage += "País inválido!\n";
            PaisText.setStyle("-fx-border-color:red");
        } else {
            PaisText.setStyle("");
        }
        if (EstadoText.getText() == null || EstadoText.getText().length() == 0) {
            errorMessage += "Estado inválido!\n";
            EstadoText.setStyle("-fx-border-color:red");
        } else {
            EstadoText.setStyle("");
        }
        if (CidadeText.getText() == null || CidadeText.getText().length() == 0) {
            errorMessage += "Cidade inválido!\n";
            CidadeText.setStyle("-fx-border-color:red");
        } else {
            CidadeText.setStyle("");
        }

        if (BairroText.getText() == null || BairroText.getText().length() == 0) {
            errorMessage += "Bairro inválido!\n";
            BairroText.setStyle("-fx-border-color:red");
        } else {
            BairroText.setStyle("");
        }
        if (RuaText.getText() == null || RuaText.getText().length() == 0) {
            errorMessage += "Bairro inválido!\n";
            RuaText.setStyle("-fx-border-color:red");
        } else {
            RuaText.setStyle("");
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            MsgErro.MessagemErroFormulario(errorMessage);

            return false;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        On_Off_Button(true);
        registrosView.setItems(data.getClientData());
        bntDetalhes.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnEditar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        ClienteColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

    }

}
