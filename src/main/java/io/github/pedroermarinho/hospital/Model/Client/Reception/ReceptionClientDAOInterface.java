package io.github.pedroermarinho.hospital.Model.Client.Reception;


import java.time.LocalDate;
import java.util.List;

public interface ReceptionClientDAOInterface {
    ReceptionClientModel get(int id);

    public List<ReceptionClientModel> getAllData(LocalDate date);

    List<ReceptionClientModel> getAll();

    Integer create(ReceptionClientModel obj);

    Integer update(ReceptionClientModel obj);

    Integer delete(int id);
}
