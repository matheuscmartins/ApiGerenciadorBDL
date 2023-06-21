package br.com.brigadadoslobos.gerenciador.domains;

import br.com.brigadadoslobos.gerenciador.domains.dtos.InfractionDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Infraction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate infractionDate;
    private String description;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Infraction() {
    }

    public Infraction(Integer id, String type, String description, LocalDate infractionDate, Member member) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.infractionDate = infractionDate;
        this.member = member;
    }

    public Infraction(InfractionDTO obj) {
        this.id = obj.getId();
        this.type = obj.getType();
        this.description = obj.getDescription();
        this.infractionDate = obj.getInfractionDate();
        this.member = obj.getMember();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getInfractionDate() {
        return infractionDate;
    }

    public void setInfractionDate(LocalDate infractionDate) {
        this.infractionDate = infractionDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
