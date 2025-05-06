package com.PixelGround.back.vo;

import java.time.LocalDateTime;

public class AmistadVO {

    private Long id;
    private Long idSolicitante;
    private Long idReceptor;
    private String nombreReceptor;
    private String estado;
    private LocalDateTime fechaSolicitud;

    public AmistadVO() {}

    public AmistadVO(Long id, Long idSolicitante, Long idReceptor, String nombreReceptor, String estado, LocalDateTime fechaSolicitud) {
        this.id = id;
        this.idSolicitante = idSolicitante;
        this.idReceptor = idReceptor;
        this.nombreReceptor = nombreReceptor;
        this.estado = estado;
        this.fechaSolicitud = fechaSolicitud;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(Long idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public Long getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(Long idReceptor) {
        this.idReceptor = idReceptor;
    }

    public String getNombreReceptor() {
        return nombreReceptor;
    }

    public void setNombreReceptor(String nombreReceptor) {
        this.nombreReceptor = nombreReceptor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

}
