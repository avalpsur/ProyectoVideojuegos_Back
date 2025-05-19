package com.PixelGround.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PixelGround.back.model.JuegoModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.model.VotacionModel;

public interface VotacionRepository extends JpaRepository<VotacionModel, Long> {
    Optional<VotacionModel> findByUsuarioAndJuego(UsuarioModel usuario, JuegoModel juego);
    List<VotacionModel> findByUsuarioId(Long usuarioId);

}
