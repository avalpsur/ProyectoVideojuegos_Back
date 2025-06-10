package com.PixelGround.back.repository;

import com.PixelGround.back.model.RetoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RetoRepository extends JpaRepository<RetoModel, Long> {
    List<RetoModel> findByJuegoContainingIgnoreCase(String juego);
    List<RetoModel> findByFechaExpiracionAfterOrderByFechaExpiracionAsc(java.time.LocalDate fecha);
}
