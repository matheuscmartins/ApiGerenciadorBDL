package br.com.brigadadoslobos.gerenciador.domains;

import br.com.brigadadoslobos.gerenciador.domains.dtos.InfractionDTO;
import br.com.brigadadoslobos.gerenciador.domains.enums.InfractionType;
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
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate infractionDate;
    private String description;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(nullable = false)
    private InfractionType infractionType;

    public Infraction() {
    }

    public Infraction(Integer id, String description, LocalDate infractionDate, Member member,
                      InfractionType infractionType) {
        this.id = id;
        this.description = description;
        this.infractionDate = infractionDate;
        this.member = member;
        this.infractionType = InfractionType.valueOf(infractionType.getId());
    }

    public Infraction(InfractionDTO obj) {
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

    public void addInfractionType(InfractionType infractionType) {
        this.infractionType = InfractionType.valueOf(infractionType.getId());
    }
}
