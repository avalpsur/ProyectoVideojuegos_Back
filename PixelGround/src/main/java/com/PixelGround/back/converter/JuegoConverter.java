package com.PixelGround.back.converter;


import com.PixelGround.back.model.JuegoModel;
import com.PixelGround.back.vo.JuegoVO;

public class JuegoConverter {

    public static JuegoVO toVO(JuegoModel model) {
        if (model == null) return null;

        return new JuegoVO(
            model.getId(),
            model.getApiId(),
            model.getNombre(),
            model.getImagenUrl()
        );
    }

    public static JuegoModel toModel(JuegoVO vo) {
        if (vo == null) return null;

        JuegoModel model = new JuegoModel();
        model.setId(vo.getId());
        model.setApiId(vo.getApiId());
        model.setNombre(vo.getNombre());
        model.setImagenUrl(vo.getImagenUrl());

        return model;
    }
}
