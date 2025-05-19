package com.PixelGround.back.vo;

import java.time.LocalDateTime;

public class ActividadVO {

    private Long id;
    private Long usuarioId;
    private String nombreUsuario;

    private String juegoApiId;
    private String nombreJuego;
    private String imagenUrlJuego;

    private String tipo; 
    private String contenidoExtra;

    private LocalDateTime fecha;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getJuegoApiId() {
        return juegoApiId;
    }

    public void setJuegoApiId(String juegoApiId) {
        this.juegoApiId = juegoApiId;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    public String getImagenUrlJuego() {
        return imagenUrlJuego;
    }

    public void setImagenUrlJuego(String imagenUrlJuego) {
        this.imagenUrlJuego = imagenUrlJuego;
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


