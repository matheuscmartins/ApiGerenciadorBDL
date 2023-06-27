package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.City;
import br.com.brigadadoslobos.gerenciador.domains.dtos.CityDTO;
import br.com.brigadadoslobos.gerenciador.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cidades")
public class CityResource {
    @Autowired
    private CityService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CityDTO> findById(@PathVariable Integer id) {
        City obj = service.findById(id);
        return ResponseEntity.ok().body(new CityDTO(obj));
    }
    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll(){
        List<City> list = service.findAll();
        List<CityDTO> listDTO = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<CityDTO> create(@Valid @RequestBody CityDTO objDTO){
        City newObj = service.create(objDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri()).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<CityDTO> update(@PathVariable Integer id, @Valid @RequestBody CityDTO objDTO){
        City obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new CityDTO(obj));
    }
   /* @DeleteMapping(value = "/{id}")
    public ResponseEntity<CityDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }*/
}
