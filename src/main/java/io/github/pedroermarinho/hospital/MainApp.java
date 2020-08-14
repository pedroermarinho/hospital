package io.github.pedroermarinho.hospital;

import io.github.pedroermarinho.hospital.Model.Configuracao_Local.DataBaseModel;
import io.github.pedroermarinho.hospital.Model.Usuario.UserModel;
import io.github.pedroermarinho.hospital.Util.BD.DataBaseClient;
import io.github.pedroermarinho.hospital.Util.BD.DataBaseSettings;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static io.github.pedroermarinho.hospital.Util.BD.Tables.createTable;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com > marinho
 */
public class MainApp extends Application {
    private final Dados data = new Dados();
    private final DataBaseClient banco_de_dados = DataBaseClient.instance();
    private final ChamadasDeTela telas = new ChamadasDeTela();
    final DataBaseSettings dataBaseSettings = DataBaseSettings.instance();
    private UserModel usuario = null;
    private DataBaseModel dados_db = new DataBaseModel();

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public ChamadasDeTela getTelas() {
        return telas;
    }

    public UserModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }


    public void setDados_db(DataBaseModel dados_db) {
        this.dados_db = dados_db;
    }

    @Override
    public void start(Stage stage) {
        System.out.println("start");

        this.telas.primeriaCena = stage;
        this.telas.primeriaCena.setTitle("Clinica");
        telas.setMainApp(this);

        setDados_db(telas.SelectBanco_de_Dados());
        banco_de_dados.open(dados_db);
        if (banco_de_dados.getConnection() != null) {
            createTable(banco_de_dados.getConnection(), dados_db);

            Image image = new Image(getClass().getResource("/io/github/pedroermarinho/hospital/Icons/icon.png").toString());

            if (!data.getUserData().isEmpty()) {
                if (telas.usuario() != null) {
                    telas.primeriaCena.getIcons().add(image);
                    telas.PalcoPrincipal();
                    telas.MenuTop();
                    telas.CentralTexto();
                }
            } else {
                setUsuario(telas.CadastroUsuario());
                if (getUsuario() != null) {
                    if (telas.usuario() != null) {
                        telas.PalcoPrincipal();
                        telas.MenuTop();
                        telas.CentralTexto();
                    }
                }

            }
        }

    }

}
