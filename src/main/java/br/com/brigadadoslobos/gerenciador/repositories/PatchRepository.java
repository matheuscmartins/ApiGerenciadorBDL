package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.Patch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatchRepository extends JpaRepository<Patch, Integer> {
    Optional<Patch> findByName(String name);
}
