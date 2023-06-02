package br.com.brigadadoslobos.gerenciador.domains;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "country")
    private List<Uf> ufs = new ArrayList<>();

    public Country() {
    }

    public Country(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public List<Uf> getUfss() {
        return ufs;
    }

    public void setChamados(List<Uf> ufs) {
        this.ufs = ufs;
    }
}
