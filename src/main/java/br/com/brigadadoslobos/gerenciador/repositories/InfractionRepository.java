package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.Infraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InfractionRepository extends JpaRepository<Infraction, Integer> {
    Optional <List<Infraction>>findByMemberId(@Param("memberId") Integer id);
}
