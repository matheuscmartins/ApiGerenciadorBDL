package br.com.brigadadoslobos.gerenciador.domains.dtos.summarys;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class RoleDutySummaryDTO implements Serializable {

    private Integer id;
    private String roleName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    private MemberSummaryDTO member;

    public RoleDutySummaryDTO(Integer id, String roleName,
                              LocalDate startDate, LocalDate endDate,
                              Integer memberId, String firstName, String lastName, String nickName,
                              Integer headQuarterId, String headQuarterDescription) {
        this.id = id;
        this.roleName = roleName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = new MemberSummaryDTO(memberId, firstName, lastName, nickName, headQuarterId, headQuarterDescription);
    }

    public Integer getId() { return id; }
    public String getRoleName() { return roleName; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public MemberSummaryDTO getMember() { return member; }
}
