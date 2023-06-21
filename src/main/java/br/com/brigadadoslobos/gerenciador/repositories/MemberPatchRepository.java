package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.MemberPatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPatchRepository extends JpaRepository<MemberPatch, Integer> {

}
