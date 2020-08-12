/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Model.Usuario;

import io.github.pedroermarinho.hospital.MainApp;
import io.github.pedroermarinho.hospital.Model.Usuario.UsuarioDAO.UserDAO;
import javafx.beans.property.*;

import java.util.List;

/**
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class UserModel {

    private static UserDAO dao;
    private final IntegerProperty ID_usuario = new SimpleIntegerProperty();
    private final StringProperty Usuario = new SimpleStringProperty();
    private final StringProperty Email = new SimpleStringProperty();
    private final StringProperty Nome = new SimpleStringProperty();
    private final StringProperty Sobrenome = new SimpleStringProperty();
    private final StringProperty Senha = new SimpleStringProperty();
    private final IntegerProperty ID_sexo = new SimpleIntegerProperty();
    private final ObjectProperty<java.sql.Date> DataNascimento = new SimpleObjectProperty<>();

    public UserModel() {
        dao = new UserDAO();
    }

    public static List<UserModel> all(MainApp mainApp) {
        return new UserDAO().getUsuarioList();
    }

    public static UserModel find(int pk) {
        return new UserDAO().getUsuarioID(pk);
    }

    public int getID_usuario() {
        return ID_usuario.get();
    }

    public void setID_usuario(int value) {
        ID_usuario.set(value);
    }

    public IntegerProperty ID_usuarioProperty() {
        return ID_usuario;
    }

    public String getEmail() {
        return Email.get();
    }

    public void setEmail(String value) {
        Email.set(value);
    }

    public StringProperty EmailProperty() {
        return Email;
    }

    public String getUsuario() {
        return Usuario.get();
    }

    public void setUsuario(String value) {
        Usuario.set(value);
    }

    public StringProperty UsuarioProperty() {
        return Usuario;
    }

    public java.sql.Date getDataNascimento() {
        return DataNascimento.get();
    }

    public void setDataNascimento(java.sql.Date value) {
        DataNascimento.set(value);
    }

    public ObjectProperty DataNascimentoProperty() {
        return DataNascimento;
    }

    public int getSexo() {
        return ID_sexo.get();
    }

    public void setSexo(int value) {
        ID_sexo.set(value);
    }

    public IntegerProperty SexoProperty() {
        return ID_sexo;
    }

    public String getSenha() {
        return Senha.get();
    }

    public void setSenha(String value) {
        Senha.set(value);
    }

    public StringProperty SenhaProperty() {
        return Senha;
    }

    public String getSobrenome() {
        return Sobrenome.get();
    }

    public void setSobrenome(String value) {
        Sobrenome.set(value);
    }

    public StringProperty SobrenomeProperty() {
        return Sobrenome;
    }

    public String getNome() {
        return Nome.get();
    }

    public void setNome(String value) {
        Nome.set(value);
    }

    public StringProperty NomeProperty() {
        return Nome;
    }

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_usuario.getValue() != null && ID_usuario.get() != 0) {
            if (find(ID_usuario.get()) != null) {
                dao.updateUsuario(this);
            } else {
                dao.creatUsuario(this);
            }
        } else {
            dao.creatUsuario(this);
        }
    }

    public void delete() {
        if (find(ID_usuario.get()) != null) {
            dao.deleteUsuario(this);
        }
    }

}
