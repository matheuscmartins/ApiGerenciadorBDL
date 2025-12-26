package br.com.brigadadoslobos.gerenciador.domains.dtos.summarys;

import br.com.brigadadoslobos.gerenciador.domains.enums.KmControl;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class TravelControlSummaryDTO implements Serializable {

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate travelDate;
    private Double km;
    private String departureLocation;
    private String arrivalLocation;
    private String description;
    private MemberSummaryDTO member;

    private KmControl kmControl;

    public TravelControlSummaryDTO(Integer id, LocalDate travelDate, Double km,
                                 String departureLocation, String arrivalLocation,
                                  String description, KmControl kmControl,
                                 Integer memberId, String firstName, String lastName, String nickName,
                                 Integer headQuarterId, String headQuarterDescription) {
        this.id = id;
        this.travelDate = travelDate;
        this.km = km;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.description = description;
        this.kmControl =kmControl;
        this.member = new MemberSummaryDTO(memberId, firstName, lastName, nickName, headQuarterId, headQuarterDescription);
    }
    public Integer getId() {return id;}
    public LocalDate getTravelDate() { return travelDate; }
    public Double getKm() { return km; }
    public String getDepartureLocation() { return departureLocation; }
    public String getArrivalLocation() { return arrivalLocation; }
    public String getDescription() { return description; }
    public KmControl getKmControl() { return kmControl; }
    public MemberSummaryDTO getMember() { return member; }

}
