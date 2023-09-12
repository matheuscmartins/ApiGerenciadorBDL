package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.RoleDuty;
import br.com.brigadadoslobos.gerenciador.domains.dtos.RoleDutyDTO;
import br.com.brigadadoslobos.gerenciador.repositories.RoleDutyRepository;
import br.com.brigadadoslobos.gerenciador.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleDutyService {
    @Autowired
    private RoleDutyRepository repository;


    public RoleDuty findById(Integer id){
        Optional<RoleDuty> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<RoleDuty> findAll() {
        return repository.findAll();
    }

    public RoleDuty create(RoleDutyDTO objDTO) {
        objDTO.setId(null);
        RoleDuty newObj = new RoleDuty(objDTO);
        return repository.save(newObj);
    }
    public RoleDuty update(Integer id, RoleDutyDTO objDTO) {
        objDTO.setId(id);
        RoleDuty oldObj = findById(id);
        oldObj = new RoleDuty(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        RoleDuty obj = findById(id);
        updateForDelete(obj);
        //throw new DataIntegrityViolationException("Não pode ser excluido");
        repository.deleteById(id);
    }
    public void updateForDelete(RoleDuty obj) {
        obj.setMember(null);
        repository.save(obj);
    }

    public List<RoleDuty> findByMemberId(Integer id) {
        Optional<List<RoleDuty>> listOptional = repository.findByMemberId(id);
        return listOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }
}
