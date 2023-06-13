package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.Member;
import br.com.brigadadoslobos.gerenciador.domains.dtos.MemberDTO;
import br.com.brigadadoslobos.gerenciador.repositories.MemberRepository;
import br.com.brigadadoslobos.gerenciador.services.exceptions.DataIntegrityViolationException;
import br.com.brigadadoslobos.gerenciador.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository repository;

    public Member findById(Integer id){
        Optional<Member> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<Member> findAll() {
        return repository.findAll();
    }

    public Member create(MemberDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEmail(objDTO);
        Member newObj = new Member(objDTO);
        return repository.save(newObj);
    }
    public Member update(Integer id, MemberDTO objDTO) {
        objDTO.setId(id);
        Member oldObj = findById(id);
        validaPorCpfEmail(objDTO);
        oldObj = new Member(objDTO);
        return repository.save(oldObj);
    }
     /*
    public void delete(Integer id) {
        Member obj = findById(id);
       //throw new DataIntegrityViolationException("Não pode ser excluido");
       repository.deleteById(id);
    }
      */
    private void validaPorCpfEmail(MemberDTO objDTO){
        Optional<Member> obj = repository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        obj = repository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }



}
