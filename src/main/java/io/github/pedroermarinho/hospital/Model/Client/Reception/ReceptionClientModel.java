package io.github.pedroermarinho.hospital.Model.Client.Reception;

import io.github.pedroermarinho.hospital.Model.Client.Address.AddressClientDAO;
import io.github.pedroermarinho.hospital.Model.Client.Address.AddressClientModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class ReceptionClientModel {

    private static final ReceptionClientDAOInterface dao = new ReceptionClientDAO();
    private final IntegerProperty idReceptionClient = new SimpleIntegerProperty();
    private final IntegerProperty idClient = new SimpleIntegerProperty();
    private final StringProperty especialidade = new SimpleStringProperty();
    private final StringProperty recepcao = new SimpleStringProperty();
    private final StringProperty modificationDate = new SimpleStringProperty();

    public static List<AddressClientModel> all() {
        return new AddressClientDAO().getAll();
    }

    public static AddressClientModel find(int pk) {
        return new AddressClientDAO().get(pk);
    }

    public int getIdReceptionClient() {
        return idReceptionClient.get();
    }

    public void setIdReceptionClient(int idReceptionClient) {
        this.idReceptionClient.set(idReceptionClient);
    }

    public IntegerProperty idReceptionClientProperty() {
        return idReceptionClient;
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

    public String getEspecialidade() {
        return especialidade.get();
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade.set(especialidade);
    }

    public StringProperty especialidadeProperty() {
        return especialidade;
    }

    public String getRecepcao() {
        return recepcao.get();
    }

    public void setRecepcao(String recepcao) {
        this.recepcao.set(recepcao);
    }

    public StringProperty recepcaoProperty() {
        return recepcao;
    }

    public String getModificationDate() {
        return modificationDate.get();
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate.set(modificationDate);
    }

    public StringProperty modificationDateProperty() {
        return modificationDate;
    }

    public void save() {
        if (idReceptionClient.get() != 0) {
            if (find(idReceptionClient.get()) != null) {
                dao.update(this);
            } else {
                dao.create(this);
            }
        } else {
            dao.create(this);
        }
    }

    public void delete() {
        if (find(idReceptionClient.get()) != null) {
            dao.delete(getIdReceptionClient());
        }
    }
}
