package br.com.brigadadoslobos.gerenciador.domains;

import br.com.brigadadoslobos.gerenciador.domains.dtos.TravelControlDTO;
import br.com.brigadadoslobos.gerenciador.domains.enums.KmControl;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class TravelControl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate travelDate;
    @Column(nullable = false)
    private Double km;
    @Column(nullable = false)
    private String departureLocation;
    @Column(nullable = false)
    private String arrivalLocation;
    private String description;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(nullable = false)
    private KmControl kmControl;

    public TravelControl(){
    }

    public TravelControl(Integer id, LocalDate travelDate, Double km,
                         String departureLocation, String arrivalLocation,
                         String description, Member member, KmControl kmControl) {
        this.id = id;
        this.travelDate = travelDate;
        this.km = km;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.description = description;
        this.member = member;
        this.kmControl = KmControl.valueOf(kmControl.getId());
    }
    public TravelControl(TravelControlDTO obj) {
        this.id = obj.getId();
        this.travelDate =  obj.getTravelDate();
        this.km = obj.getKm();
        this.departureLocation =  obj.getDepartureLocation();
        this.arrivalLocation =  obj.getArrivalLocation();
        this.description = obj.getDescription();
        this.member = obj.getMember();
        this.kmControl =  obj.getKmControl();
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

    public void addKmControl(KmControl kmControl) {

        this.kmControl = KmControl.valueOf(kmControl.getId());
    }
}
