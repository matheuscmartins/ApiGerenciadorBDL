package br.com.brigadadoslobos.gerenciador.domains;

import br.com.brigadadoslobos.gerenciador.domains.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Member implements Serializable {
    private  static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String firsName;
    protected String lastName;
    protected String nickName;
    protected String rg;
    @Column(unique = true)
    protected String cpf;
    protected String cnh;
    protected String celPhone;
    protected String phone;
    protected String familiarPhone1;
    protected String familiarPhone2;
    @Column(unique = true)
    protected String email;
    protected String password;
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate birthDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate admissionDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate shutdowDate;

    @ElementCollection(fetch =  FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    protected Set<Integer> profile = new HashSet<>();
    protected Set<HeadQuarter> headQuarter = new HashSet<>();
    protected Set<Address> address = new HashSet<>();
    protected Set<BloodType> bloodType = new HashSet<>();

    public Member(){
        addProfile(Profile.USUARIO);
    }

    public Member(Integer id, String firsName, String lastName, String nickName, String rg, String cpf, String cnh, String celPhone, String phone, String familiarPhone1, String familiarPhone2, String email, String password, LocalDate birthDate, LocalDate admissionDate, LocalDate shutdowDate) {
        this.id = id;
        this.firsName = firsName;
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
    public Set<Profile> getProfiles(){
        return profile.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }
    public void addProfile(Profile profile){
        this.profile.add(profile.getId());
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
