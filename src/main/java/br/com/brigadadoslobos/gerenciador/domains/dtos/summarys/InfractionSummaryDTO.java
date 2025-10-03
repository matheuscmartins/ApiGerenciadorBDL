package br.com.brigadadoslobos.gerenciador.domains.dtos.summarys;

import br.com.brigadadoslobos.gerenciador.domains.enums.InfractionType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class InfractionSummaryDTO implements Serializable {

    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate infractionDate;

    private InfractionType infractionType;

    private MemberSummaryDTO member;

    public InfractionSummaryDTO(Integer id, LocalDate infractionDate, InfractionType infractionType,
                                Integer memberId, String firstName, String lastName, String nickName,
                                Integer headQuarterId, String headQuarterDescription) {
        this.id = id;
        this.infractionDate = infractionDate;
        this.infractionType = infractionType;
        this.member = new MemberSummaryDTO(memberId, firstName, lastName, nickName, headQuarterId, headQuarterDescription);
    }

    public Integer getId() { return id; }
    public LocalDate getInfractionDate() { return infractionDate; }
    public InfractionType getInfractionType() { return infractionType; }
    public MemberSummaryDTO getMember() { return member; }
}
