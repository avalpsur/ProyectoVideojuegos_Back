package com.PixelGround.back.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class RetoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descripcion;

    private String juego;

    private LocalDate fechaInicio;

    private LocalDate fechaExpiracion;

    @ManyToOne
    @JoinColumn(name = "creador_id")
    private UsuarioModel creador;

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

    public UsuarioModel getCreador() { return creador; }
    public void setCreador(UsuarioModel creador) { this.creador = creador; }
}
