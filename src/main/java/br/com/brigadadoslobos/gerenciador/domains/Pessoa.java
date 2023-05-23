package br.com.brigadadoslobos.gerenciador.domains;

import br.com.brigadadoslobos.gerenciador.domains.enums.Perfil;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Pessoa {
    protected Integer id;
    protected String firsName;
    protected String lastName;
    protected String nickName;
    protected String rg;
    protected String cpf;
    protected String cnh;
    protected String telefone;
    protected String telFixo;
    protected String telFamiliar1;
    protected String telFamiliar2;
    protected String email;
    protected String senha;
    protected LocalDate dataNascimento;
    protected LocalDate dataAdmissao;
    protected LocalDate dataDesligamento;
    protected Set<Perfil> perfis = new HashSet<>();
}
