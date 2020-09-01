/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Model.Cliente.Address;

import io.github.pedroermarinho.hospital.Util.BD.DataBaseClient;
import io.github.pedroermarinho.hospital.Util.MsgErro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class AddressClientDAO implements AddressClientDaoInterface {

    private final DataBaseClient db = DataBaseClient.instance();
    private PreparedStatement stmt;

    @Override
    public AddressClientModel get(int id) {
        AddressClientModel obj = new AddressClientModel();

        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `address_client` WHERE id_address_client = '" + id + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setIdAddressClient(rs.getInt("id_address_client"));
                obj.setIdClient(rs.getInt("id_client"));
                obj.setCidade(rs.getString("cidade"));
                obj.setRua(rs.getString("rua"));
                obj.setBairro(rs.getString("bairro"));
                obj.setNumeroCasa(rs.getInt("numero_casa"));
                obj.setComplemento(rs.getString("complemento"));
            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getEnderecoClienteID");
            return null;
        }
    }

    @Override
    public List<AddressClientModel> getAll() {
        ArrayList<AddressClientModel> result = new ArrayList<>();
        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `address_client` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AddressClientModel obj = new AddressClientModel();

                obj.setIdAddressClient(rs.getInt("id_address_client"));
                obj.setIdClient(rs.getInt("id_client"));
                obj.setPais(rs.getString("pais"));
                obj.setEstado(rs.getString("estado"));
                obj.setCidade(rs.getString("cidade"));
                obj.setRua(rs.getString("rua"));
                obj.setBairro(rs.getString("bairro"));
                obj.setNumeroCasa(rs.getInt("numero_casa"));
                obj.setComplemento(rs.getString("complemento"));
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getEnderecoClienteList");
            return null;

        }
    }

    @Override
    public Integer create(AddressClientModel obj) {
        try {
            stmt = db.getConnection().prepareStatement("INSERT INTO address_client ( `id_client`, `pais`, `estado`, `cidade`, `rua`, `bairro`, `numero_casa`, `complemento`) VALUES(?,?,?,?,?,?,?,?);");


            stmt.setInt(1, obj.getIdClient());
            stmt.setString(2, obj.getPais());
            stmt.setString(3, obj.getEstado());
            stmt.setString(4, obj.getCidade());
            stmt.setString(5, obj.getRua());
            stmt.setString(6, obj.getBairro());
            stmt.setInt(7, obj.getNumeroCasa());
            stmt.setString(8, obj.getComplemento());

            return stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatEnderecoCliente");
            return null;
        }
    }

    @Override
    public Integer update(AddressClientModel obj) {
        try {
            stmt = db.getConnection().prepareStatement("UPDATE address_client SET"
                    + " id_client = ?,"
                    + " pais = ?,"
                    + " estado = ?,"
                    + " cidade = ?,"
                    + " rua = ?,"
                    + " bairro = ?,"
                    + " numero_casa = ?,"
                    + " complemento = ?"
                    + " WHERE id_address_client = ?;");

            stmt.setInt(1, obj.getIdClient());
            stmt.setString(2, obj.getPais());
            stmt.setString(3, obj.getEstado());
            stmt.setString(4, obj.getCidade());
            stmt.setString(5, obj.getRua());
            stmt.setString(6, obj.getBairro());
            stmt.setInt(7, obj.getNumeroCasa());
            stmt.setString(8, obj.getComplemento());
            stmt.setInt(9, obj.getIdAddressClient());

            return stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateEnderecoCliente");
            return null;
        }
    }

    public Integer delete(int id) {

        try {
            stmt = db.getConnection().prepareStatement("DELETE FROM address_client WHERE id_address_client = ?;");

            stmt.setInt(1, id);

            return stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteEnderecoCliente");
            return null;
        }
    }

}
