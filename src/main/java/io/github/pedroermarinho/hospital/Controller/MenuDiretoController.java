package io.github.pedroermarinho.hospital.Controller;

import io.github.pedroermarinho.hospital.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class MenuDiretoController implements Initializable {

    protected BorderPane PalcoPrincipal;
    private MainApp mainApp;
    @FXML
    private Button btnInicio;
    @FXML
    private Button btnAgenda;
    @FXML
    private Button btnUsuarios;
    @FXML
    private Button btnCadastros;

    @FXML
    private Button btnConfiguracao;
    @FXML
    private Button btnFecharMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {//dados a ser inicializado em conjunto com a interface
        //tablePessoa.refresh();

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.PalcoPrincipal = this.mainApp.getTelas().getPalcoPrincipal();

    }

    @FXML
    void OnCadastros(ActionEvent event) {
        this.mainApp.getTelas().Cadastros();
    }

    @FXML
    void OnUsuarios(ActionEvent event) {
        this.mainApp.getTelas().usuarios();
    }

    @FXML
    void OnConfiguracao(ActionEvent event) {
        this.mainApp.getTelas().Menu_Configuracao();
    }

    @FXML
    void OnFecharMenu(ActionEvent event) {
        this.PalcoPrincipal.setLeft(null);

    }

    @FXML
    void OnInicio(ActionEvent event) {
        this.mainApp.getTelas().CentralTexto();
    }

    @FXML
    void OnSobre(ActionEvent event) {
        this.mainApp.getTelas().sobre();
    }

}
