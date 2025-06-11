package com.PixelGround.back.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HiloForoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Lob
    private String contenido;

    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel creador;

    @ManyToOne
    @JoinColumn(name = "tema_id")
    private TemaForoModel tema;

    @OneToMany(mappedBy = "hilo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MensajeForoModel> respuestas = new ArrayList<>();

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public UsuarioModel getCreador() { return creador; }
    public void setCreador(UsuarioModel creador) { this.creador = creador; }

    public TemaForoModel getTema() { return tema; }
    public void setTema(TemaForoModel tema) { this.tema = tema; }

    public List<MensajeForoModel> getRespuestas() { return respuestas; }
    public void setRespuestas(List<MensajeForoModel> respuestas) { this.respuestas = respuestas; }
}

