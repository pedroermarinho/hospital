/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Model.Usuario.UsuarioDAO;

import io.github.pedroermarinho.hospital.Model.Usuario.UserModel;
import io.github.pedroermarinho.hospital.Util.BD.DataBaseCliente;
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

    private final DataBaseCliente db = DataBaseCliente.instance();
    private PreparedStatement stmt;

    public UserModel getUsuarioID(int ID) {
        UserModel obj = new UserModel();

        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `usuario` WHERE ID_usuario = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setID_usuario(rs.getInt("ID_usuario"));//1
                obj.setNome(rs.getString("Nome"));//2
                obj.setSobrenome(rs.getString("Sobrenome"));//3
                obj.setSenha(rs.getString("Senha"));//4
                obj.setSexo(rs.getInt("ID_sexo"));//5
                obj.setDataNascimento(rs.getDate("DataNascimento"));//6
                obj.setUsuario(rs.getString("Usuario"));//7
                obj.setEmail(rs.getString("Email"));//8
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

            stmt = db.getConnection().prepareStatement("SELECT * FROM `usuario` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UserModel obj = new UserModel();

                obj.setID_usuario(rs.getInt("ID_usuario"));//1
                obj.setNome(rs.getString("Nome"));//2
                obj.setSobrenome(rs.getString("Sobrenome"));//3
                obj.setSenha(rs.getString("Senha"));//4
                obj.setSexo(rs.getInt("ID_sexo"));//5
                obj.setDataNascimento(rs.getDate("DataNascimento"));//6
                obj.setUsuario(rs.getString("Usuario"));//7
                obj.setEmail(rs.getString("Email"));//8
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
            stmt = db.getConnection().prepareStatement("INSERT INTO usuario (`Nome`, `Sobrenome`, `Senha`, `ID_sexo`, `DataNascimento`, `Usuario`, `Email`) VALUES(?,?,?,?,?,?,?);");


            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getSobrenome());
            stmt.setString(3, obj.getSenha());
            stmt.setInt(4, obj.getSexo());
            stmt.setDate(5, obj.getDataNascimento());
            stmt.setString(6, obj.getUsuario());
            stmt.setString(7, obj.getEmail());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatUsuario");
        }
    }

    public void updateUsuario(UserModel obj) {
        try {
            stmt = db.getConnection().prepareStatement("UPDATE usuario SET"
                    + " Nome = ?,"//1
                    + " Sobrenome = ?,"//2
                    + " Senha = ?,"//3
                    + " ID_sexo = ?,"//4
                    + " DataNascimento = ?,"//5
                    + " Usuario = ?,"//6
                    + " Email = ?"//7
                    + " WHERE ID_usuario = ?;");//8

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getSobrenome());
            stmt.setString(3, obj.getSenha());
            stmt.setInt(4, obj.getSexo());
            stmt.setDate(5, obj.getDataNascimento());
            stmt.setString(6, obj.getUsuario());
            stmt.setString(7, obj.getEmail());
            stmt.setInt(8, obj.getID_usuario());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateUsuario");
        }
    }

    public void deleteUsuario(UserModel obj) {
        try {
            stmt = db.getConnection().prepareStatement("DELETE FROM usuario WHERE ID_usuario = ?;");

            stmt.setInt(1, obj.getID_usuario());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteUsuario");
        }
    }
}
