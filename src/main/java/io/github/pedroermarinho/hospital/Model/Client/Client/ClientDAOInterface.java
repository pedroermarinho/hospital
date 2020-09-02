package io.github.pedroermarinho.hospital.Model.Client.Client;


import java.util.List;

public interface ClientDAOInterface {
    ClientModel get(int id);

    List<ClientModel> getAll();

    Integer create(ClientModel obj);

    Integer update(ClientModel obj);

    Integer delete(int id);
}
