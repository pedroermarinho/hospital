/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Util.BD;

import io.github.pedroermarinho.hospital.Model.SettingsLocal.DataBase.DataBaseModel;
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

        final String CLIENTS_SQL = "CREATE TABLE IF NOT EXISTS client ("
                + "  id_client INTEGER PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  cpf varchar(16) UNIQUE,"
                + "  cartao_sus varchar(50) UNIQUE,"
                + "  identidade varchar(50),"
                + "  nome varchar(220),"
                + "  mae varchar(220),"
                + "  data_nascimento date,"
                + "  sexo varchar(50)"
                + ");";

        final String CONTACT_CLIENT_SQL = "CREATE TABLE IF NOT EXISTS contact_client ("
                + "  id_contact_client INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  id_client INTEGER not null UNIQUE, "
                + "  email VARCHAR (250),"
                + "  telefone varchar(21),"
                + "  FOREIGN KEY (id_client) REFERENCES client ( id_client )"
                + ");";

        final String RECEPTION_CLIENT_SQL = "CREATE TABLE IF NOT EXISTS reception_client ("
                + "  id_reception_client INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  id_client INTEGER not null, "
                + "  especialidade VARCHAR (250),"
                + "  recepcao VARCHAR (250),"
                + "  modification_date date,"
                + "  FOREIGN KEY (id_client) REFERENCES client ( id_client )"
                + ");";

        final String ADDRESS_CLIENT_SQL = "CREATE TABLE IF NOT EXISTS address_client ("
                + "  id_address_client INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  id_client INTEGER not null UNIQUE, "
                + "  pais varchar(30),"
                + "  estado varchar(30),"
                + "  cidade varchar(30),"
                + "  rua varchar(200),"
                + "  bairro varchar(200),"
                + "  numero_casa varchar(30),"
                + "  complemento TEXT,"
                + "  FOREIGN KEY (id_client) REFERENCES client ( id_client )"
                + ");";


        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(CLIENTS_SQL);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.CLIENTS_SQL");
        }

        try {
            stmt = connection.prepareStatement(CONTACT_CLIENT_SQL);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.CONTACT_CLIENT_SQL");
        }

        try {
            stmt = connection.prepareStatement(RECEPTION_CLIENT_SQL);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.RECEPTION_CLIENT_SQL");
        }

        try {
            stmt = connection.prepareStatement(ADDRESS_CLIENT_SQL);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.ADDRESS_CLIENT_SQL");
        }
    }

}
