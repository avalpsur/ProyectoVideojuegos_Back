package com.PixelGround.back.model;


import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lista_juego")
public class ListaJuegoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel usuario;

    @ManyToMany
    @JoinTable(
        name = "lista_juego_juegos",
        joinColumns = @JoinColumn(name = "lista_id"),
        inverseJoinColumns = @JoinColumn(name = "juego_id")
    )
    private Set<JuegoModel> juegos;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public Set<JuegoModel> getJuegos() {
        return juegos;
    }

    public void setJuegos(Set<JuegoModel> juegos) {
        this.juegos = juegos;
    }
}
