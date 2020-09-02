/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller.Settings;

import io.github.pedroermarinho.hospital.Dados;
import io.github.pedroermarinho.hospital.MainApp;
import io.github.pedroermarinho.hospital.Model.SettingsLocal.DataBase.DataBaseModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

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

    @FXML
    private Button btnSelecionar;

    @FXML
    private ListView<DataBaseModel> BancosView;

    @FXML
    void OnCancelar() {
        mainApp.getTelas().getPrimeriaCena().close();
    }

    @FXML
    void OnAtualizar() {
            BancosView.setItems(data.getDataBaseData());

    }

    @FXML
    void OnADD() {
        mainApp.getTelas().RegisterDataBase();
    }

    @FXML
    void OnSelecionar() {
        final DataBaseModel dataBaseModel = BancosView.getSelectionModel().getSelectedItem();
        mainApp.getTelas().getPalcoPrincipal().setCenter(null);
        mainApp.initApp(dataBaseModel);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        BancosView.setItems(data.getDataBaseData());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSelecionar.disableProperty().bind(BancosView.getSelectionModel().selectedItemProperty().isNull());

    }


}
