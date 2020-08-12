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
                obj.setNome(rs.getString("nome"));//3
                obj.setMae(rs.getString("mae"));//4
                obj.setPai(rs.getString("pai"));//5
                obj.setDataNascimento(rs.getString("data_nascimento"));//6
                obj.setCartaoSUS(rs.getString("cartao_sus"));//7
                obj.setSexo(rs.getString("sexo"));//8
                obj.setEmail(rs.getString("email"));//9
                obj.setFoto(rs.getString("foto"));//10

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

                obj.setIdClient(rs.getInt("id_client"));//1
                obj.setCpf(rs.getString("cpf"));//2
                obj.setNome(rs.getString("nome"));//3
                obj.setMae(rs.getString("mae"));//4
                obj.setPai(rs.getString("pai"));//5
                obj.setDataNascimento(rs.getString("data_nascimento"));//6
                obj.setCartaoSUS(rs.getString("cartao_sus"));//7
                obj.setSexo(rs.getString("sexo"));//8
                obj.setEmail(rs.getString("email"));//9
                obj.setFoto(rs.getString("foto"));//10
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
            stmt = db.getConnection().prepareStatement("INSERT INTO client (`cpf`, `nome`, `mae`, `pai`, `data_nascimento`, `cartao_sus`, `sexo`, `email`, `foto`) VALUES(?,?,?,?,?,?,?,?,?);");

            stmt.setString(1, obj.getCpf());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getMae());
            stmt.setString(4, obj.getPai());
            stmt.setString(5, obj.getDataNascimento());
            stmt.setString(6, obj.getCartaoSUS());
            stmt.setString(7, obj.getSexo());
            stmt.setString(8, obj.getEmail());
            stmt.setString(9, obj.getFoto());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatCliente");
        }
    }

    public void updateCliente(ClientModel obj) {

        try {
            stmt = db.getConnection().prepareStatement("UPDATE client SET"
                    + " cpf = ?,"//1
                    + " nome = ?,"//2
                    + " mae = ?,"//3
                    + " pai = ?,"//4
                    + " data_nascimento = ?,"//5
                    + " cartao_sus = ?,"//6
                    + " sexo = ?,"//7
                    + " email = ?,"//8
                    + " foto = ?"//9
                    + " WHERE id_client = ?;");//10

            stmt.setString(1, obj.getCpf());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getMae());
            stmt.setString(4, obj.getPai());
            stmt.setString(5, obj.getDataNascimento());
            stmt.setString(6, obj.getCartaoSUS());
            stmt.setString(7, obj.getSexo());
            stmt.setString(8, obj.getEmail());
            stmt.setString(9, obj.getFoto());
            stmt.setInt(10, obj.getIdClient());

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
