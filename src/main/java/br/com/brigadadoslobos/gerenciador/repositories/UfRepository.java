package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UfRepository extends JpaRepository<Uf, Integer> {
    Optional<List<Uf>> findByCountryId(@Param("countryId") Integer id);
}
