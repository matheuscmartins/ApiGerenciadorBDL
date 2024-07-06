package br.com.brigadadoslobos.gerenciador.domains.dtos;

import br.com.brigadadoslobos.gerenciador.domains.Member;
import br.com.brigadadoslobos.gerenciador.domains.RoleDuty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDate;

public class RoleDutyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @Column(length = 100)
    private String roleName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    private Member member;

    public RoleDutyDTO(){
    }
    public RoleDutyDTO(RoleDuty obj){
        this.id = obj.getId();
        this.roleName = obj.getRoleName();
        this.startDate= obj.getStartDate();
        this.endDate= obj.getEndDate();
        this.member = obj.getMember();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
