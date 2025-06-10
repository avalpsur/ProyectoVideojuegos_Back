package com.PixelGround.back.repository;

import com.PixelGround.back.model.RetoUsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetoUsuarioRepository extends JpaRepository<RetoUsuarioModel, Long> {
    List<RetoUsuarioModel> findByUsuarioId(Long usuarioId);
    List<RetoUsuarioModel> findByRetoId(Long retoId);
    List<RetoUsuarioModel> findByCompletadoTrueOrderByFechaCompletadoDesc();
}
