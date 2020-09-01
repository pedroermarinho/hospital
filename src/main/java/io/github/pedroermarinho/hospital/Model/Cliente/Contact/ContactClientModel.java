package io.github.pedroermarinho.hospital.Model.Cliente.Contact;

import io.github.pedroermarinho.hospital.Model.Cliente.Address.AddressClientDAO;
import io.github.pedroermarinho.hospital.Model.Cliente.Address.AddressClientModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class ContactClientModel {

    private static final ContactClientDaoInterface dao = new ContactClientDAO();
    private final IntegerProperty idContactClient = new SimpleIntegerProperty();
    private final IntegerProperty idClient = new SimpleIntegerProperty();
    private final StringProperty telefone = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();

    public static List<AddressClientModel> all() {
        return new AddressClientDAO().getAll();
    }

    public static ContactClientModel find(int pk) {
        return new ContactClientDAO().get(pk);
    }

    public int getIdContactClient() {
        return idContactClient.get();
    }

    public void setIdContactClient(int idContactClient) {
        this.idContactClient.set(idContactClient);
    }

    public IntegerProperty idContactClientProperty() {
        return idContactClient;
    }

    public int getIdClient() {
        return idClient.get();
    }

    public void setIdClient(int idClient) {
        this.idClient.set(idClient);
    }

    public IntegerProperty idClientProperty() {
        return idClient;
    }

    public String getTelefone() {
        return telefone.get();
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void save() {
        if (idContactClient.get() != 0) {
            if (find(idContactClient.get()) != null) {
                dao.update(this);
            } else {
                dao.create(this);
            }
        } else {
            dao.create(this);
        }
    }

    public void delete() {
        if (find(idContactClient.get()) != null) {
            dao.delete(getIdContactClient());
        }
    }
}
