package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.Uf;
import br.com.brigadadoslobos.gerenciador.domains.dtos.UfDTO;
import br.com.brigadadoslobos.gerenciador.services.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/ufs")
public class UfResource {
    @Autowired
    private UfService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<UfDTO> findById(@PathVariable Integer id) {
        Uf obj = service.findById(id);
        return ResponseEntity.ok().body(new UfDTO(obj));
    }
    @GetMapping
    public ResponseEntity<List<UfDTO>> findAl(){
        List<Uf> list = service.findAll();
        List<UfDTO> listDTO = list.stream().map(obj -> new UfDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
