/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller.Settings;

import io.github.pedroermarinho.hospital.Dados;
import io.github.pedroermarinho.hospital.MainApp;
import io.github.pedroermarinho.hospital.Model.Configuracao_Local.DataBaseModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho
 * <pedro.marinho238@gmail.com & https://github.com/TECFlyingCommunity>
 */
public class DataBaseController implements Initializable {
    private final Dados data = new Dados();
    private MainApp mainApp;
    private DataBaseModel dataBaseModel;
//    @FXML
//    private Button btnPesquisar;

    @FXML
    private TextField PesquisarField;

    @FXML
    private Button btnSelecionar;

//    @FXML
//    private Button btnCancelar;

    @FXML
    private ListView<DataBaseModel> BancosView;
    private Stage dialogStage;

    @FXML
    void OnCancelar() {
        dialogStage.close();
    }

    @FXML
    void OnPesquisar() {
        if (!PesquisarField.getText().equals("")) {
            BancosView.setItems(findItems());
        } else {
            BancosView.setItems(data.getDataBaseData());
        }

    }

    @FXML
    void OnADD() {
        mainApp.getTelas().CadastroBancoDeDados();
    }

    @FXML
    void OnSelecionar() {
        dataBaseModel = BancosView.getSelectionModel().getSelectedItem();
        dialogStage.close();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        BancosView.setItems(data.getDataBaseData());

    }

    public DataBaseModel getDataBase() {
        return dataBaseModel;
    }

    private ObservableList<DataBaseModel> findItems() {
        ObservableList<DataBaseModel> itensEncontrados = FXCollections.observableArrayList();
        Integer ID;
        try {
            ID = Integer.parseInt(PesquisarField.getText());

        } catch (NumberFormatException a) {
            ID = null;

        }
        for (DataBaseModel itens : data.getDataBaseData()) {

            //itens.getID().contains(Integer.valueOf( PesquisaField.getText())
            if (ID != null) {
                if (itens.getID_banco_de_dados() == ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getHost().contains(PesquisarField.getText()) || itens.getUser().equalsIgnoreCase(PesquisarField.getText()) || itens.getDataBase().contains(PesquisarField.getText())) {
                    itensEncontrados.add(itens);

                }
            }
        }
        return itensEncontrados;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSelecionar.disableProperty().bind(BancosView.getSelectionModel().selectedItemProperty().isNull());

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
