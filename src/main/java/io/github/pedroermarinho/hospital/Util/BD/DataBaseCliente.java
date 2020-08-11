package io.github.pedroermarinho.hospital.Util.BD;

import io.github.pedroermarinho.hospital.Model.Configuracao_Local.DataBaseModel;
import io.github.pedroermarinho.hospital.Util.MsgErro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public final class DataBaseCliente {

    private Connection conexao;

    private static volatile DataBaseCliente instanciBancoDadosCliente;


    public static DataBaseCliente instance() {
        if (instanciBancoDadosCliente == null) {
            synchronized (DataBaseCliente.class) {
                if (instanciBancoDadosCliente == null) {
                    instanciBancoDadosCliente = new DataBaseCliente();
                }
            }
        }
        return instanciBancoDadosCliente;
    }

    public Connection open(DataBaseModel dataBaseModel) {
        if (dataBaseModel != null) {

            String url = dataBaseModel.getPrefix() + "//" + dataBaseModel.getHost() + ":" + dataBaseModel.getPorts() + "/" + dataBaseModel.getDataBase()+".db";
//        System.out.println(url);
            try {
                if (conexao == null) {
//                    System.out.println(obj.getUser());
//                    System.out.println(obj.getPassword());
                    if (dataBaseModel.getPrefix().equals("jdbc:sqlite:")) {
//                        String path=System.getenv().get("SNAP_USER_DATA");
//                        System.out.println("path->>>>>"+path);
                        conexao = DriverManager.getConnection(dataBaseModel.getPrefix() + dataBaseModel.getDataBase()+".db");
                        System.out.println("Sqlite->" + dataBaseModel.getPrefix() + "/" + dataBaseModel.getDataBase());
                    } else {
                        conexao = DriverManager.getConnection(url, dataBaseModel.getUser(), dataBaseModel.getPassword());
                    }
//                    System.out.println("conexão realizada");
                }
                return conexao;
            } catch (SQLException ex) {
                close();
                System.out.println("erro");
                MsgErro.MessagemErroBD(ex, "open");
                return null;
            }
        } else {
            return conexao;
        }
    }
    public Connection getConnection() {
      return  conexao;
    }
    public void close() {
        try {
            if (conexao != null) {
                conexao.close();
                conexao = null;
            }
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "close");
        }
    }

















//
//    public Connection conexao = null;
//    private DataBaseModel banco_de_dados = null;
//
//
//    public Connection open() {
//        if (banco_de_dados != null) {
////
//            model_banco_de_dados obj = banco_de_dados;
//            String url = obj.getPrefix() + "//" + obj.getHost() + ":" + obj.getPorts() + "/" + obj.getDataBase()+".db";
////        System.out.println(url);
//            try {
//                if (conexao == null) {
////                    System.out.println(obj.getUser());
////                    System.out.println(obj.getPassword());
//                    if (obj.getPrefix().equals("jdbc:sqlite:")) {
////                        String path=System.getenv().get("SNAP_USER_DATA");
////                        System.out.println("path->>>>>"+path);
//                        conexao = DriverManager.getConnection(obj.getPrefix() + obj.getDataBase()+".db");
//                        System.out.println("Sqlite->" + obj.getPrefix() + "/" + obj.getDataBase());
//                    } else {
//                        conexao = DriverManager.getConnection(url, obj.getUser(), obj.getPassword());
//                    }
////                    System.out.println("conexão realizada");
//                }
//                return conexao;
//            } catch (SQLException ex) {
//                close();
//                System.out.println("erro");
//                MsgErro.MessagemErroBD(ex, "open");
//                return null;
//            }
//        } else {
//            return conexao;
//        }
//
//    }



}
