package br.com.brigadadoslobos.gerenciador.domains;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String logradouro;
    private String number;
    private String adressComplement;
    private String postCode;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Address() {
    }

    public Address(Integer id, String logradouro, String number, String adressComplement, String postCode, City city) {
        this.id = id;
        this.logradouro = logradouro;
        this.number = number;
        this.adressComplement = adressComplement;
        this.postCode = postCode;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAdressComplement() {
        return adressComplement;
    }

    public void setAdressComplement(String adressComplement) {
        this.adressComplement = adressComplement;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
