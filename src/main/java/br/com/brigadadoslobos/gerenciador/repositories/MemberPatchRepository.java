package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.MemberPatch;
import br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.MemberPatchSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberPatchRepository extends JpaRepository<MemberPatch, Integer> {
    @Query("select new br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.MemberPatchSummaryDTO(" +
            "mp.id, mp.admissionDate, " +
            "m.id, m.firstName, m.lastName, m.nickName, " +
            "h.id, h.description, " +
            "p.id, p.name) " +
            "from MemberPatch mp " +
            "join mp.member m " +
            "left join m.headQuarter h " +
            "left join mp.patch p")
    List<MemberPatchSummaryDTO> findAllSummaries();
}
