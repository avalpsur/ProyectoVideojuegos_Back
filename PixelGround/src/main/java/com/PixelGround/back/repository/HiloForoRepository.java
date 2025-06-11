package com.PixelGround.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PixelGround.back.model.HiloForoModel;

public interface HiloForoRepository extends JpaRepository<HiloForoModel, Long> {
    List<HiloForoModel> findByTemaId(Long temaId);
}
