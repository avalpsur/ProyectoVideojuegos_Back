package com.PixelGround.back.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.PixelGround.back.converter.RetoConverter;
import com.PixelGround.back.model.RetoModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.RetoRepository;
import com.PixelGround.back.vo.RetoVO;

@Service
public class RetoServiceImpl implements RetoService {

    private final RetoRepository retoRepository;
    private final RetoConverter retoConverter;

    public RetoServiceImpl(RetoRepository retoRepository, RetoConverter retoConverter) {
        this.retoRepository = retoRepository;
        this.retoConverter = retoConverter;
    }

    @Override
    public RetoVO crearReto(RetoVO retoVO, UsuarioModel creador) {
        RetoModel reto = retoConverter.toModel(retoVO);
        reto.setCreador(creador); 
        reto = retoRepository.save(reto);
        return retoConverter.toVO(reto);
    }


    @Override
    public List<RetoVO> obtenerRetosActivos() {
        return retoRepository.findByFechaExpiracionAfterOrderByFechaExpiracionAsc(LocalDate.now())
                .stream().map(retoConverter::toVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RetoVO> obtenerTodos() {
        return retoRepository.findAll().stream()
                .map(retoConverter::toVO)
                .collect(Collectors.toList());
    }

    @Override
    public RetoVO obtenerPorId(Long id) {
        return retoRepository.findById(id)
                .map(retoConverter::toVO)
                .orElse(null);
    }

    @Override
    public void eliminarReto(Long id) {
        retoRepository.deleteById(id);
    }
}
