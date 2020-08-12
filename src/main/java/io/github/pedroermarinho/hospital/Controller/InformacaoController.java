/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller;

import io.github.pedroermarinho.hospital.MainApp;
import io.github.pedroermarinho.hospital.Model.Cliente.ClientModel;
import io.github.pedroermarinho.hospital.Model.Cliente.AddressClientModel;
import io.github.pedroermarinho.hospital.Util.Filtro;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class InformacaoController implements Initializable {

    private MainApp mainApp;
    private AddressClientModel endereco_cliente;


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
    private Label PaiLabel;

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

    @FXML
    private Label TelefoneFixoLabel;



    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        RegistroClientesView.setItems(this.mainApp.getDadosData().getClientData());
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        IDClienteColumn.setCellValueFactory(new PropertyValueFactory<>("ID_cliente"));
        NomeClienteColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        RegistroClientesView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> Informacoes(newValue));
    }

    private void Informacoes(ClientModel newValue) {
        try {

            CPFLabel.textProperty().bind(newValue.CPFProperty());
            CartaoSUSLabel.textProperty().bind(newValue.Cartao_SUSProperty());
            NascimentoLabel.textProperty().bind(newValue.Data_NascimentoProperty());
            MaeLabel.textProperty().bind(newValue.MaeProperty());
            EmailLabel.textProperty().bind(newValue.EmailProperty());
            NomeLabel.textProperty().bind(newValue.NomeProperty());
            PaiLabel.textProperty().bind(newValue.PaiProperty());

        } catch (Exception ex) {
            CPFLabel.textProperty().bind(new SimpleStringProperty(""));
            CartaoSUSLabel.textProperty().bind(new SimpleStringProperty(""));
            NascimentoLabel.textProperty().bind(new SimpleStringProperty(""));
            SexoLabel.textProperty().bind(new SimpleStringProperty(""));
            MaeLabel.textProperty().bind(new SimpleStringProperty(""));
            EmailLabel.textProperty().bind(new SimpleStringProperty(""));
            NomeLabel.textProperty().bind(new SimpleStringProperty(""));
            PaiLabel.textProperty().bind(new SimpleStringProperty(""));

        }
        try {

            endereco_cliente = Filtro.Cliente_para_Endereco(newValue.getID_cliente());
            System.out.println(endereco_cliente);
            if (endereco_cliente != null && endereco_cliente.getID_Endereco_Cliente() != 0) {
                System.out.println("ok");


                NCasaLabel.textProperty().bind(endereco_cliente.Numero_CasaProperty().asString());
                TelefoneLabel.textProperty().bind(endereco_cliente.TelefoneProperty());
                TelefoneFixoLabel.textProperty().bind(endereco_cliente.Telefone_FixoProperty());
            } else {
                RuaLabel.textProperty().bind(new SimpleStringProperty(""));
                BairroLabel.textProperty().bind(new SimpleStringProperty(""));
                CidadeLabel.textProperty().bind(new SimpleStringProperty(""));
                EstadoLabel.textProperty().bind(new SimpleStringProperty(""));
                PaisLabel.textProperty().bind(new SimpleStringProperty(""));

                NCasaLabel.textProperty().bind(new SimpleStringProperty(""));
                TelefoneLabel.textProperty().bind(new SimpleStringProperty(""));
                TelefoneFixoLabel.textProperty().bind(new SimpleStringProperty(""));
            }
        } catch (Exception a) {
            RuaLabel.textProperty().bind(new SimpleStringProperty(""));
            BairroLabel.textProperty().bind(new SimpleStringProperty(""));
            CidadeLabel.textProperty().bind(new SimpleStringProperty(""));
            EstadoLabel.textProperty().bind(new SimpleStringProperty(""));
            PaisLabel.textProperty().bind(new SimpleStringProperty(""));

            NCasaLabel.textProperty().bind(new SimpleStringProperty(""));
            TelefoneLabel.textProperty().bind(new SimpleStringProperty(""));
            TelefoneFixoLabel.textProperty().bind(new SimpleStringProperty(""));
        }

    }



}
