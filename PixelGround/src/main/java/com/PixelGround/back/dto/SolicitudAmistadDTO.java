package com.PixelGround.back.dto;

public class SolicitudAmistadDTO {

    private Long solicitanteId;
    private Long receptorId;

    public SolicitudAmistadDTO() {}

    public SolicitudAmistadDTO(Long solicitanteId, Long receptorId) {
        this.solicitanteId = solicitanteId;
        this.receptorId = receptorId;
    }

    public Long getSolicitanteId() {
        return solicitanteId;
    }

    public void setSolicitanteId(Long solicitanteId) {
        this.solicitanteId = solicitanteId;
    }

    public Long getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(Long receptorId) {
        this.receptorId = receptorId;
    }
}

