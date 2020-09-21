package io.github.pedroermarinho.hospital;

import io.github.pedroermarinho.hospital.Model.SettingsLocal.DataBase.DataBaseModel;
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

    private final DataBaseClient dataBaseClient = DataBaseClient.instance();
    private final ChamadasDeTela telas = new ChamadasDeTela();
    final DataBaseSettings dataBaseSettings = DataBaseSettings.instance();

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

    public void initApp(DataBaseModel dataBaseModel){
        dataBaseClient.open(dataBaseModel);
        if (dataBaseClient.getConnection() != null) {
            createTable(dataBaseClient.getConnection(), dataBaseModel);
            telas.MenuTop();
            telas.CentralText();
        }
    }

    @Override
    public void start(Stage stage) {
        dataBaseSettings.open();

        this.telas.primeriaCena = stage;
        this.telas.primeriaCena.setTitle("Hospital");
        telas.setMainApp(this);
        Image image = new Image(getClass().getResource("/io/github/pedroermarinho/hospital/Icons/icon.png").toString());

        telas.primeriaCena.getIcons().add(image);
        telas.PalcoPrincipal();
        telas.SelectDataBase();

    }

}
