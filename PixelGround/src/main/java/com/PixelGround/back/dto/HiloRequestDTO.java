package com.PixelGround.back.dto;

public class HiloRequestDTO {
    private Long temaId;
    private String titulo;
    private String contenido;
    

    // Getters y Setters
    public Long getTemaId() { return temaId; }
    public void setTemaId(Long temaId) { this.temaId = temaId; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
}
