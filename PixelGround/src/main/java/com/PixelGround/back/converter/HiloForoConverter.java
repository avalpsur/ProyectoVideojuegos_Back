package com.PixelGround.back.converter;

import org.springframework.stereotype.Component;

import com.PixelGround.back.model.HiloForoModel;
import com.PixelGround.back.model.TemaForoModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.vo.HiloForoVO;

@Component
public class HiloForoConverter {

    public HiloForoVO toVO(HiloForoModel model) {
        HiloForoVO vo = new HiloForoVO();
        vo.setId(model.getId());
        vo.setTitulo(model.getTitulo());
        vo.setContenido(model.getContenido());
        vo.setFechaCreacion(model.getFechaCreacion());
        vo.setCreadorId(model.getCreador().getId());
        vo.setNombreCreador(model.getCreador().getNombreUsuario());
        vo.setTemaId(model.getTema().getId());
        return vo;
    }

    public HiloForoModel toModel(HiloForoVO vo, UsuarioModel creador, TemaForoModel tema) {
        HiloForoModel model = new HiloForoModel();
        model.setId(vo.getId());
        model.setTitulo(vo.getTitulo());
        model.setContenido(vo.getContenido());
        model.setFechaCreacion(vo.getFechaCreacion());
        model.setCreador(creador);
        model.setTema(tema);
        return model;
    }
}
