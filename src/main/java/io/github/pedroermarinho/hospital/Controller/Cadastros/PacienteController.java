/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller.Cadastros;

import io.github.pedroermarinho.hospital.MainApp;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class PacienteController implements Initializable {

    private ClientModel modifição_cliente;

    @FXML
    private TextField NomeText;

    @FXML
    private TextField CPFText;

    @FXML
    private TextField NomeMaeText;

    @FXML
    private TextField NomePaiText;

    @FXML
    private DatePicker NascimentoDate;

    @FXML
    private TextField CartaoText;

    @FXML
    private ComboBox<String> SexoBox;

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

    @FXML
    private Button btnNovo;

    private MainApp mainApp;
    @FXML
    private TextField PesquisarField;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        registrosView.setItems(this.mainApp.getDadosData().getClientData());
//        SexoBox.setItems(this.mainApp.getDadosData().getSexosData());

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
                if (itens.getID_cliente() == ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getCPF().contains(PesquisarField.getText()) || itens.getCartao_SUS().equalsIgnoreCase(PesquisarField.getText()) || itens.getNome().contains(PesquisarField.getText())) {
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
    void OnDeletar(ActionEvent event) {
        System.out.println("DeletaPessoa");
        ClientModel selected = registrosView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.delete();
            mainApp.getDadosData().getClientData();
        } else {

        }
    }

    @FXML
    void OnDetalhes(ActionEvent event) {
        On_Off_Button(true);
        modifição_cliente = registrosView.getSelectionModel().getSelectedItem();
        NomeText.setText(modifição_cliente.getNome());
        CPFText.setText(modifição_cliente.getCPF());
        NomeMaeText.setText(modifição_cliente.getMae());
        NomePaiText.setText(modifição_cliente.getPai());
        NascimentoDate.setValue(LocalDate.parse(modifição_cliente.getData_Nascimento()));
        CartaoText.setText(modifição_cliente.getCartao_SUS());
        EmailText.setText(modifição_cliente.getEmail());
    }

    @FXML
    void OnEditar(ActionEvent event) {
        On_Off_Button(false);
        modifição_cliente = registrosView.getSelectionModel().getSelectedItem();
        NomeText.setText(modifição_cliente.getNome());
        CPFText.setText(modifição_cliente.getCPF());
        NomeMaeText.setText(modifição_cliente.getMae());
        NomePaiText.setText(modifição_cliente.getPai());
        NascimentoDate.setValue(LocalDate.parse(modifição_cliente.getData_Nascimento()));
        CartaoText.setText(modifição_cliente.getCartao_SUS());
        EmailText.setText(modifição_cliente.getEmail());

    }

    @FXML
    void OnNovo(ActionEvent event) {

        On_Off_Button(false);

        LimparCampo();
    }

    @FXML
    void OnSalvar(ActionEvent event) {
        if (modifição_cliente == null) {
            modifição_cliente = new ClientModel();
        }
        if (isInputValid()) {
//            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            modifição_cliente.setNome(NomeText.getText());
            modifição_cliente.setCPF(CPFText.getText());
            modifição_cliente.setMae(NomeMaeText.getText());
            modifição_cliente.setPai(NomePaiText.getText());
            modifição_cliente.setData_Nascimento(String.valueOf(java.sql.Date.valueOf(NascimentoDate.getValue())));
            modifição_cliente.setCartao_SUS(CartaoText.getText());
            modifição_cliente.setEmail(EmailText.getText());
            modifição_cliente.save();
            LimparCampo();
            mainApp.getDadosData().getClientData();
            On_Off_Button(true);
        }

    }

    private void LimparCampo() {

        NomeText.setText("");
        CPFText.setText("");
        NomeMaeText.setText("");
        NomePaiText.setText("");
        NascimentoDate.setValue(null);
        CartaoText.setText("");
        SexoBox.setValue(null);
        EmailText.setText("");

        modifição_cliente = null;
    }

    private void On_Off_Button(boolean es) {
        NomeText.setDisable(es);
        CPFText.setDisable(es);
        NomeMaeText.setDisable(es);
        NomePaiText.setDisable(es);
        NascimentoDate.setDisable(es);
        CartaoText.setDisable(es);
        SexoBox.setDisable(es);
        EmailText.setDisable(es);
        bntSalva.setDisable(es);
        BtnCancelar.setDisable(es);
    }

    @FXML
    void OnFoto(ActionEvent event) {

    }

    private boolean isInputValid() {
        System.out.println("isInputValid");
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
        if (NomePaiText.getText() == null || NomePaiText.getText().length() == 0) {
            errorMessage += "Nome Pai inválido!\n";
            NomePaiText.setStyle("-fx-border-color:red");
        } else {
            NomePaiText.setStyle("");
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
//            System.out.println(errorMessage);
//           
            return false;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        On_Off_Button(true);
        bntDetalhes.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnEditar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnDeletar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_cliente"));
        ClienteColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));

    }

}
