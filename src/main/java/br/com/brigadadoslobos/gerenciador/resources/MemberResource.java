package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.Member;
import br.com.brigadadoslobos.gerenciador.domains.dtos.MemberDTO;
import br.com.brigadadoslobos.gerenciador.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
