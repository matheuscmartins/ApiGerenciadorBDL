package br.com.brigadadoslobos.gerenciador.domains;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class MemberPatch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public String description;
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate admissionDate;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "patch_id")
    private Patch patch;

    public MemberPatch() {
    }

    public MemberPatch(Integer id, String description, LocalDate admissionDate, Member member, Patch patch) {
        this.id = id;
        this.description = description;
        this.admissionDate = admissionDate;
        this.member = member;
        this.patch = patch;
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

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public br.com.brigadadoslobos.gerenciador.domains.Member getMember() {
        return member;
    }

    public void setMember(br.com.brigadadoslobos.gerenciador.domains.Member member) {
    }

    public Patch getPatch() {
        return patch;
    }

    public void setPatch(Patch patch) {
        this.patch = patch;
    }
}
