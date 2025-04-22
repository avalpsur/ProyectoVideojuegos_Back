package com.PixelGround.back.converter;


import com.PixelGround.back.model.ListaJuegoModel;
import com.PixelGround.back.vo.ListaJuegoVO;
import com.PixelGround.back.model.UsuarioModel;

import java.util.stream.Collectors;

public class ListaJuegoConverter {

    public static ListaJuegoVO toVO(ListaJuegoModel model) {
        if (model == null) return null;

        return new ListaJuegoVO(
            model.getId(),
            model.getNombre(),
            model.getDescripcion(),
            model.getUsuario().getId(),
            model.getJuegos().stream()
                .map(JuegoConverter::toVO)
                .collect(Collectors.toList())
        );
    }

    public static ListaJuegoModel toModel(ListaJuegoVO vo, UsuarioModel usuario) {
        if (vo == null) return null;

        ListaJuegoModel model = new ListaJuegoModel();
        model.setId(vo.getId());
        model.setNombre(vo.getNombre());
        model.setDescripcion(vo.getDescripcion());
        model.setUsuario(usuario);
        return model;
    }
}