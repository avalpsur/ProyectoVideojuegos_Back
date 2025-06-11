package com.PixelGround.back.service;

import java.util.List;

import com.PixelGround.back.vo.HiloForoVO;
import com.PixelGround.back.vo.MensajeForoVO;
import com.PixelGround.back.vo.TemaForoVO;

public interface ForoService {

    List<TemaForoVO> obtenerTemas();

    List<HiloForoVO> obtenerHilosPorTema(Long temaId);

    HiloForoVO crearHilo(Long temaId, String titulo, String contenido, Long usuarioId);

    MensajeForoVO responderAHilo(Long hiloId, String contenido, Long usuarioId);

    void eliminarHilo(Long hiloId, String token); 
    
    HiloForoVO cambiarTemaDelHilo(Long hiloId, Long nuevoTemaId, String token); 
    
    TemaForoVO crearTema(String nombre, String descripcion, String token);
    
    void eliminarTema(Long temaId, String token);
    
    List<MensajeForoVO> obtenerRespuestasDeHilo(Long hiloId);
    
    public HiloForoVO obtenerHiloPorId(Long hiloId);

    
}
