package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.TravelControl;
import br.com.brigadadoslobos.gerenciador.domains.dtos.TravelControlDTO;
import br.com.brigadadoslobos.gerenciador.services.TravelControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/viagens")
public class TravelControlResource {
    @Autowired
    private TravelControlService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TravelControlDTO> findById(@PathVariable Integer id) {
        TravelControl obj = service.findById(id);
        return ResponseEntity.ok().body(new TravelControlDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<TravelControlDTO>> findAll() {
        List<TravelControl> list = service.findAll();
        List<TravelControlDTO> listDTO = list.stream().map(obj -> new TravelControlDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO', 'DIRETOR', 'EDITOR')")
    @PostMapping
    public ResponseEntity<TravelControlDTO> create(@Valid @RequestBody TravelControlDTO objDTO) {
        TravelControl newObj = service.create(objDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri()).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO', 'DIRETOR', 'EDITOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TravelControlDTO> update(@PathVariable Integer id, @Valid @RequestBody TravelControlDTO objDTO) {
        TravelControl obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new TravelControlDTO(obj));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO', 'DIRETOR', 'EDITOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TravelControlDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/membro/{id}")
    public ResponseEntity<List<TravelControlDTO>> findByMemberId(@PathVariable Integer id) {
        List<TravelControl> list = service.findByMemberId(id);
        List<TravelControlDTO> listDTO = list.stream().map(obj -> new TravelControlDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @GetMapping(value = "sede/{id}/periodo/{begin}/{end}")
    public ResponseEntity<List<TravelControlDTO>> FindbyHeadQuarterIdAndPeriod(@PathVariable Integer id,
                                                                         @PathVariable String begin, @PathVariable String end) {
        List<TravelControl> list = service.FindbyHeadQuarterIdAndPeriod(id, begin, end);
        List<TravelControlDTO> listDTO = list.stream().map(obj -> new TravelControlDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}


