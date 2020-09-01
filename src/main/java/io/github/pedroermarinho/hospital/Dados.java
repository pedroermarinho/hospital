/*
 * To change MainApp license header, choose License Headers in Project Properties.
 * To change MainApp template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital;

import io.github.pedroermarinho.hospital.Model.Cliente.Address.AddressClientModel;
import io.github.pedroermarinho.hospital.Model.Cliente.Client.ClientModel;
import io.github.pedroermarinho.hospital.Model.Configuracao_Local.DataBaseModel;
import io.github.pedroermarinho.hospital.Model.Usuario.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Pedro Marinho
 * <pedro.marinho238@gmail.com & https://github.com/TECFlyingCommunity>
 */
public class Dados {


    final ObservableList<DataBaseModel> dataBase = FXCollections.observableArrayList();

    public ObservableList<DataBaseModel> getDataBaseData() {
        final var data = DataBaseModel.all();
        if (data != null) {
            dataBase.clear();
            dataBase.setAll(data);
        }
        return dataBase;
    }

    final ObservableList<UserModel> userData = FXCollections.observableArrayList();

    public ObservableList<UserModel> getUserData() {
        final var data = UserModel.all();
        if (data != null) {
            userData.clear();
            userData.setAll(data);
        }
        return userData;
    }


    final ObservableList<ClientModel> clientData = FXCollections.observableArrayList();

    public ObservableList<ClientModel> getClientData() {
        final var data = ClientModel.all();
        if (data != null) {
            clientData.clear();
            clientData.setAll(data);
        }
        return clientData;
    }

    final ObservableList<AddressClientModel> addressClientData = FXCollections.observableArrayList();

    public ObservableList<AddressClientModel> getAddressClientData() {

        final var data = AddressClientModel.all();
        if (data != null) {
            addressClientData.clear();
            addressClientData.setAll(data);
        }
        return addressClientData;
    }


}
