package br.com.brigadadoslobos.gerenciador.domains;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Address {
    private Integer id;
    private String logradouro;
    private String number;
    private String adressComplement;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city ;

    public Address(){
    }

    public Address(Integer id, String logradouro, String number, String adressComplement) {
        this.id = id;
        this.logradouro = logradouro;
        this.number = number;
        this.adressComplement = adressComplement;
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
}
