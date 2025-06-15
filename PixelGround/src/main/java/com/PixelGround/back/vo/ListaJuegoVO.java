package com.PixelGround.back.vo;

import java.util.HashSet;
import java.util.Set;

public class ListaJuegoVO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Long usuarioId; 
    private Set<String> juegosId = new HashSet<>();
    private boolean publica;
    private Set<Long> miembrosId = new HashSet<>();

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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Set<String> getJuegosId() {
        return juegosId;
    }

    public void setJuegosId(Set<String> juegosId) {
        this.juegosId = juegosId;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    public Set<Long> getMiembrosId() {
        return miembrosId;
    }

    public void setMiembrosId(Set<Long> miembrosId) {
        this.miembrosId = miembrosId;
    }
}
