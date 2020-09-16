package io.github.pedroermarinho.hospital.Util.BD;

import io.github.pedroermarinho.hospital.Util.MsgErro;
import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.AppDirsFactory;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static io.github.pedroermarinho.hospital.Util.MsgErro.IGNORE_RESULT;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class DataBaseSettings {

    private static volatile DataBaseSettings instanceDataBaseSettings;
    private final AppDirs appDirs = AppDirsFactory.getInstance();
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
        final String path = appDirs.getUserDataDir("hospital", null, "pedroermarinho");
        IGNORE_RESULT(new File(path).mkdirs());
        final String pathDataBase = Paths.get(path, "settings.db").toString();
        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:sqlite:" + pathDataBase);
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
