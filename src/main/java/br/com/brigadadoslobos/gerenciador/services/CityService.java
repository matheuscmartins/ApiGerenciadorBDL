package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.City;
import br.com.brigadadoslobos.gerenciador.domains.dtos.CityDTO;
import br.com.brigadadoslobos.gerenciador.repositories.CityRepository;
import br.com.brigadadoslobos.gerenciador.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository repository;
    public City findById(Integer id){
        Optional<City> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<City> findAll() {
        return repository.findAll();
    }

    public City create(CityDTO objDTO) {
        objDTO.setId(null);
        City newObj = new City(objDTO);
        return repository.save(newObj);
    }
    public City update(Integer id, CityDTO objDTO) {
        objDTO.setId(id);
        City oldObj = findById(id);

        oldObj = new City(objDTO);
        return repository.save(oldObj);
    }
   public void delete(Integer id) {
       City obj = findById(id);
      //throw new DataIntegrityViolationException("Não pode ser excluido");
      repository.deleteById(id);
   }

    public List<City> findByName(String name) {
        Optional<List<City>> listOptional = repository.findByName(name);
        return listOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Nome: "+ name));
    }
}
