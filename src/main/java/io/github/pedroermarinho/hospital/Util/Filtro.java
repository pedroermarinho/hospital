/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Util;

import io.github.pedroermarinho.hospital.MainApp;
import io.github.pedroermarinho.hospital.Model.Cliente.model_cliente;
import io.github.pedroermarinho.hospital.Model.Cliente.model_corpo_cliente;
import io.github.pedroermarinho.hospital.Model.Cliente.model_endereco_cliente;
import io.github.pedroermarinho.hospital.Model.Cliente.model_ficha;
import io.github.pedroermarinho.hospital.Model.Usuario.model_agenda;
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



    public static ObservableList<model_corpo_cliente> Cliente_para_Corpo(int ID_Cliente, MainApp mainApp) {
        List<model_corpo_cliente> corpos = model_corpo_cliente.all(mainApp);
        ObservableList<model_corpo_cliente> result = FXCollections.observableArrayList();
        for (model_corpo_cliente corpo : corpos) {
            if (corpo.getID_cliente() == ID_Cliente) {
                result.add(corpo);
            }
        }
        return result;
    }

    public static model_endereco_cliente Cliente_para_Endereco(int ID_Cliente, MainApp mainApp) {
        List<model_endereco_cliente> enderecos = model_endereco_cliente.all(mainApp);
        model_endereco_cliente result = null;
        for (model_endereco_cliente endereco : enderecos) {
            if (endereco.getID_cliente() == ID_Cliente) {
                result = endereco;
                break;
            }
        }
        return result;
    }


    public static ObservableList<model_ficha> Cliente_para_Ficha(int ID_Cliente, MainApp mainApp) {
        List<model_ficha> fichas = model_ficha.all(mainApp);
        ObservableList<model_ficha> result = FXCollections.observableArrayList();
        for (model_ficha ficha : fichas) {
            if (ficha.getID_cliente() == ID_Cliente) {
                result.add(ficha);
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

    public static ObservableList<model_agenda> Agenda_Data_Atual(MainApp mainApp) {
        List<model_agenda> agendas = model_agenda.all(mainApp);
        Date date = new Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        ObservableList<model_agenda> result = FXCollections.observableArrayList();
        for (model_agenda agenda : agendas) {
            if (agenda.getData().toString().equals(data.toString())) {
                result.add(agenda);
            }
        }
        return result;
    }

    public static ObservableList<model_cliente> Agenda_Cliente_Data_Atual(MainApp mainApp, LocalDate date) {
        List<model_agenda> agendas = model_agenda.all(mainApp);
        java.sql.Date data = java.sql.Date.valueOf(date);
        ObservableList<model_cliente> result = FXCollections.observableArrayList();
        model_cliente cliente;
        for (model_agenda agenda : agendas) {
            if (agenda.getData().toString().equals(data.toString())) {
                cliente = model_cliente.find(agenda.getID_cliente(), mainApp);
                result.add(cliente);
            }
        }
        return result;
    }

}
