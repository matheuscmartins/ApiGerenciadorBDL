package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.Feed;
import br.com.brigadadoslobos.gerenciador.domains.dtos.FeedDTO;
import br.com.brigadadoslobos.gerenciador.services.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<FeedDTO>> findAll(){
        List<Feed> list = service.findAll();
        List<FeedDTO> listDTO = list.stream().map(obj -> new FeedDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<FeedDTO> create(@Valid @RequestBody FeedDTO objDTO){
        Feed newObj = service.create(objDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri()).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<FeedDTO> update(@PathVariable Integer id, @Valid @RequestBody FeedDTO objDTO){
        Feed obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new FeedDTO(obj));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<FeedDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
