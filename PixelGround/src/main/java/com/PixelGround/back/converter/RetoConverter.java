package com.PixelGround.back.converter;

import org.springframework.stereotype.Component;
import com.PixelGround.back.model.RetoModel;
import com.PixelGround.back.vo.RetoVO;
import com.PixelGround.back.repository.UsuarioRepository;

@Component
public class RetoConverter {

    private final UsuarioRepository usuarioRepository;

    public RetoConverter(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public RetoVO toVO(RetoModel reto) {
        RetoVO vo = new RetoVO();
        vo.setId(reto.getId());
        vo.setTitulo(reto.getTitulo());
        vo.setDescripcion(reto.getDescripcion());
        vo.setJuego(reto.getJuego());
        vo.setFechaInicio(reto.getFechaInicio());
        vo.setFechaExpiracion(reto.getFechaExpiracion());
        if (reto.getCreador() != null) {
            vo.setIdCreador(reto.getCreador().getId());
            vo.setNombreUsuarioCreador(reto.getCreador().getNombreUsuario());
        }
        return vo;
    }

    public RetoModel toModel(RetoVO vo) {
        RetoModel reto = new RetoModel();
        reto.setId(vo.getId());
        reto.setTitulo(vo.getTitulo());
        reto.setDescripcion(vo.getDescripcion());
        reto.setJuego(vo.getJuego());
        reto.setFechaInicio(vo.getFechaInicio());
        reto.setFechaExpiracion(vo.getFechaExpiracion());
        if (vo.getIdCreador() != null) {
            reto.setCreador(usuarioRepository.findById(vo.getIdCreador()).orElse(null));
        }
        return reto;
    }
}
