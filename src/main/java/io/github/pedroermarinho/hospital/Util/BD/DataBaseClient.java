package io.github.pedroermarinho.hospital.Util.BD;

import io.github.pedroermarinho.hospital.Model.SettingsLocal.DataBase.DataBaseModel;
import io.github.pedroermarinho.hospital.Util.MsgErro;
import io.github.pedroermarinho.hospital.Util.PathSettings;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public final class DataBaseClient {
    private static volatile DataBaseClient instanceDataBaseClient;

    private Connection connection;

    public static DataBaseClient instance() {
        if (instanceDataBaseClient == null) {
            synchronized (DataBaseClient.class) {
                if (instanceDataBaseClient == null) {
                    instanceDataBaseClient = new DataBaseClient();
                }
            }
        }
        return instanceDataBaseClient;
    }


    public Connection open(DataBaseModel dataBaseModel) {
        if (dataBaseModel != null) {
            String url = dataBaseModel.getPrefix() + "//" + dataBaseModel.getHost() + ":" + dataBaseModel.getPorts() + "/" + dataBaseModel.getDataBase() + ".db";
            try {
                if (connection == null) {
                    if (dataBaseModel.getPrefix().equals("jdbc:sqlite:")) {
                        connection = DriverManager.getConnection(dataBaseModel.getPrefix() + PathSettings.pathDataBase(dataBaseModel.getDataBase()));
                    } else {
                        connection = DriverManager.getConnection(url, dataBaseModel.getUser(), dataBaseModel.getPassword());
                    }
                }
                return connection;
            } catch (SQLException ex) {
                close();
                MsgErro.MessagemErroBD(ex, "open");
                return null;
            }
        } else {
            return connection;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "close");
        }
    }

}
