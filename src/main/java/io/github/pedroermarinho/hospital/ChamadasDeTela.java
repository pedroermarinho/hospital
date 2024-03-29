/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital;

import io.github.pedroermarinho.hospital.Controller.MenuDiretoController;
import io.github.pedroermarinho.hospital.Controller.MenuTopController;
import io.github.pedroermarinho.hospital.Controller.RegisterController;
import io.github.pedroermarinho.hospital.Controller.Settings.DataBaseController;
import io.github.pedroermarinho.hospital.Controller.Settings.MenuSettingsController;
import io.github.pedroermarinho.hospital.Controller.Settings.RegisterDataBaseController;
import io.github.pedroermarinho.hospital.Controller.Util.ErroController;
import io.github.pedroermarinho.hospital.Util.MsgErro;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;

import static io.github.pedroermarinho.hospital.Util.MsgErro.MessagemErroTela;

/**
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class ChamadasDeTela {

    /**
     *
     */
    public static Stage primeriaCena;

    /**
     *
     */
    protected BorderPane PalcoPrincipal;

    /**
     *
     */
    protected MainApp mainapp;

    public Stage getPrimeriaCena() {
        return primeriaCena;
    }

    public BorderPane getPalcoPrincipal() {
        return PalcoPrincipal;
    }

    /**
     * @param mainApp Instancia da class principal
     */
    public void setMainApp(MainApp mainApp) {
        this.mainapp = mainApp;
    }

    /**
     *
     */
    public void PalcoPrincipal() {
        try {
            JMetro jMetro = new JMetro(Style.LIGHT);
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/github/pedroermarinho/hospital/Fxml/PalcoPrincipal.fxml"));
            PalcoPrincipal = loader.load();

            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(PalcoPrincipal);
            jMetro.setScene(scene);
            primeriaCena.setScene(scene);

            primeriaCena.show();
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "PalcoPrincipal");
        }

    }

    /**
     *
     */
    public void MenuDireto() {
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/github/pedroermarinho/hospital/Fxml/MenuDireto.fxml"));
            AnchorPane personOverview = loader.load();

            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setLeft(personOverview);

            // Dá ao controlador acesso à the main app.
            MenuDiretoController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "MenuDireto");
        }

    }

    /**
     *
     */
    public void CentralText() {
        try {


            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/github/pedroermarinho/hospital/Fxml/CentralText.fxml"));
            AnchorPane personOverview = loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CentralTexto");
        }
    }



    /**
     *
     */
    public void MenuTop() {
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/github/pedroermarinho/hospital/Fxml/MenuTop.fxml"));
            AnchorPane personOverview = loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            PalcoPrincipal.setTop(personOverview);

            // Dá ao controlador acesso à the main app.
            MenuTopController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {

            MsgErro.MessagemErroTela(e, "MenuTop");
        }
    }

    /**
     *
     */
    public void Register() {
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/github/pedroermarinho/hospital/Fxml/Register.fxml"));
            AnchorPane personOverview = loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

            // Dá ao controlador acesso à the main app.
            RegisterController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Cadastros");
        }
    }





    public void Informacao() {
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/github/pedroermarinho/hospital/Fxml/Informacao.fxml"));
            AnchorPane personOverview = loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);


        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Informacao");
        }
    }
    public void MenuSettings() {
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/github/pedroermarinho/hospital/Fxml/Settings/MenuSettings.fxml"));
            AnchorPane personOverview = loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

            // Dá ao controlador acesso à the main app.
            MenuSettingsController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Menu_Configuracao");
        }
    }

    /**
     * @return AnchorPane
     */
    public AnchorPane RegisterClient() {
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/github/pedroermarinho/hospital/Fxml/Register/Client.fxml"));

            return loader.load();
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroCliente");
            return null;
        }
    }

    /**
     * @return AnchorPane
     */
    public AnchorPane SettingsDataBase() {
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/github/pedroermarinho/hospital/Fxml/Settings/SettingsDataBase.fxml"));

            return loader.load();
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Configuracao_Banco_de_Dados");
            return null;
        }
    }

    /**
     * @return AnchorPane
     */
    public AnchorPane RegisterAddressClient() {
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/github/pedroermarinho/hospital/Fxml/Register/AddressClient.fxml"));

            return loader.load();
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroEnderecoCliente");
            return null;
        }
    }

    public static void errorScreen(Exception ex) {
        try {
            JMetro jMetro = new JMetro(Style.LIGHT);
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ChamadasDeTela.class.getResource("/io/github/pedroermarinho/hospital/Fxml/Util/Erro.fxml"));
            AnchorPane page = loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Erro");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primeriaCena);
            Scene scene = new Scene(page);
            jMetro.setScene(scene);
            dialogStage.setScene(scene);

            Image image = new Image(ChamadasDeTela.class.getResource("/io/github/pedroermarinho/hospital/Icons/icon.png").toString());
            dialogStage.getIcons().add(image);
            // Define a pessoa no controller.
            ErroController controller = loader.getController();
            controller.setMainApp(ex, dialogStage);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "erro");
        }

    }

    public void About() {
        try {
            JMetro jMetro = new JMetro(Style.LIGHT);
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/github/pedroermarinho/hospital/Fxml/Util/Sobre.fxml"));
            AnchorPane page = loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Sobre");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primeiraCena);
            Scene scene = new Scene(page);
            jMetro.setScene(scene);
            dialogStage.setScene(scene);

            Image image = new Image(getClass().getResource("/io/github/pedroermarinho/hospital/Icons/icon.png").toString());
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
//            SobreController controller = loader.getController();
            //controller.setMainApp(this);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

        } catch (IOException e) {
            errorScreen(e);

        }

    }

    public void SelectDataBase() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/github/pedroermarinho/hospital/Fxml/Settings/DataBase.fxml"));
            AnchorPane anchorPane = loader.load();

            PalcoPrincipal.setCenter(anchorPane);

            DataBaseController controller = loader.getController();

            controller.setMainApp(mainapp);

        } catch (Exception e) {
            MessagemErroTela(e, "SelectBanco_de_Dados");
        }
    }

    public void RegisterDataBase() {
        try {
            JMetro jMetro = new JMetro(Style.LIGHT);
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/github/pedroermarinho/hospital/Fxml/Settings/RegisterDataBase.fxml"));
            AnchorPane page = loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de banco de dados");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primeriaCena);
            Scene scene = new Scene(page);
            jMetro.setScene(scene);
            dialogStage.setScene(scene);

            Image image = new Image(getClass().getResource("/io/github/pedroermarinho/hospital/Icons/icon.png").toString());
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            RegisterDataBaseController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();
        } catch (IOException e) {
            MessagemErroTela(e, "CadastroBancoDeDados");
        }
    }

}
