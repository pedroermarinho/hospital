/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller.Register;

import io.github.pedroermarinho.hospital.Controller.Util.SexoEnum;
import io.github.pedroermarinho.hospital.Dados;
import io.github.pedroermarinho.hospital.Model.Client.Client.ClientModel;
import io.github.pedroermarinho.hospital.Util.MsgErro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static io.github.pedroermarinho.hospital.Util.Filtro.Cliente_para_Endereco;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class ClientController implements Initializable {
    private final Dados data = new Dados();
    private ClientModel modificaoClient;

    @FXML
    private TextField NomeText;

    @FXML
    private TextField CPFText;

    @FXML
    private TextField NomeMaeText;

    @FXML
    private DatePicker NascimentoDate;

    @FXML
    private TextField CartaoText;

    @FXML
    private ComboBox<SexoEnum> SexoBox;

    @FXML
    private TextField EmailText;

    @FXML
    private Button bntSalva;

    @FXML
    private Button BtnCancelar;

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
    private Button btnDeletar;

//    @FXML
//    private Button btnNovo;

    @FXML
    private TextField atendimentoField;

    @FXML
    private TextField recepcaoField;

    @FXML
    private TextField identidadeField;


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
    void OnDeletar() {
        ClientModel selected = registrosView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            final var addressClientModel = Cliente_para_Endereco(selected.getIdClient());
            if (addressClientModel != null) {
                addressClientModel.delete();
            }
            selected.delete();
            data.getClientData();
        }
    }

    @FXML
    void OnDetalhes() {
        On_Off_Button(true);
        modificaoClient = registrosView.getSelectionModel().getSelectedItem();
        NomeText.setText(modificaoClient.getNome());
        CPFText.setText(modificaoClient.getCpf());
        NomeMaeText.setText(modificaoClient.getMae());
        NascimentoDate.setValue(LocalDate.parse(modificaoClient.getDataNascimento()));
        CartaoText.setText(modificaoClient.getCartaoSUS());
//        EmailText.setText(modificaoClient.getEmail());
        SexoBox.setValue(SexoEnum.valueOf(modificaoClient.getSexo().toUpperCase()));
//        atendimentoField.setText(modificaoClient.getEspecialidade());
//        recepcaoField.setText(modificaoClient.getRecepcao());
        identidadeField.setText(modificaoClient.getIdentidade());
    }

    @FXML
    void OnEditar() {
        On_Off_Button(false);
        modificaoClient = registrosView.getSelectionModel().getSelectedItem();
        NomeText.setText(modificaoClient.getNome());
        CPFText.setText(modificaoClient.getCpf());
        NomeMaeText.setText(modificaoClient.getMae());
        NascimentoDate.setValue(LocalDate.parse(modificaoClient.getDataNascimento()));
        CartaoText.setText(modificaoClient.getCartaoSUS());
//        EmailText.setText(modificaoClient.getEmail());
        SexoBox.setValue(SexoEnum.valueOf(modificaoClient.getSexo().toUpperCase()));
//        atendimentoField.setText(modificaoClient.getEspecialidade());
//        recepcaoField.setText(modificaoClient.getRecepcao());
        identidadeField.setText(modificaoClient.getIdentidade());
    }

    @FXML
    void OnNovo() {

        On_Off_Button(false);

        LimparCampo();
    }

    @FXML
    void OnSalvar() {
        if (modificaoClient == null) {
            modificaoClient = new ClientModel();
        }
        if (isInputValid()) {
//            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            modificaoClient.setNome(NomeText.getText());
            modificaoClient.setCpf(CPFText.getText());
            modificaoClient.setMae(NomeMaeText.getText());
            modificaoClient.setDataNascimento(String.valueOf(java.sql.Date.valueOf(NascimentoDate.getValue())));
            modificaoClient.setCartaoSUS(CartaoText.getText());
//            modificaoClient.setEmail(EmailText.getText());
            modificaoClient.setIdentidade(identidadeField.getText());
//            modificaoClient.setRecepcao(recepcaoField.getText());
//            modificaoClient.setEspecialidade(atendimentoField.getText());
            modificaoClient.setSexo(SexoBox.getValue().getDescricao());
            modificaoClient.save();
            LimparCampo();
            data.getClientData();
            On_Off_Button(true);
        }

    }

    private void LimparCampo() {

        NomeText.setText("");
        CPFText.setText("");
        NomeMaeText.setText("");
        NascimentoDate.setValue(null);
        CartaoText.setText("");
        SexoBox.setValue(null);
        EmailText.setText("");
        atendimentoField.setText("");
        recepcaoField.setText("");
        identidadeField.setText("");
        modificaoClient = null;
    }

    private void On_Off_Button(boolean es) {
        NomeText.setDisable(es);
        CPFText.setDisable(es);
        NomeMaeText.setDisable(es);
        NascimentoDate.setDisable(es);
        CartaoText.setDisable(es);
        SexoBox.setDisable(es);
        EmailText.setDisable(es);
        atendimentoField.setDisable(es);
        recepcaoField.setDisable(es);
        identidadeField.setDisable(es);
        bntSalva.setDisable(es);
        BtnCancelar.setDisable(es);
    }


    private boolean isInputValid() {
        String errorMessage = "";

        if (NomeText.getText() == null || NomeText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            NomeText.setStyle("-fx-border-color:red");
        } else {
            NomeText.setStyle("");
        }
        if (CPFText.getText() == null || CPFText.getText().length() == 0) {
            errorMessage += "CPF inválido!\n";
            CPFText.setStyle("-fx-border-color:red");
        } else {
            CPFText.setStyle("");
        }
        if (NomeMaeText.getText() == null || NomeMaeText.getText().length() == 0) {
            errorMessage += "Nome Mãe inválido!\n";
            NomeMaeText.setStyle("-fx-border-color:red");
        } else {
            NomeMaeText.setStyle("");
        }
        if (CartaoText.getText() == null || CartaoText.getText().length() == 0) {
            errorMessage += "Cartão SUS inválido!\n";
            CartaoText.setStyle("-fx-border-color:red");
        } else {
            CartaoText.setStyle("");
        }
        if (EmailText.getText() == null || EmailText.getText().length() == 0) {
            errorMessage += "Email inválido!\n";
            EmailText.setStyle("-fx-border-color:red");
        } else {
            EmailText.setStyle("");
        }
        if (atendimentoField.getText() == null || atendimentoField.getText().length() == 0) {
            errorMessage += "Email inválido!\n";
            atendimentoField.setStyle("-fx-border-color:red");
        } else {
            atendimentoField.setStyle("");
        }
        if (recepcaoField.getText() == null || recepcaoField.getText().length() == 0) {
            errorMessage += "Email inválido!\n";
            recepcaoField.setStyle("-fx-border-color:red");
        } else {
            recepcaoField.setStyle("");
        }
        if (identidadeField.getText() == null || identidadeField.getText().length() == 0) {
            errorMessage += "Email inválido!\n";
            identidadeField.setStyle("-fx-border-color:red");
        } else {
            identidadeField.setStyle("");
        }
        if (NascimentoDate.getValue() == null || NascimentoDate.getValue().toString().length() == 0) {
            errorMessage += "Data inválido!\n";
            NascimentoDate.setStyle("-fx-border-color:red");
        } else {
            NascimentoDate.setStyle("");
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            MsgErro.MessagemErroFormulario(errorMessage);

            return false;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        registrosView.setItems(data.getClientData());
        On_Off_Button(true);
        SexoBox.getItems().addAll(SexoEnum.values());
        bntDetalhes.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnEditar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnDeletar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        ClienteColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

    }

}
