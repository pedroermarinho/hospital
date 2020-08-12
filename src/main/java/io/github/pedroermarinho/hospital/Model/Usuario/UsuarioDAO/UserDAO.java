/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Model.Usuario.UsuarioDAO;

import io.github.pedroermarinho.hospital.Model.Usuario.UserModel;
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
public class UserDAO {

    private final DataBaseClient db = DataBaseClient.instance();
    private PreparedStatement stmt;

    public UserModel getUsuarioID(int ID) {
        UserModel obj = new UserModel();

        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `user` WHERE id_user = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setIdUser(rs.getInt("id_user"));//1
                obj.setNome(rs.getString("nome"));//2
                obj.setSobrenome(rs.getString("sobrenome"));//3
                obj.setSenha(rs.getString("senha"));//4
                obj.setSexo(rs.getInt("sexo"));//5
                obj.setDataNascimento(rs.getDate("data_nascimento"));//6
                obj.setUserName(rs.getString("user_name"));//7
                obj.setEmail(rs.getString("email"));//8
            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getUsuarioID");
            return null;
        }
    }

    public List<UserModel> getUsuarioList() {
        ArrayList<UserModel> result = new ArrayList<>();
        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `user` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UserModel obj = new UserModel();

                obj.setIdUser(rs.getInt("id_user"));//1
                obj.setNome(rs.getString("nome"));//2
                obj.setSobrenome(rs.getString("sobrenome"));//3
                obj.setSenha(rs.getString("senha"));//4
                obj.setSexo(rs.getInt("sexo"));//5
                obj.setDataNascimento(rs.getDate("data_nascimento"));//6
                obj.setUserName(rs.getString("user_name"));//7
                obj.setEmail(rs.getString("email"));//8
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getUsuarioList");
            return null;

        }
    }

    public void creatUsuario(UserModel obj) {
        try {
            stmt = db.getConnection().prepareStatement("INSERT INTO user (`nome`, `sobrenome`, `senha`, `sexo`, `data_nascimento`, `user_name`, `email`) VALUES(?,?,?,?,?,?,?);");


            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getSobrenome());
            stmt.setString(3, obj.getSenha());
            stmt.setInt(4, obj.getSexo());
            stmt.setDate(5, obj.getDataNascimento());
            stmt.setString(6, obj.getUserName());
            stmt.setString(7, obj.getEmail());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatUsuario");
        }
    }

    public void updateUsuario(UserModel obj) {
        try {
            stmt = db.getConnection().prepareStatement("UPDATE user SET"
                    + " nome = ?,"//1
                    + " sobrenome = ?,"//2
                    + " senha = ?,"//3
                    + " sexo = ?,"//4
                    + " data_nascimento = ?,"//5
                    + " user_name = ?,"//6
                    + " email = ?"//7
                    + " WHERE id_user = ?;");//8

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getSobrenome());
            stmt.setString(3, obj.getSenha());
            stmt.setInt(4, obj.getSexo());
            stmt.setDate(5, obj.getDataNascimento());
            stmt.setString(6, obj.getUserName());
            stmt.setString(7, obj.getEmail());
            stmt.setInt(8, obj.getIdUser());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateUsuario");
        }
    }

    public void deleteUsuario(UserModel obj) {
        try {
            stmt = db.getConnection().prepareStatement("DELETE FROM user WHERE id_user = ?;");

            stmt.setInt(1, obj.getIdUser());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteUsuario");
        }
    }
}
