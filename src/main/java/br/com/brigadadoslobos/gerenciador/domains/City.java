package br.com.brigadadoslobos.gerenciador.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class City implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 100)
    @NotEmpty(message = "O campo nome da cidade é obrigatorio")
    private String name;
    @ManyToOne
    @JoinColumn(name = "uf_id")
    private Uf uf;
    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private List<Address> addresses = new ArrayList<>();

    public City() {
    }

    public City(Integer id, String name, Uf uf) {
        this.id = id;
        this.name = name;
        this.uf = uf;
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
