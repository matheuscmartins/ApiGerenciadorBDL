package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.RoleDuty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleDutyRepository extends JpaRepository<RoleDuty, Integer> {
    Optional<List<RoleDuty>> findByMemberId(@Param("memberId") Integer id);
}
