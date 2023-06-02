package br.com.brigadadoslobos.gerenciador.domains;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class HeadQuarter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;

    public HeadQuarter() {
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
