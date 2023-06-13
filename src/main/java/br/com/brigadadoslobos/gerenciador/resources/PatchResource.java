package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.Patch;
import br.com.brigadadoslobos.gerenciador.domains.dtos.PatchDTO;
import br.com.brigadadoslobos.gerenciador.services.PatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/patchs")
public class PatchResource {
    @Autowired
    private PatchService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatchDTO> findById(@PathVariable Integer id) {
        Patch obj = service.findById(id);
        return ResponseEntity.ok().body(new PatchDTO(obj));
    }
    @GetMapping
    public ResponseEntity<List<PatchDTO>> findAl(){
        List<Patch> list = service.findAll();
        List<PatchDTO> listDTO = list.stream().map(obj -> new PatchDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<PatchDTO> create(@Valid @RequestBody PatchDTO objDTO){
        Patch newObj = service.create(objDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri()).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<PatchDTO> update(@PathVariable Integer id, @Valid @RequestBody PatchDTO objDTO){
        Patch obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new PatchDTO(obj));
    }
   /* @DeleteMapping(value = "/{id}")
    public ResponseEntity<PatchDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }*/
}
