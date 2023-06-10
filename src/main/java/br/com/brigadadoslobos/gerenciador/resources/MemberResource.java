package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.Member;
import br.com.brigadadoslobos.gerenciador.domains.dtos.MemberDTO;
import br.com.brigadadoslobos.gerenciador.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<List<MemberDTO>> findAl(){
        List<Member> list = service.findAll();
        List<MemberDTO> listDTO = list.stream().map(obj -> new MemberDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<MemberDTO> create(@RequestBody MemberDTO objDTO){
        Member newObj = service.create(objDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri()).build();
    }
}
