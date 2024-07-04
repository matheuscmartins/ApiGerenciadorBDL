package br.com.brigadadoslobos.gerenciador.domains.dtos;

import br.com.brigadadoslobos.gerenciador.domains.Infraction;
import br.com.brigadadoslobos.gerenciador.domains.Member;
import br.com.brigadadoslobos.gerenciador.domains.enums.InfractionType;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDate;

public class InfractionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate infractionDate;
    private Member member;
    @Column(nullable = false)
    private InfractionType infractionType;

    public InfractionDTO() {
    }

    public InfractionDTO(Infraction obj) {
        this.id = obj.getId();
        this.description = obj.getDescription();
        this.infractionDate = obj.getInfractionDate();
        this.member = obj.getMember();
        this.infractionType = obj.getInfractionType();
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

    public InfractionType getInfractionType() {
        return infractionType;
    }

    public void setInfractionType(InfractionType infractionType) {
        this.infractionType = infractionType;
    }
}
