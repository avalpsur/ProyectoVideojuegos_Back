package com.PixelGround.back.converter;

import com.PixelGround.back.model.RetoUsuarioModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.model.RetoModel;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.repository.RetoRepository;
import com.PixelGround.back.vo.RetoUsuarioVO;
import org.springframework.stereotype.Component;

@Component
public class RetoUsuarioConverter {

    private final UsuarioRepository usuarioRepository;
    private final RetoRepository retoRepository;

    public RetoUsuarioConverter(UsuarioRepository usuarioRepository, RetoRepository retoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.retoRepository = retoRepository;
    }

    public RetoUsuarioVO toVO(RetoUsuarioModel entity) {
        RetoUsuarioVO vo = new RetoUsuarioVO();
        vo.setId(entity.getId());
        vo.setComentario(entity.getComentario());
        vo.setImagenPruebaUrl(entity.getImagenPruebaUrl());
        vo.setCompletado(entity.isCompletado());
        vo.setFechaCompletado(entity.getFechaCompletado());

        if (entity.getUsuario() != null) {
            vo.setIdUsuario(entity.getUsuario().getId());
            vo.setNombreUsuario(entity.getUsuario().getNombreUsuario());
        }

        if (entity.getReto() != null) {
            vo.setIdReto(entity.getReto().getId());
            vo.setTituloReto(entity.getReto().getTitulo());
        }

        return vo;
    }

    public RetoUsuarioModel toModel(RetoUsuarioVO vo) {
        RetoUsuarioModel entity = new RetoUsuarioModel();
        entity.setId(vo.getId());
        entity.setComentario(vo.getComentario());
        entity.setImagenPruebaUrl(vo.getImagenPruebaUrl());
        entity.setCompletado(vo.isCompletado());
        entity.setFechaCompletado(vo.getFechaCompletado());

        if (vo.getIdUsuario() != null) {
            UsuarioModel usuario = usuarioRepository.findById(vo.getIdUsuario()).orElse(null);
            entity.setUsuario(usuario);
        }

        if (vo.getIdReto() != null) {
            RetoModel reto = retoRepository.findById(vo.getIdReto()).orElse(null);
            entity.setReto(reto);
        }

        return entity;
    }
}
