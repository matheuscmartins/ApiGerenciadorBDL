package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.Country;
import br.com.brigadadoslobos.gerenciador.domains.dtos.CountryDTO;
import br.com.brigadadoslobos.gerenciador.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/paises")
public class CountryResource {

    @Autowired
    private CountryService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<CountryDTO> findById(@PathVariable Integer id) {
        Country obj = service.findById(id);
        return ResponseEntity.ok().body(new CountryDTO(obj));
    }
    @GetMapping
    public ResponseEntity<List<CountryDTO>> findAll(){
        List<Country> list = service.findAll();
        List<CountryDTO> listDTO = list.stream().map(obj -> new CountryDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
