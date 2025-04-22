package com.PixelGround.back.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "juego")
public class JuegoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_id", nullable = false, unique = true)
    private String apiId;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @ManyToMany(mappedBy = "juegos")
    private Set<ListaJuegoModel> listas;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Set<ListaJuegoModel> getListas() {
        return listas;
    }

    public void setListas(Set<ListaJuegoModel> listas) {
        this.listas = listas;
    }
}
