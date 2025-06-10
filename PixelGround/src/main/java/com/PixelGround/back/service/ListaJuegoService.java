package com.PixelGround.back.service;

import com.PixelGround.back.vo.JuegoVO;
import com.PixelGround.back.vo.ListaJuegoVO;

import java.util.List;

public interface ListaJuegoService {

    ListaJuegoVO crearLista(ListaJuegoVO vo);

    void añadirJuegoALista(Long listaId, JuegoVO juegoVO, Long usuarioId);

    List<ListaJuegoVO> obtenerListasDeUsuario(Long usuarioId);

    void eliminarLista(Long listaId, Long usuarioId);
}
