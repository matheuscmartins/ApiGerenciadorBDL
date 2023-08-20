package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address>findByPostCode(String postCode);
    Optional<List<Address>> findByCityId(@Param("cityId") Integer id);
    @Query(value ="SELECT * FROM address a WHERE a.logradouro LIKE %:logradouro% ", nativeQuery = true)
    Optional<List<Address>> findByLogradouro(String logradouro);
}
