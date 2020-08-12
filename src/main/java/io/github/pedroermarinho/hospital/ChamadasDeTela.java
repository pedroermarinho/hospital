/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital;

import io.github.pedroermarinho.hospital.Controller.*;
import io.github.pedroermarinho.hospital.Controller.Cadastros.AddressClientController;
import io.github.pedroermarinho.hospital.Controller.Cadastros.ClientController;
import io.github.pedroermarinho.hospital.Controller.Configuracao.Bancos_de_DadosController;
import io.github.pedroermarinho.hospital.Controller.Configuracao.CadastroBancoDeDadosController;
import io.github.pedroermarinho.hospital.Controller.Configuracao.Configuracao_Banco_de_DadosController;
import io.github.pedroermarinho.hospital.Controller.Configuracao.Menu_ConfiguracaoController;
import io.github.pedroermarinho.hospital.Controller.Usuario.CadastroUsuarioController;
import io.github.pedroermarinho.hospital.Controller.Usuario.UsuarioController;
import io.github.pedroermarinho.hospital.Controller.Usuario.UsuariosController;
import io.github.pedroermarinho.hospital.Controller.Util.ErroController;
import io.github.pedroermarinho.hospital.Controller.Util.SenhaController;
import io.github.pedroermarinho.hospital.Model.Configuracao_Local.DataBaseModel;
import io.github.pedroermarinho.hospital.Model.Usuario.UserModel;
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
    protected Stage primeriaCena;

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
        System.out.println("setMainApp");
        this.mainapp = mainApp;
    }

    /**
     *
     */
    public void PalcoPrincipal() {
        System.out.println("PalcoPrincipal");
        try {
            JMetro jMetro = new JMetro(Style.LIGHT);
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/PalcoPrincipal.fxml"));
            PalcoPrincipal = loader.load();

            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(PalcoPrincipal);
            jMetro.setScene(scene);
            primeriaCena.setScene(scene);

            // Dá ao controller o acesso ao main app.
            PalcoPrincipalController controller = loader.getController();
            controller.setMainApp(mainapp);

            primeriaCena.show();
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "PalcoPrincipal");
        }

    }

    /**
     *
     */
    public void MenuDireto() {
        System.out.println("MenuDireto");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/MenuDireto.fxml"));
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
    public void CentralTexto() {
        System.out.println("CentralTexto");
        try {


            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/CentralTexto.fxml"));
            AnchorPane personOverview = loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

            // Dá ao controlador acesso à the main app.
            CentralTextoController controller = loader.getController();
            controller.setMainApp(mainapp);

//            controller.setMainApp(this);
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CentralTexto");
        }
    }



    /**
     *
     */
    public void MenuTop() {
        System.out.println("MenuTop");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/MenuTop.fxml"));
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
    public void Cadastros() {
        System.out.println("Cadastros");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/Cadastro.fxml"));
            AnchorPane personOverview = loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

            // Dá ao controlador acesso à the main app.
            CadastroController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Cadastros");
        }
    }





    public void Informacao() {
        System.out.println("Informacao");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/Informacao.fxml"));
            AnchorPane personOverview = loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

            // Dá ao controlador acesso à the main app.
            InformacaoController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Informacao");
        }
    }
    public void Menu_Configuracao() {
        System.out.println("Menu_Configuracao");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/Configuracao/Menu_Configuracao.fxml"));
            AnchorPane personOverview = loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

            // Dá ao controlador acesso à the main app.
            Menu_ConfiguracaoController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Menu_Configuracao");
        }
    }

    /**
     * @return AnchorPane
     */
    public AnchorPane CadastroCliente() {
        System.out.println("CadastroCliente");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/Cadastros/Client.fxml"));
            AnchorPane personOverview = loader.load();

            // Dá ao controlador acesso à the main app.
            ClientController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroCliente");
            return null;
        }
    }

    /**
     * @return AnchorPane
     */
    public AnchorPane Configuracao_Banco_de_Dados() {
        System.out.println("Configuracao_Banco_de_Dados");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/Configuracao/Configuracao_Banco_de_Dados.fxml"));
            AnchorPane personOverview = loader.load();

            // Dá ao controlador acesso à the main app.
            Configuracao_Banco_de_DadosController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Configuracao_Banco_de_Dados");
            return null;
        }
    }

    /**
     * @return AnchorPane
     */
    public AnchorPane CadastroEnderecoCliente() {
        System.out.println("CadastroEnderecoCliente");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/Cadastros/AddressClient.fxml"));
            AnchorPane personOverview = loader.load();

            // Dá ao controlador acesso à the main app.
            AddressClientController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroEnderecoCliente");
            return null;
        }
    }

    public void Erro(Exception ex) {
        System.out.println("Erro");
        try {
            JMetro jMetro = new JMetro(Style.LIGHT);
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/Util/Erro.fxml"));
            AnchorPane page = loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Erro");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primeriaCena);
            Scene scene = new Scene(page);
            jMetro.setScene(scene);
            dialogStage.setScene(scene);

            Image image = new Image("/io/github/pedroermarinho/hospital/Icons/icon.png");
            dialogStage.getIcons().add(image);
            // Define a pessoa no controller.
            ErroController controller = loader.getController();
            controller.setMainApp(mainapp, ex);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "erro");
        }

    }

    public void sobre() {
        try {
            JMetro jMetro = new JMetro(Style.LIGHT);
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/Util/Sobre.fxml"));
            AnchorPane page = loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Sobre");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primeiraCena);
            Scene scene = new Scene(page);
            jMetro.setScene(scene);
            dialogStage.setScene(scene);

            Image image = new Image("/io/github/pedroermarinho/hospital/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
//            SobreController controller = loader.getController();
            //controller.setMainApp(this);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

        } catch (IOException e) {
            Erro(e);

        }

    }

    public boolean senha(UserModel pessoa) {
        try {
            JMetro jMetro = new JMetro(Style.LIGHT);
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/Util/Senha.fxml"));
            AnchorPane page = loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("senha");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primeiraCena);
            Scene scene = new Scene(page);
            jMetro.setScene(scene);
            dialogStage.setScene(scene);

            Image image = new Image("/io/github/pedroermarinho/hospital/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            SenhaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp, pessoa);
            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();
            return controller.senha();

        } catch (IOException e) {
            Erro(e);
            return false;

        }
    }

    public void usuarios() {
        try {

            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/Usuario/Usuarios.fxml"));
            AnchorPane page = loader.load();

            PalcoPrincipal.setCenter(page);

            // Define a pessoa no controller.
            UsuariosController controller = loader.getController();
            //controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp);

            // Mostra a janela e espera até o usuário fechar.
//            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public UserModel usuario() {
        try {
            JMetro jMetro = new JMetro(Style.LIGHT);
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/Usuario/Usuario.fxml"));
            AnchorPane page = loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Usuário");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primeiraCena);
            Scene scene = new Scene(page);
            jMetro.setScene(scene);
            dialogStage.setScene(scene);

            Image image = new Image("/io/github/pedroermarinho/hospital/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            UsuarioController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.usuario();
        } catch (IOException e) {
            Erro(e);

            return null;

        }
    }

    public UserModel CadastroUsuario() {
        try {
            JMetro jMetro = new JMetro(Style.LIGHT);
            System.out.println("CadastroUsuario");
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/Usuario/CadastroUsuario.fxml"));
            AnchorPane page = loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro Usuario");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primeiraCena);
            Scene scene = new Scene(page);
            jMetro.setScene(scene);
            dialogStage.setScene(scene);

            Image image = new Image("/io/github/pedroermarinho/hospital/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            CadastroUsuarioController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.getUsuario();
        } catch (IOException e) {
            Erro(e);

            return null;

        }
    }
    public DataBaseModel SelectBanco_de_Dados() {
        try {
            JMetro jMetro = new JMetro(Style.LIGHT);
            System.out.println("SelectBanco_de_Dados");
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/Configuracao/Bancos_de_Dados.fxml"));
            AnchorPane page = loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("SelectBanco_de_Dados");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primeriaCena);
            Scene scene = new Scene(page);
            jMetro.setScene(scene);
            dialogStage.setScene(scene);

            Image image = new Image("/io/github/pedroermarinho/hospital/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            Bancos_de_DadosController controller = loader.getController();

            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();
            return controller.getBanco_de_Dados();
        } catch (Exception e) {
            MessagemErroTela(e, "SelectBanco_de_Dados");
            return null;
        }
    }

    public void CadastroBancoDeDados() {
        try {
            JMetro jMetro = new JMetro(Style.LIGHT);
            System.out.println("CadastroBancoDeDados");
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/io/github/pedroermarinho/hospital/Fxml/Configuracao/CadastroBancoDeDados.fxml"));
            AnchorPane page = loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("CadastroBancoDeDados");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primeriaCena);
            Scene scene = new Scene(page);
            jMetro.setScene(scene);
            dialogStage.setScene(scene);

            Image image = new Image("/io/github/pedroermarinho/hospital/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            CadastroBancoDeDadosController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();
        } catch (IOException e) {
            MessagemErroTela(e, "CadastroBancoDeDados");
        }
    }

}
