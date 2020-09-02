package io.github.pedroermarinho.hospital.Model.SettingsLocal.DataBase;


import java.util.List;

public interface DataBaseDAOInterface {
    DataBaseModel get(int id);

    List<DataBaseModel> getAll();

    Integer create(DataBaseModel obj);

    Integer update(DataBaseModel obj);

    Integer delete(int id);
}
