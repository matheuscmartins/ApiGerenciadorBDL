package br.com.brigadadoslobos.gerenciador.domains.dtos;

import br.com.brigadadoslobos.gerenciador.domains.BloodType;
import br.com.brigadadoslobos.gerenciador.domains.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BloodTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String description;
    @JsonIgnore
    private List<Member> members = new ArrayList<>();

    public BloodTypeDTO(){
        super();
    }
    public BloodTypeDTO(BloodType obj) {
        this.id = obj.getId();
        this.description = obj.getDescription();
        this.members = obj.getMembers().stream().map( x -> x
        ).collect(Collectors.toList());
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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
