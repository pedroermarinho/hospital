package io.github.pedroermarinho.hospital.Model.Cliente;

import io.github.pedroermarinho.hospital.Model.Cliente.ClienteDAO.ClientDAO;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class ClientModel {

    private static final ClientDAO dao=new ClientDAO();
    private final IntegerProperty idClient = new SimpleIntegerProperty(0);
    private final StringProperty nome = new SimpleStringProperty("teste");
    private final StringProperty cpf = new SimpleStringProperty("1212331");
    private final StringProperty mae = new SimpleStringProperty("teste");
    private final StringProperty pai = new SimpleStringProperty("teste");
    private final StringProperty dataNascimento = new SimpleStringProperty("2000-01-02");
    private final StringProperty cartaoSUS = new SimpleStringProperty();
    private final StringProperty sexo = new SimpleStringProperty();
    private final StringProperty foto = new SimpleStringProperty("/e");
    private final StringProperty email = new SimpleStringProperty("teste");



    public static List<ClientModel> all() {

        return new ClientDAO().getClienteList();
    }

    public static ClientModel find(int pk) {
        return new ClientDAO().getClienteID(pk);
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

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getCpf() {
        return cpf.get();
    }

    public StringProperty cpfProperty() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public String getMae() {
        return mae.get();
    }

    public StringProperty maeProperty() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae.set(mae);
    }

    public String getPai() {
        return pai.get();
    }

    public StringProperty paiProperty() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai.set(pai);
    }

    public String getDataNascimento() {
        return dataNascimento.get();
    }

    public StringProperty dataNascimentoProperty() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento.set(dataNascimento);
    }

    public String getCartaoSUS() {
        return cartaoSUS.get();
    }

    public StringProperty cartaoSUSProperty() {
        return cartaoSUS;
    }

    public void setCartaoSUS(String cartaoSUS) {
        this.cartaoSUS.set(cartaoSUS);
    }

    public String getSexo() {
        return sexo.get();
    }

    public StringProperty sexoProperty() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
    }

    public String getFoto() {
        return foto.get();
    }

    public StringProperty fotoProperty() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto.set(foto);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    @Override
    public String toString() {
        return idClient.get() + ") " + nome.get() + "->" + cpf.get();
    }

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (idClient.getValue() != null && idClient.get() != 0) {
            if (find(idClient.get()) != null) {
                dao.updateCliente(this);
            } else {
                dao.creatCliente(this);
            }
        } else {
            dao.creatCliente(this);
        }
    }

    public void delete() {
        if (find(idClient.get()) != null) {
            dao.deleteCliente(this);
        }
    }

}
