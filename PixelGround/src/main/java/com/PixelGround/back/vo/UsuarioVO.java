package com.PixelGround.back.vo;

public class UsuarioVO {

    private Long id;
    private String nombreUsuario;
    private String email;
    private String rol;


    public UsuarioVO() {}

    public UsuarioVO(Long id, String nombreUsuario, String email, String rol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.rol = rol;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNombreUsuario() { return nombreUsuario; }

    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getRol() { return rol; }

    public void setRol(String rol) { this.rol = rol; }

}
