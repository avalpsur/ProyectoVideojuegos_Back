package com.PixelGround.back.converter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.PixelGround.back.model.JuegoModel;
import com.PixelGround.back.model.ReviewModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.vo.ReviewVO;

@Component
public class ReviewConverter {
    public ReviewVO toVO(ReviewModel model) {
        ReviewVO vo = new ReviewVO();
        vo.setId(model.getId());
        vo.setIdUsuario(model.getUsuario().getId());
        vo.setNombreUsuario(model.getUsuario().getNombreUsuario());
        vo.setIdJuego(model.getJuego().getId());
        vo.setNombreJuego(model.getJuego().getNombre());
        vo.setContenido(model.getContenido());
        vo.setFecha(model.getFecha());
        vo.setImagenUrlJuego(model.getJuego().getImagenUrl()); 
        return vo;
    }

    public ReviewModel toModel(ReviewVO vo, UsuarioModel usuario, JuegoModel juego) {
        ReviewModel model = new ReviewModel();
        model.setId(vo.getId());
        model.setUsuario(usuario);
        model.setJuego(juego);
        model.setContenido(vo.getContenido());
        model.setFecha(LocalDateTime.now());
        return model;
    }
}

