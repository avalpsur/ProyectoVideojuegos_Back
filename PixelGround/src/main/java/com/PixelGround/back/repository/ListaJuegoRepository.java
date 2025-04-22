package com.PixelGround.back.repository;

import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.model.ListaJuegoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListaJuegoRepository extends JpaRepository<ListaJuegoModel, Long> {
    List<ListaJuegoModel> findByUsuario(UsuarioModel usuario);
}