/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller.Register;

import io.github.pedroermarinho.hospital.ChamadasDeTela;
import io.github.pedroermarinho.hospital.Controller.Util.SexoEnum;
import io.github.pedroermarinho.hospital.Dados;
import io.github.pedroermarinho.hospital.Model.Client.Address.AddressClientModel;
import io.github.pedroermarinho.hospital.Model.Client.Client.ClientModel;
import io.github.pedroermarinho.hospital.Model.Client.Contact.ContactClientModel;
import io.github.pedroermarinho.hospital.Model.Client.Reception.ReceptionClientModel;
import io.github.pedroermarinho.hospital.Util.ExceptionCustom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class ClientController implements Initializable {
    private final Dados data = new Dados();
    private ClientModel modificaoClient;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField cpfField;

    @FXML
    private TextField nomeMaeField;

    @FXML
    private DatePicker nascimentoDate;

    @FXML
    private TextField cartaoField;

    @FXML
    private ComboBox<SexoEnum> sexoBox;

    @FXML
    private TextField emailField;

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
    private TextField telefoneField;

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
            selected.delete();

            final var addressClientModel = AddressClientModel.find(selected.getIdClient());
            if (addressClientModel != null) {
                addressClientModel.delete();
            }
            final var contactClientModel = ContactClientModel.find(selected.getIdClient());
            if (contactClientModel != null) {
                contactClientModel.delete();
            }

            final var receptionClientModel = ReceptionClientModel.find(selected.getIdClient());

            if (receptionClientModel != null) {
                receptionClientModel.delete();
            }

            data.getClientData();
        }
    }

    @FXML
    void OnDetalhes() {
        On_Off_Button(true);
        modificaoClient = registrosView.getSelectionModel().getSelectedItem();
        nomeField.setText(modificaoClient.getNome());
        cpfField.setText(modificaoClient.getCpf());
        nomeMaeField.setText(modificaoClient.getMae());
        nascimentoDate.setValue(LocalDate.parse(modificaoClient.getDataNascimento()));
        cartaoField.setText(modificaoClient.getCartaoSUS());
        sexoBox.setValue(SexoEnum.valueOf(modificaoClient.getSexo().toUpperCase()));
        identidadeField.setText(modificaoClient.getIdentidade());
        final var contactClientModel = ContactClientModel.find(modificaoClient.getIdClient());

        if (contactClientModel != null) {
            emailField.setText(contactClientModel.getEmail());
            telefoneField.setText(contactClientModel.getTelefone());
        }

        final var receptionClientModel = ReceptionClientModel.find(modificaoClient.getIdClient());

        if (receptionClientModel != null) {
            atendimentoField.setText(receptionClientModel.getEspecialidade());
            recepcaoField.setText(receptionClientModel.getRecepcao());
        }
    }

    @FXML
    void OnEditar() {
        On_Off_Button(false);
        modificaoClient = registrosView.getSelectionModel().getSelectedItem();
        nomeField.setText(modificaoClient.getNome());
        cpfField.setText(modificaoClient.getCpf());
        nomeMaeField.setText(modificaoClient.getMae());
        nascimentoDate.setValue(LocalDate.parse(modificaoClient.getDataNascimento()));
        cartaoField.setText(modificaoClient.getCartaoSUS());
        sexoBox.setValue(SexoEnum.valueOf(modificaoClient.getSexo().toUpperCase()));
        identidadeField.setText(modificaoClient.getIdentidade());

        final var contactClientModel = ContactClientModel.find(modificaoClient.getIdClient());

        if (contactClientModel != null) {
            emailField.setText(contactClientModel.getEmail());
            telefoneField.setText(contactClientModel.getTelefone());
        }

        final var receptionClientModel = ReceptionClientModel.find(modificaoClient.getIdClient());

        if (receptionClientModel != null) {
            atendimentoField.setText(receptionClientModel.getEspecialidade());
            recepcaoField.setText(receptionClientModel.getRecepcao());
        }


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
        try {
            isInputValid();

            modificaoClient.setNome(nomeField.getText());
            modificaoClient.setCpf(cpfField.getText());
            modificaoClient.setMae(nomeMaeField.getText());
            modificaoClient.setDataNascimento(String.valueOf(java.sql.Date.valueOf(nascimentoDate.getValue())));
            modificaoClient.setCartaoSUS(cartaoField.getText());

            modificaoClient.setIdentidade(identidadeField.getText());
            modificaoClient.setSexo(sexoBox.getValue().getDescricao());
            final Integer id = modificaoClient.save();
            System.out.println("id criado:" + id);
            if (id != null) {


                final ContactClientModel contactClientModel = new ContactClientModel();
                contactClientModel.setIdClient(id);
                contactClientModel.setEmail(emailField.getText());
                contactClientModel.setTelefone(telefoneField.getText());
                contactClientModel.save();

                final ReceptionClientModel receptionClientModel = new ReceptionClientModel();
                receptionClientModel.setIdClient(id);
                receptionClientModel.setRecepcao(recepcaoField.getText());
                receptionClientModel.setEspecialidade(atendimentoField.getText());
                receptionClientModel.setModificationDate(java.sql.Date.valueOf(LocalDate.now()).toString());
                receptionClientModel.save();

            LimparCampo();
            data.getClientData();
            On_Off_Button(true);
            }

        } catch (ExceptionCustom exceptionCustom) {
            ChamadasDeTela.errorScreen(exceptionCustom);
        }

    }

    private void LimparCampo() {

        nomeField.setText("");
        telefoneField.setText("");
        cpfField.setText("");
        nomeMaeField.setText("");
        nascimentoDate.setValue(null);
        cartaoField.setText("");
        sexoBox.setValue(null);
        emailField.setText("");
        atendimentoField.setText("");
        recepcaoField.setText("");
        identidadeField.setText("");
        modificaoClient = null;
    }

    private void On_Off_Button(boolean es) {
        nomeField.setDisable(es);
        telefoneField.setDisable(es);
        cpfField.setDisable(es);
        nomeMaeField.setDisable(es);
        nascimentoDate.setDisable(es);
        cartaoField.setDisable(es);
        sexoBox.setDisable(es);
        emailField.setDisable(es);
        atendimentoField.setDisable(es);
        recepcaoField.setDisable(es);
        identidadeField.setDisable(es);
        bntSalva.setDisable(es);
        BtnCancelar.setDisable(es);
    }


    private void isInputValid() throws ExceptionCustom {
        if (!isValidNomeField()) {
            throw new ExceptionCustom("Nome inválido!");
        }
        if (!isValidNascimentoDate()) {
            throw new ExceptionCustom("Data de nascimento inválido!");
        }
        if (!isValidSexo()) {
            throw new ExceptionCustom("Sexo inválido!");
        }
    }

    @FXML
    boolean isValidAtendimentoField() {
        if (atendimentoField.getText() == null || atendimentoField.getText().length() == 0) {
            atendimentoField.setStyle("-fx-border-color:red");
            return false;
        } else {
            atendimentoField.setStyle("");
            return true;
        }
    }

    @FXML
    boolean isValidSexo() {
        if (sexoBox.getValue() == null || sexoBox.getValue().getDescricao().length() == 0) {
            sexoBox.setStyle("-fx-border-color:red");
            return false;
        } else {
            sexoBox.setStyle("");
            return true;
        }
    }

    @FXML
    boolean isValidCPFField() {
        if (cpfField.getText() == null || cpfField.getText().length() == 0) {

            cpfField.setStyle("-fx-border-color:red");
            return false;
        } else {
            cpfField.setStyle("");
            return true;
        }
    }

    @FXML
    boolean isValidCartaoField() {
        if (cartaoField.getText() == null || cartaoField.getText().length() == 0) {

            cartaoField.setStyle("-fx-border-color:red");
            return false;
        } else {
            cartaoField.setStyle("");
            return true;
        }
    }

    @FXML
    boolean isValidEmailField() {
        if (emailField.getText() == null || emailField.getText().length() == 0) {

            emailField.setStyle("-fx-border-color:red");
            return false;
        } else {
            emailField.setStyle("");
            return true;
        }
    }

    @FXML
    boolean isValidIdentidadeField() {
        if (identidadeField.getText() == null || identidadeField.getText().length() == 0) {
            identidadeField.setStyle("-fx-border-color:red");
            return false;
        } else {
            identidadeField.setStyle("");
            return true;
        }
    }

    @FXML
    boolean isValidNomeField() {
        if (nomeField.getText() == null || nomeField.getText().length() == 0) {
            nomeField.setStyle("-fx-border-color:red");
            return false;
        } else {
            nomeField.setStyle("");
            return true;
        }
    }

    @FXML
    boolean isValidNomeMaeField() {
        if (nomeMaeField.getText() == null || nomeMaeField.getText().length() == 0) {
            nomeMaeField.setStyle("-fx-border-color:red");
            return false;
        } else {
            nomeMaeField.setStyle("");
            return true;
        }
    }

    @FXML
    boolean isValidRecepcaoField() {
        if (recepcaoField.getText() == null || recepcaoField.getText().length() == 0) {
            recepcaoField.setStyle("-fx-border-color:red");
            return false;
        } else {
            recepcaoField.setStyle("");
            return true;
        }
    }

    @FXML
    boolean isValidTelefoneField() {
        if (telefoneField.getText() == null || telefoneField.getText().length() == 0) {
            telefoneField.setStyle("-fx-border-color:red");
            return false;
        } else {
            telefoneField.setStyle("");
            return true;

        }
    }


    @FXML
    boolean isValidNascimentoDate() {
        if (nascimentoDate.getValue() == null || nascimentoDate.getValue().toString().length() == 0) {
            nascimentoDate.setStyle("-fx-border-color:red");
            return false;
        } else {
            try {
                java.sql.Date.valueOf(nascimentoDate.getValue());
            }catch (NullPointerException e){
                nascimentoDate.setStyle("-fx-border-color:red");
                return false;
            }
            nascimentoDate.setStyle("");
            return true;
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
        sexoBox.getItems().addAll(SexoEnum.values());
        bntDetalhes.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnEditar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnDeletar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("cartaoSUS"));
        ClienteColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));


    }

}
