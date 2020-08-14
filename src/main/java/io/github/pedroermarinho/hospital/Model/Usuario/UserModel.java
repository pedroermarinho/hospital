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
    private final IntegerProperty idUser = new SimpleIntegerProperty();
    private final StringProperty userName = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final StringProperty sobrenome = new SimpleStringProperty();
    private final StringProperty senha = new SimpleStringProperty();
    private final StringProperty sexo = new SimpleStringProperty();
    private final ObjectProperty<java.sql.Date> dataNascimento = new SimpleObjectProperty<>();

    public UserModel() {
        dao = new UserDAO();
    }

    public static List<UserModel> all() {
        return new UserDAO().getUsuarioList();
    }

    public static UserModel find(int pk) {
        return new UserDAO().getUsuarioID(pk);
    }

    public int getIdUser() {
        return idUser.get();
    }

    public void setIdUser(int value) {
        idUser.set(value);
    }

    public IntegerProperty idUserProperty() {
        return idUser;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String value) {
        email.set(value);
    }

    public StringProperty EmailProperty() {
        return email;
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String value) {
        userName.set(value);
    }

    public StringProperty UsuarioProperty() {
        return userName;
    }

    public java.sql.Date getDataNascimento() {
        return dataNascimento.get();
    }

    public void setDataNascimento(java.sql.Date value) {
        dataNascimento.set(value);
    }

    public ObjectProperty DataNascimentoProperty() {
        return dataNascimento;
    }

    public String getSexo() {
        return sexo.get();
    }

    public void setSexo(String value) {
        sexo.set(value);
    }

    public StringProperty SexoProperty() {
        return sexo;
    }

    public String getSenha() {
        return senha.get();
    }

    public void setSenha(String value) {
        senha.set(value);
    }

    public StringProperty SenhaProperty() {
        return senha;
    }

    public String getSobrenome() {
        return sobrenome.get();
    }

    public void setSobrenome(String value) {
        sobrenome.set(value);
    }

    public StringProperty SobrenomeProperty() {
        return sobrenome;
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String value) {
        nome.set(value);
    }

    public StringProperty NomeProperty() {
        return nome;
    }

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (idUser.getValue() != null && idUser.get() != 0) {
            if (find(idUser.get()) != null) {
                dao.updateUsuario(this);
            } else {
                dao.creatUsuario(this);
            }
        } else {
            dao.creatUsuario(this);
        }
    }

    public void delete() {
        if (find(idUser.get()) != null) {
            dao.deleteUsuario(this);
        }
    }

}
