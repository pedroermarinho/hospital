/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Util;

import io.github.pedroermarinho.hospital.MainApp;
import io.github.pedroermarinho.hospital.Model.Cliente.AddressClientModel;
import io.github.pedroermarinho.hospital.Model.Usuario.UserModel;

import java.util.List;

/**
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class Filtro {

    public static AddressClientModel Cliente_para_Endereco(int ID_Cliente) {
        List<AddressClientModel> enderecos = AddressClientModel.all();
        AddressClientModel result = null;
        for (AddressClientModel endereco : enderecos) {
            if (endereco.getID_cliente() == ID_Cliente) {
                result = endereco;
                break;
            }
        }
        return result;
    }


    public static UserModel Senha_Usuario(String Usuario, String Senha, MainApp mainApp) {
        List<UserModel> usuarios = UserModel.all(mainApp);
        UserModel result = null;
        for (UserModel usuario : usuarios) {
            if (usuario.getUsuario().equals(Usuario) && usuario.getSenha().equals(Senha)) {
                result = usuario;
                break;
            }
        }
        return result;
    }



}
