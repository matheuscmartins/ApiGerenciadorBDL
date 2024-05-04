package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.Uf;
import br.com.brigadadoslobos.gerenciador.repositories.UfRepository;
import br.com.brigadadoslobos.gerenciador.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UfService {
    @Autowired
    private UfRepository repository;
    public Uf findById(Integer id){
        Optional<Uf> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<Uf> findAll() {
        return repository.findAll();
    }
    public List<Uf> findByCountryId(Integer id) {
        Optional<List<Uf>> listOptional = repository.findByCountryId(id);
        return listOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! paisId: " + id));
    }
}
