package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.Patch;
import br.com.brigadadoslobos.gerenciador.domains.dtos.PatchDTO;
import br.com.brigadadoslobos.gerenciador.repositories.PatchRepository;
import br.com.brigadadoslobos.gerenciador.services.exceptions.DataIntegrityViolationException;
import br.com.brigadadoslobos.gerenciador.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatchService {
    @Autowired
    private PatchRepository repository;
    public Patch findById(Integer id){
        Optional<Patch> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<Patch> findAll() {
        return repository.findAll();
    }

    public Patch create(PatchDTO objDTO) {
        objDTO.setId(null);
        validaPorName(objDTO);
        Patch newObj = new Patch(objDTO);
        return repository.save(newObj);
    }
    public Patch update(Integer id, PatchDTO objDTO) {
        objDTO.setId(id);
        Patch oldObj = findById(id);
        validaPorName(objDTO);
        oldObj = new Patch(objDTO);
        return repository.save(oldObj);
    }
    /*
   public void delete(Integer id) {
       Patch obj = findById(id);
      //throw new DataIntegrityViolationException("Não pode ser excluido");
      repository.deleteById(id);
   }
     */
    private void validaPorName(PatchDTO objDTO){
        Optional<Patch> obj = repository.findByName(objDTO.getName());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("Patch já cadastrado no sistema!");
        }
    }

}
