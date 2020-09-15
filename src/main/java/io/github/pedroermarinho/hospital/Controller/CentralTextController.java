/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller;

import io.github.pedroermarinho.hospital.Dados;
import io.github.pedroermarinho.hospital.Model.Client.Client.ClientModel;
import io.github.pedroermarinho.hospital.Model.Client.Contact.ContactClientModel;
import io.github.pedroermarinho.hospital.Util.Filter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class CentralTextController implements Initializable {

    private final Dados data = new Dados();

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
    private TableView<ClientModel> AgendaView;

    @FXML
    private TableColumn<ClientModel, Time> dataCartaoSUSColumn;


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





    private void info(ClientModel newValue)  {
        try {
            CPFLabel.textProperty().bind(newValue.cpfProperty());
            CartaoSUSLabel.textProperty().bind(newValue.cartaoSUSProperty());
            NascimentoLabel.textProperty().bind(newValue.dataNascimentoProperty());
            NomeLabel.textProperty().bind(newValue.nomeProperty());
            SexoLabel.textProperty().bind(newValue.sexoProperty());
        } catch (NullPointerException ex) {
            CPFLabel.textProperty().bind(new SimpleStringProperty(""));
            CartaoSUSLabel.textProperty().bind(new SimpleStringProperty(""));
            NascimentoLabel.textProperty().bind(new SimpleStringProperty(""));
            SexoLabel.textProperty().bind(new SimpleStringProperty(""));
            NomeLabel.textProperty().bind(new SimpleStringProperty(""));
        }
        try {

            ContactClientModel contactClientModel = ContactClientModel.find(newValue.getIdClient());

            if (contactClientModel != null) {
                EmailLabel.textProperty().bind(contactClientModel.emailProperty());
                TelefoneLabel.textProperty().bind(contactClientModel.telefoneProperty());
            }

        } catch (NullPointerException e) {
            EmailLabel.textProperty().bind(new SimpleStringProperty(""));
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AgendaView.setItems(Filter.findClientsData(LocalDate.now()));
        clientTableView.setItems(data.getClientData());
        dataCartaoSUSColumn.setCellValueFactory(new PropertyValueFactory<>("cartaoSUS"));
        AgendaView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> info(newValue));

        cartaoSUSColumn.setCellValueFactory(new PropertyValueFactory<>("cartaoSUS"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clientTableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> info(newValue));

        DataMenuTopLabel.setText( java.sql.Date.valueOf(LocalDate.now()).toString());
    }
}
