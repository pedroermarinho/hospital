package io.github.pedroermarinho.hospital.Util.BD;

import io.github.pedroermarinho.hospital.Util.MsgErro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class Banco_de_Dados_Configuracao {

    protected Connection conexao = null;

    public Banco_de_Dados_Configuracao() {
        open();
    }

    public Connection open() {
        try {
//            conexao = DriverManager.getConnection("jdbc:mysql://localhost/clinica", "root", "");
            if (conexao == null) {
//                String path=System.getenv().get("home");
                String path = "/home";

                conexao = DriverManager.getConnection("jdbc:sqlite:Configuracao.db");
                System.out.println("jdbc:sqlite:" + path + "/Configuracao.db");
//                System.out.println("conexão realizada");

            }
            return conexao;
        } catch (SQLException ex) {
            close();
            System.out.println("erro");
            MsgErro.MessagemErroBD(ex, "open");
        } catch (Exception ex) {
            close();
            MsgErro.MessagemErroBD(ex, "open");
        }
        return null;
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
