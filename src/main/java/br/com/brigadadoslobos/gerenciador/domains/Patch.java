package br.com.brigadadoslobos.gerenciador.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String description;
    @JsonBackReference
    @OneToMany(mappedBy = "patch")
    private List<MemberPatch> memberPatchList = new ArrayList<>();

    public Patch() {
    }

    public Patch(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List<MemberPatch> getMemberPatchList() {
        return memberPatchList;
    }

    public void setMemberPatchList(List<MemberPatch> memberPatchList) {
        this.memberPatchList = memberPatchList;
    }
}
