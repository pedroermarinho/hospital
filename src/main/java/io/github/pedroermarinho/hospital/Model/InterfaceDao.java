package io.github.pedroermarinho.hospital.Model;


import java.util.List;

public interface InterfaceDao {
     Object get(int id);
      List getAll();
     Integer create(Object obj);
     Integer update(Object obj);
     Integer delete(int id);
}
