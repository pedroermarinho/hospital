package io.github.pedroermarinho.hospital.Util;
import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.AppDirsFactory;

import java.io.File;
import java.nio.file.Paths;

import static io.github.pedroermarinho.hospital.Util.MsgErro.IGNORE_RESULT;


public class PathSettings {
    public static String pathSettings(){
        final AppDirs appDirs = AppDirsFactory.getInstance();
        final String path = appDirs.getUserDataDir("hospital", "2", "pedroermarinho");
        IGNORE_RESULT(new File(path).mkdirs());
        return path;
    }

    public static String pathDataBase(String nameDataBase){
        return Paths.get(pathSettings(), nameDataBase + ".sqlite3").toString();
    }


}
