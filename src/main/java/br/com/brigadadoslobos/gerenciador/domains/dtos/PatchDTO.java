package br.com.brigadadoslobos.gerenciador.domains.dtos;

import br.com.brigadadoslobos.gerenciador.domains.MemberPatch;
import br.com.brigadadoslobos.gerenciador.domains.Patch;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatchDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotNull(message = "O campo Nome é requerido")
    private String name;
    @JsonBackReference
    private List<MemberPatch> memberPatchList = new ArrayList<>();

    public PatchDTO(){
        super();
    }

    public PatchDTO(Patch obj) {
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
