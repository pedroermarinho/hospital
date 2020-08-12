/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller.Cadastros;

import io.github.pedroermarinho.hospital.MainApp;
import io.github.pedroermarinho.hospital.Model.Cliente.AddressClientModel;
import io.github.pedroermarinho.hospital.Model.Cliente.ClientModel;
import io.github.pedroermarinho.hospital.Util.MsgErro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    private MainApp mainApp;
    private AddressClientModel modifição_endereco;
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
    private TextField TelefoneText;
    @FXML
    private TextField TelefoneFixoText;
    @FXML
    private TextArea ObservacaoText;
    @FXML
    private Button bntSalva;
    @FXML
    private Button BtnCancelar;
    @FXML
    private TextField PesquisaText;
    @FXML
    private Button btnPesquisar;
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

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        registrosView.setItems(this.mainApp.getDadosData().getClientData());
    }

    @FXML
    void OnPesquisar(ActionEvent event) {
        if (!PesquisarField.getText().equals("")) {
            registrosView.setItems(findItems());
        } else {
            registrosView.setItems(mainApp.getDadosData().getClientData());
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
        for (ClientModel itens : mainApp.getDadosData().getClientData()) {

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
    void OnCancelar(ActionEvent event) {
        LimparCampo();
        On_Off_Button(true);
    }

    @FXML
    void OnDetalhes(ActionEvent event) {
        On_Off_Button(true);
        cliente = registrosView.getSelectionModel().getSelectedItem();
        modifição_endereco = Cliente_para_Endereco(cliente.getIdClient());
        if (modifição_endereco != null) {

            NCasaText.setText(String.valueOf(modifição_endereco.getNumeroCasa()));
            TelefoneText.setText(modifição_endereco.getTelefone());
            TelefoneFixoText.setText(modifição_endereco.getTelefoneFixo());
            ObservacaoText.setText(modifição_endereco.getTelefoneFixo());

            RuaText.setText(modifição_endereco.getRua());
            BairroText.setText(modifição_endereco.getBairro());
            CidadeText.setText(modifição_endereco.getCidade());
            EstadoText.setText(modifição_endereco.getEstado());
            PaisText.setText(modifição_endereco.getPais());
        }
    }


    @FXML
    void OnEditar(ActionEvent event) {
        On_Off_Button(false);
        cliente = registrosView.getSelectionModel().getSelectedItem();

        modifição_endereco = Cliente_para_Endereco(cliente.getIdClient());
        if (modifição_endereco != null) {
            NCasaText.setText(String.valueOf(modifição_endereco.getNumeroCasa()));
            TelefoneText.setText(modifição_endereco.getTelefone());
            TelefoneFixoText.setText(modifição_endereco.getTelefoneFixo());
            ObservacaoText.setText(modifição_endereco.getComplemento());

            RuaText.setText(modifição_endereco.getRua());
            BairroText.setText(modifição_endereco.getBairro());
            CidadeText.setText(modifição_endereco.getCidade());
            EstadoText.setText(modifição_endereco.getEstado());
            PaisText.setText(modifição_endereco.getPais());
        }
    }

    @FXML
    void OnSalvar(ActionEvent event) {
        if (modifição_endereco == null) {
            modifição_endereco = new AddressClientModel();
        }
        if (isInputValid()) {

            modifição_endereco.setPais(PaisText.getText());
            modifição_endereco.setEstado(EstadoText.getText());
            modifição_endereco.setCidade(CidadeText.getText());
            modifição_endereco.setBairro(BairroText.getText());
            modifição_endereco.setRua(RuaText.getText());
            modifição_endereco.setNumeroCasa(Integer.parseInt(NCasaText.getText()));
            modifição_endereco.setTelefone(TelefoneText.getText());
            modifição_endereco.setTelefoneFixo(TelefoneFixoText.getText());
            modifição_endereco.setComplemento(ObservacaoText.getText());
            modifição_endereco.setIdClient(cliente.getIdClient());

            modifição_endereco.save();
            LimparCampo();
            mainApp.getDadosData().getAddressClientData();
            On_Off_Button(true);
        }

    }

    private void LimparCampo() {
        NCasaText.setText("");
        TelefoneText.setText("");
        TelefoneFixoText.setText("");
        ObservacaoText.setText("");
//
        PaisText.setText("");
        EstadoText.setText("");
        CidadeText.setText("");
        BairroText.setText("");
        RuaText.setText("");

        cliente = null;
        modifição_endereco = null;
    }


    private void On_Off_Button(boolean es) {
        modifição_endereco = null;
        NCasaText.setDisable(es);
        TelefoneText.setDisable(es);
        TelefoneFixoText.setDisable(es);
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
        System.out.println("isInputValid");
        String errorMessage = "";

        if (NCasaText.getText() == null || NCasaText.getText().length() == 0) {
            errorMessage += "Nº Casa inválido!\n";
            NCasaText.setStyle("-fx-border-color:red");
        } else {
            try {
                final var i =Integer.valueOf(NCasaText.getText());
            }catch (NumberFormatException e){
                errorMessage += "Nº Casa inválido!\n";
                NCasaText.setStyle("-fx-border-color:red");
            }
            NCasaText.setStyle("");
        }
        if (TelefoneText.getText() == null || TelefoneText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            TelefoneText.setStyle("-fx-border-color:red");
        } else {
            TelefoneText.setStyle("");
        }
        if (TelefoneFixoText.getText() == null || TelefoneFixoText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            TelefoneFixoText.setStyle("-fx-border-color:red");
        } else {
            TelefoneFixoText.setStyle("");
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
//            System.out.println(errorMessage);
//           
            return false;
        }
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
        On_Off_Button(true);
        bntDetalhes.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnEditar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        ClienteColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

    }

}
