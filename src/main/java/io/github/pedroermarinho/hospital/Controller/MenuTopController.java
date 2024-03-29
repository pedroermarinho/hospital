/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller;

import io.github.pedroermarinho.hospital.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class MenuTopController implements Initializable {

    private MainApp mainApp;
    private boolean on_off;
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private BorderPane menuBorder;

    @FXML
    private HBox menuHBox;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnMLateral;

    @FXML
    private Button btnCadastros;

    @FXML
    private Button btnInformacoes;

//    @FXML
//    private Button btnAgenda;



    @FXML
    void OnCadastros(ActionEvent event) {
        mainApp.getTelas().Register();
    }


    @FXML
    void OnInformacoes(ActionEvent event) {
        mainApp.getTelas().Informacao();
    }

    @FXML
    void OnInicio(ActionEvent event) {
        mainApp.getTelas().CentralText();
    }

    @FXML
    void OnMLateral(ActionEvent event) {
        mainApp.getTelas().MenuDireto();
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    @FXML
    void on_off_menu(ActionEvent event) {
        if (on_off) {
            anchorpane.setPrefHeight(55);
            menuBorder.setCenter(menuHBox);
            on_off = false;

        } else {
            anchorpane.setPrefHeight(5);
            menuBorder.setCenter(null);
            on_off = true;
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
