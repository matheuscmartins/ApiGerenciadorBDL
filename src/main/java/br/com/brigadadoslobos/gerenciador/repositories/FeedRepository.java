package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.Feed;
import br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.FeedSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Integer> {

    @Query(value ="SELECT * FROM Feed f WHERE f.reunion_date BETWEEN :begin and :end ", nativeQuery = true)
    Optional <List<Feed>> findByDateReunionPeriod(@Param("begin")String begin, @Param("end")String end);
    @Query(value ="SELECT * FROM Feed f WHERE f.HEAD_QUARTER_ID = :headQuarterid ", nativeQuery = true)
    Optional <List<Feed>> FindbyHeadQuarterId(@Param("headQuarterid") Integer id);
    @Query(value ="SELECT * FROM Feed f WHERE f.HEAD_QUARTER_ID = :headQuarterid " +
            " AND f.reunion_date BETWEEN :begin and :end ", nativeQuery = true)
    Optional <List<Feed>> FindbyHeadQuarterIdAndPeriod(@Param("headQuarterid") Integer id,
                                                       @Param("begin")String begin, @Param("end")String end);

    // Retorna todos os feeds como summary (JPQL constructor expression)
    @Query("select new br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.FeedSummaryDTO(" +
            "f.id, f.postDate, f.reunionDate, f.title, f.text, h.id, h.description) " +
            "from Feed f " +
            "left join f.headQuarter h")
    List<FeedSummaryDTO> findAllSummaries();

    // Por sede (summary)
    @Query("select new br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.FeedSummaryDTO(" +
            "f.id, f.postDate, f.reunionDate, f.title, f.text, h.id, h.description) " +
            "from Feed f " +
            "left join f.headQuarter h " +
            "where h.id = :headQuarterid")
    List<FeedSummaryDTO> findSummariesByHeadQuarterId(@Param("headQuarterid") Integer headQuarterid);

    // Por período e sede (summary)
    @Query("select new br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.FeedSummaryDTO(" +
            "f.id, f.postDate, f.reunionDate, f.title, f.text, h.id, h.description) " +
            "from Feed f " +
            "left join f.headQuarter h " +
            "where h.id = :headQuarterid and f.reunionDate between :begin and :end")
    List<FeedSummaryDTO> findSummariesByHeadQuarterIdAndPeriod(@Param("headQuarterid") Integer headQuarterid,
                                                               @Param("begin") LocalDate begin,
                                                               @Param("end") LocalDate end);

    // Por período (sem sede)
    @Query("select new br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.FeedSummaryDTO(" +
            "f.id, f.postDate, f.reunionDate, f.title, f.text, h.id, h.description) " +
            "from Feed f " +
            "left join f.headQuarter h " +
            "where f.reunionDate between :begin and :end")
    List<FeedSummaryDTO> findSummariesByPeriod(@Param("begin") LocalDate begin, @Param("end") LocalDate end);
}