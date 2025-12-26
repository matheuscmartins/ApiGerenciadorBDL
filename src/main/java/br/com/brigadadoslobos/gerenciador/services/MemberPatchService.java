package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.MemberPatch;
import br.com.brigadadoslobos.gerenciador.domains.dtos.MemberPatchDTO;
import br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.MemberPatchSummaryDTO;
import br.com.brigadadoslobos.gerenciador.repositories.MemberPatchRepository;
import br.com.brigadadoslobos.gerenciador.repositories.MemberRepository;
import br.com.brigadadoslobos.gerenciador.repositories.PatchRepository;
import br.com.brigadadoslobos.gerenciador.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberPatchService {
    @Autowired
    private MemberPatchRepository repository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PatchRepository patchRepository;

    public MemberPatch findById(Integer id){
        Optional<MemberPatch> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<MemberPatch> findAll() {
        return repository.findAll();
    }

    public List<MemberPatchSummaryDTO> findAllSummaries() {
        return repository.findAllSummaries();
    }

    public MemberPatch create(MemberPatchDTO objDTO) {
        objDTO.setId(null);
        MemberPatch newObj = new MemberPatch(objDTO);
        return repository.save(newObj);
    }
    public MemberPatch update(Integer id, MemberPatchDTO objDTO) {
        objDTO.setId(id);
        MemberPatch oldObj = findById(id);
        oldObj = new MemberPatch(objDTO);
        return repository.save(oldObj);
    }

   public void delete(Integer id) {
       MemberPatch obj = findById(id);
       updateForDelete(obj);
      //throw new DataIntegrityViolationException("Não pode ser excluido");
      repository.deleteById(id);
   }
    public void updateForDelete(MemberPatch obj) {
        obj.setPatch(null);
        obj.setMember(null);
        repository.save(obj);
    }
    public List<MemberPatchSummaryDTO> findByMemberId(Integer memberId) {
        return repository.findSummariesByMemberId(memberId);
    }
}
