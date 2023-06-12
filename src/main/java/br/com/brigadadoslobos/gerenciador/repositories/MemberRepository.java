package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository <Member, Integer> {
    Optional<Member> findByCpf(String cpf);
    Optional<Member> findByEmail(String cpf);
}
