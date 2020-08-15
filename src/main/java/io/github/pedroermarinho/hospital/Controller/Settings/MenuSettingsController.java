/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller.Settings;

import io.github.pedroermarinho.hospital.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class MenuSettingsController implements Initializable {

    private MainApp mainApp;
    //    @FXML
//    private Button btnBancoDeDados;
    @FXML
    private BorderPane Border;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void OnBancoDeDados() {
        Border.setCenter(mainApp.getTelas().Configuracao_Banco_de_Dados());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
