package com.PixelGround.back.vo;

import java.util.List;

public class ListaJuegoVO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Long usuarioId;
    private List<JuegoVO> juegos;

    public ListaJuegoVO() {}

    public ListaJuegoVO(Long id, String nombre, String descripcion, Long usuarioId, List<JuegoVO> juegos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuarioId = usuarioId;
        this.juegos = juegos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<JuegoVO> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<JuegoVO> juegos) {
        this.juegos = juegos;
    }
}
