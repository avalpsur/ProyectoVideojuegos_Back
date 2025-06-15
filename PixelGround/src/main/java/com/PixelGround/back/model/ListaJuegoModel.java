package com.PixelGround.back.model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
    
    @Column(nullable = false)
    private boolean publica;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel usuario; 

    @ManyToMany
    @JoinTable(
        name = "lista_juego_juegos",
        joinColumns = @JoinColumn(name = "lista_id"),
        inverseJoinColumns = @JoinColumn(name = "juego_id")
    )
    private Set<JuegoModel> juegos = new HashSet<>();


    @ManyToMany
    @JoinTable(
        name = "lista_juego_usuarios",
        joinColumns = @JoinColumn(name = "lista_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<UsuarioModel> miembros = new HashSet<>();

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
	
    public Set<UsuarioModel> getMiembros() {
        return miembros;
    }

    public void setMiembros(Set<UsuarioModel> miembros) {
        this.miembros = miembros;
    }
    
    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }
    
}
