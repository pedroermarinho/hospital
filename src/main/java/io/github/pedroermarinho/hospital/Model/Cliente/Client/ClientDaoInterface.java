package io.github.pedroermarinho.hospital.Model.Cliente.Client;


import java.util.List;

public interface ClientDaoInterface {
    ClientModel get(int id);

    List<ClientModel> getAll();

    Integer create(ClientModel obj);

    Integer update(ClientModel obj);

    Integer delete(int id);
}
