/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller.Util;

import io.github.pedroermarinho.hospital.Util.ExceptionCustom;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class ErroController implements Initializable {

    @FXML
    public Label titleLabel;
    @FXML
    private Label errorLabel;
    private Stage dialogStage;


    public void setMainApp(Exception ex, Stage dialogStage) {
        this.dialogStage = dialogStage;
        msg(ex);
    }


    private void msg(Exception ex) {
        try {
            throw ex;
        } catch (ExceptionCustom e) {
            titleLabel.setText("Campo Inválido");
        } catch (org.sqlite.SQLiteException e) {
            switch (e.getResultCode()) {
                case SQLITE_CONSTRAINT:
                    titleLabel.setText("Cartão do SUS ja cadastrado");
                    break;
                case SQLITE_CONSTRAINT_PRIMARYKEY:
                    titleLabel.setText("Erro ao salvar o dados");
                    break;
                default:
                    titleLabel.setText(e.getResultCode().message);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            errorLabel.setText(ex.getMessage());
        }

    }

    @FXML
    private void ok() {
        dialogStage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
