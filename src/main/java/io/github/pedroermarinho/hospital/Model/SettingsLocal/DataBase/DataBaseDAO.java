/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Model.SettingsLocal.DataBase;

import io.github.pedroermarinho.hospital.ChamadasDeTela;
import io.github.pedroermarinho.hospital.Util.BD.DataBaseSettings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class DataBaseDAO implements DataBaseDAOInterface {

    private final DataBaseSettings db = DataBaseSettings.instance();
    private PreparedStatement stmt;

    public DataBaseDAO() {
        try {
            db.open();
            stmt = db.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS dados_db ("
                    + " ID_banco_de_dados INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " Hosts VARCHAR(100) NOT NULL,"
                    + " Users VARCHAR(100) NOT NULL,"
                    + " Passwords VARCHAR(100),"
                    + " DataBases VARCHAR(100) NOT NULL,"
                    + " Prefixs VARCHAR(100) NOT NULL,"
                    + " Ports INT VARCHAR NULL"
                    + " );");

            stmt.executeUpdate();
        } catch (SQLException e) {
            ChamadasDeTela.errorScreen(e);
        }

    }

    @Override
    public DataBaseModel get(int id) {
        DataBaseModel obj = new DataBaseModel();

        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `dados_db` WHERE ID_banco_de_dados = '" + id + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setIdDayabase(rs.getInt("ID_banco_de_dados"));//1
                obj.setHost(rs.getString("Hosts"));//1
                obj.setUser(rs.getString("Users"));//1
                obj.setPassword(rs.getString("Passwords"));//1
                obj.setDataBase(rs.getString("DataBases"));//1
                obj.setPrefix(rs.getString("Prefixs"));//1
                obj.setPorts(rs.getInt("Ports"));//1
            }

            return obj;
        } catch (SQLException e) {
            ChamadasDeTela.errorScreen(e);
            return null;
        }
    }

    @Override
    public List<DataBaseModel> getAll() {
        ArrayList<DataBaseModel> result = new ArrayList<>();
        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `dados_db` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DataBaseModel obj = new DataBaseModel();

                obj.setIdDayabase(rs.getInt("ID_banco_de_dados"));//1
                obj.setHost(rs.getString("Hosts"));
                obj.setUser(rs.getString("Users"));
                obj.setPassword(rs.getString("Passwords"));
                obj.setDataBase(rs.getString("DataBases"));
                obj.setPrefix(rs.getString("Prefixs"));
                obj.setPorts(rs.getInt("Ports"));

                result.add(obj);
            }
            return result;

        } catch (SQLException e) {
            ChamadasDeTela.errorScreen(e);
            return null;

        }
    }

    @Override
    public Integer create(DataBaseModel obj) {
        try {
            stmt = db.getConnection().prepareStatement("INSERT INTO `dados_db` ( Hosts, Users, Passwords, DataBases, Prefixs, Ports )VALUES(?,?,?,?,?,?);");

            stmt.setString(1, obj.getHost());
            stmt.setString(2, obj.getUser());
            stmt.setString(3, obj.getPassword());
            stmt.setString(4, obj.getDataBase());
            stmt.setString(5, obj.getPrefix());
            stmt.setInt(6, obj.getPorts());
//           

            return  stmt.executeUpdate();

        } catch (SQLException e) {
            ChamadasDeTela.errorScreen(e);
            return null;
        }
    }

    @Override
    public Integer update(DataBaseModel obj) {

        try {
            stmt = db.getConnection().prepareStatement("UPDATE `dados_db` SET"
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
            stmt.setInt(7, obj.getIdDayabase());

           return stmt.executeUpdate();

        } catch (SQLException e) {
            ChamadasDeTela.errorScreen(e);
            return null;
        }
    }

    @Override
    public Integer delete(int id) {
        try {
            stmt = db.getConnection().prepareStatement("DELETE FROM dados_db WHERE ID_banco_de_dados = ?;");

            stmt.setInt(1,id);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            ChamadasDeTela.errorScreen(e);
            return null;
        }
    }
}
