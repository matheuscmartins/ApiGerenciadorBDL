package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    @Query(value ="SELECT * FROM city c WHERE c.name LIKE %:name% ", nativeQuery = true)
    Optional<List<City>> findByName(@Param("name") String name);
}
