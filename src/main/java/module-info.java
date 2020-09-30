/**
 *
 */
module hospital
{
    requires javafx.fxml;
    requires javafx.controls;
    requires org.jfxtras.styles.jmetro;
    requires net.harawata.appdirs;
    requires sqlite.jdbc;
    requires itextpdf;
    requires html2pdf;
    requires org.apache.commons.io;
    requires java.desktop;
    requires java.sql;

    opens io.github.pedroermarinho.hospital to javafx.fxml, javafx.controls;
    opens io.github.pedroermarinho.hospital.Controller.Settings to javafx.fxml;
    opens io.github.pedroermarinho.hospital.Controller.Register to javafx.fxml;
    opens io.github.pedroermarinho.hospital.Controller.Util to javafx.fxml;
    opens io.github.pedroermarinho.hospital.Controller to javafx.fxml;
    opens io.github.pedroermarinho.hospital.Model.Client.Address to javafx.base;
    opens io.github.pedroermarinho.hospital.Model.Client.Client to javafx.base;
    opens io.github.pedroermarinho.hospital.Model.Client.Contact to javafx.base;
    opens io.github.pedroermarinho.hospital.Model.Client.Reception to javafx.base;
    opens io.github.pedroermarinho.hospital.Model.SettingsLocal.DataBase to javafx.base;

    exports io.github.pedroermarinho.hospital;
    exports io.github.pedroermarinho.hospital.Model.SettingsLocal.DataBase;
    exports io.github.pedroermarinho.hospital.Model.Client.Client;
    exports io.github.pedroermarinho.hospital.Model.Client.Address;
}
