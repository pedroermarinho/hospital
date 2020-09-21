package io.github.pedroermarinho.hospital.Model.Client.Client;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class ClientModel {


    private static final ClientDAOInterface dao = new ClientDAO();
    private final IntegerProperty idClient = new SimpleIntegerProperty(0);
    private final StringProperty cpf = new SimpleStringProperty();
    private final StringProperty cartaoSUS = new SimpleStringProperty();
    private final StringProperty identidade = new SimpleStringProperty();
    private final StringProperty nome = new SimpleStringProperty("");
    private final StringProperty mae = new SimpleStringProperty();
    private final StringProperty dataNascimento = new SimpleStringProperty();
    private final StringProperty sexo = new SimpleStringProperty();

    public static List<ClientModel> all() {
        return new ClientDAO().getAll();
    }

    public static ClientModel find(int id) {
        return new ClientDAO().get(id);
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

    public String getCpf() {
        return cpf.get();
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public StringProperty cpfProperty() {
        return cpf;
    }

    public String getCartaoSUS() {
        return cartaoSUS.get();
    }

    public void setCartaoSUS(String cartaoSUS) {
        this.cartaoSUS.set(cartaoSUS);
    }

    public StringProperty cartaoSUSProperty() {
        return cartaoSUS;
    }

    public String getIdentidade() {
        return identidade.get();
    }

    public void setIdentidade(String identidade) {
        this.identidade.set(identidade);
    }

    public StringProperty identidadeProperty() {
        return identidade;
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public String getMae() {
        return mae.get();
    }

    public void setMae(String mae) {
        this.mae.set(mae);
    }

    public StringProperty maeProperty() {
        return mae;
    }

    public String getDataNascimento() {
        return dataNascimento.get();
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento.set(dataNascimento);
    }

    public StringProperty dataNascimentoProperty() {
        return dataNascimento;
    }

    public String getSexo() {
        return sexo.get();
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
    }

    public StringProperty sexoProperty() {
        return sexo;
    }

    @Override
    public String toString() {
        return nome.get();
    }

    public Integer save() {
        if (idClient.getValue() != null && idClient.get() != 0) {
            if (find(idClient.get()) != null) {
                return dao.update(this);
            } else {
                return dao.create(this);
            }
        } else {
            return dao.create(this);
        }
    }

    public Integer delete() {
        if (find(idClient.get()) != null) {
            return dao.delete(getIdClient());
        } else {
            return null;
        }
    }

}
