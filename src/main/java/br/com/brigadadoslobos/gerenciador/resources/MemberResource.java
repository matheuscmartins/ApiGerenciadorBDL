package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.Member;
import br.com.brigadadoslobos.gerenciador.domains.dtos.MemberDTO;
import br.com.brigadadoslobos.gerenciador.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/membros")
public class MemberResource {
    @Autowired
    private MemberService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MemberDTO> findById(@PathVariable Integer id) {
        Member obj = service.findById(id);
        return ResponseEntity.ok().body(new MemberDTO(obj));
    }
    @GetMapping
    public ResponseEntity<List<MemberDTO>> findAll(){
        List<Member> list = service.findAll();
        List<MemberDTO> listDTO = list.stream().map(obj -> new MemberDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<MemberDTO> create(@Valid @RequestBody MemberDTO objDTO){
        Member newObj = service.create(objDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri()).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<MemberDTO> update(@PathVariable Integer id, @Valid @RequestBody MemberDTO objDTO){
        Member obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new MemberDTO(obj));
    }
   /* @DeleteMapping(value = "/{id}")
    public ResponseEntity<MemberDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }*/
}
