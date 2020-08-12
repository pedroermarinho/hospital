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

        final String CLIENTES_STRING = "CREATE TABLE IF NOT EXISTS clientes ("
                + "  ID_cliente INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  cpf varchar(16) not null,"
                + "  nome varchar(220) not null,"
                + "  mae varchar(220),"
                + "  pai varchar(220),"
                + "  data_nascimento date not null,"
                + "  cartao_sus varchar(50) UNIQUE,"
                + "  ID_sexo INTEGER not null,"
                + "  email VARCHAR (250) ,"
                + "  foto TEXT,"
                + "  FOREIGN KEY (ID_sexo) REFERENCES sexos(ID_sexo)"
                + ""
                + ");";

//        final String ENDERECO_CLIENTE_STRING = "CREATE TABLE IF NOT EXISTS endereco_cliente ("
//                + "  ID_endereco_cliente INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
//                + "  ID_cliente INTEGER not null UNIQUE, "
//                + "  telefone varchar(21),"
//                + "  telefone_fixo varchar(21),"
//                + "  ID_cidade INTEGER not null,"
//                + "  ID_rua INTEGER,"
//                + "  ID_bairro INTEGER,"
//                + "  numero_casa INTEGER,"
//                + "  complemento TEXT,"
//                + "  FOREIGN KEY (ID_cidade) REFERENCES cidades ( ID_cidade ),"
//                + "  FOREIGN KEY (ID_rua) REFERENCES ruas ( ID_rua ),"
//                + "  FOREIGN KEY (ID_bairro) REFERENCES bairros ( ID_bairro ),"
//                + "  FOREIGN KEY (ID_cliente) REFERENCES clientes ( ID_cliente )"
//                //            + "  PRIMARY KEY(ID_endereco_cliente)"
//                + ");";



        final String USUARIO_STRING = "CREATE TABLE IF NOT EXISTS usuario("
                + "    ID_usuario INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "    Nome varchar(220) not null,"
                + "    Sobrenome varchar(220) not null,"
                + "    Senha varchar(50) not null,"
                + "    ID_sexo INTEGER not null,"
                + "    DataNascimento date,"
                + "    Usuario varchar(100) not null UNIQUE,"
                + "    Email varchar(220) not null UNIQUE,"
                + "    FOREIGN KEY (ID_sexo) REFERENCES sexos(ID_sexo) "
                + ");";



        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(CLIENTES_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.CLIENTES_STRING");
        }

        try {
            stmt = connection.prepareStatement(USUARIO_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.USUARIO_STRING");
        }

    }

}
