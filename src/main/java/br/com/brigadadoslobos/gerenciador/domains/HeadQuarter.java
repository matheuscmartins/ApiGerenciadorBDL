package br.com.brigadadoslobos.gerenciador.domains;

import java.util.HashSet;
import java.util.Set;

public class HeadQuarter {
    private Integer id;
    private String description;
    private Set<Address> address = new HashSet<>();

    public HeadQuarter(){

    }

    public HeadQuarter(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
