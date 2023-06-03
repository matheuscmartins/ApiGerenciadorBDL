package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.HeadQuarter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeadQuarterRepository extends JpaRepository<HeadQuarter, Integer> {
}
