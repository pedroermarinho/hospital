/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Util;

import io.github.pedroermarinho.hospital.Model.Client.Address.AddressClientModel;

import java.util.List;

/**
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class Filtro {

    public static AddressClientModel Cliente_para_Endereco(int idClient) {
        List<AddressClientModel> enderecos = AddressClientModel.all();
        for (AddressClientModel endereco : enderecos) {
            if (endereco.getIdClient() == idClient) {
                return  endereco;
            }
        }
        return null;
    }
}
