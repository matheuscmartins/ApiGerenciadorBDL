package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.TravelControl;
import br.com.brigadadoslobos.gerenciador.domains.dtos.TravelControlDTO;
import br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.TravelControlSummaryDTO;
import br.com.brigadadoslobos.gerenciador.services.TravelControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<List<TravelControlSummaryDTO>> findAll() {
        List<TravelControlSummaryDTO> listDTO = service.findAll();
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

    @GetMapping(value = "/membro/{id}/periodo/{begin}/{end}")
    public ResponseEntity<List<TravelControlSummaryDTO>> findByMemberIdAndPeriod(@PathVariable Integer id,
                                                                 @PathVariable String begin, @PathVariable String end) {
        List<TravelControlSummaryDTO> listDTO = service.findByMemberIdAndPeriod(id, begin, end);
        return ResponseEntity.ok().body(listDTO);
    }
    @GetMapping(value = "sede/{id}/periodo/{begin}/{end}")
    public ResponseEntity<List<TravelControlSummaryDTO>> FindbyHeadQuarterIdAndPeriod(@PathVariable Integer id,
                                                                         @PathVariable String begin, @PathVariable String end) {
        List<TravelControlSummaryDTO> listDTO = service.FindbyHeadQuarterIdAndPeriod(id, begin, end);
        return ResponseEntity.ok().body(listDTO);
    }
    @GetMapping(value = "periodo/{begin}/{end}")
    public ResponseEntity<List<TravelControlSummaryDTO>> FindByPeriod(@PathVariable String begin, @PathVariable String end) {
        List<TravelControlSummaryDTO> listDto = service.FindSummariesByPeriod(begin, end);
        return ResponseEntity.ok().body(listDto);
    }
}


