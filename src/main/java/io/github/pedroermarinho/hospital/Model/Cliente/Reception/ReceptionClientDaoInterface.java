package io.github.pedroermarinho.hospital.Model.Cliente.Reception;


import java.util.List;

public interface ReceptionClientDaoInterface {
    ReceptionClientModel get(int id);

    List<ReceptionClientModel> getAll();

    Integer create(ReceptionClientModel obj);

    Integer update(ReceptionClientModel obj);

    Integer delete(int id);
}
