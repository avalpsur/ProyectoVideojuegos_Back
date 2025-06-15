package com.PixelGround.back.service;
import java.util.List;

import com.PixelGround.back.vo.ListaJuegoVO;
import com.PixelGround.back.vo.JuegoVO;
import com.PixelGround.back.model.UsuarioModel;


public interface ListaJuegoService {

    ListaJuegoVO crearLista(ListaJuegoVO vo);

    void añadirJuegoALista(Long listaId, JuegoVO juegoVO, Long usuarioId);

    List<ListaJuegoVO> obtenerListasDeUsuario(Long usuarioId);

    void eliminarLista(Long listaId, Long usuarioId);

    void agregarUsuario(Long listaId, UsuarioModel usuario);  

    List<ListaJuegoVO> obtenerListasPublicas();

    ListaJuegoVO obtenerListaPorId(Long id, UsuarioModel usuario); 

    void cambiarVisibilidad(Long listaId, Long usuarioId); 
    
    public void eliminarJuegoDeLista(Long listaId, String apiId, Long usuarioId);
}

