package io.github.pedroermarinho.hospital.Util.BD;

import io.github.pedroermarinho.hospital.Model.Configuracao_Local.model_banco_de_dados;
import io.github.pedroermarinho.hospital.Util.MsgErro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public final class Banco_de_Dados_Cliente {

    public Connection conexao = null;
    private model_banco_de_dados banco_de_dados = null;

    public model_banco_de_dados getBanco_de_dados() {
        return banco_de_dados;
    }

    public void setBanco_de_dados(model_banco_de_dados banco_de_dados) {
        this.banco_de_dados = banco_de_dados;
    }

    public Connection open() {
        if (banco_de_dados != null) {
//          
            model_banco_de_dados obj = banco_de_dados;
            String url = obj.getPrefix() + "//" + obj.getHost() + ":" + obj.getPorts() + "/" + obj.getDataBase()+".db";
//        System.out.println(url);
            try {
                if (conexao == null) {
//                    System.out.println(obj.getUser());
//                    System.out.println(obj.getPassword());
                    if (obj.getPrefix().equals("jdbc:sqlite:")) {
//                        String path=System.getenv().get("SNAP_USER_DATA");
//                        System.out.println("path->>>>>"+path);
                        conexao = DriverManager.getConnection(obj.getPrefix() + obj.getDataBase()+".db");
                        System.out.println("Sqlite->" + obj.getPrefix() + "/" + obj.getDataBase());
                    } else {
                        conexao = DriverManager.getConnection(url, obj.getUser(), obj.getPassword());
                    }
//                    System.out.println("conexão realizada");
                }
                return conexao;
            } catch (SQLException ex) {
                close();
                System.out.println("erro");
                MsgErro.MessagemErroBD(ex, "open");
                return null;
            } catch (Exception ex) {
                close();
                MsgErro.MessagemErroBD(ex, "open");
                return null;
            }
        } else {
            return conexao;
        }

    }

    public void close() {

        try {
            if (conexao != null) {
                conexao.close();
                conexao = null;
            }
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "close");
        } catch (Exception ex) {
            MsgErro.MessagemErroBD(ex, "close");
        }

    }

}
