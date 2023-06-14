package br.com.brigadadoslobos.gerenciador.domains.dtos;

import br.com.brigadadoslobos.gerenciador.domains.Country;
import br.com.brigadadoslobos.gerenciador.domains.Uf;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CountryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    @JsonBackReference
    private List<Uf> ufs = new ArrayList<>();

    public CountryDTO(){
        super();
    }
    public CountryDTO(Country obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.ufs = obj.getUfs().stream().map( x -> x
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

    public List<Uf> getUfs() {
        return ufs;
    }

    public void setUfs(List<Uf> ufs) {
        this.ufs = ufs;
    }
}
