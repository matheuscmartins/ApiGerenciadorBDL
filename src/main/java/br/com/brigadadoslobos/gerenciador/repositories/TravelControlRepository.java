package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.TravelControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TravelControlRepository extends JpaRepository<TravelControl, Integer> {
    Optional<List<TravelControl>> findByMemberId(@Param("memberId") Integer id);

    @Query(value = "SELECT * FROM TRAVEL_CONTROL T " +
            "JOIN MEMBER M ON T.MEMBER_ID = M.ID " +
            "JOIN HEAD_QUARTER HD ON M.HEAD_QUARTER_ID  = HD.ID" +
            " WHERE HD.ID = :headQuarterid AND T.travel_date BETWEEN :begin and :end ", nativeQuery = true)
    Optional<List<TravelControl>> FindbyHeadQuarterIdAndPeriod(@Param("headQuarterid") Integer id,
                                                         @Param("begin")String begin, @Param("end")String end);

}
