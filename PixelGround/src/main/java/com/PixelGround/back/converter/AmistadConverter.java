package com.PixelGround.back.converter;

import com.PixelGround.back.model.AmistadModel;
import com.PixelGround.back.vo.AmistadVO;

public class AmistadConverter {

    public static AmistadVO toVO(AmistadModel model) {
        return new AmistadVO(
            model.getId(),
            model.getSolicitante().getId(),
            model.getReceptor().getId(),
            model.getReceptor().getNombreUsuario(),
            model.getEstado().name(),
            model.getFechaSolicitud()
        );
    }
}
