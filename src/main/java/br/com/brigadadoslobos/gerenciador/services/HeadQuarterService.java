package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.HeadQuarter;
import br.com.brigadadoslobos.gerenciador.domains.dtos.HeadQuarterDTO;
import br.com.brigadadoslobos.gerenciador.repositories.HeadQuarterRepository;
import br.com.brigadadoslobos.gerenciador.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeadQuarterService {
    @Autowired
    private HeadQuarterRepository repository;

    public HeadQuarter findById(Integer id){
        Optional<HeadQuarter> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<HeadQuarter> findAll() {
        return repository.findAll();
    }

    public HeadQuarter create(HeadQuarterDTO objDTO) {
        objDTO.setId(null);
        HeadQuarter newObj = new HeadQuarter(objDTO);
        return repository.save(newObj);
    }
    public HeadQuarter update(Integer id, HeadQuarterDTO objDTO) {
        objDTO.setId(id);
        HeadQuarter oldObj = findById(id);
        oldObj = new HeadQuarter(objDTO);
        return repository.save(oldObj);
    }
    /*
   public void delete(Integer id) {
       HeadQuarter obj = findById(id);
      //throw new DataIntegrityViolationException("Não pode ser excluido");
      repository.deleteById(id);
   }
     */
}
