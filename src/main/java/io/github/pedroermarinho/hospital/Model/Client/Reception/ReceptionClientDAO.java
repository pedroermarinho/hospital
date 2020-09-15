/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Model.Client.Reception;

import io.github.pedroermarinho.hospital.Util.BD.DataBaseClient;
import io.github.pedroermarinho.hospital.Util.MsgErro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class ReceptionClientDAO implements ReceptionClientDAOInterface {

    private final DataBaseClient db = DataBaseClient.instance();
    private PreparedStatement stmt;

    @Override
    public ReceptionClientModel get(int id) {
       final ReceptionClientModel obj = new ReceptionClientModel();

        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `reception_client` WHERE id_client = '" + id + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setIdReceptionClient(rs.getInt("id_reception_client"));
                obj.setIdClient(rs.getInt("id_client"));
                obj.setEspecialidade(rs.getString("especialidade"));
                obj.setRecepcao(rs.getString("recepcao"));
                obj.setModificationDate(rs.getString("modification_date"));

            }

            return obj;
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "getEnderecoClienteID");
            return null;
        }
    }

    @Override
    public List<ReceptionClientModel> getAllData(LocalDate date) {
        ArrayList<ReceptionClientModel> result = new ArrayList<>();

        try {
            final var dateSql= java.sql.Date.valueOf(date.toString());

            stmt = db.getConnection().prepareStatement("SELECT * FROM `reception_client` WHERE modification_date = '" + dateSql.toString() + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ReceptionClientModel obj = new ReceptionClientModel();
                obj.setIdReceptionClient(rs.getInt("id_reception_client"));
                obj.setIdClient(rs.getInt("id_client"));
                obj.setEspecialidade(rs.getString("especialidade"));
                obj.setRecepcao(rs.getString("recepcao"));
                obj.setModificationDate(rs.getString("modification_date"));
                result.add(obj);
            }

            return result;
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "getEnderecoClienteID");
            return null;
        }
    }

    @Override
    public List<ReceptionClientModel> getAll() {
        ArrayList<ReceptionClientModel> result = new ArrayList<>();
        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `id_client` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ReceptionClientModel obj = new ReceptionClientModel();

                obj.setIdReceptionClient(rs.getInt("id_reception_client"));
                obj.setIdClient(rs.getInt("id_client"));
                obj.setEspecialidade(rs.getString("especialidade"));
                obj.setRecepcao(rs.getString("recepcao"));
                obj.setModificationDate(rs.getString("modification_date"));
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "getEnderecoClienteList");
            return null;

        }
    }

    @Override
    public Integer create(ReceptionClientModel obj) {
        try {
            stmt = db.getConnection().prepareStatement("INSERT INTO reception_client ( `id_client`, `especialidade`, `recepcao`, `modification_date`) VALUES(?,?,?,?);");


            stmt.setInt(1, obj.getIdClient());
            stmt.setString(2, obj.getEspecialidade());
            stmt.setString(3, obj.getRecepcao());
            stmt.setString(4, obj.getModificationDate());

            return stmt.executeUpdate();

        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "creatEnderecoCliente");
            return null;
        }
    }

    @Override
    public Integer update(ReceptionClientModel obj) {
        try {
            stmt = db.getConnection().prepareStatement("UPDATE reception_client SET"
                    + " id_client = ?,"
                    + " especialidade = ?,"
                    + " recepcao = ?,"
                    + " modification_date = ?"
                    + " WHERE id_client = ?;");

            stmt.setInt(1, obj.getIdClient());
            stmt.setString(2, obj.getEspecialidade());
            stmt.setString(3, obj.getRecepcao());
            stmt.setString(4, obj.getModificationDate());
            stmt.setInt(5, obj.getIdReceptionClient());

            return stmt.executeUpdate();

        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "updateEnderecoCliente");
            return null;
        }
    }

    public Integer delete(int id) {

        try {
            stmt = db.getConnection().prepareStatement("DELETE FROM reception_client WHERE id_client = ?;");

            stmt.setInt(1, id);

            return stmt.executeUpdate();

        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "deleteEnderecoCliente");
            return null;
        }
    }

}
