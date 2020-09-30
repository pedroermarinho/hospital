/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Model.Client.Address;

import io.github.pedroermarinho.hospital.ChamadasDeTela;
import io.github.pedroermarinho.hospital.Util.BD.DataBaseClient;
import io.github.pedroermarinho.hospital.Util.MsgErro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class AddressClientDAO implements AddressClientDAOInterface {

    private final DataBaseClient db = DataBaseClient.instance();
    private PreparedStatement stmt;

    @Override
    public AddressClientModel get(int id) {
        AddressClientModel obj = null;

        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `address_client` WHERE id_client = '" + id + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj = new AddressClientModel();
                obj.setIdAddressClient(rs.getInt("id_address_client"));
                obj.setIdClient(rs.getInt("id_client"));
                obj.setCidade(rs.getString("cidade"));
                obj.setRua(rs.getString("rua"));
                obj.setBairro(rs.getString("bairro"));
                obj.setNumeroCasa(rs.getString("numero_casa"));
                obj.setComplemento(rs.getString("complemento"));
            }

            return obj;
        } catch (SQLException e) {
            ChamadasDeTela.errorScreen(e);
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
                obj.setNumeroCasa(rs.getString("numero_casa"));
                obj.setComplemento(rs.getString("complemento"));
                result.add(obj);
            }
            return result;

        } catch (SQLException e) {
            ChamadasDeTela.errorScreen(e);
            return null;

        }
    }

    @Override
    public Integer create(AddressClientModel obj) {
        try {
            stmt = db.getConnection().prepareStatement("INSERT INTO address_client ( `id_client`, `pais`, `estado`, `cidade`, `rua`, `bairro`, `numero_casa`, `complemento`) VALUES(?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);


            stmt.setInt(1, obj.getIdClient());
            stmt.setString(2, obj.getPais());
            stmt.setString(3, obj.getEstado());
            stmt.setString(4, obj.getCidade());
            stmt.setString(5, obj.getRua());
            stmt.setString(6, obj.getBairro());
            stmt.setString(7, obj.getNumeroCasa());
            stmt.setString(8, obj.getComplemento());

            stmt.executeUpdate();
            final ResultSet rs = stmt.getGeneratedKeys();
            Integer resultID = null;
            if (rs.next()) {
                resultID = rs.getInt(1);
            }
            return resultID;

        } catch (SQLException e) {
            ChamadasDeTela.errorScreen(e);
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
                    + " WHERE id_address_client = ?;", Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, obj.getIdClient());
            stmt.setString(2, obj.getPais());
            stmt.setString(3, obj.getEstado());
            stmt.setString(4, obj.getCidade());
            stmt.setString(5, obj.getRua());
            stmt.setString(6, obj.getBairro());
            stmt.setString(7, obj.getNumeroCasa());
            stmt.setString(8, obj.getComplemento());
            stmt.setInt(9, obj.getIdAddressClient());

            stmt.executeUpdate();

            return obj.getIdAddressClient();

        } catch (SQLException e) {
            ChamadasDeTela.errorScreen(e);
            return null;
        }
    }

    public Integer delete(int id) {

        try {
            stmt = db.getConnection().prepareStatement("DELETE FROM address_client WHERE id_address_client = ?;");

            stmt.setInt(1, id);

            stmt.executeUpdate();

            return id;

        } catch (SQLException e) {
            ChamadasDeTela.errorScreen(e);
            return null;
        }
    }

}
