package br.com.brigadadoslobos.gerenciador.domains.dtos.summarys;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class MemberPatchSummaryDTO implements Serializable {

    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate admissionDate;

    private MemberSummaryDTO member;

    private PatchSummary patch;

    public MemberPatchSummaryDTO(Integer id, LocalDate admissionDate,
                                 Integer memberId, String firstName, String lastName, String nickName,
                                 Integer headQuarterId, String headQuarterDescription,
                                 Integer patchId, String patchName) {
        this.id = id;
        this.admissionDate = admissionDate;
        this.member = new MemberSummaryDTO(memberId, firstName, lastName, nickName, headQuarterId, headQuarterDescription);
        this.patch = new PatchSummary(patchId, patchName);
    }

    public Integer getId() { return id; }
    public LocalDate getAdmissionDate() { return admissionDate; }
    public MemberSummaryDTO getMember() { return member; }
    public PatchSummary getPatch() { return patch; }

    public static class PatchSummary implements Serializable {
        private Integer id;
        private String name;
        public PatchSummary(Integer id, String name) { this.id = id; this.name = name; }
        public Integer getId() { return id; }
        public String getName() { return name; }
    }
}
