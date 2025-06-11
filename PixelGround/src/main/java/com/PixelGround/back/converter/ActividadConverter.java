package com.PixelGround.back.converter;

import java.util.stream.Collectors;

import com.PixelGround.back.model.ActividadModel;
import com.PixelGround.back.model.JuegoModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.vo.ActividadVO;

public class ActividadConverter {

	public static ActividadVO toVO(ActividadModel model) {
	    ActividadVO vo = new ActividadVO();

	    vo.setId(model.getId());
	    vo.setUsuarioId(model.getUsuario().getId());
	    vo.setNombreUsuario(model.getUsuario().getNombreUsuario());

	    vo.setJuegoApiId(model.getJuego().getApiId());
	    vo.setNombreJuego(model.getJuego().getNombre());
	    vo.setImagenUrlJuego(model.getJuego().getImagenUrl());

	    vo.setTipo(model.getTipo());
	    vo.setContenidoExtra(model.getContenidoExtra());
	    vo.setFecha(model.getFecha());

	    return vo;
	}



}

