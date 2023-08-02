package br.com.brigadadoslobos.gerenciador.domains.enums;

public enum KmControl {
    KMCHEIO(0, "KMCHEIO"),
    MEIOKM(1, "MEIOKM");
    private Integer id;
    private String description;

    KmControl(Integer id, String description) {
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


    public static KmControl valueOf(int id) {
        for (KmControl value : KmControl.values()) {
            if (value.getId() == id) {
                return value;
            }
        }
        throw new IllegalArgumentException("Codigo do Controle de KM Invalido");
    }
}
