package com.PixelGround.back.converter;

import org.springframework.stereotype.Component;

import com.PixelGround.back.model.HiloForoModel;
import com.PixelGround.back.model.MensajeForoModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.vo.MensajeForoVO;

@Component
public class MensajeForoConverter {

    public MensajeForoVO toVO(MensajeForoModel model) {
        MensajeForoVO vo = new MensajeForoVO();
        vo.setId(model.getId());
        vo.setContenido(model.getContenido());
        vo.setFecha(model.getFecha());
        vo.setAutorId(model.getAutor().getId());
        vo.setNombreAutor(model.getAutor().getNombreUsuario());
        vo.setHiloId(model.getHilo().getId());
        return vo;
    }

    public MensajeForoModel toModel(MensajeForoVO vo, UsuarioModel autor, HiloForoModel hilo) {
        MensajeForoModel model = new MensajeForoModel();
        model.setId(vo.getId());
        model.setContenido(vo.getContenido());
        model.setFecha(vo.getFecha());
        model.setAutor(autor);
        model.setHilo(hilo);
        return model;
    }
}
