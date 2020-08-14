package io.github.pedroermarinho.hospital.Util.BD;

import io.github.pedroermarinho.hospital.Util.MsgErro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class DataBaseSettings {


    private static volatile DataBaseSettings instanceDataBaseSettings;
    private Connection connection;

    public static DataBaseSettings instance() {
        if (instanceDataBaseSettings == null) {
            synchronized (DataBaseSettings.class) {
                if (instanceDataBaseSettings == null) {
                    instanceDataBaseSettings = new DataBaseSettings();
                }
            }
        }
        return instanceDataBaseSettings;
    }

    public Connection open() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:sqlite:settings.db");
            }
            return connection;
        } catch (SQLException ex) {
            close();
            MsgErro.MessagemErroBD(ex, "open");
        }
        return null;
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
