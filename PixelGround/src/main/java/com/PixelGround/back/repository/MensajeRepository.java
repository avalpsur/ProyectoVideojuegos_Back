package com.PixelGround.back.repository;

import com.PixelGround.back.model.MensajeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensajeRepository extends JpaRepository<MensajeModel, Long> {

    @Query("SELECT m FROM MensajeModel m WHERE " +
           "(m.remitente.id = :id1 AND m.receptor.id = :id2) OR " +
           "(m.remitente.id = :id2 AND m.receptor.id = :id1) " +
           "ORDER BY m.fechaEnvio ASC")
    List<MensajeModel> findMensajesEntreUsuarios(@Param("id1") Long id1, @Param("id2") Long id2);
}
