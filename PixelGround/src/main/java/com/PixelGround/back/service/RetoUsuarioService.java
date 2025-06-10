package com.PixelGround.back.service;

import com.PixelGround.back.vo.RetoUsuarioVO;
import java.util.List;

public interface RetoUsuarioService {
    RetoUsuarioVO unirseAReto(RetoUsuarioVO vo);
    RetoUsuarioVO marcarComoCompletado(Long id, String comentario, String imagenUrl);
    List<RetoUsuarioVO> obtenerPorUsuario(Long usuarioId);
    List<RetoUsuarioVO> obtenerPorReto(Long retoId);
    List<RetoUsuarioVO> obtenerCompletados();
}
