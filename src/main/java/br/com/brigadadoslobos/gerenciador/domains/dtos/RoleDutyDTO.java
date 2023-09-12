package br.com.brigadadoslobos.gerenciador.domains.dtos;

import br.com.brigadadoslobos.gerenciador.domains.Member;
import br.com.brigadadoslobos.gerenciador.domains.RoleDuty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import java.io.Serializable;

public class RoleDutyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @Column(length = 100)
    private String roleName;
    @JsonIgnore
    private Member member;

    public RoleDutyDTO(){
    }
    public RoleDutyDTO(RoleDuty obj){
        this.id = obj.getId();
        this.roleName = obj.getRoleName();
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
