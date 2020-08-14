/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller;

import io.github.pedroermarinho.hospital.Dados;
import io.github.pedroermarinho.hospital.Model.Cliente.ClientModel;
import io.github.pedroermarinho.hospital.Model.Cliente.AddressClientModel;
import io.github.pedroermarinho.hospital.Util.Filtro;
import javafx.beans.property.SimpleStringProperty;
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
public class InformacaoController implements Initializable {
    private final Dados data = new Dados();



    @FXML
    private TableView<ClientModel> RegistroClientesView;

    @FXML
    private TableColumn<ClientModel, String> IDClienteColumn;

    @FXML
    private TableColumn<ClientModel, String> NomeClienteColumn;

    @FXML
    private Label CPFLabel;

    @FXML
    private Label CartaoSUSLabel;

    @FXML
    private Label NascimentoLabel;

    @FXML
    private Label SexoLabel;

    @FXML
    private Label MaeLabel;

    @FXML
    private Label EmailLabel;

    @FXML
    private Label NomeLabel;

    @FXML
    private Label CidadeLabel;

    @FXML
    private Label BairroLabel;

    @FXML
    private Label EstadoLabel;

    @FXML
    private Label PaisLabel;

    @FXML
    private Label RuaLabel;

    @FXML
    private Label NCasaLabel;

    @FXML
    private Label TelefoneLabel;

//    @FXML
//    private Button btnPesquisar;

    @FXML
    private TextField PesquisarField;

    @FXML
    private Label identidadeLabel;

    @FXML
    private Label especialidadeLabel;

    @FXML
    private Label recepcaoLabel;





    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        RegistroClientesView.setItems(data.getClientData());
        IDClienteColumn.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        NomeClienteColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        RegistroClientesView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> Informacoes(newValue));
    }

    private void Informacoes(ClientModel newValue)  {
        try {
            System.out.println(newValue.getSexo());
            CPFLabel.textProperty().bind(newValue.cpfProperty());
            CartaoSUSLabel.textProperty().bind(newValue.cartaoSUSProperty());
            NascimentoLabel.textProperty().bind(newValue.dataNascimentoProperty());
            MaeLabel.textProperty().bind(newValue.maeProperty());
            EmailLabel.textProperty().bind(newValue.emailProperty());
            NomeLabel.textProperty().bind(newValue.nomeProperty());
            SexoLabel.textProperty().bind(newValue.sexoProperty());
            identidadeLabel.textProperty().bind(newValue.identidadeProperty());
            especialidadeLabel.textProperty().bind(newValue.especialidadeProperty());
            recepcaoLabel.textProperty().bind(newValue.recepcaoProperty());


        } catch (NullPointerException ex) {
            CPFLabel.textProperty().bind(new SimpleStringProperty(""));
            CartaoSUSLabel.textProperty().bind(new SimpleStringProperty(""));
            NascimentoLabel.textProperty().bind(new SimpleStringProperty(""));
            SexoLabel.textProperty().bind(new SimpleStringProperty(""));
            MaeLabel.textProperty().bind(new SimpleStringProperty(""));
            EmailLabel.textProperty().bind(new SimpleStringProperty(""));
            NomeLabel.textProperty().bind(new SimpleStringProperty(""));
            identidadeLabel.textProperty().bind(new SimpleStringProperty(""));
            especialidadeLabel.textProperty().bind(new SimpleStringProperty(""));
            recepcaoLabel.textProperty().bind(new SimpleStringProperty(""));
        }
        try {

            AddressClientModel endereco_cliente = Filtro.Cliente_para_Endereco(newValue.getIdClient());
            System.out.println(endereco_cliente);
            if (endereco_cliente != null && endereco_cliente.getIdAddressClient() != 0) {
                System.out.println("ok");

                RuaLabel.textProperty().bind(endereco_cliente.ruaProperty());
                BairroLabel.textProperty().bind(endereco_cliente.bairroProperty());
                CidadeLabel.textProperty().bind(endereco_cliente.cidadeProperty());
                EstadoLabel.textProperty().bind(endereco_cliente.estadoProperty());
                PaisLabel.textProperty().bind(endereco_cliente.paisProperty());
                NCasaLabel.textProperty().bind(endereco_cliente.numeroCasaProperty().asString());
                TelefoneLabel.textProperty().bind(endereco_cliente.telefoneProperty());

            } else {
              throw new NullPointerException("Sem dados");
            }
        } catch (NullPointerException e) {
            RuaLabel.textProperty().bind(new SimpleStringProperty(""));
            BairroLabel.textProperty().bind(new SimpleStringProperty(""));
            CidadeLabel.textProperty().bind(new SimpleStringProperty(""));
            EstadoLabel.textProperty().bind(new SimpleStringProperty(""));
            PaisLabel.textProperty().bind(new SimpleStringProperty(""));
            NCasaLabel.textProperty().bind(new SimpleStringProperty(""));
            TelefoneLabel.textProperty().bind(new SimpleStringProperty(""));
        }

    }


    @FXML
    void OnPesquisar() {
        if (!PesquisarField.getText().equals("")) {
            RegistroClientesView.setItems(findItems());
        } else {
            RegistroClientesView.setItems(data.getClientData());
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
        for (ClientModel itens :data.getClientData()) {
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

}
