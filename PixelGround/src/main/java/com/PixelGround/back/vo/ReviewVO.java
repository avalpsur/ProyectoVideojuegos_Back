package com.PixelGround.back.vo;

import java.time.LocalDateTime;

public class ReviewVO {
    private Long id;
    private Long idUsuario;
    private String nombreUsuario;
    private Long idJuego;
    private String nombreJuego;
    private String contenido;
    private LocalDateTime fecha;
    private String imagenUrlJuego; 

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public Long getIdJuego() { return idJuego; }
    public void setIdJuego(Long idJuego) { this.idJuego = idJuego; }

    public String getNombreJuego() { return nombreJuego; }
    public void setNombreJuego(String nombreJuego) { this.nombreJuego = nombreJuego; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getImagenUrlJuego() { return imagenUrlJuego; }
    public void setImagenUrlJuego(String imagenUrlJuego) { this.imagenUrlJuego = imagenUrlJuego; }
}

