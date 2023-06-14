package br.com.brigadadoslobos.gerenciador.domains.dtos;

import br.com.brigadadoslobos.gerenciador.domains.City;
import br.com.brigadadoslobos.gerenciador.domains.Country;
import br.com.brigadadoslobos.gerenciador.domains.Uf;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UfDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String acronym;
    private Country country;
    @JsonIgnore
    private List<City> citys = new ArrayList<>();

    public UfDTO(){
        super();
    }
    public UfDTO(Uf obj) {
        this.id = obj.getId();
        this.name= obj.getName();
        this.acronym = obj.getAcronym();
        this.country = obj.getCountry();
        this.citys = obj.getCitys().stream().map( x -> x
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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<City> getCitys() {
        return citys;
    }

    public void setCitys(List<City> citys) {
        this.citys = citys;
    }
}
