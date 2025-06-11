package com.PixelGround.back.service;

import com.PixelGround.back.model.JuegoModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.vo.ActividadVO;

import java.util.List;

public interface ActividadService {

    void registrarActividad(UsuarioModel usuario, JuegoModel juego, String tipo, String contenidoExtra);

    List<ActividadVO> obtenerFeedUsuarioYAmigos(Long usuarioId);

}
