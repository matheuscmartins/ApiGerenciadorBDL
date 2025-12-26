package br.com.brigadadoslobos.gerenciador.resources;

import br.com.brigadadoslobos.gerenciador.domains.Member;
import br.com.brigadadoslobos.gerenciador.domains.dtos.MemberDTO;
import br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.MemberSummaryDTO;
import br.com.brigadadoslobos.gerenciador.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /*
    @GetMapping
    public ResponseEntity<List<MemberDTO>> findAll(){
        List<Member> list = service.findAll();
        List<MemberDTO> listDTO = list.stream().map(obj -> new MemberDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    */
    @GetMapping
    public ResponseEntity<List<MemberSummaryDTO>> findAll(){
        List<MemberSummaryDTO> listDTO = service.findAllSummary();
        return ResponseEntity.ok().body(listDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO', 'DIRETOR')")
    @PostMapping
    public ResponseEntity<MemberDTO> create(@Valid @RequestBody MemberDTO objDTO){
        Member newObj = service.create(objDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri()).build();
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'COMANDO', 'DIRETOR')")
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
   @GetMapping(value = "/sede/{id}")
   public ResponseEntity<List<MemberSummaryDTO>> findByHeadQuarterId(@PathVariable Integer id) {
       List<MemberSummaryDTO> listDTO = service.findSummariesByHeadQuarterId(id);
       return ResponseEntity.ok().body(listDTO);
   }
    @PostMapping(value = "/{id}/upload-foto")
    public ResponseEntity<Map<String, String>> uploadFotoPerfil(
            @PathVariable Integer id,
            @RequestParam("file") MultipartFile file) {

        try {
            // Chama o serviço que processa e salva a imagem
            String imagePath = service.uploadProfileImage(id, file);

            // Retorna o caminho da imagem
            Map<String, String> response = new HashMap<>();
            response.put("imagePath", imagePath);
            response.put("message", "Foto atualizada com sucesso!");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
