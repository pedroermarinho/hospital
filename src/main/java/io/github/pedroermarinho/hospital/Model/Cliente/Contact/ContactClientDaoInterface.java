package io.github.pedroermarinho.hospital.Model.Cliente.Contact;


import java.util.List;

public interface ContactClientDaoInterface {
    ContactClientModel get(int id);

    List<ContactClientModel> getAll();

    Integer create(ContactClientModel obj);

    Integer update(ContactClientModel obj);

    Integer delete(int id);
}
