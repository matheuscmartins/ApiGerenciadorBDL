package br.com.brigadadoslobos.gerenciador.domains.dtos;

import br.com.brigadadoslobos.gerenciador.domains.Address;
import br.com.brigadadoslobos.gerenciador.domains.City;
import br.com.brigadadoslobos.gerenciador.domains.Uf;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CityDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotNull(message = "O campo Nome é requerido")
    private String name;
    @NotNull(message = "O campo Estado é requerido")
    private Uf uf;
    @JsonIgnore
    private List<Address> addresses = new ArrayList<>();

    public CityDTO(){
        super();
    }

    public CityDTO(City obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.uf = obj.getUf();
        this.addresses = obj.getAddresses().stream().map( x -> x
        ).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
