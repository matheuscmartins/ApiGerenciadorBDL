package br.com.brigadadoslobos.gerenciador.domains.dtos;

import br.com.brigadadoslobos.gerenciador.domains.Member;
import br.com.brigadadoslobos.gerenciador.domains.TravelControl;
import br.com.brigadadoslobos.gerenciador.domains.enums.KmControl;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDate;

public class TravelControlDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate travelDate;
    private Double km;
    private String departureLocation;
    private String arrivalLocation;
    private String description;
    private Member member;
    @Column(nullable = false)
    private KmControl kmControl;

    public TravelControlDTO() {

    }

    public TravelControlDTO(TravelControl obj) {
        this.id = obj.getId();
        this.travelDate = obj.getTravelDate();
        this.km = obj.getKm();
        this.departureLocation = obj.getDepartureLocation();
        this.arrivalLocation = obj.getArrivalLocation();
        this.description = obj.getDescription();
        this.member = obj.getMember();
        this.kmControl = obj.getKmControl();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public KmControl getKmControl() {
        return kmControl;
    }

    public void setKmControl(KmControl kmControl) {
        this.kmControl = kmControl;
    }
}
