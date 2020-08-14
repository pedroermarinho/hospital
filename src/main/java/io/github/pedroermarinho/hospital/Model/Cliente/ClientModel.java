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


    private final IntegerProperty idClient = new SimpleIntegerProperty(0);
    private final StringProperty cpf = new SimpleStringProperty();
    private final StringProperty cartaoSUS = new SimpleStringProperty();
    private final StringProperty identidade = new SimpleStringProperty();
    private final StringProperty nome = new SimpleStringProperty("");
    private final StringProperty mae = new SimpleStringProperty();
    private final StringProperty dataNascimento = new SimpleStringProperty();
    private final StringProperty sexo = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty especialidade = new SimpleStringProperty();
    private final StringProperty recepcao = new SimpleStringProperty();

    public int getIdClient() {
        return idClient.get();
    }

    public IntegerProperty idClientProperty() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient.set(idClient);
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

    public String getCartaoSUS() {
        return cartaoSUS.get();
    }

    public StringProperty cartaoSUSProperty() {
        return cartaoSUS;
    }

    public void setCartaoSUS(String cartaoSUS) {
        this.cartaoSUS.set(cartaoSUS);
    }

    public String getIdentidade() {
        return identidade.get();
    }

    public StringProperty identidadeProperty() {
        return identidade;
    }

    public void setIdentidade(String identidade) {
        this.identidade.set(identidade);
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

    public String getMae() {
        return mae.get();
    }

    public StringProperty maeProperty() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae.set(mae);
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

    public String getSexo() {
        return sexo.get();
    }

    public StringProperty sexoProperty() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
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

    public String getEspecialidade() {
        return especialidade.get();
    }

    public StringProperty especialidadeProperty() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade.set(especialidade);
    }

    public String getRecepcao() {
        return recepcao.get();
    }

    public StringProperty recepcaoProperty() {
        return recepcao;
    }

    public void setRecepcao(String recepcao) {
        this.recepcao.set(recepcao);
    }

    @Override
    public String toString() {
        return nome.get();
    }


    private static final ClientDAO dao=new ClientDAO();

    public static List<ClientModel> all() {

        return new ClientDAO().getClienteList();
    }

    public static ClientModel find(int pk) {
        return new ClientDAO().getClienteID(pk);
    }


    public void save() {
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
