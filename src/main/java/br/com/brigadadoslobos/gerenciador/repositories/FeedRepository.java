package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}
