package com.PixelGround.back.repository;

import com.PixelGround.back.model.ActividadModel;
import com.PixelGround.back.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadRepository extends JpaRepository<ActividadModel, Long> {

    List<ActividadModel> findByUsuarioInOrderByFechaDesc(List<UsuarioModel> usuarios);
}
