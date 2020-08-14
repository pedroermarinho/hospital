/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Controller.User;

import io.github.pedroermarinho.hospital.MainApp;
import io.github.pedroermarinho.hospital.Model.Usuario.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static io.github.pedroermarinho.hospital.Util.Filtro.Senha_Usuario;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class UsuarioController implements Initializable {
    @FXML
    private TextField usuarioText;

    @FXML
    private PasswordField senhaPassword;

//    @FXML
//    private Button btOk;

//    @FXML
//    private Circle avatarCircle;

    private Stage dialogStage;

    //    private Image img;
//
//    private ImagePattern pattern;

    private UserModel usuario = null;

    @FXML
    void ok() {

        usuario = Senha_Usuario(usuarioText.getText(), senhaPassword.getText());
        if (usuario != null) {
            dialogStage.close();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            img = new Image("http://fxexperience.com/wp-content/uploads/2012/03/FxExperienceToolsIcon-256.png");
//            pattern = new ImagePattern(img);
//            avatarCircle.setFill(pattern);
//        } catch (Exception e) {
//
//        }
    }

    public UserModel usuario() {
        return usuario;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainapp) {
        if (mainapp.getUser() != null) {
            usuarioText.setText(mainapp.getUser().getUserName());
        }
    }

}
