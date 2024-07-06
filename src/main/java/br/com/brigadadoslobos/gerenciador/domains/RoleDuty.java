package br.com.brigadadoslobos.gerenciador.domains;

import br.com.brigadadoslobos.gerenciador.domains.dtos.RoleDutyDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class RoleDuty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100)
    private String roleName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public RoleDuty(){
    }

    public RoleDuty(Integer id, String roleName, LocalDate startDate, LocalDate endDate, Member member) {
        this.id = id;
        this.roleName = roleName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
    }
    public RoleDuty(RoleDutyDTO obj){
        this.id = obj.getId();
        this.roleName = obj.getRoleName();
        this.startDate = obj.getStartDate();
        this.endDate = obj.getEndDate();
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
    public LocalDate getEndDate() { return endDate; }

    public void setEndDate(LocalDate endDate) {this.endDate = endDate; }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
