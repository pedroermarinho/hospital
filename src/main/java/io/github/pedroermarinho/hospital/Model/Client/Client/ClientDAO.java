/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Model.Client.Client;

import io.github.pedroermarinho.hospital.ChamadasDeTela;
import io.github.pedroermarinho.hospital.Util.BD.DataBaseClient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class ClientDAO implements ClientDAOInterface {

    private final DataBaseClient db = DataBaseClient.instance();
    private PreparedStatement stmt;

    @Override
    public ClientModel get(int id) {
        ClientModel obj = null;

        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `client` WHERE id_client = '" + id + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj = new ClientModel();
                obj.setIdClient(rs.getInt("id_client"));//1
                obj.setCpf(rs.getString("cpf"));//2
                obj.setCartaoSUS(rs.getString("cartao_sus"));//7
                obj.setIdentidade(rs.getString("identidade"));//7
                obj.setNome(rs.getString("nome"));//3
                obj.setMae(rs.getString("mae"));//4
                obj.setDataNascimento(rs.getString("data_nascimento"));//6
                obj.setSexo(rs.getString("sexo"));//8

            }

            return obj;
        } catch (SQLException e) {

            ChamadasDeTela.errorScreen(e);
            return null;
        }
    }

    @Override
    public List<ClientModel> getAll() {
        ArrayList<ClientModel> result = new ArrayList<>();
        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `client`");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ClientModel obj = new ClientModel();

                obj.setIdClient(rs.getInt("id_client"));
                obj.setCpf(rs.getString("cpf"));
                obj.setCartaoSUS(rs.getString("cartao_sus"));
                obj.setIdentidade(rs.getString("identidade"));
                obj.setNome(rs.getString("nome"));
                obj.setMae(rs.getString("mae"));
                obj.setDataNascimento(rs.getString("data_nascimento"));
                obj.setSexo(rs.getString("sexo"));
                result.add(obj);
            }
            return result;

        } catch (SQLException e) {

            ChamadasDeTela.errorScreen(e);
            return null;

        }
    }

    @Override
    public Integer create(ClientModel obj) {

        try {
            stmt = db.getConnection().prepareStatement("INSERT INTO client (`cpf`,`cartao_sus`,`identidade`, `nome`, `mae`,`data_nascimento`,`sexo`) VALUES(?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, obj.getCpf());
            stmt.setString(2, obj.getCartaoSUS());
            stmt.setString(3, obj.getIdentidade());
            stmt.setString(4, obj.getNome());
            stmt.setString(5, obj.getMae());
            stmt.setString(6, obj.getDataNascimento());
            stmt.setString(7, obj.getSexo());

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
    public Integer update(ClientModel obj) {

        try {
            stmt = db.getConnection().prepareStatement("UPDATE client SET"
                    + " cpf = ?,"
                    + " cartao_sus = ?,"
                    + " identidade = ?,"
                    + " nome = ?,"
                    + " mae = ?,"
                    + " data_nascimento = ?,"
                    + " sexo = ?"
                    + " WHERE id_client = ?;"
            );

            stmt.setString(1, obj.getCpf());
            stmt.setString(2, obj.getCartaoSUS());
            stmt.setString(3, obj.getIdentidade());
            stmt.setString(4, obj.getNome());
            stmt.setString(5, obj.getMae());
            stmt.setString(6, obj.getDataNascimento());
            stmt.setString(7, obj.getSexo());
            stmt.setInt(8, obj.getIdClient());

            stmt.executeUpdate();
            return obj.getIdClient();

        } catch (SQLException e) {
            ChamadasDeTela.errorScreen(e);
            return null;
        }
    }

    public Integer delete(int id) {
        try {
            stmt = db.getConnection().prepareStatement("DELETE FROM client WHERE id_client = ?;");

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return id;

        } catch (SQLException e) {
            ChamadasDeTela.errorScreen(e);
            return null;
        }
    }
}
