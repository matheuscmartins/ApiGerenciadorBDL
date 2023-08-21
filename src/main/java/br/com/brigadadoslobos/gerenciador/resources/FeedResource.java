package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.Feed;
import br.com.brigadadoslobos.gerenciador.domains.dtos.FeedDTO;
import br.com.brigadadoslobos.gerenciador.services.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/feed")
public class FeedResource {
    @Autowired
    private FeedService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<FeedDTO> findById(@PathVariable Integer id) {
        Feed obj = service.findById(id);
        return ResponseEntity.ok().body(new FeedDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<FeedDTO>> findAll() {
        List<Feed> list = service.findAll();
        List<FeedDTO> listDTO = list.stream().map(obj -> new FeedDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO', 'DIRETOR', 'EDITOR')")
    @PostMapping
    public ResponseEntity<FeedDTO> create(@Valid @RequestBody FeedDTO objDTO) {
        Feed newObj = service.create(objDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri()).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO', 'DIRETOR', 'EDITOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<FeedDTO> update(@PathVariable Integer id, @Valid @RequestBody FeedDTO objDTO) {
        Feed obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new FeedDTO(obj));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO', 'DIRETOR', 'EDITOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<FeedDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "periodo/{begin}/{end}")
    public ResponseEntity<List<FeedDTO>> findByDateReunionPeriod(@PathVariable String begin, @PathVariable String end) {
        List<Feed> list = service.findByDateReunionPeriod(begin, end);
        List<FeedDTO> listDTO = list.stream().map(obj -> new FeedDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "sede/{id}")
    public ResponseEntity<List<FeedDTO>> FindbyHeadQuarterId(@PathVariable Integer id) {
        List<Feed> list = service.FindbyHeadQuarterId(id);
        List<FeedDTO> listDTO = list.stream().map(obj -> new FeedDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "sede/{id}/periodo/{begin}/{end}")
    public ResponseEntity<List<FeedDTO>> FindbyHeadQuarterIdAndPeriod(@PathVariable Integer id,
                                                                      @PathVariable String begin, @PathVariable String end) {
        List<Feed> list = service.FindbyHeadQuarterIdAndPeriod(id, begin, end);
        List<FeedDTO> listDTO = list.stream().map(obj -> new FeedDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
