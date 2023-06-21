package br.com.brigadadoslobos.gerenciador.domains;

import br.com.brigadadoslobos.gerenciador.domains.dtos.HeadQuarterDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class HeadQuarter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column( length = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public HeadQuarter() {
    }

    public HeadQuarter(Integer id, String description,Address address) {
        this.id = id;
        this.description = description;
        this.address = address;
    }
    public HeadQuarter(HeadQuarterDTO obj) {
        this.id = obj.getId();
        this.description = obj.getDescription();
        this.address = obj.getAddress();
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
