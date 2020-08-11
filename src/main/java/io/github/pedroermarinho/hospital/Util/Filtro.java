/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Util;

import io.github.pedroermarinho.hospital.MainApp;
import io.github.pedroermarinho.hospital.Model.Cliente.model_cliente;
import io.github.pedroermarinho.hospital.Model.Cliente.model_endereco_cliente;
import io.github.pedroermarinho.hospital.Model.Usuario.model_usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class Filtro {

    public static model_endereco_cliente Cliente_para_Endereco(int ID_Cliente) {
        List<model_endereco_cliente> enderecos = model_endereco_cliente.all();
        model_endereco_cliente result = null;
        for (model_endereco_cliente endereco : enderecos) {
            if (endereco.getID_cliente() == ID_Cliente) {
                result = endereco;
                break;
            }
        }
        return result;
    }


    public static model_usuario Senha_Usuario(String Usuario, String Senha, MainApp mainApp) {
        List<model_usuario> usuarios = model_usuario.all(mainApp);
        model_usuario result = null;
        for (model_usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(Usuario) && usuario.getSenha().equals(Senha)) {
                result = usuario;
                break;
            }
        }
        return result;
    }



}
