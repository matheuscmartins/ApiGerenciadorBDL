package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.TravelControl;
import br.com.brigadadoslobos.gerenciador.domains.dtos.TravelControlDTO;
import br.com.brigadadoslobos.gerenciador.repositories.TravelControlRepository;
import br.com.brigadadoslobos.gerenciador.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelControlService {
    @Autowired
    private TravelControlRepository repository;


    public TravelControl findById(Integer id){
        Optional<TravelControl> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<TravelControl> findAll() {
        return repository.findAll();
    }

    public TravelControl create(TravelControlDTO objDTO) {
        objDTO.setId(null);
        TravelControl newObj = new TravelControl(objDTO);
        return repository.save(newObj);
    }
    public TravelControl update(Integer id, TravelControlDTO objDTO) {
        objDTO.setId(id);
        TravelControl oldObj = findById(id);
        oldObj = new TravelControl(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        TravelControl obj = findById(id);
        updateForDelete(obj);
        //throw new DataIntegrityViolationException("Não pode ser excluido");
        repository.deleteById(id);
    }
    public void updateForDelete(TravelControl obj) {
        obj.setMember(null);
        repository.save(obj);
    }
}
