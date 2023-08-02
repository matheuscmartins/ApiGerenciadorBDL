package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.TravelControl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelControlRepository extends JpaRepository<TravelControl, Integer> {
}
