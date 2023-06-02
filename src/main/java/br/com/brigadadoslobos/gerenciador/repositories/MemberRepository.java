package br.com.brigadadoslobos.gerenciador.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
