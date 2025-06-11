package com.PixelGround.back.converter;

import org.springframework.stereotype.Component;

import com.PixelGround.back.model.TemaForoModel;
import com.PixelGround.back.vo.TemaForoVO;

@Component
public class TemaForoConverter {

    public TemaForoVO toVO(TemaForoModel model) {
        TemaForoVO vo = new TemaForoVO();
        vo.setId(model.getId());
        vo.setNombre(model.getNombre());
        vo.setDescripcion(model.getDescripcion());
        return vo;
    }

    public TemaForoModel toModel(TemaForoVO vo) {
        TemaForoModel model = new TemaForoModel();
        model.setId(vo.getId());
        model.setNombre(vo.getNombre());
        model.setDescripcion(vo.getDescripcion());
        return model;
    }
}
