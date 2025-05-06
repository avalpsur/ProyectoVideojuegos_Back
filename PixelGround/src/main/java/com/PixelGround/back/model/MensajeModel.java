package com.PixelGround.back.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes")
public class MensajeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "remitente_id", nullable = false)
    private UsuarioModel remitente;

    @ManyToOne
    @JoinColumn(name = "receptor_id", nullable = false)
    private UsuarioModel receptor;

    @Column(nullable = false)
    private String contenido;

    @Column(nullable = false)
    private LocalDateTime fechaEnvio;

    public MensajeModel() {}

    public MensajeModel(UsuarioModel remitente, UsuarioModel receptor, String contenido) {
        this.remitente = remitente;
        this.receptor = receptor;
        this.contenido = contenido;
        this.fechaEnvio = LocalDateTime.now();
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UsuarioModel getRemitente() { return remitente; }
    public void setRemitente(UsuarioModel remitente) { this.remitente = remitente; }

    public UsuarioModel getReceptor() { return receptor; }
    public void setReceptor(UsuarioModel receptor) { this.receptor = receptor; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }
}
