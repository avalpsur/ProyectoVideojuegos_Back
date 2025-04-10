package com.PixelGround.back.repository;

import com.PixelGround.back.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByEmail(String email);

    Optional<UsuarioModel> findByNombreUsuario(String nombreUsuario);
}
