package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.Feed;
import br.com.brigadadoslobos.gerenciador.domains.dtos.FeedDTO;
import br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.FeedSummaryDTO;
import br.com.brigadadoslobos.gerenciador.repositories.FeedRepository;
import br.com.brigadadoslobos.gerenciador.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class FeedService {
    @Autowired
    private FeedRepository repository;


    public Feed findById(Integer id){
        Optional<Feed> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

    public List<Feed> findAll() {
        return repository.findAll();
    }

    public Feed create(FeedDTO objDTO) {
        objDTO.setId(null);
        Feed newObj = new Feed(objDTO);
        return repository.save(newObj);
    }
    public Feed update(Integer id, FeedDTO objDTO) {
        objDTO.setId(id);
        Feed oldObj = findById(id);
        oldObj = new Feed(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Feed obj = findById(id);
        updateForDelete(obj);
        //throw new DataIntegrityViolationException("Não pode ser excluido");
        repository.deleteById(id);
    }
    public void updateForDelete(Feed obj) {
        obj.setHeadQuarter(null);
        repository.save(obj);
    }

    public List<Feed> findByDateReunionPeriod(String begin, String end) {
        Optional<List<Feed>> listOptional = repository.findByDateReunionPeriod(begin, end);
        return listOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! nesse periodo: "
                + begin +" á "+ end));
    }

    public List<Feed> FindbyHeadQuarterId(Integer id) {
        Optional<List<Feed>> listOptional = repository.FindbyHeadQuarterId(id);
        return listOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! pra essa sede id: "
                + id));
    }

    public List<Feed> FindbyHeadQuarterIdAndPeriod(Integer id, String begin, String end) {
        Optional<List<Feed>> listOptional = repository.FindbyHeadQuarterIdAndPeriod(id, begin, end);
        return listOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! pra essa sede id: "
                + id +" ou nesse periodo: " + begin +" á "+ end));
    }
    // Retorna todos os feeds como summaries
    public List<FeedSummaryDTO> findAllSummaries() {
        return repository.findAllSummaries();
    }

    // Por sede (summary)
    public List<FeedSummaryDTO> FindSummariesByHeadQuarterId(Integer id) {
        return repository.findSummariesByHeadQuarterId(id);
    }

    // Por sede e período (summary) - converter strings "dd/MM/yyyy" -> LocalDate
    public List<FeedSummaryDTO> FindSummariesByHeadQuarterIdAndPeriod(Integer id, String begin, String end) {
        Optional<List<FeedSummaryDTO>> listOptional = repository.findSummariesByHeadQuarterIdAndPeriod(id, begin, end);
        return listOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! pra essa sede id: "
                + id +" ou nesse periodo: " + begin +" á "+ end));
    }

    // Por período (summary)
    public List<FeedSummaryDTO> findSummariesByPeriod(String begin, String end) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate b = LocalDate.parse(begin, fmt);
        LocalDate e = LocalDate.parse(end, fmt);
        return repository.findSummariesByPeriod(b, e);
    }
}