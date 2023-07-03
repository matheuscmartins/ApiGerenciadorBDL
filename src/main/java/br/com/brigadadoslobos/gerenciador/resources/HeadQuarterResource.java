package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.HeadQuarter;
import br.com.brigadadoslobos.gerenciador.domains.dtos.HeadQuarterDTO;
import br.com.brigadadoslobos.gerenciador.services.HeadQuarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/sedes")
public class HeadQuarterResource {
    @Autowired
    private HeadQuarterService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<HeadQuarterDTO> findById(@PathVariable Integer id) {
        HeadQuarter obj = service.findById(id);
        return ResponseEntity.ok().body(new HeadQuarterDTO(obj));
    }
    @GetMapping
    public ResponseEntity<List<HeadQuarterDTO>> findAll(){
        List<HeadQuarter> list = service.findAll();
        List<HeadQuarterDTO> listDTO = list.stream().map(obj -> new HeadQuarterDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO')")
    @PostMapping
    public ResponseEntity<HeadQuarterDTO> create(@Valid @RequestBody HeadQuarterDTO objDTO){
        HeadQuarter newObj = service.create(objDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri()).build();
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<HeadQuarterDTO> update(@PathVariable Integer id, @Valid @RequestBody HeadQuarterDTO objDTO){
        HeadQuarter obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new HeadQuarterDTO(obj));
    }
   /* @DeleteMapping(value = "/{id}")
    public ResponseEntity<HeadQuarterDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }*/
}
