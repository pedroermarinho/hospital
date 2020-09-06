package io.github.pedroermarinho.hospital.Model.Client.Reception;


import java.util.List;

public interface ReceptionClientDAOInterface {
    ReceptionClientModel get(int id);

    ReceptionClientModel getClient(int id);

    List<ReceptionClientModel> getAll();

    Integer create(ReceptionClientModel obj);

    Integer update(ReceptionClientModel obj);

    Integer delete(int id);
}
