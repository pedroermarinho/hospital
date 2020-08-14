/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller;

import io.github.pedroermarinho.hospital.Dados;
import io.github.pedroermarinho.hospital.MainApp;
import io.github.pedroermarinho.hospital.Model.Cliente.ClientModel;
import io.github.pedroermarinho.hospital.Model.Cliente.AddressClientModel;
import io.github.pedroermarinho.hospital.Util.Filtro;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class CentralTextoController implements Initializable {

    private final Dados data = new Dados();

    private AddressClientModel endereco_cliente;
    private ClientModel cliente;

    @FXML
    private AnchorPane TextoMenuAnchor;
    @FXML
    private BorderPane BorderTop;
//
//    @FXML
//    private Button btnOFF_ON_TopMenu;
//
//    @FXML
//    private Label TituloMenuTopLabel;

    @FXML
    private Label DataMenuTopLabel;

//
//    @FXML
//    private Button btnPesquisarData;

    @FXML
    private TableView<ClientModel> clientTableView;

    @FXML
    private TableColumn<ClientModel, String> cartaoSUSColumn;

    @FXML
    private TableColumn<ClientModel, String> nomeColumn;

    @FXML
    private MenuItem btnDetalhesListCenter;

    @FXML
    private TableView<ClientModel> AgendaView;

    @FXML
    private TableColumn<ClientModel, Time> HoraAgendaColumn;

    @FXML
    private MenuItem btnDetalhesViewHoje;

    @FXML
    private Label NomeLabel;

    @FXML
    private Label NascimentoLabel;

    @FXML
    private Label CPFLabel;

    @FXML
    private Label CartaoSUSLabel;

    @FXML
    private Label SexoLabel;

    @FXML
    private Label EmailLabel;


    @FXML
    private Label TelefoneLabel;

    @FXML
    private TextField PesquisarField;


    private boolean on_off;

    @FXML
    void OnDetalhesListCenter() {
        cliente = clientTableView.getSelectionModel().getSelectedItem();
        try {

            CPFLabel.textProperty().bind(cliente.cpfProperty());
            CartaoSUSLabel.textProperty().bind(cliente.cartaoSUSProperty());
            NascimentoLabel.textProperty().bind(cliente.dataNascimentoProperty());
            EmailLabel.textProperty().bind(cliente.emailProperty());
            NomeLabel.textProperty().bind(cliente.nomeProperty());
            SexoLabel.textProperty().bind(cliente.sexoProperty());

        } catch (Exception ex) {
            CPFLabel.textProperty().bind(new SimpleStringProperty(""));
            CartaoSUSLabel.textProperty().bind(new SimpleStringProperty(""));
            NascimentoLabel.textProperty().bind(new SimpleStringProperty(""));
            SexoLabel.textProperty().bind(new SimpleStringProperty(""));
            EmailLabel.textProperty().bind(new SimpleStringProperty(""));
            NomeLabel.textProperty().bind(new SimpleStringProperty(""));

        }
        try {

            endereco_cliente = Filtro.Cliente_para_Endereco(cliente.getIdClient());
            System.out.println(endereco_cliente);
            if (endereco_cliente != null && endereco_cliente.getIdAddressClient() != 0) {

                TelefoneLabel.textProperty().bind(endereco_cliente.telefoneProperty());
            } else {
                TelefoneLabel.textProperty().bind(new SimpleStringProperty(""));
            }
        } catch (Exception a) {
            TelefoneLabel.textProperty().bind(new SimpleStringProperty(""));
        }
    }

    @FXML
    void OnDetalhesViewHoje() {

        cliente = AgendaView.getSelectionModel().getSelectedItem();
        try {

            CPFLabel.textProperty().bind(cliente.cpfProperty());
            CartaoSUSLabel.textProperty().bind(cliente.cartaoSUSProperty());
            NascimentoLabel.textProperty().bind(cliente.dataNascimentoProperty());
            EmailLabel.textProperty().bind(cliente.emailProperty());
            NomeLabel.textProperty().bind(cliente.nomeProperty());
            SexoLabel.textProperty().bind(cliente.sexoProperty());

        } catch (Exception ex) {
            CPFLabel.textProperty().bind(new SimpleStringProperty(""));
            CartaoSUSLabel.textProperty().bind(new SimpleStringProperty(""));
            NascimentoLabel.textProperty().bind(new SimpleStringProperty(""));
            SexoLabel.textProperty().bind(new SimpleStringProperty(""));
            EmailLabel.textProperty().bind(new SimpleStringProperty(""));
            NomeLabel.textProperty().bind(new SimpleStringProperty(""));

        }
        try {

            endereco_cliente = Filtro.Cliente_para_Endereco(cliente.getIdClient());
            System.out.println(endereco_cliente);
            if (endereco_cliente != null && endereco_cliente.getIdAddressClient() != 0) {

                TelefoneLabel.textProperty().bind(endereco_cliente.telefoneProperty());
            } else {
                TelefoneLabel.textProperty().bind(new SimpleStringProperty(""));
            }
        } catch (Exception a) {
            TelefoneLabel.textProperty().bind(new SimpleStringProperty(""));
        }
    }

    @FXML
    void OnPesquisarData() {
        if (!PesquisarField.getText().equals("")) {
            clientTableView.setItems(findItems());
        } else {
            clientTableView.setItems(data.getClientData());
        }
    }

    @FXML
    void On_OFF_ON_TopMenu() {
        if (on_off) {
            BorderTop.setMaxHeight(80);
            BorderTop.setCenter(TextoMenuAnchor);
            on_off = false;

        } else {
            BorderTop.setMaxHeight(5);
            BorderTop.setCenter(null);
            on_off = true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientTableView.setItems(data.getClientData());
        HoraAgendaColumn.setCellValueFactory(new PropertyValueFactory<>("horario"));

        cartaoSUSColumn.setCellValueFactory(new PropertyValueFactory<>("cartaoSUS"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clientTableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> Informacoes(newValue));


        btnDetalhesViewHoje.disableProperty().bind(AgendaView.getSelectionModel().selectedItemProperty().isNull());
        btnDetalhesListCenter.disableProperty().bind(clientTableView.getSelectionModel().selectedItemProperty().isNull());

        Date date = new Date();
        DataMenuTopLabel.setText(String.valueOf(new java.sql.Date(date.getTime())));
    }

    private void Informacoes(ClientModel newValue)  {
        try {
            CPFLabel.textProperty().bind(newValue.cpfProperty());
            CartaoSUSLabel.textProperty().bind(newValue.cartaoSUSProperty());
            NascimentoLabel.textProperty().bind(newValue.dataNascimentoProperty());
            EmailLabel.textProperty().bind(newValue.emailProperty());
            NomeLabel.textProperty().bind(newValue.nomeProperty());
            SexoLabel.textProperty().bind(newValue.sexoProperty());
        } catch (NullPointerException ex) {
            CPFLabel.textProperty().bind(new SimpleStringProperty(""));
            CartaoSUSLabel.textProperty().bind(new SimpleStringProperty(""));
            NascimentoLabel.textProperty().bind(new SimpleStringProperty(""));
            SexoLabel.textProperty().bind(new SimpleStringProperty(""));
            EmailLabel.textProperty().bind(new SimpleStringProperty(""));
            NomeLabel.textProperty().bind(new SimpleStringProperty(""));
        }
        try {
            endereco_cliente = Filtro.Cliente_para_Endereco(newValue.getIdClient());
            if (endereco_cliente != null && endereco_cliente.getIdAddressClient() != 0) {
                TelefoneLabel.textProperty().bind(endereco_cliente.telefoneProperty());
            } else {
                throw new NullPointerException("Sem dados");
            }
        } catch (NullPointerException e) {
            TelefoneLabel.textProperty().bind(new SimpleStringProperty(""));
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

}
