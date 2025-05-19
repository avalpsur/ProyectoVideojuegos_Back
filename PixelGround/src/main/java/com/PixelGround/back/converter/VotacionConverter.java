package com.PixelGround.back.converter;

import com.PixelGround.back.model.VotacionModel;
import com.PixelGround.back.vo.VotacionVO;

public class VotacionConverter {

	public static VotacionVO toVO(VotacionModel model) {
	    VotacionVO vo = new VotacionVO();
	    vo.setId(model.getId());
	    vo.setUsuarioId(model.getUsuario().getId());
	    vo.setJuegoApiId(model.getJuego().getApiId());
	    vo.setPuntuacion(model.getPuntuacion());
	    vo.setFecha(model.getFecha());
	    vo.setNombreJuego(model.getJuego().getNombre());
	    vo.setImagenUrlJuego(model.getJuego().getImagenUrl());
	    return vo;
	}

}
