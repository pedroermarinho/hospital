package io.github.pedroermarinho.hospital.Model.Cliente.Address;


import java.util.List;

public interface AddressClientDaoInterface {
    AddressClientModel get(int id);

    List<AddressClientModel> getAll();

    Integer create(AddressClientModel obj);

    Integer update(AddressClientModel obj);

    Integer delete(int id);
}
