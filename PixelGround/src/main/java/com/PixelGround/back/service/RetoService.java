package com.PixelGround.back.service;

import java.util.List;

import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.vo.RetoVO;

public interface RetoService {
	RetoVO crearReto(RetoVO retoVO, UsuarioModel creador);
    List<RetoVO> obtenerRetosActivos();
    List<RetoVO> obtenerTodos();
    RetoVO obtenerPorId(Long id);
    void eliminarReto(Long id);
}
