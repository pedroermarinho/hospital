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


    private static final AddressClientDAO dao = new AddressClientDAO();
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

    public static List<AddressClientModel> all() {
        return new AddressClientDAO().getAddressClientList();
    }

    public static AddressClientModel find(int pk) {
        return new AddressClientDAO().getAddressClientID(pk);
    }

    public int getIdAddressClient() {
        return idAddressClient.get();
    }

    public void setIdAddressClient(int idAddressClient) {
        this.idAddressClient.set(idAddressClient);
    }

    public IntegerProperty idAddressClientProperty() {
        return idAddressClient;
    }

    public int getIdClient() {
        return idClient.get();
    }

    public void setIdClient(int idClient) {
        this.idClient.set(idClient);
    }

    public IntegerProperty idClientProperty() {
        return idClient;
    }

    public String getPais() {
        return pais.get();
    }

    public void setPais(String pais) {
        this.pais.set(pais);
    }

    public StringProperty paisProperty() {
        return pais;
    }

    public String getEstado() {
        return estado.get();
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public String getCidade() {
        return cidade.get();
    }

    public void setCidade(String cidade) {
        this.cidade.set(cidade);
    }

    public StringProperty cidadeProperty() {
        return cidade;
    }

    public String getRua() {
        return rua.get();
    }

    public void setRua(String rua) {
        this.rua.set(rua);
    }

    public StringProperty ruaProperty() {
        return rua;
    }

    public String getBairro() {
        return bairro.get();
    }

    public void setBairro(String bairro) {
        this.bairro.set(bairro);
    }

    public StringProperty bairroProperty() {
        return bairro;
    }

    public int getNumeroCasa() {
        return numeroCasa.get();
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa.set(numeroCasa);
    }

    public IntegerProperty numeroCasaProperty() {
        return numeroCasa;
    }

    public String getTelefone() {
        return telefone.get();
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }

    public String getComplemento() {
        return complemento.get();
    }

    public void setComplemento(String complemento) {
        this.complemento.set(complemento);
    }

    public StringProperty complementoProperty() {
        return complemento;
    }

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
