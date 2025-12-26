package br.com.brigadadoslobos.gerenciador.repositories;

import br.com.brigadadoslobos.gerenciador.domains.TravelControl;
import br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.TravelControlSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TravelControlRepository extends JpaRepository<TravelControl, Integer> {
    @Query("select new br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.TravelControlSummaryDTO(" +
            "t.id, t.travelDate, t.km, t.departureLocation, t.arrivalLocation, t.description, t.kmControl, " +
            "m.id, m.firstName, m.lastName, m.nickName, " +
            "h.id, h.description) " +
            "from TravelControl t " +
            "join t.member m " +
            "left join m.headQuarter h "+
            "WHERE m.id = :memberId AND t.travelDate between :begin and :end ")
    Optional<List<TravelControlSummaryDTO>> findByMemberIdAndPeriod(@Param("memberId") Integer id,
                                                          @Param("begin")LocalDate begin, @Param("end")LocalDate end);

    @Query("select new br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.TravelControlSummaryDTO(" +
            "t.id, t.travelDate, t.km, t.departureLocation, t.arrivalLocation, t.description, t.kmControl, " +
            "m.id, m.firstName, m.lastName, m.nickName, " +
            "h.id, h.description) " +
            "from TravelControl t " +
            "join t.member m " +
            "left join m.headQuarter h "+
            "WHERE h.id = :headQuarterid AND t.travelDate between :begin and :end ")
    Optional<List<TravelControlSummaryDTO>> FindbyHeadQuarterIdAndPeriod(@Param("headQuarterid") Integer id,
                                                         @Param("begin")LocalDate begin, @Param("end")LocalDate end);
    @Query("select new br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.TravelControlSummaryDTO(" +
            "t.id, t.travelDate, t.km, t.departureLocation, t.arrivalLocation, t.description, t.kmControl, " +
            "m.id, m.firstName, m.lastName, m.nickName, " +
            "h.id, h.description) " +
            "from TravelControl t " +
            "join t.member m " +
            "left join m.headQuarter h "+
            "WHERE t.travelDate between :begin and :end ")
    Optional<List<TravelControlSummaryDTO>> FindSummariesByPeriod(@Param("begin")LocalDate begin, @Param("end")LocalDate end);

    @Query("select new br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.TravelControlSummaryDTO(" +
            "t.id, t.travelDate, t.km, t.departureLocation, t.arrivalLocation, t.description, t.kmControl, " +
            "m.id, m.firstName, m.lastName, m.nickName, " +
            "h.id, h.description) " +
            "from TravelControl t " +
            "join t.member m " +
            "left join m.headQuarter h ")
    List<TravelControlSummaryDTO> findAllSummaries();
}
