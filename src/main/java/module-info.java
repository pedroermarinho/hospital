/**
 *
 */
module hospital
{
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    requires org.jfxtras.styles.jmetro;
    requires java.desktop;

    opens io.github.pedroermarinho.hospital to javafx.fxml, javafx.controls,javafx.graphics;
    opens io.github.pedroermarinho.hospital.Controller.Configuracao to javafx.fxml;
    opens io.github.pedroermarinho.hospital.Controller.Cadastros to javafx.fxml;
    opens io.github.pedroermarinho.hospital.Controller.Usuario to javafx.fxml;
    opens io.github.pedroermarinho.hospital.Controller.Util to javafx.fxml;
    opens io.github.pedroermarinho.hospital.Controller to javafx.fxml;
    opens io.github.pedroermarinho.hospital.Model.Cliente to javafx.base;
    opens io.github.pedroermarinho.hospital.Model.Configuracao_Local to javafx.base;
    opens io.github.pedroermarinho.hospital.Model.Usuario to javafx.base;

    exports io.github.pedroermarinho.hospital;
    exports io.github.pedroermarinho.hospital.Model.Cliente;
    exports io.github.pedroermarinho.hospital.Model.Configuracao_Local;
    exports io.github.pedroermarinho.hospital.Model.Usuario;
}
