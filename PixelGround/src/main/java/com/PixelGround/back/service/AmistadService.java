package com.PixelGround.back.service;

import java.util.List;

import com.PixelGround.back.vo.AmistadVO;
import com.PixelGround.back.vo.UsuarioVO;

public interface AmistadService {
    void enviarSolicitud(Long solicitanteId, Long receptorId);
    void aceptarSolicitud(Long receptorId, Long solicitanteId);
    void rechazarSolicitud(Long receptorId, Long solicitanteId);
    List<AmistadVO> obtenerSolicitudesRecibidas(Long idUsuario);
    List<UsuarioVO> obtenerAmigos(Long idUsuario);
}
