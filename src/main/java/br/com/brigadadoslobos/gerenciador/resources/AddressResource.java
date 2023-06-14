package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.Address;
import br.com.brigadadoslobos.gerenciador.domains.dtos.AddressDTO;
import br.com.brigadadoslobos.gerenciador.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/enderecos")
public class AddressResource {
    @Autowired
    private AddressService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Integer id) {
        Address obj = service.findById(id);
        return ResponseEntity.ok().body(new AddressDTO(obj));
    }
    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAl(){
        List<Address> list = service.findAll();
        List<AddressDTO> listDTO = list.stream().map(obj -> new AddressDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<AddressDTO> create(@Valid @RequestBody AddressDTO objDTO){
        Address newObj = service.create(objDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri()).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> update(@PathVariable Integer id, @Valid @RequestBody AddressDTO objDTO){
        Address obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new AddressDTO(obj));
    }
   /* @DeleteMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }*/
}
