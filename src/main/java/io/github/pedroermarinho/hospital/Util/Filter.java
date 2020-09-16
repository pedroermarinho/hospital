/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Util;

import io.github.pedroermarinho.hospital.Model.Client.Client.ClientModel;
import io.github.pedroermarinho.hospital.Model.Client.Reception.ReceptionClientModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;


/**
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class Filter {

//    public static AddressClientModel clientForAddress(int idClient) {
//        List<AddressClientModel> addresses = AddressClientModel.all();
//        for (AddressClientModel address : addresses) {
//            if (address.getIdClient() == idClient) {
//                return  address;
//            }
//        }
//        return null;
//    }


    public static ObservableList<ClientModel> findClientsData(LocalDate date) {
        final ObservableList<ClientModel> result = FXCollections.observableArrayList();
        for (ReceptionClientModel reception : ReceptionClientModel.findData(date)) {
            ClientModel client = ClientModel.find(reception.getIdClient());
            if (client.getIdClient() != 0) {
                result.add(client);
            }
        }
        return result;
    }

}
