package com.PixelGround.back.repository;

import com.PixelGround.back.model.AmistadModel;
import com.PixelGround.back.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AmistadRepository extends JpaRepository<AmistadModel, Long> {

    Optional<AmistadModel> findBySolicitanteAndReceptor(UsuarioModel solicitante, UsuarioModel receptor);

    List<AmistadModel> findByReceptorAndEstado(UsuarioModel receptor, AmistadModel.EstadoAmistad estado);

    List<AmistadModel> findBySolicitanteAndEstado(UsuarioModel solicitante, AmistadModel.EstadoAmistad estado);

    void deleteBySolicitanteAndReceptor(UsuarioModel solicitante, UsuarioModel receptor);

    @Query("SELECT a FROM AmistadModel a " +
           "WHERE (a.solicitante.id = :id OR a.receptor.id = :id) " +
           "AND a.estado = com.PixelGround.back.model.AmistadModel.EstadoAmistad.ACEPTADA")
    List<AmistadModel> findAmistadesAceptadasPorUsuario(@Param("id") Long id);
}
