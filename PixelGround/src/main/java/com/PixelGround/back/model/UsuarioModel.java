package com.PixelGround.back.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres")
    @Column(nullable = false, unique = true, length = 50)
    private String nombreUsuario;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe tener un formato válido")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "El rol no puede estar vacío")
    @Column(nullable = false)
    private String rol;

    @Column(nullable = false)
    private LocalDateTime fechaAlta;
    
    @Column(nullable = false)
    private boolean perfilPublico = true;
    
    @Column(name = "steam_id", nullable = true)
    private String steamId;
    

    public UsuarioModel() {}

    public UsuarioModel(Long id, String nombreUsuario, String email, String password, String rol, LocalDateTime fechaAlta) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.fechaAlta = fechaAlta;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNombreUsuario() { return nombreUsuario; }

    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }

    public void setRol(String rol) { this.rol = rol; }

    public LocalDateTime getFechaAlta() { return fechaAlta; }

    public void setFechaAlta(LocalDateTime fechaAlta) { this.fechaAlta = fechaAlta; }
    
	public String getSteamId() {
	    return steamId;
	}
	
	public void setSteamId(String steamId) {
	    this.steamId = steamId;
	}
	
	public boolean isPerfilPublico() {
	    return perfilPublico;
	}

	public void setPerfilPublico(boolean perfilPublico) {
	    this.perfilPublico = perfilPublico;
	}
    
}
