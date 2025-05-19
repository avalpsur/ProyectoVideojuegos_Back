package com.PixelGround.back.vo;

import java.time.LocalDateTime;

public class VotacionVO {

    private Long id;
    private Long usuarioId;
    private String juegoApiId;
    private Double puntuacion;
    private LocalDateTime fecha;
    private String nombreJuego;
    private String imagenUrlJuego;

    // Getters y setters

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

    public String getJuegoApiId() {
        return juegoApiId;
    }

    public void setJuegoApiId(String juegoApiId) {
        this.juegoApiId = juegoApiId;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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
}

