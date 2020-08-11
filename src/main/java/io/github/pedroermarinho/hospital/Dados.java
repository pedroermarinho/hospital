/*
 * To change MainApp license header, choose License Headers in Project Properties.
 * To change MainApp template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital;

import io.github.pedroermarinho.hospital.Model.Cliente.model_cliente;
import io.github.pedroermarinho.hospital.Model.Cliente.model_endereco_cliente;
import io.github.pedroermarinho.hospital.Model.Configuracao_Local.DataBaseModel;

import io.github.pedroermarinho.hospital.Model.Usuario.model_usuario;
import io.github.pedroermarinho.hospital.Util.MsgErro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * @author Pedro Marinho
 * <pedro.marinho238@gmail.com & https://github.com/TECFlyingCommunity>
 */
public class Dados {

    private final ObservableList<model_cliente> ClientesData = FXCollections.observableArrayList();
    private final ObservableList<model_endereco_cliente> EnderecoClienteData = FXCollections.observableArrayList();

    private final ObservableList<DataBaseModel> Bancos_de_DadosData = FXCollections.observableArrayList();
    private final ObservableList<model_usuario> UsuariosData = FXCollections.observableArrayList();
    public Thread SincronizarBD_Thread;
    private final MainApp MainApp;

    public Dados(MainApp MainApp) {
        this.MainApp = MainApp;
    }

    public ObservableList<DataBaseModel> getBancos_de_DadosData() {
        List<DataBaseModel> ObjData = DataBaseModel.all();
        if (ObjData != null) {
            Bancos_de_DadosData.clear();
            Bancos_de_DadosData.setAll(ObjData);
        }
        return Bancos_de_DadosData;
    }

    public ObservableList<model_usuario> getUsuariosData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            UsuariosDataNotThread();
        } else {
            new Thread(() -> {
                UsuariosDataNotThread();
            }).start();
        }
        return UsuariosData;
    }



    public ObservableList<model_cliente> getClientesData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            ClientesDataNotThread();
        } else {
            new Thread(() -> {
                ClientesDataNotThread();
            }).start();
        }
        return ClientesData;
    }


    public ObservableList<model_endereco_cliente> getEnderecoClienteData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            EnderecoClienteDataNotThread();
        } else {
            new Thread(() -> {
                EnderecoClienteDataNotThread();
            }).start();
        }
        return EnderecoClienteData;
    }





    public ObservableList<model_usuario> UsuariosDataNotThread() {

        List<model_usuario> ObjData = model_usuario.all(MainApp);
        if (ObjData != null) {
            UsuariosData.clear();
            UsuariosData.setAll(ObjData);
        }

        return UsuariosData;
    }





    public ObservableList<model_cliente> ClientesDataNotThread() {

        List<model_cliente> ObjData = model_cliente.all();
        if (ObjData != null) {
            ClientesData.clear();
            ClientesData.setAll(ObjData);
        }

        return ClientesData;
    }


    public ObservableList<model_endereco_cliente> EnderecoClienteDataNotThread() {

        List<model_endereco_cliente> ObjData = model_endereco_cliente.all();
        if (ObjData != null) {
            EnderecoClienteData.clear();
            EnderecoClienteData.setAll(ObjData);
        }

        return EnderecoClienteData;
    }

    public void SincronizarBD() {
        try {
            if (!MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
                System.out.println("not jdbc:sqlite: ");
                SincronizarBD_Thread = new Thread(() -> {
                    while (true) {
                        System.out.println("SincronizarBD");

                        getClientesData();
                        getEnderecoClienteData();
                        getUsuariosData();

                        if (!MainApp.getTelas().primeriaCena.isShowing()) {
                            SincronizarBD_Thread.stop();
                        }
                        try {
                            Thread.sleep(60000);
                        } catch (InterruptedException ex) {
                            System.err.println("SincronizarBD");
                        }
                    }
                });

                SincronizarBD_Thread.start();
            }
        } catch (Exception e) {
            MsgErro.MessagemErroBD(e, "SincronizarBD");
        }
    }

}
