/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller;

import io.github.pedroermarinho.hospital.MainApp;
import io.github.pedroermarinho.hospital.Model.Cliente.model_cliente;
import io.github.pedroermarinho.hospital.Model.Cliente.model_endereco_cliente;
import io.github.pedroermarinho.hospital.Model.Usuario.model_agenda;
import io.github.pedroermarinho.hospital.Util.Filtro;
import javafx.beans.property.SimpleStringProperty;
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

import static io.github.pedroermarinho.hospital.Util.Filtro.Agenda_Data_Atual;

//import io.github.pedroermarinho.hospital.Model.model_bairros;
//import io.github.pedroermarinho.hospital.Model.model_cidades;
//import io.github.pedroermarinho.hospital.Model.model_estado;
//import io.github.pedroermarinho.hospital.Model.model_pais;
//import io.github.pedroermarinho.hospital.Model.model_ruas;
//import com.jfoenix.controls.JFXDatePicker;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class CentralTextoController implements Initializable {

    private model_endereco_cliente endereco_cliente;
    private model_cliente cliente;
    private model_agenda agenda;

    @FXML
    private AnchorPane TextoMenuAnchor;
    @FXML
    private BorderPane BorderTop;

    @FXML
    private Button btnOFF_ON_TopMenu;

    @FXML
    private Label TituloMenuTopLabel;

    @FXML
    private Label DataMenuTopLabel;
//
//    @FXML
//    private JFXDatePicker PequisaDataPicker;

    @FXML
    private Button btnPesquisarData;

    @FXML
    private ListView<model_cliente> ClientesDataListView;

    @FXML
    private MenuItem btnDetalhesListCenter;

    @FXML
    private Label horaŁistCenterLabel;

    @FXML
    private Label ClinicaListCenterLabel;

    @FXML
    private Label UsuarioŁistCenterLabel;

    @FXML
    private TableView<model_agenda> AgendaView;

    @FXML
    private TableColumn<model_agenda, Time> HoraAgendaColumn;

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
    private Label TelefoneFixoLabel;

    @FXML
    private Label TelefoneLabel;
    private boolean on_off;
    private MainApp mainApp;

    @FXML
    void OnDetalhesListCenter(ActionEvent event) {
        cliente = ClientesDataListView.getSelectionModel().getSelectedItem();
        try {

            CPFLabel.textProperty().bind(cliente.CPFProperty());
            CartaoSUSLabel.textProperty().bind(cliente.Cartao_SUSProperty());
            NascimentoLabel.textProperty().bind(cliente.Data_NascimentoProperty());
            EmailLabel.textProperty().bind(cliente.EmailProperty());
            NomeLabel.textProperty().bind(cliente.NomeProperty());

        } catch (Exception ex) {
            CPFLabel.textProperty().bind(new SimpleStringProperty(""));
            CartaoSUSLabel.textProperty().bind(new SimpleStringProperty(""));
            NascimentoLabel.textProperty().bind(new SimpleStringProperty(""));
            SexoLabel.textProperty().bind(new SimpleStringProperty(""));
            EmailLabel.textProperty().bind(new SimpleStringProperty(""));
            NomeLabel.textProperty().bind(new SimpleStringProperty(""));

        }
        try {

            endereco_cliente = Filtro.Cliente_para_Endereco(cliente.getID_cliente(), mainApp);
            System.out.println(endereco_cliente);
            if (endereco_cliente != null && endereco_cliente.getID_Endereco_Cliente() != 0) {

                TelefoneLabel.textProperty().bind(endereco_cliente.TelefoneProperty());
                TelefoneFixoLabel.textProperty().bind(endereco_cliente.Telefone_FixoProperty());
            } else {
                TelefoneLabel.textProperty().bind(new SimpleStringProperty(""));
                TelefoneFixoLabel.textProperty().bind(new SimpleStringProperty(""));
            }
        } catch (Exception a) {
            TelefoneLabel.textProperty().bind(new SimpleStringProperty(""));
            TelefoneFixoLabel.textProperty().bind(new SimpleStringProperty(""));
        }
    }

    @FXML
    void OnDetalhesViewHoje(ActionEvent event) {

        cliente = model_cliente.find(AgendaView.getSelectionModel().getSelectedItem().getID_cliente(), mainApp);
        try {

            CPFLabel.textProperty().bind(cliente.CPFProperty());
            CartaoSUSLabel.textProperty().bind(cliente.Cartao_SUSProperty());
            NascimentoLabel.textProperty().bind(cliente.Data_NascimentoProperty());
            EmailLabel.textProperty().bind(cliente.EmailProperty());
            NomeLabel.textProperty().bind(cliente.NomeProperty());

        } catch (Exception ex) {
            CPFLabel.textProperty().bind(new SimpleStringProperty(""));
            CartaoSUSLabel.textProperty().bind(new SimpleStringProperty(""));
            NascimentoLabel.textProperty().bind(new SimpleStringProperty(""));
            SexoLabel.textProperty().bind(new SimpleStringProperty(""));
            EmailLabel.textProperty().bind(new SimpleStringProperty(""));
            NomeLabel.textProperty().bind(new SimpleStringProperty(""));

        }
        try {

            endereco_cliente = Filtro.Cliente_para_Endereco(cliente.getID_cliente(), mainApp);
            System.out.println(endereco_cliente);
            if (endereco_cliente != null && endereco_cliente.getID_Endereco_Cliente() != 0) {

                TelefoneLabel.textProperty().bind(endereco_cliente.TelefoneProperty());
                TelefoneFixoLabel.textProperty().bind(endereco_cliente.Telefone_FixoProperty());
            } else {
                TelefoneLabel.textProperty().bind(new SimpleStringProperty(""));
                TelefoneFixoLabel.textProperty().bind(new SimpleStringProperty(""));
            }
        } catch (Exception a) {
            TelefoneLabel.textProperty().bind(new SimpleStringProperty(""));
            TelefoneFixoLabel.textProperty().bind(new SimpleStringProperty(""));
        }
    }

    @FXML
    void OnPesquisarData(ActionEvent event) {
//        ClientesDataListView.setItems(Filtro.Agenda_Cliente_Data_Atual(mainApp, PequisaDataPicker.getValue()));
    }

    @FXML
    void On_OFF_ON_TopMenu(ActionEvent event) {
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

        HoraAgendaColumn.setCellValueFactory(new PropertyValueFactory<>("horario"));

        btnDetalhesViewHoje.disableProperty().bind(AgendaView.getSelectionModel().selectedItemProperty().isNull());
        btnDetalhesListCenter.disableProperty().bind(ClientesDataListView.getSelectionModel().selectedItemProperty().isNull());

        Date date = new Date();
        DataMenuTopLabel.setText(String.valueOf(new java.sql.Date(date.getTime())));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        AgendaView.setItems(Agenda_Data_Atual(this.mainApp));
    }

    private void InforListCenter() {
        horaŁistCenterLabel.setText("");
        ClinicaListCenterLabel.setText("");
        UsuarioŁistCenterLabel.setText("");
    }

}
