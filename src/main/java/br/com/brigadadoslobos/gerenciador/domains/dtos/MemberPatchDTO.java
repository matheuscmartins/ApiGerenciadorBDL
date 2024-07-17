package br.com.brigadadoslobos.gerenciador.domains.dtos;

import br.com.brigadadoslobos.gerenciador.domains.Member;
import br.com.brigadadoslobos.gerenciador.domains.MemberPatch;
import br.com.brigadadoslobos.gerenciador.domains.Patch;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class MemberPatchDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate admissionDate;
    private Member member;
    private Patch patch;

    public MemberPatchDTO(){
        super();
    }

    public MemberPatchDTO(MemberPatch obj) {
        this.id = obj.getId();
        this.description = obj.getDescription();
        this.admissionDate = obj.getAdmissionDate();
        this.member = obj.getMember();
        this.patch = obj.getPatch();
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Patch getPatch() {
        return patch;
    }

    public void setPatch(Patch patch) {
        this.patch = patch;
    }
}
