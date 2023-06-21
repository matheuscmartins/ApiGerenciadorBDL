package br.com.brigadadoslobos.gerenciador.domains.dtos;

import br.com.brigadadoslobos.gerenciador.domains.Infraction;
import br.com.brigadadoslobos.gerenciador.domains.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDate;

public class InfractionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String type;
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate infractionDate;
    @JsonIgnore
    private Member member;

    public InfractionDTO() {
    }

    public InfractionDTO(Infraction obj) {
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
