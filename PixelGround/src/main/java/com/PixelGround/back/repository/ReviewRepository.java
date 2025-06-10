package com.PixelGround.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PixelGround.back.model.ReviewModel;

public interface ReviewRepository extends JpaRepository<ReviewModel, Long> {
    List<ReviewModel> findByJuegoId(Long idJuego);
    List<ReviewModel> findByUsuarioId(Long idUsuario);
    List<ReviewModel> findByJuegoApiId(String apiId);
}
