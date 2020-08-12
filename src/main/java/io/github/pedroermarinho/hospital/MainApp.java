package io.github.pedroermarinho.hospital;

import io.github.pedroermarinho.hospital.Model.Configuracao_Local.DataBaseModel;
import io.github.pedroermarinho.hospital.Model.Usuario.UserModel;
import io.github.pedroermarinho.hospital.Util.BD.DataBaseCliente;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static io.github.pedroermarinho.hospital.Util.BD.Tables.createTable;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com > marinho
 */
public class MainApp extends Application {

    private final Dados DadosData = new Dados(this);
    private final ChamadasDeTela telas = new ChamadasDeTela();
    private final DataBaseCliente banco_de_dados = DataBaseCliente.instance();
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

    public Dados getDadosData() {
        return DadosData;
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

    public DataBaseModel getDados_db() {
        return dados_db;
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

            Image image = new Image("/io/github/pedroermarinho/hospital/Icons/icon.png");

            if (!getDadosData().userDataNotThread().isEmpty()) {
                if (telas.usuario() != null) {
                    telas.primeriaCena.getIcons().add(image);
                    getDadosData().sincronizarBD();
                    telas.PalcoPrincipal();
                    telas.MenuTop();
                    telas.CentralTexto();
                }
            } else {
                setUsuario(telas.CadastroUsuario());
                if (getUsuario() != null) {
                    if (telas.usuario() != null) {
                        getDadosData().sincronizarBD();
                        telas.PalcoPrincipal();
                        telas.MenuTop();
                        telas.CentralTexto();
                    }
                }

            }
        }

    }

}
