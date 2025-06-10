package com.PixelGround.back.service;

import java.util.List;

import com.PixelGround.back.vo.ReviewVO;

public interface ReviewService {
    ReviewVO crearReview(ReviewVO vo);
    List<ReviewVO> obtenerPorJuego(Long idJuego);
    List<ReviewVO> obtenerPorUsuario(Long idUsuario);
    ReviewVO editarReview(Long id, ReviewVO vo);
    void eliminarReview(Long id);
}
