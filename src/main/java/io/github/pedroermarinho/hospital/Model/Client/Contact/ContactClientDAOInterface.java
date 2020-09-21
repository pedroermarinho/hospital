package io.github.pedroermarinho.hospital.Model.Client.Contact;


import java.util.List;

public interface ContactClientDAOInterface {
    ContactClientModel get(int id);

    List<ContactClientModel> getAll();

    Integer create(ContactClientModel obj);

    Integer update(ContactClientModel obj);

    Integer delete(int id);
}
