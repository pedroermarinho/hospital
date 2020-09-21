/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Model.SettingsLocal.DataBase;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

/**
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class DataBaseModel {


    private final IntegerProperty idDayabase = new SimpleIntegerProperty();
    private final StringProperty hosts = new SimpleStringProperty("localhost");
    private final StringProperty users = new SimpleStringProperty("root");
    private final StringProperty passwords = new SimpleStringProperty("");
    private final StringProperty dataBases = new SimpleStringProperty("hospital");
    private final StringProperty prefixs = new SimpleStringProperty("jdbc:mysql:");
    private final IntegerProperty ports = new SimpleIntegerProperty(3306);



    public int getPorts() {
        return ports.get();
    }

    public void setPorts(int value) {
        ports.set(value);
    }

    public IntegerProperty PortsProperty() {
        return ports;
    }

    public String getPrefix() {
        return prefixs.get();
    }

    public void setPrefix(String value) {
        prefixs.set(value);
    }

    public StringProperty PrefixProperty() {
        return prefixs;
    }

    public String getDataBase() {
        return dataBases.get();
    }

    public void setDataBase(String value) {
        dataBases.set(value);
    }

    public StringProperty DataBaseProperty() {
        return dataBases;
    }

    public String getPassword() {
        return passwords.get();
    }

    public void setPassword(String value) {
        passwords.set(value);
    }

    public StringProperty PasswordProperty() {
        return passwords;
    }

    public String getUser() {
        return users.get();
    }

    public void setUser(String value) {
        users.set(value);
    }

    public StringProperty UserProperty() {
        return users;
    }

    public String getHost() {
        return hosts.get();
    }

    public void setHost(String value) {
        hosts.set(value);
    }

    public StringProperty HostProperty() {
        return hosts;
    }

    public int getIdDayabase() {
        return idDayabase.get();
    }

    public void setIdDayabase(int value) {
        idDayabase.set(value);
    }

    public IntegerProperty idDayabaseProperty() {
        return idDayabase;
    }

    @Override
    public String toString() {
        return hosts.get() + " -> " + dataBases.get();
    }

    private final DataBaseDAO dao = new DataBaseDAO();

    public static List<DataBaseModel> all() {
        return new DataBaseDAO().getAll();
    }

    public static DataBaseModel find(int id) { return new DataBaseDAO().get(id); }

    public Integer save() {
        if (idDayabase.getValue() != null && idDayabase.get() != 0) {
            if (find(idDayabase.get()) != null) {
                return dao.update(this);
            } else {
                return dao.create(this);
            }
        } else {
            return dao.create(this);
        }
    }

    public Integer delete() {
        if (find(idDayabase.get()) != null) {
            return dao.delete(getIdDayabase());
        } else {
            return null;
        }
    }
}
