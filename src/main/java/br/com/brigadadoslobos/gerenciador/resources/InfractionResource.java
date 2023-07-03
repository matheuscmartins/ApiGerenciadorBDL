package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.Infraction;
import br.com.brigadadoslobos.gerenciador.domains.dtos.InfractionDTO;
import br.com.brigadadoslobos.gerenciador.services.InfractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/advertencias")
public class InfractionResource {
    @Autowired
    private InfractionService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<InfractionDTO> findById(@PathVariable Integer id) {
        Infraction obj = service.findById(id);
        return ResponseEntity.ok().body(new InfractionDTO(obj));
    }
    @GetMapping
    public ResponseEntity<List<InfractionDTO>> findAll(){
        List<Infraction> list = service.findAll();
        List<InfractionDTO> listDTO = list.stream().map(obj -> new InfractionDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO')")
    @PostMapping
    public ResponseEntity<InfractionDTO> create(@Valid @RequestBody InfractionDTO objDTO){
        Infraction newObj = service.create(objDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri()).build();
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO', 'DIRETOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<InfractionDTO> update(@PathVariable Integer id, @Valid @RequestBody InfractionDTO objDTO){
        Infraction obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new InfractionDTO(obj));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO', 'DIRETOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<InfractionDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
