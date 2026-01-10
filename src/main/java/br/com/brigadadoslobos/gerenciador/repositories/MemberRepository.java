package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.Member;
import br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.MemberSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByCpf(String cpf);
    Optional<Member> findByEmail(String email);

    @Query("SELECT new br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.MemberSummaryDTO(" +
            "m.id, m.firstName, m.lastName, m.nickName, h.id, h.description) " +
            "FROM Member m " +
            "LEFT JOIN m.headQuarter h ")
    List<MemberSummaryDTO> findAllByAdminSummary();

    @Query("SELECT DISTINCT new br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.MemberSummaryDTO(" +
            "m.id, m.firstName, m.lastName, m.nickName, h.id, h.description) " +
            "FROM Member m " +
            "LEFT JOIN m.headQuarter h " +
            "WHERE 0 NOT IN elements(m.profile) ")
    List<MemberSummaryDTO> findAllSummary();

    @Query("SELECT new br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.MemberSummaryDTO(" +
            "m.id, m.firstName, m.lastName, m.nickName, h.id, h.description) " +
            "FROM Member m " +
            "LEFT JOIN m.headQuarter h "+
            "WHERE h.id = :headQuarterid AND 0 NOT IN elements(m.profile) ")
    Optional <List<MemberSummaryDTO>> findSummariesByHeadQuarterId(@Param("headQuarterid") Integer headQuarterid);
}
