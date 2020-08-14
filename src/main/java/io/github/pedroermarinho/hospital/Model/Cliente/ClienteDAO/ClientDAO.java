/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Model.Cliente.ClienteDAO;

import io.github.pedroermarinho.hospital.Model.Cliente.ClientModel;
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
public class ClientDAO {

    private final DataBaseClient db = DataBaseClient.instance();
    private PreparedStatement stmt;


    public ClientModel getClienteID(int ID) {
        ClientModel obj = new ClientModel();

        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `client` WHERE id_client = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setIdClient(rs.getInt("id_client"));//1
                obj.setCpf(rs.getString("cpf"));//2
                obj.setCartaoSUS(rs.getString("cartao_sus"));//7
                obj.setIdentidade(rs.getString("identidade"));//7
                obj.setNome(rs.getString("nome"));//3
                obj.setMae(rs.getString("mae"));//4
                obj.setDataNascimento(rs.getString("data_nascimento"));//6
                obj.setSexo(rs.getString("sexo"));//8
                obj.setEmail(rs.getString("email"));//9
                obj.setEspecialidade(rs.getString("especialidade"));//9
                obj.setRecepcao(rs.getString("recepcao"));//9

            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getClienteID");
            return null;
        }
    }

    public List<ClientModel> getClienteList() {
        ArrayList<ClientModel> result = new ArrayList<>();
        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `client` ");
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
                obj.setEmail(rs.getString("email"));
                obj.setEspecialidade(rs.getString("especialidade"));
                obj.setRecepcao(rs.getString("recepcao"));
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getClienteList");
            return null;

        }
    }

    public void creatCliente(ClientModel obj) {

        try {
            stmt = db.getConnection().prepareStatement("INSERT INTO client (`cpf`,`cartao_sus`,`identidade`, `nome`, `mae`,`data_nascimento`,`sexo`, `email`,`especialidade`,`recepcao`) VALUES(?,?,?,?,?,?,?,?,?,?);");

            stmt.setString(1, obj.getCpf());
            stmt.setString(2, obj.getCartaoSUS());
            stmt.setString(3, obj.getIdentidade());
            stmt.setString(4, obj.getNome());
            stmt.setString(5, obj.getMae());
            stmt.setString(6, obj.getDataNascimento());
            stmt.setString(7, obj.getSexo());
            stmt.setString(8, obj.getEmail());
            stmt.setString(9, obj.getEspecialidade());
            stmt.setString(10, obj.getRecepcao());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatCliente");
        }
    }

    public void updateCliente(ClientModel obj) {

        try {
            stmt = db.getConnection().prepareStatement("UPDATE client SET"
                    + " cpf = ?,"
                    + " cartao_sus = ?,"
                    + " identidade = ?,"
                    + " nome = ?,"
                    + " mae = ?,"
                    + " data_nascimento = ?,"
                    + " sexo = ?,"
                    + " email = ?,"
                    + " especialidade = ?,"
                    + " recepcao = ?"
                    + " WHERE id_client = ?;"
            );

            stmt.setString(1, obj.getCpf());
            stmt.setString(2, obj.getCartaoSUS());
            stmt.setString(3, obj.getIdentidade());
            stmt.setString(4, obj.getNome());
            stmt.setString(5, obj.getMae());
            stmt.setString(6, obj.getDataNascimento());
            stmt.setString(7, obj.getSexo());
            stmt.setString(8, obj.getEmail());
            stmt.setString(9, obj.getEspecialidade());
            stmt.setString(10, obj.getRecepcao());
            stmt.setInt(11, obj.getIdClient());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateCliente");
        }
    }

    public void deleteCliente(ClientModel obj) {
        try {
            stmt = db.getConnection().prepareStatement("DELETE FROM client WHERE id_client = ?;");

            stmt.setInt(1, obj.getIdClient());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteCliente");
        }
    }
}
