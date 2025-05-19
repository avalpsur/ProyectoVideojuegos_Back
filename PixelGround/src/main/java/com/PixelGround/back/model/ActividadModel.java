package com.PixelGround.back.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "actividad")
public class ActividadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UsuarioModel usuario;

    @ManyToOne
    private JuegoModel juego;

    @Column(nullable = false)
    private String tipo;

    @Column(length = 1000)
    private String contenidoExtra;

    private LocalDateTime fecha;

    // Getters y Setters

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContenidoExtra() {
        return contenidoExtra;
    }

    public void setContenidoExtra(String contenidoExtra) {
        this.contenidoExtra = contenidoExtra;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}

