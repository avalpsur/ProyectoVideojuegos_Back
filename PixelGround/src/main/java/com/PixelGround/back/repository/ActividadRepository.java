package com.PixelGround.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PixelGround.back.model.ActividadModel;
import com.PixelGround.back.model.JuegoModel;
import com.PixelGround.back.model.UsuarioModel;

public interface ActividadRepository extends JpaRepository<ActividadModel, Long> {

    List<ActividadModel> findByUsuarioInOrderByFechaDesc(List<UsuarioModel> usuarios);

}
