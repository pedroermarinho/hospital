/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Model.Configuracao_Local;

import io.github.pedroermarinho.hospital.Model.Configuracao_Local.Dao_Configuracao.SettingsDAO;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

/**
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class SettingsModel {

    private static SettingsDAO dao = new SettingsDAO();
    private final IntegerProperty ID_configuracao = new SimpleIntegerProperty();
    private final IntegerProperty ID_db = new SimpleIntegerProperty();


    public static List<SettingsModel> all() {
        return new SettingsDAO().getConfiguracaoList();
    }

    public static SettingsModel find(int pk) {
        return new SettingsDAO().getConfiguracaoID(pk);
    }

    public int getID_db() {
        return ID_db.get();
    }

    public void setID_db(int value) {
        ID_db.set(value);
    }

    public IntegerProperty ID_dbProperty() {
        return ID_db;
    }

    public int getID_configuracao() {
        return ID_configuracao.get();
    }

    public void setID_configuracao(int value) {
        ID_configuracao.set(value);
    }

    public IntegerProperty ID_configuracaoProperty() {
        return ID_configuracao;
    }

    public void save() {
        if (ID_configuracao.getValue() != null && ID_configuracao.get() != 0) {
            if (find(ID_configuracao.get()) != null) {
                dao.updateConfiguracao(this);
            } else {
                dao.creatConfiguracao(this);
            }
        } else {
            dao.creatConfiguracao(this);
        }
    }

    public void delete() {
        if (find(ID_configuracao.get()) != null) {
            dao.deleteConfiguracao(this);
        }
    }
}
