/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Util.BD;

import io.github.pedroermarinho.hospital.Model.Configuracao_Local.DataBaseModel;
import io.github.pedroermarinho.hospital.Util.MsgErro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Pedro Marinho
 * <pedro.marinho238@gmail.com & https://github.com/pedroermarinho>
 */
public class Tables {

    public static void createTable(Connection connection, DataBaseModel db) {

        String AUTO_INCREMENT = "AUTO_INCREMENT";
        if (db.getPrefix().equals("jdbc:sqlite:")) {
            AUTO_INCREMENT = "AUTOINCREMENT";
        } else if (db.getPrefix().equals("jdbc:mysql:")) {
            AUTO_INCREMENT = "AUTO_INCREMENT";
        }
        System.out.println(AUTO_INCREMENT);

        final String CLIENTES_STRING = "CREATE TABLE IF NOT EXISTS client ("
                + "  id_client INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  cpf varchar(16) not null,"
                + "  nome varchar(220) not null,"
                + "  mae varchar(220),"
                + "  pai varchar(220),"
                + "  data_nascimento date,"
                + "  cartao_sus varchar(50) UNIQUE,"
                + "  sexo varchar(50),"
                + "  email VARCHAR (250) ,"
                + "  foto TEXT"
                + ");";

        final String ADDRESS_CLIENT_STRING = "CREATE TABLE IF NOT EXISTS address_client ("
                + "  id_address_client INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  id_client INTEGER not null UNIQUE, "
                + "  telefone varchar(21),"
                + "  telefone_fixo varchar(21),"
                + "  pais varchar(30),"
                + "  estado varchar(30),"
                + "  cidade varchar(30),"
                + "  rua varchar(200),"
                + "  bairro varchar(200),"
                + "  numero_casa INTEGER,"
                + "  complemento TEXT,"
                + "  FOREIGN KEY (id_client) REFERENCES client ( id_client )"
                + ");";



        final String USER_STRING = "CREATE TABLE IF NOT EXISTS user("
                + "    id_user INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "    nome varchar(220) not null,"
                + "    sobrenome varchar(220) not null,"
                + "    senha varchar(50) not null,"
                + "    sexo varchar(50),"
                + "    data_nascimento date,"
                + "    user_name varchar(100) not null UNIQUE,"
                + "    email varchar(220) not null UNIQUE"
                + ");";



        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(CLIENTES_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.CLIENTES_STRING");
        }

        try {
            stmt = connection.prepareStatement(USER_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.USUARIO_STRING");
        }

        try {
            stmt = connection.prepareStatement(ADDRESS_CLIENT_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.USUARIO_STRING");
        }

    }

}
