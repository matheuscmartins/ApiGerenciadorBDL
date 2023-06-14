package br.com.brigadadoslobos.gerenciador.domains;

import br.com.brigadadoslobos.gerenciador.domains.dtos.PatchDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Patch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    @JsonBackReference
    @OneToMany(mappedBy = "patch")
    private List<MemberPatch> memberPatchList = new ArrayList<>();

    public Patch() {
    }

    public Patch(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Patch(PatchDTO obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.memberPatchList = obj.getMemberPatchList().stream().map( x -> x
        ).collect(Collectors.toList());
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<MemberPatch> getMemberPatchList() {
        return memberPatchList;
    }

    public void setMemberPatchList(List<MemberPatch> memberPatchList) {
        this.memberPatchList = memberPatchList;
    }
}
