package com.PixelGround.back.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ReviewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private UsuarioModel usuario;

    @ManyToOne(optional = false)
    private JuegoModel juego;

    private String contenido;

    private LocalDateTime fecha;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public JuegoModel getJuego() {
        return juego;
    }

    public void setJuego(JuegoModel juego) {
        this.juego = juego;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}

