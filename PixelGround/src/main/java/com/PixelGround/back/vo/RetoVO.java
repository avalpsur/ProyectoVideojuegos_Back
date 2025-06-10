package com.PixelGround.back.vo;

import java.time.LocalDate;

public class RetoVO {

    private Long id;
    private String titulo;
    private String descripcion;
    private String juego;
    private LocalDate fechaInicio;
    private LocalDate fechaExpiracion;
    private Long idCreador;
    private String nombreUsuarioCreador;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getJuego() { return juego; }
    public void setJuego(String juego) { this.juego = juego; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaExpiracion() { return fechaExpiracion; }
    public void setFechaExpiracion(LocalDate fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }

    public Long getIdCreador() { return idCreador; }
    public void setIdCreador(Long idCreador) { this.idCreador = idCreador; }

    public String getNombreUsuarioCreador() { return nombreUsuarioCreador; }
    public void setNombreUsuarioCreador(String nombreUsuarioCreador) { this.nombreUsuarioCreador = nombreUsuarioCreador; }
}
