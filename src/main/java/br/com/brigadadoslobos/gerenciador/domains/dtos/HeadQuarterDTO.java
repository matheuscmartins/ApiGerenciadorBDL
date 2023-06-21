package br.com.brigadadoslobos.gerenciador.domains.dtos;

import br.com.brigadadoslobos.gerenciador.domains.Address;
import br.com.brigadadoslobos.gerenciador.domains.HeadQuarter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class HeadQuarterDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotEmpty(message = "O campo Descrição é requerido")
    @Column( length = 100)
    private String description;
    private Address address;
    public HeadQuarterDTO(){
        super();
    }

    public HeadQuarterDTO(HeadQuarter obj) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
