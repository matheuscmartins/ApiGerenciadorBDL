package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.RoleDuty;
import br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.RoleDutySummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleDutyRepository extends JpaRepository<RoleDuty, Integer> {
    Optional<List<RoleDuty>> findByMemberId(@Param("memberId") Integer id);
    @Query("select new br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.RoleDutySummaryDTO(" +
            "r.id, r.roleName, r.startDate, r.endDate, " +
            "m.id, m.firstName, m.lastName, m.nickName, " +
            "h.id, h.description) " +
            "from RoleDuty r " +
            "join r.member m " +
            "left join m.headQuarter h")
    List<RoleDutySummaryDTO> findAllSummaries();
}
