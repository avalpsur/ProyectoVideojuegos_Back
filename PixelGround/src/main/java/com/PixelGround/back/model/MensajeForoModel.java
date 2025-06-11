package com.PixelGround.back.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MensajeForoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String contenido;

    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel autor;

    @ManyToOne
    @JoinColumn(name = "hilo_id")
    private HiloForoModel hilo;

    // Getters y Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public UsuarioModel getAutor() { return autor; }
    public void setAutor(UsuarioModel autor) { this.autor = autor; }

    public HiloForoModel getHilo() { return hilo; }
    public void setHilo(HiloForoModel hilo) { this.hilo = hilo; }
}
