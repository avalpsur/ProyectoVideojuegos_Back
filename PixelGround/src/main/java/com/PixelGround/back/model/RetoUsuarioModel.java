package com.PixelGround.back.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class RetoUsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UsuarioModel usuario;

    @ManyToOne
    private RetoModel reto;

    private String comentario;

    private String imagenPruebaUrl;

    private boolean completado;

    private LocalDate fechaCompletado;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UsuarioModel getUsuario() { return usuario; }
    public void setUsuario(UsuarioModel usuario) { this.usuario = usuario; }

    public RetoModel getReto() { return reto; }
    public void setReto(RetoModel reto) { this.reto = reto; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public String getImagenPruebaUrl() { return imagenPruebaUrl; }
    public void setImagenPruebaUrl(String imagenPruebaUrl) { this.imagenPruebaUrl = imagenPruebaUrl; }

    public boolean isCompletado() { return completado; }
    public void setCompletado(boolean completado) { this.completado = completado; }

    public LocalDate getFechaCompletado() { return fechaCompletado; }
    public void setFechaCompletado(LocalDate fechaCompletado) { this.fechaCompletado = fechaCompletado; }
}
