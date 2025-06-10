package com.PixelGround.back.converter;


import java.util.List;
import java.util.stream.Collectors;

import com.PixelGround.back.model.ListaJuegoModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.vo.ListaJuegoVO;

public class ListaJuegoConverter {

	public static ListaJuegoVO toVO(ListaJuegoModel model) {
	    if (model == null) return null;

	    return new ListaJuegoVO(
	        model.getId(),
	        model.getNombre(),
	        model.getDescripcion(),
	        model.getUsuario().getId(),
	        model.getJuegos() != null
	            ? model.getJuegos().stream().map(JuegoConverter::toVO).collect(Collectors.toList())
	            : List.of()
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