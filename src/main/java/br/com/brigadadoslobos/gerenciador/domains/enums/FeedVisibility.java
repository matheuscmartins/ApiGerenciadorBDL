package br.com.brigadadoslobos.gerenciador.domains.enums;

public enum FeedVisibility {
    PUBLICA(0, "PUBLICA"),
    RESTRITA(1, "RESTRITA");

    private final Integer id;
    private final String description;

    FeedVisibility(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static FeedVisibility valueOf(int id) {
        for (FeedVisibility value : FeedVisibility.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código de visibilidade inválido: " + id);
    }
}
