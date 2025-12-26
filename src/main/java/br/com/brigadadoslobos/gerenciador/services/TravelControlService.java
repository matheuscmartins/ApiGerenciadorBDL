package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.TravelControl;
import br.com.brigadadoslobos.gerenciador.domains.dtos.TravelControlDTO;
import br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.TravelControlSummaryDTO;
import br.com.brigadadoslobos.gerenciador.repositories.TravelControlRepository;
import br.com.brigadadoslobos.gerenciador.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TravelControlService {
    @Autowired
    private TravelControlRepository repository;


    public TravelControl findById(Integer id) {
        Optional<TravelControl> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }
    public List<TravelControlSummaryDTO> findAll() {
        return repository.findAllSummaries();
    }

    public TravelControl create(TravelControlDTO objDTO) {
        objDTO.setId(null);
        TravelControl newObj = new TravelControl(objDTO);
        return repository.save(newObj);
    }

    public TravelControl update(Integer id, TravelControlDTO objDTO) {
        objDTO.setId(id);
        TravelControl oldObj = findById(id);
        oldObj = new TravelControl(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        TravelControl obj = findById(id);
        updateForDelete(obj);
        //throw new DataIntegrityViolationException("Não pode ser excluido");
        repository.deleteById(id);
    }

    public void updateForDelete(TravelControl obj) {
        obj.setMember(null);
        repository.save(obj);
    }

    public List<TravelControlSummaryDTO> findByMemberIdAndPeriod(Integer id, String begin, String end) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Optional<List<TravelControlSummaryDTO>> listOptional = repository.findByMemberIdAndPeriod(id, LocalDate.parse(begin, fmt),
                LocalDate.parse(end, fmt));
        return listOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! MembroId: " + id
        +" nesse periodo:"  + begin + " á " + end));
    }

    public List<TravelControlSummaryDTO> FindbyHeadQuarterIdAndPeriod(Integer id, String begin, String end) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Optional<List<TravelControlSummaryDTO>> listOptional = repository.FindbyHeadQuarterIdAndPeriod(id,LocalDate.parse(begin, fmt),
                LocalDate.parse(end, fmt));
        return listOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! nesse periodo: "
                + begin + " á " + end));
    }
    public List<TravelControlSummaryDTO> FindSummariesByPeriod(String begin, String end) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Optional<List<TravelControlSummaryDTO>> listOptional = repository.FindSummariesByPeriod(LocalDate.parse(begin, fmt),
                LocalDate.parse(end, fmt));
        return listOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! nesse periodo: "
                + begin + " á " + end));
    }
}
