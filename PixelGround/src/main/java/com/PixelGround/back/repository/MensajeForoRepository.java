package com.PixelGround.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PixelGround.back.model.MensajeForoModel;

public interface MensajeForoRepository extends JpaRepository<MensajeForoModel, Long> {
    List<MensajeForoModel> findByHiloId(Long hiloId);
}

