package com.PixelGround.back.service;

import com.PixelGround.back.converter.JuegoConverter;
import com.PixelGround.back.model.JuegoModel;
import com.PixelGround.back.repository.JuegoRepository;
import com.PixelGround.back.vo.JuegoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JuegoServiceImpl implements JuegoService {

    @Autowired
    private JuegoRepository juegoRepository;

    @Override
    public JuegoModel obtenerOcrearJuego(JuegoVO juegoVO) {
        Optional<JuegoModel> existente = juegoRepository.findByApiId(juegoVO.getApiId());

        return existente.orElseGet(() -> {
            JuegoModel nuevo = JuegoConverter.toModel(juegoVO);
            return juegoRepository.save(nuevo);
        });
    }
}
