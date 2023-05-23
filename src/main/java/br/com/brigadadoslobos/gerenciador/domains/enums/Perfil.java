package br.com.brigadadoslobos.gerenciador.domains.enums;

public enum Perfil {
    ADMIN(0, "ROLE_ADMIN"),
    COMANDO(1, "ROLE_COMANDO"),
    DIRETOR(2, "ROLE_DIRETOR"),
    EDITOR(3, "ROLE_EDITOR"),
    USUARIO(4, "ROLE_USUARIO"),
    DESLIGADO(5, "ROLE_DESLIGADO");
    private Integer codigo;
    private String descricao;

    Perfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public static Perfil toEnum(Integer codigo){
        if (codigo == null){
            return null;
        }
        for (Perfil x : Perfil.values()){
            if (codigo.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil Invalido");
    }
}
