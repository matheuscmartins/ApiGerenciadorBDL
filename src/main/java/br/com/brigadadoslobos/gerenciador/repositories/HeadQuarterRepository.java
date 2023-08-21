package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.HeadQuarter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeadQuarterRepository extends JpaRepository<HeadQuarter, Integer> {
    Optional<HeadQuarter> findByDescription(String description);
}
