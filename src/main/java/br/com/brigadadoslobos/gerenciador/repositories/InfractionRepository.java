package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.Infraction;
import br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.InfractionSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InfractionRepository extends JpaRepository<Infraction, Integer> {
    Optional <List<Infraction>>findByMemberId(@Param("memberId") Integer id);

    @Query("select new br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.InfractionSummaryDTO(" +
            "i.id, i.infractionDate, i.infractionType, " +
            "m.id, m.firstName, m.lastName, m.nickName, " +
            "h.id, h.description) " +
            "from Infraction i " +
            "join i.member m " +
            "left join m.headQuarter h")
    List<InfractionSummaryDTO> findAllSummaries();
}
