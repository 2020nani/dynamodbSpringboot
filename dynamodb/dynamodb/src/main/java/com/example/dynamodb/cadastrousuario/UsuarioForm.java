package com.example.dynamodb.cadastrousuario;

public class UsuarioForm {
    private String usuarioId;
    private String name;
    private String email;

    public UsuarioForm(String usuarioId,String name, String email) {
        this.usuarioId = usuarioId;
        this.name = name;
        this.email = email;
    }

    public Usuario converte() {
        return new Usuario(usuarioId,name,email);
    }
}
