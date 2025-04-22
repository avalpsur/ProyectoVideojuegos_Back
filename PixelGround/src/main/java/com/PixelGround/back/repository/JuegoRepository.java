package com.PixelGround.back.repository;

import com.PixelGround.back.model.JuegoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JuegoRepository extends JpaRepository<JuegoModel, Long> {
    Optional<JuegoModel> findByApiId(String apiId);
}
