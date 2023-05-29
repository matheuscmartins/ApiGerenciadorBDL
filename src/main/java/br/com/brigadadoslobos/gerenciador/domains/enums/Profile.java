package br.com.brigadadoslobos.gerenciador.domains.enums;

public enum Profile {
    ADMIN(0, "ROLE_ADMIN"),
    COMANDO(1, "ROLE_COMANDO"),
    DIRETOR(2, "ROLE_DIRETOR"),
    EDITOR(3, "ROLE_EDITOR"),
    USUARIO(4, "ROLE_USUARIO"),
    DESLIGADO(5, "ROLE_DESLIGADO");
    private Integer id;
    private String description;

    Profile(Integer id, String description) {
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
    public static Profile toEnum(Integer id){
        if (id == null){
            return null;
        }
        for (Profile x : Profile.values()){
            if (id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil Invalido");
    }
}
