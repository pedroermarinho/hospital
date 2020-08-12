package io.github.pedroermarinho.hospital.Controller;

import io.github.pedroermarinho.hospital.MainApp;
import io.github.pedroermarinho.hospital.Util.MsgErro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class CadastroController {

    private MainApp mainApp;
    private boolean on_off;
    @FXML
    private BorderPane Border;
    @FXML
    private BorderPane TelaCadastrosBorderPane;
    @FXML
    private VBox menuVBox;
    @FXML
    private Button btnEndereco;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @FXML
    void OnCliente(ActionEvent event) {
        try {
            TelaCadastrosBorderPane.setCenter(mainApp.getTelas().CadastroCliente());
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "OnCliente");
        }
    }

    @FXML
    void On_OFF_ON(ActionEvent event) {
        if (on_off) {
            Border.setPrefWidth(200);
            Border.setCenter(menuVBox);
            on_off = false;

        } else {
            Border.setPrefWidth(5);
            Border.setCenter(null);
            on_off = true;
        }
    }

    @FXML
    void OnEndereco(ActionEvent event) {
        try {
            TelaCadastrosBorderPane.setCenter(mainApp.getTelas().CadastroEnderecoCliente());
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "OnEndereco");
        }
    }
}
