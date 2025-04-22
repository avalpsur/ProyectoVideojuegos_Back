package com.PixelGround.back.service;

import com.PixelGround.back.vo.ListaJuegoVO;
import com.PixelGround.back.vo.JuegoVO;

import java.util.List;

public interface ListaJuegoService {
    ListaJuegoVO crearLista(ListaJuegoVO vo);
    void añadirJuegoALista(Long listaId, JuegoVO juegoVO);
    List<ListaJuegoVO> obtenerListasDeUsuario(Long usuarioId);
    void eliminarLista(Long listaId);
}
