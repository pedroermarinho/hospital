/*
 * To change MainApp license header, choose License Headers in Project Properties.
 * To change MainApp template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital;

import io.github.pedroermarinho.hospital.Model.Cliente.ClientModel;
import io.github.pedroermarinho.hospital.Model.Cliente.AddressClientModel;
import io.github.pedroermarinho.hospital.Model.Configuracao_Local.DataBaseModel;

import io.github.pedroermarinho.hospital.Model.Usuario.UserModel;
import io.github.pedroermarinho.hospital.Util.MsgErro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * @author Pedro Marinho
 * <pedro.marinho238@gmail.com & https://github.com/TECFlyingCommunity>
 */
public class Dados {

    private final ObservableList<ClientModel> ClientesData = FXCollections.observableArrayList();
    private final ObservableList<AddressClientModel> EnderecoClienteData = FXCollections.observableArrayList();

    private final ObservableList<DataBaseModel> Bancos_de_DadosData = FXCollections.observableArrayList();
    private final ObservableList<UserModel> UsuariosData = FXCollections.observableArrayList();
    public Thread SincronizarBD_Thread;
    private final MainApp MainApp;

    public Dados(MainApp MainApp) {
        this.MainApp = MainApp;
    }

    public ObservableList<DataBaseModel> getDataBaseData() {
        List<DataBaseModel> ObjData = DataBaseModel.all();
        if (ObjData != null) {
            Bancos_de_DadosData.clear();
            Bancos_de_DadosData.setAll(ObjData);
        }
        return Bancos_de_DadosData;
    }

    public ObservableList<UserModel> getUserData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            userDataNotThread();
        } else {
            new Thread(this::userDataNotThread).start();
        }
        return UsuariosData;
    }



    public ObservableList<ClientModel> getClientData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            clientDataNotThread();
        } else {
            new Thread(this::clientDataNotThread).start();
        }
        return ClientesData;
    }


    public void getAddressClientData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            addressClientDataNotThread();
        } else {
            new Thread(this::addressClientDataNotThread).start();
        }
    }





    public ObservableList<UserModel> userDataNotThread() {

        List<UserModel> ObjData = UserModel.all(MainApp);
        if (ObjData != null) {
            UsuariosData.clear();
            UsuariosData.setAll(ObjData);
        }

        return UsuariosData;
    }





    public void clientDataNotThread() {

        List<ClientModel> ObjData = ClientModel.all();
        if (ObjData != null) {
            ClientesData.clear();
            ClientesData.setAll(ObjData);
        }

    }


    public void addressClientDataNotThread() {

        List<AddressClientModel> ObjData = AddressClientModel.all();
        if (ObjData != null) {
            EnderecoClienteData.clear();
            EnderecoClienteData.setAll(ObjData);
        }

    }

    public void sincronizarBD() {
        try {
            if (!MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
                System.out.println("not jdbc:sqlite: ");
                SincronizarBD_Thread = new Thread(() -> {
                    while (true) {
                        System.out.println("SincronizarBD");

                        getClientData();
                        getAddressClientData();
                        getUserData();

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
