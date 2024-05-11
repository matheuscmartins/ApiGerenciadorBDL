package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.Address;
import br.com.brigadadoslobos.gerenciador.domains.dtos.AddressDTO;
import br.com.brigadadoslobos.gerenciador.repositories.AddressRepository;
import br.com.brigadadoslobos.gerenciador.services.exceptions.DataIntegrityViolationException;
import br.com.brigadadoslobos.gerenciador.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository repository;

    public Address findById(Integer id){
        Optional<Address> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<Address> findAll() {
        return repository.findAll();
    }

    public Address create(AddressDTO objDTO) {
        objDTO.setId(null);
        //validaPorCep(objDTO);
        Address newObj = new Address(objDTO);
        return repository.save(newObj);
    }
    public Address update(Integer id, AddressDTO objDTO) {
        objDTO.setId(id);
        Address oldObj = findById(id);
        //validaPorCep(objDTO);
        oldObj = new Address(objDTO);
        return repository.save(oldObj);
    }

   public void delete(Integer id) {
       Address obj = findById(id);
      //throw new DataIntegrityViolationException("Não pode ser excluido");
      repository.deleteById(id);
   }

    private void validaPorCep(AddressDTO objDTO){
        Optional<Address> obj = repository.findByPostCode(objDTO.getPostCode());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("CEP já cadastrado no sistema!");
        }
    }

    public List<Address> findByCityId(Integer id) {
        Optional<List<Address>> listOptional = repository.findByCityId(id);
        return listOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }


    public Address findByPostCode(String postCode) {
        Optional<Address> obj = repository.findByPostCode(postCode);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! CEP: "+ postCode));
    }

    public List<Address> findByLogradouro(String logradouro) {
        Optional<List<Address>> listOptional = repository.findByLogradouro(logradouro);
        return listOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Rua: "+ logradouro));
    }
}
