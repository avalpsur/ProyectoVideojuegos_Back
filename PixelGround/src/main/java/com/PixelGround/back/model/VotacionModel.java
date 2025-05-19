package com.PixelGround.back.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "votacion", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "juego_id"}))
public class VotacionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "juego_id")
    private JuegoModel juego;

    private double puntuacion; 

    private LocalDateTime fecha;


    //Getters y setters 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public JuegoModel getJuego() {
        return juego;
    }

    public void setJuego(JuegoModel juego) {
        this.juego = juego;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}

