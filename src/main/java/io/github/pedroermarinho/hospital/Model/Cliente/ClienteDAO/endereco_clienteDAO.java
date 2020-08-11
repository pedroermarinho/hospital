/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Model.Cliente.ClienteDAO;

import io.github.pedroermarinho.hospital.MainApp;
import io.github.pedroermarinho.hospital.Model.Cliente.model_endereco_cliente;
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
public class endereco_clienteDAO {

    private final DataBaseCliente db = DataBaseCliente.instance();
    private PreparedStatement stmt;



    public model_endereco_cliente getEnderecoClienteID(int ID) {
        model_endereco_cliente obj = new model_endereco_cliente();

        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `endereco_cliente` WHERE ID_endereco_cliente = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setID_Endereco_Cliente(rs.getInt("ID_endereco_cliente"));
                obj.setID_cliente(rs.getInt("ID_cliente"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setTelefone_Fixo(rs.getString("telefone_fixo"));
                obj.setID_cidade(rs.getInt("ID_cidade"));
                obj.setID_Rua(rs.getInt("ID_rua"));
                obj.setID_Bairro(rs.getInt("ID_bairro"));
                obj.setNumero_Casa(rs.getInt("numero_casa"));
                obj.setComplemento(rs.getString("complemento"));
            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getEnderecoClienteID");
            return null;
        }
    }

    public List<model_endereco_cliente> getEnderecoClienteList() {
        ArrayList<model_endereco_cliente> result = new ArrayList<>();
        try {

            stmt = db.getConnection().prepareStatement("SELECT * FROM `endereco_cliente` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model_endereco_cliente obj = new model_endereco_cliente();

                obj.setID_Endereco_Cliente(rs.getInt("ID_endereco_cliente"));
                obj.setID_cliente(rs.getInt("ID_cliente"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setTelefone_Fixo(rs.getString("telefone_fixo"));
                obj.setID_cidade(rs.getInt("ID_cidade"));
                obj.setID_Rua(rs.getInt("ID_rua"));
                obj.setID_Bairro(rs.getInt("ID_bairro"));
                obj.setNumero_Casa(rs.getInt("numero_casa"));
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

    public void creatEnderecoCliente(model_endereco_cliente obj) {
        try {
            stmt = db.getConnection().prepareStatement("INSERT INTO endereco_cliente ( `ID_cliente`, `telefone`, `telefone_fixo`, `ID_cidade`, `ID_rua`, `ID_bairro`, `numero_casa`, `complemento`) VALUES(?,?,?,?,?,?,?,?);");


            stmt.setInt(1, obj.getID_cliente());
            stmt.setString(2, obj.getTelefone());
            stmt.setString(3, obj.getTelefone_Fixo());
            stmt.setInt(4, obj.getID_cidade());
            stmt.setInt(5, obj.getID_Rua());
            stmt.setInt(6, obj.getID_Bairro());
            stmt.setInt(7, obj.getNumero_Casa());
            stmt.setString(8, obj.getComplemento());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatEnderecoCliente");
        }
    }

    public void updateEnderecoCliente(model_endereco_cliente obj) {
        try {
            stmt = db.getConnection().prepareStatement("UPDATE endereco_cliente SET"
                    + " ID_cliente = ?,"
                    + " telefone = ?,"
                    + " telefone_fixo = ?,"
                    + " ID_cidade = ?,"
                    + " ID_rua = ?,"
                    + " ID_bairro = ?,"
                    + " numero_casa = ?,"
                    + " complemento = ?"
                    + " WHERE ID_endereco_cliente = ?;");

            stmt.setInt(1, obj.getID_cliente());
            stmt.setString(2, obj.getTelefone());
            stmt.setString(3, obj.getTelefone_Fixo());
            stmt.setInt(4, obj.getID_cidade());
            stmt.setInt(5, obj.getID_Rua());
            stmt.setInt(6, obj.getID_Bairro());
            stmt.setInt(7, obj.getNumero_Casa());
            stmt.setString(8, obj.getComplemento());
            stmt.setInt(9, obj.getID_Endereco_Cliente());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateEnderecoCliente");
        }
    }

    public void deleteEnderecoCliente(model_endereco_cliente obj) {

        try {
            stmt = db.getConnection().prepareStatement("DELETE FROM endereco_cliente WHERE ID_endereco_cliente = ?;");

            stmt.setInt(1, obj.getID_Endereco_Cliente());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteEnderecoCliente");
        }
    }

}
