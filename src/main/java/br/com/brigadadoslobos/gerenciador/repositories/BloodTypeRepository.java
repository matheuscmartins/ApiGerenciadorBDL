package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodTypeRepository extends JpaRepository<BloodType, Integer> {
}
