package br.com.brigadadoslobos.gerenciador.domains.enums;

public enum InfractionType {
    ESCRITA(0, "ESCRITA"),
    SUSPENSAO(1, "SUSPENSAO"),
    VERBAL(2, "VERBAL"),
    DESLIGAMENTO(3, "DESLIGAMENTO"),
    REBAIXAMENTO(4, "REBAIXAMENTO"),
    EXPULSAO(5, "EXPULSAO");
    private Integer id;
    private String description;

    InfractionType(Integer id, String description) {
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


    public static InfractionType valueOf(int id) {
        for (InfractionType value : InfractionType.values()) {
            if (value.getId() == id) {
                return value;
            }
        }
        throw new IllegalArgumentException("Codigo da Tipo de Advertencia Invalido");
    }
}
