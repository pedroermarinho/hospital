/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Model.Client.Contact;

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
public class ContactClientDAO implements ContactClientDAOInterface {

    private final DataBaseClient db = DataBaseClient.instance();
    private PreparedStatement stmt;

    @Override
    public ContactClientModel get(int id) {
        ContactClientModel obj = new ContactClientModel();

        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `contact_client` WHERE id_client = '" + id + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setIdContactClient(rs.getInt("id_contact_client"));
                obj.setIdClient(rs.getInt("id_client"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getEnderecoClienteID");
            return null;
        }
    }

    @Override
    public List<ContactClientModel> getAll() {
        ArrayList<ContactClientModel> result = new ArrayList<>();
        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `contact_client` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ContactClientModel obj = new ContactClientModel();

                obj.setIdContactClient(rs.getInt("id_contact_client"));
                obj.setIdClient(rs.getInt("id_client"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
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
    public Integer create(ContactClientModel obj) {
        try {
            stmt = db.getConnection().prepareStatement("INSERT INTO contact_client ( `id_client`, `email`, `telefone`) VALUES(?,?,?);");


            stmt.setInt(1, obj.getIdClient());
            stmt.setString(2, obj.getEmail());
            stmt.setString(3, obj.getTelefone());

            return stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatEnderecoCliente");
            return null;
        }
    }

    @Override
    public Integer update(ContactClientModel obj) {
        try {
            stmt = db.getConnection().prepareStatement("UPDATE contact_client SET"
                    + " id_client = ?,"
                    + " email = ?,"
                    + " telefone = ?"
                    + " WHERE id_contact_client = ?;");

            stmt.setInt(1, obj.getIdClient());
            stmt.setString(2, obj.getEmail());
            stmt.setString(3, obj.getTelefone());
            stmt.setInt(4, obj.getIdContactClient());

            return stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateEnderecoCliente");

            return null;
        }
    }

    public Integer delete(int id) {

        try {
            stmt = db.getConnection().prepareStatement("DELETE FROM contact_client WHERE id_client = ?;");

            stmt.setInt(1, id);

            return stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteEnderecoCliente");
            return null;
        }
    }

}
