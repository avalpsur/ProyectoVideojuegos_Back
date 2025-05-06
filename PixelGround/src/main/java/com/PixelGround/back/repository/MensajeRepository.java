package com.PixelGround.back.repository;

import com.PixelGround.back.model.MensajeModel;
import com.PixelGround.back.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<MensajeModel, Long> {

    List<MensajeModel> findByRemitenteAndReceptorOrderByFechaEnvioAsc(UsuarioModel remitente, UsuarioModel receptor);

    List<MensajeModel> findByReceptorAndRemitenteOrderByFechaEnvioAsc(UsuarioModel receptor, UsuarioModel remitente);
}
