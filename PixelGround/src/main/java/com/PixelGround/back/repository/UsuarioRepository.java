package com.PixelGround.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PixelGround.back.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByEmail(String email);

    Optional<UsuarioModel> findByNombreUsuario(String nombreUsuario);
    
    //Búsquedas parciales para el buscador de amistades
    List<UsuarioModel> findByNombreUsuarioContainingIgnoreCase(String nombreUsuario);

}
