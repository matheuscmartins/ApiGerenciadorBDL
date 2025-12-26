package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.MemberPatch;
import br.com.brigadadoslobos.gerenciador.domains.dtos.MemberPatchDTO;
import br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.MemberPatchSummaryDTO;
import br.com.brigadadoslobos.gerenciador.services.MemberPatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/membrospatchs")
public class MemberPatchResource {
    @Autowired
    private MemberPatchService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MemberPatchDTO> findById(@PathVariable Integer id) {
        MemberPatch obj = service.findById(id);
        return ResponseEntity.ok().body(new MemberPatchDTO(obj));
    }

    /*
    @GetMapping
    public ResponseEntity<List<MemberPatchDTO>> findAll(){
        List<MemberPatch> list = service.findAll();
        List<MemberPatchDTO> listDTO = list.stream().map(obj -> new MemberPatchDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    */

    @GetMapping
    public ResponseEntity<List<MemberPatchSummaryDTO>> findAll(){
        List<MemberPatchSummaryDTO> listDTO = service.findAllSummaries();
        return ResponseEntity.ok().body(listDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO', 'DIRETOR')")
    @PostMapping
    public ResponseEntity<MemberPatchDTO> create(@Valid @RequestBody MemberPatchDTO objDTO){
        MemberPatch newObj = service.create(objDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri()).build();
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO', 'DIRETOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<MemberPatchDTO> update(@PathVariable Integer id, @Valid @RequestBody MemberPatchDTO objDTO){
        MemberPatch obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new MemberPatchDTO(obj));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO', 'DIRETOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MemberPatchDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/membro/{memberId}")
    public ResponseEntity<List<MemberPatchSummaryDTO>> findByMemberId(@PathVariable Integer memberId){
        List<MemberPatchSummaryDTO> listDTO = service.findByMemberId(memberId);
        return ResponseEntity.ok().body(listDTO);
    }
}
