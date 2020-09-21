package io.github.pedroermarinho.hospital.Model.Client.Address;


import java.util.List;

public interface AddressClientDAOInterface {
    AddressClientModel get(int id);

    List<AddressClientModel> getAll();

    Integer create(AddressClientModel obj);

    Integer update(AddressClientModel obj);

    Integer delete(int id);
}
