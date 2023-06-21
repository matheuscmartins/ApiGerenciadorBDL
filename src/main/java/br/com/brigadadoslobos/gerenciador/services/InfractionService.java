package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.Infraction;
import br.com.brigadadoslobos.gerenciador.domains.dtos.InfractionDTO;
import br.com.brigadadoslobos.gerenciador.repositories.InfractionRepository;
import br.com.brigadadoslobos.gerenciador.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfractionService {
    @Autowired
    private InfractionRepository repository;


    public Infraction findById(Integer id){
        Optional<Infraction> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<Infraction> findAll() {
        return repository.findAll();
    }

    public Infraction create(InfractionDTO objDTO) {
        objDTO.setId(null);
        Infraction newObj = new Infraction(objDTO);
        return repository.save(newObj);
    }
    public Infraction update(Integer id, InfractionDTO objDTO) {
        objDTO.setId(id);
        Infraction oldObj = findById(id);
        oldObj = new Infraction(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Infraction obj = findById(id);
        updateForDelete(obj);
        //throw new DataIntegrityViolationException("Não pode ser excluido");
        repository.deleteById(id);
    }
    public void updateForDelete(Infraction obj) {
        obj.setMember(null);
        repository.save(obj);
    }
}
