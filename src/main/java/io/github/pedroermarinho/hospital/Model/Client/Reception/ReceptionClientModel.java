package io.github.pedroermarinho.hospital.Model.Client.Reception;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.List;

public class ReceptionClientModel {


    private static final ReceptionClientDAOInterface dao = new ReceptionClientDAO();
    private final IntegerProperty idReceptionClient = new SimpleIntegerProperty();
    private final IntegerProperty idClient = new SimpleIntegerProperty();
    private final StringProperty especialidade = new SimpleStringProperty();
    private final StringProperty recepcao = new SimpleStringProperty();
    private final StringProperty modificationDate = new SimpleStringProperty();

    public static List<ReceptionClientModel> all() {
        return new ReceptionClientDAO().getAll();
    }

    public static ReceptionClientModel find(int id) {
        return new ReceptionClientDAO().get(id);
    }

    public static List<ReceptionClientModel> findData(LocalDate date) {
        return new ReceptionClientDAO().getAllData(date);
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

    public Integer save() {
        if (idClient.getValue() != null && idClient.get() != 0) {
            if (find(idClient.get()) != null && find(idClient.get()).getIdReceptionClient() != 0) {
                return dao.update(this);
            } else {
                return dao.create(this);
            }
        } else {
            return dao.create(this);
        }
    }

    public Integer delete() {
        if (find(idClient.get()) != null) {
            return dao.delete(getIdReceptionClient());
        } else {
            return null;
        }
    }
}
