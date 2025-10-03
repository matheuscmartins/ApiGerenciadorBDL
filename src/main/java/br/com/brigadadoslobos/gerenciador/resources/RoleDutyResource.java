package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.RoleDuty;
import br.com.brigadadoslobos.gerenciador.domains.dtos.RoleDutyDTO;
import br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.RoleDutySummaryDTO;
import br.com.brigadadoslobos.gerenciador.services.RoleDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cargos")
public class RoleDutyResource {
    @Autowired
    private RoleDutyService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoleDutyDTO> findById(@PathVariable Integer id) {
        RoleDuty obj = service.findById(id);
        return ResponseEntity.ok().body(new RoleDutyDTO(obj));
    }
    /*
    @GetMapping
    public ResponseEntity<List<RoleDutyDTO>> findAll(){
        List<RoleDuty> list = service.findAll();
        List<RoleDutyDTO> listDTO = list.stream().map(obj -> new RoleDutyDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
     */

    @GetMapping
    public ResponseEntity<List<RoleDutySummaryDTO>> findAll(){
        List<RoleDutySummaryDTO> listDTO = service.findAllSummaries();
        return ResponseEntity.ok().body(listDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO')")
    @PostMapping
    public ResponseEntity<RoleDutyDTO> create(@Valid @RequestBody RoleDutyDTO objDTO){
        RoleDuty newObj = service.create(objDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri()).build();
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO', 'DIRETOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<RoleDutyDTO> update(@PathVariable Integer id, @Valid @RequestBody RoleDutyDTO objDTO){
        RoleDuty obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new RoleDutyDTO(obj));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO', 'DIRETOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<RoleDutyDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value = "/membro/{id}")
    public ResponseEntity<List<RoleDutyDTO>>  findByMemberId(@PathVariable Integer id) {
        List<RoleDuty> list = service.findByMemberId(id);
        List<RoleDutyDTO> listDTO = list.stream().map(obj -> new RoleDutyDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
