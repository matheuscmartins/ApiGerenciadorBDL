package br.com.brigadadoslobos.gerenciador.domains.dtos;

import br.com.brigadadoslobos.gerenciador.domains.*;
import br.com.brigadadoslobos.gerenciador.domains.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MemberDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String firsName;
    private String lastName;
    private String nickName;
    private String rg;
    private String cpf;
    private String cnh;
    private String celPhone;
    private String phone;
    private String familiarPhone1;
    private String familiarPhone2;
    private String email;
    private String password;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate admissionDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate shutdowDate;
    private List<MemberPatch> memberPatchList = new ArrayList<>();
    private Set<Integer> profile = new HashSet<>();
    private HeadQuarter headQuarter;
    private Address address;
    private BloodType bloodType;

    public MemberDTO(){
        super();
        addProfile(Profile.USUARIO);
    }

    public MemberDTO(Member obj) {
        this.id = obj.getId();
        this.firsName = obj.getFirsName();
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
        this.memberPatchList = obj.getMemberPatch();
        this.profile = obj.getProfiles().stream().map(x -> x.getId()).collect(Collectors.toSet()); ;
        this.headQuarter = obj.getHeadQuarter();
        this.address = obj.getAddress();
        this.bloodType = obj.getBloodType();
        addProfile(Profile.USUARIO);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
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

    public LocalDate getShutdowDate() {
        return shutdowDate;
    }

    public void setShutdowDate(LocalDate shutdowDate) {
        this.shutdowDate = shutdowDate;
    }

    public List<MemberPatch> getMemberPatchList() {
        return memberPatchList;
    }

    public void setMemberPatchList(List<MemberPatch> memberPatchList) {
        this.memberPatchList = memberPatchList;
    }

    public Set<Profile> getProfile() {
        return profile.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        this.profile.add(profile.getId());
    }

    public HeadQuarter getHeadQuarter() {
        return headQuarter;
    }

    public void setHeadQuarter(HeadQuarter headQuarter) {
        this.headQuarter = headQuarter;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

}
