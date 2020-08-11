/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Model.Configuracao_Local.Dao_Configuracao;

import io.github.pedroermarinho.hospital.Model.Configuracao_Local.DataBaseModel;
import io.github.pedroermarinho.hospital.Util.BD.Banco_de_Dados_Configuracao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.github.pedroermarinho.hospital.Util.MsgErro.MessagemErroBD;

/**
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class DataBaseDAO extends Banco_de_Dados_Configuracao {

    private PreparedStatement stmt;

    public DataBaseDAO() {
        try {
            stmt = conexao.prepareStatement("CREATE TABLE IF NOT EXISTS dados_db ("
                    + " ID_banco_de_dados INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " Hosts VARCHAR(100) NOT NULL,"
                    + " Users VARCHAR(100) NOT NULL,"
                    + " Passwords VARCHAR(100),"
                    + " DataBases VARCHAR(100) NOT NULL,"
                    + " Prefixs VARCHAR(100) NOT NULL,"
                    + " Ports INT VARCHAR NULL"
                    + " );");

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DataBaseModel getDados_dbID(int ID) {
        open();
        DataBaseModel obj = new DataBaseModel();

        try {

            stmt = conexao.prepareStatement("SELECT * FROM `dados_db` WHERE ID_banco_de_dados = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setID_banco_de_dados(rs.getInt("ID_banco_de_dados"));//1
                obj.setHost(rs.getString("Hosts"));//1
                obj.setUser(rs.getString("Users"));//1
                obj.setPassword(rs.getString("Passwords"));//1
                obj.setDataBase(rs.getString("DataBases"));//1
                obj.setPrefix(rs.getString("Prefixs"));//1
                obj.setPorts(rs.getInt("Ports"));//1
//                

            }

            return obj;
        } catch (SQLException ex) {
            close();
            MessagemErroBD(ex, "getDados_dbID");
            return null;
        }
    }

    public List<DataBaseModel> getDados_dbList() {
        open();
        ArrayList<DataBaseModel> result = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement("SELECT * FROM `dados_db` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DataBaseModel obj = new DataBaseModel();

                obj.setID_banco_de_dados(rs.getInt("ID_banco_de_dados"));//1
                obj.setHost(rs.getString("Hosts"));
                obj.setUser(rs.getString("Users"));
                obj.setPassword(rs.getString("Passwords"));
                obj.setDataBase(rs.getString("DataBases"));
                obj.setPrefix(rs.getString("Prefixs"));
                obj.setPorts(rs.getInt("Ports"));
//                
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            close();
            MessagemErroBD(ex, "getDados_dbList");
            return null;

        }
    }

    public void creatDados_db(DataBaseModel obj) {
        open();
        try {
            stmt = conexao.prepareStatement("INSERT INTO `dados_db` ( Hosts, Users, Passwords, DataBases, Prefixs, Ports )VALUES(?,?,?,?,?,?);");

//            stmt.setInt(1,2);
            stmt.setString(1, obj.getHost());
            stmt.setString(2, obj.getUser());
            stmt.setString(3, obj.getPassword());
            stmt.setString(4, obj.getDataBase());
            stmt.setString(5, obj.getPrefix());
            stmt.setInt(6, obj.getPorts());
//           

            stmt.executeUpdate();

        } catch (SQLException ex) {
            close();
            MessagemErroBD(ex, "creatDados_db");
        }
    }

    public void updateDados_db(DataBaseModel obj) {
        open();
        try {
            stmt = conexao.prepareStatement("UPDATE `dados_db` SET"
                    + " Hosts = ?,"//1
                    + " Users = ?,"//2
                    + " Passwords = ?,"//3
                    + " DataBases = ?,"//4
                    + " Prefixs = ?,"//5
                    + " Ports = ? "//6
                    + " WHERE ID_banco_de_dados = ?;");//7

            stmt.setString(1, obj.getHost());
            stmt.setString(2, obj.getUser());
            stmt.setString(3, obj.getPassword());
            stmt.setString(4, obj.getDataBase());
            stmt.setString(5, obj.getPrefix());
            stmt.setInt(6, obj.getPorts());
            stmt.setInt(7, obj.getID_banco_de_dados());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            close();
            MessagemErroBD(ex, "updateDados_db");
        }
    }

    public void deleteDados_db(DataBaseModel obj) {
        open();
        try {
            stmt = conexao.prepareStatement("DELETE FROM dados_db WHERE ID_banco_de_dados = ?;");

            stmt.setInt(1, obj.getID_banco_de_dados());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            close();
            MessagemErroBD(ex, "deleteDados_db");
        }
    }
}
