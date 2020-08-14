/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Model.Cliente;

import io.github.pedroermarinho.hospital.Model.Cliente.ClienteDAO.AddressClientDAO;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class AddressClientModel {


    private final IntegerProperty idAddressClient = new SimpleIntegerProperty();
    private final IntegerProperty idClient = new SimpleIntegerProperty();
    private final StringProperty pais = new SimpleStringProperty("Brasil");
    private final StringProperty estado = new SimpleStringProperty("Amazonas");
    private final StringProperty cidade = new SimpleStringProperty("Eirunepé");
    private final StringProperty rua = new SimpleStringProperty();
    private final StringProperty bairro = new SimpleStringProperty();
    private final IntegerProperty numeroCasa = new SimpleIntegerProperty();
    private final StringProperty telefone = new SimpleStringProperty();
    private final StringProperty complemento = new SimpleStringProperty();

    public int getIdAddressClient() {
        return idAddressClient.get();
    }

    public IntegerProperty idAddressClientProperty() {
        return idAddressClient;
    }

    public void setIdAddressClient(int idAddressClient) {
        this.idAddressClient.set(idAddressClient);
    }

    public int getIdClient() {
        return idClient.get();
    }

    public IntegerProperty idClientProperty() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient.set(idClient);
    }

    public String getPais() {
        return pais.get();
    }

    public StringProperty paisProperty() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais.set(pais);
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public String getCidade() {
        return cidade.get();
    }

    public StringProperty cidadeProperty() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade.set(cidade);
    }

    public String getRua() {
        return rua.get();
    }

    public StringProperty ruaProperty() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua.set(rua);
    }

    public String getBairro() {
        return bairro.get();
    }

    public StringProperty bairroProperty() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro.set(bairro);
    }

    public int getNumeroCasa() {
        return numeroCasa.get();
    }

    public IntegerProperty numeroCasaProperty() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa.set(numeroCasa);
    }

    public String getTelefone() {
        return telefone.get();
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public String getComplemento() {
        return complemento.get();
    }

    public StringProperty complementoProperty() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento.set(complemento);
    }

    public static List<AddressClientModel> all() {
        return new AddressClientDAO().getAddressClientList();
    }

    public static AddressClientModel find(int pk) {
        return new AddressClientDAO().getAddressClientID(pk);
    }


    private static final AddressClientDAO dao = new AddressClientDAO();
    public void save() {
        if (idAddressClient.get() != 0) {
            if (find(idAddressClient.get()) != null) {
                dao.updateAddressClient(this);
            } else {
                dao.creatAddressClient(this);
            }
        } else {
            dao.creatAddressClient(this);
        }
    }

    public void delete() {
        if (find(idAddressClient.get()) != null) {
            dao.deleteAddressClient(this);
        }
    }

}
