/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller;

import io.github.pedroermarinho.hospital.ChamadasDeTela;
import io.github.pedroermarinho.hospital.Dados;
import io.github.pedroermarinho.hospital.Model.Client.Address.AddressClientModel;
import io.github.pedroermarinho.hospital.Model.Client.Client.ClientModel;
import io.github.pedroermarinho.hospital.Model.Client.Contact.ContactClientModel;
import io.github.pedroermarinho.hospital.Model.Client.Reception.ReceptionClientModel;
import io.github.pedroermarinho.hospital.Util.PDF;
import io.github.pedroermarinho.hospital.Util.Filter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
    @FXML
    private Button btnGerarPDF;

    @FXML
    private ToggleButton allToggle;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnGerarPDF.disableProperty().bind(RegistroClientesView.getSelectionModel().selectedItemProperty().isNull());
        RegistroClientesView.setItems(Filter.findClientsData(LocalDate.now()));
        IDClienteColumn.setCellValueFactory(new PropertyValueFactory<>("cartaoSUS"));
        NomeClienteColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        RegistroClientesView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> info(newValue));
        allToggle.selectedProperty().addListener((Observable, oldValue, newValue) -> all(newValue));
    }
    private void all(boolean isSelect) {
        if(isSelect){
            RegistroClientesView.setItems(data.getClientData());
        }else{
            RegistroClientesView.setItems(Filter.findClientsData(LocalDate.now()));
        }
    }

    @FXML
    void onGerarPDF() {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Salvar em");
        File file = directoryChooser.showDialog(ChamadasDeTela.primeriaCena);
        try {
            PDF.createPDF(file,RegistroClientesView.getSelectionModel().getSelectedItem());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void info(ClientModel newValue) {

        CPFLabel.textProperty().bind(newValue.cpfProperty());
        CartaoSUSLabel.textProperty().bind(newValue.cartaoSUSProperty());
        NascimentoLabel.textProperty().bind(newValue.dataNascimentoProperty());
        MaeLabel.textProperty().bind(newValue.maeProperty());

        NomeLabel.textProperty().bind(newValue.nomeProperty());
        SexoLabel.textProperty().bind(newValue.sexoProperty());
        identidadeLabel.textProperty().bind(newValue.identidadeProperty());


        ContactClientModel contactClientModel = ContactClientModel.find(newValue.getIdClient());

        if (contactClientModel != null) {
            EmailLabel.textProperty().bind(contactClientModel.emailProperty());
            TelefoneLabel.textProperty().bind(contactClientModel.telefoneProperty());
        }

        ReceptionClientModel receptionClientModel = ReceptionClientModel.find(newValue.getIdClient());

        if (receptionClientModel != null) {
            especialidadeLabel.textProperty().bind(receptionClientModel.especialidadeProperty());
            recepcaoLabel.textProperty().bind(receptionClientModel.recepcaoProperty());
        }

        AddressClientModel addressClientModel = AddressClientModel.find(newValue.getIdClient());
        if (addressClientModel != null) {
            RuaLabel.textProperty().bind(addressClientModel.ruaProperty());
            BairroLabel.textProperty().bind(addressClientModel.bairroProperty());
            CidadeLabel.textProperty().bind(addressClientModel.cidadeProperty());
            EstadoLabel.textProperty().bind(addressClientModel.estadoProperty());
            PaisLabel.textProperty().bind(addressClientModel.paisProperty());
            NCasaLabel.textProperty().bind(addressClientModel.numeroCasaProperty());

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
