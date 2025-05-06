package com.PixelGround.back.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "amistades", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"solicitante_id", "receptor_id"})
})
public class AmistadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solicitante_id", nullable = false)
    private UsuarioModel solicitante;

    @ManyToOne
    @JoinColumn(name = "receptor_id", nullable = false)
    private UsuarioModel receptor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAmistad estado;

    @Column(nullable = false)
    private LocalDateTime fechaSolicitud;

    public enum EstadoAmistad {
        PENDIENTE,
        ACEPTADA
    }

    public AmistadModel() {}

    public AmistadModel(UsuarioModel solicitante, UsuarioModel receptor, EstadoAmistad estado) {
        this.solicitante = solicitante;
        this.receptor = receptor;
        this.estado = estado;
        this.fechaSolicitud = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UsuarioModel getSolicitante() { return solicitante; }
    public void setSolicitante(UsuarioModel solicitante) { this.solicitante = solicitante; }

    public UsuarioModel getReceptor() { return receptor; }
    public void setReceptor(UsuarioModel receptor) { this.receptor = receptor; }

    public EstadoAmistad getEstado() { return estado; }
    public void setEstado(EstadoAmistad estado) { this.estado = estado; }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }
}
