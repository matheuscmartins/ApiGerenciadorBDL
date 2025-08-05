package br.com.brigadadoslobos.gerenciador.domains.dtos;

import br.com.brigadadoslobos.gerenciador.domains.Address;
import br.com.brigadadoslobos.gerenciador.domains.City;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @Column(nullable = false, length = 100)
    @NotEmpty(message = "O campo logradouro (rua, av. ou travessa) é obrigatorio")
    private String logradouro;
    @Column( length = 10)
    private String number;
    @Column( length = 50)
    private String adressComplement;
    @Column( length = 10)
    private String postCode;
    private City city;

    public AddressDTO(){
        super();
    }
    public AddressDTO(Address obj) {
        this.id = obj.getId();
        this.logradouro = obj.getLogradouro();
        this.number = obj.getNumber();
        this.adressComplement = obj.getAdressComplement();
        this.postCode = obj.getPostCode();
        this.city = obj.getCity();
    }
    public AddressDTO(Integer id, String logradouro, String number, String postCode, City city) {
        this.id = id;
        this.logradouro = logradouro;
        this.number = number;
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
