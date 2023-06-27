package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.BloodType;
import br.com.brigadadoslobos.gerenciador.domains.dtos.BloodTypeDTO;
import br.com.brigadadoslobos.gerenciador.services.BloodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tiposanguineos")
public class BloodTypeResource {
    @Autowired
    private BloodTypeService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<BloodTypeDTO> findById(@PathVariable Integer id) {
        BloodType obj = service.findById(id);
        return ResponseEntity.ok().body(new BloodTypeDTO(obj));
    }
    @GetMapping
    public ResponseEntity<List<BloodTypeDTO>> findAll(){
        List<BloodType> list = service.findAll();
        List<BloodTypeDTO> listDTO = list.stream().map(obj -> new BloodTypeDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
