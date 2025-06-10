package com.PixelGround.back.vo;

import java.time.LocalDate;

public class RetoUsuarioVO {

    private Long id;
    private Long idUsuario;
    private String nombreUsuario;
    private Long idReto;
    private String tituloReto;
    private String comentario;
    private String imagenPruebaUrl;
    private boolean completado;
    private LocalDate fechaCompletado;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public Long getIdReto() { return idReto; }
    public void setIdReto(Long idReto) { this.idReto = idReto; }

    public String getTituloReto() { return tituloReto; }
    public void setTituloReto(String tituloReto) { this.tituloReto = tituloReto; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public String getImagenPruebaUrl() { return imagenPruebaUrl; }
    public void setImagenPruebaUrl(String imagenPruebaUrl) { this.imagenPruebaUrl = imagenPruebaUrl; }

    public boolean isCompletado() { return completado; }
    public void setCompletado(boolean completado) { this.completado = completado; }

    public LocalDate getFechaCompletado() { return fechaCompletado; }
    public void setFechaCompletado(LocalDate fechaCompletado) { this.fechaCompletado = fechaCompletado; }
}
