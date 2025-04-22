package com.PixelGround.back.vo;


public class JuegoVO {

    private Long id;
    private String apiId;
    private String nombre;
    private String imagenUrl;

    public JuegoVO() {}

    public JuegoVO(Long id, String apiId, String nombre, String imagenUrl) {
        this.id = id;
        this.apiId = apiId;
        this.nombre = nombre;
        this.imagenUrl = imagenUrl;
    }

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
}
