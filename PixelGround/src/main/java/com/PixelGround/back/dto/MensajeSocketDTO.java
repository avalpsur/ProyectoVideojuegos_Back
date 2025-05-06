package com.PixelGround.back.dto;

public class MensajeSocketDTO {

    private Long remitenteId;
    private Long receptorId;
    private String contenido;

    public MensajeSocketDTO() {}

    public MensajeSocketDTO(Long remitenteId, Long receptorId, String contenido) {
        this.remitenteId = remitenteId;
        this.receptorId = receptorId;
        this.contenido = contenido;
    }

    public Long getRemitenteId() {
        return remitenteId;
    }

    public void setRemitenteId(Long remitenteId) {
        this.remitenteId = remitenteId;
    }

    public Long getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(Long receptorId) {
        this.receptorId = receptorId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}

