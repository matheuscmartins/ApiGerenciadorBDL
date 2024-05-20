package br.com.brigadadoslobos.gerenciador.domains;

import br.com.brigadadoslobos.gerenciador.domains.dtos.MemberDTO;
import br.com.brigadadoslobos.gerenciador.domains.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20)
    private String firstName;
    @Column(length = 100)
    private String lastName;
    @Column(length = 20)
    private String nickName;
    @Column(length = 20)
    private String rg;
    @CPF
    @Column(unique = true)
    private String cpf;
    @Column(length = 50)
    private String cnh;
    @Column(length = 50)
    private String celPhone;
    private String phone;
    @Column(length = 100)
    private String familiarPhone1;
    @Column(length = 100)
    private String familiarPhone2;
    @Column(unique = true)
    private String email;
    private String password;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate admissionDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate shutdowDate;
    @OneToMany(mappedBy = "member")
    private List<MemberPatch> memberPatchList = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    private Set<Integer> profile = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "headQuarter_id")
    private HeadQuarter headQuarter;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "bloodType_id")
    private BloodType bloodType;

    private String imagePath;

    public Member() {
        addProfile(Profile.USUARIO);
    }

    public Member(Integer id, String firstName, String lastName, String nickName, String rg, String cpf, String cnh,
                  String celPhone, String phone, String familiarPhone1, String familiarPhone2,
                  String email, String password, LocalDate birthDate, LocalDate admissionDate,
                  LocalDate shutdowDate, HeadQuarter headQuarter, Address address, BloodType bloodType, String imagePath) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.rg = rg;
        this.cpf = cpf;
        this.cnh = cnh;
        this.celPhone = celPhone;
        this.phone = phone;
        this.familiarPhone1 = familiarPhone1;
        this.familiarPhone2 = familiarPhone2;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.admissionDate = admissionDate;
        this.shutdowDate = shutdowDate;
        this.headQuarter = headQuarter;
        this.address = address;
        this.bloodType = bloodType;
        this.imagePath = imagePath;
        addProfile(Profile.USUARIO);
    }
    public Member(MemberDTO obj) {
        this.id = obj.getId();
        this.firstName = obj.getFirstName();
        this.lastName = obj.getLastName();
        this.nickName = obj.getNickName();
        this.rg = obj.getRg();
        this.cpf = obj.getCpf();
        this.cnh = obj.getCnh();
        this.celPhone = obj.getCelPhone();
        this.phone = obj.getPhone();
        this.familiarPhone1 = obj.getFamiliarPhone1();
        this.familiarPhone2 = obj.getFamiliarPhone2();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
        this.birthDate = obj.getBirthDate();
        this.admissionDate = obj.getAdmissionDate();
        this.shutdowDate = obj.getShutdowDate();
        this.memberPatchList = obj.getMemberPatchList();
        this.profile = obj.getProfile().stream().map(x -> x.getId()).collect(Collectors.toSet()); ;
        this.headQuarter = obj.getHeadQuarter();
        this.address = obj.getAddress();
        this.bloodType = obj.getBloodType();
        this.imagePath = obj.getImagePath();
    }
    public List<MemberPatch> getMemberPatch() {
        return memberPatchList;
    }

    public void setMemberPatch(List<MemberPatch> memberPatch) {
        this.memberPatchList = memberPatch;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getCelPhone() {
        return celPhone;
    }

    public void setCelPhone(String celPhone) {
        this.celPhone = celPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFamiliarPhone1() {
        return familiarPhone1;
    }

    public void setFamiliarPhone1(String familiarPhone1) {
        this.familiarPhone1 = familiarPhone1;
    }

    public String getFamiliarPhone2() {
        return familiarPhone2;
    }

    public void setFamiliarPhone2(String familiarPhone2) {
        this.familiarPhone2 = familiarPhone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public HeadQuarter getHeadQuarter() {
        return headQuarter;
    }

    public void setHeadQuarter(HeadQuarter headQuarter) {
        this.headQuarter = headQuarter;
    }

    public LocalDate getShutdowDate() {
        return shutdowDate;
    }

    public void setShutdowDate(LocalDate shutdowDate) {
        this.shutdowDate = shutdowDate;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public Set<Profile> getProfiles() {
        return profile.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addProfile(Profile profile) {
        this.profile.add(profile.getId());
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) && Objects.equals(cpf, member.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }

}
