package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.BloodType;
import br.com.brigadadoslobos.gerenciador.repositories.BloodTypeRepository;
import br.com.brigadadoslobos.gerenciador.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodTypeService {
    @Autowired
    private BloodTypeRepository repository;
    public BloodType findById(Integer id){
        Optional<BloodType> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<BloodType> findAll() {
        return repository.findAll();
    }
}
