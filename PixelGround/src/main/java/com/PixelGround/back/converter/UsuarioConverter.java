package com.PixelGround.back.converter;

import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.vo.UsuarioVO;

import java.time.LocalDateTime;

public class UsuarioConverter {

	public static UsuarioVO toVO(UsuarioModel model) {
	    if (model == null) return null;

	    UsuarioVO vo = new UsuarioVO(
	        model.getId(),
	        model.getNombreUsuario(),
	        model.getEmail(),
	        model.getRol()
	        
	    );
	    vo.setSteamId(model.getSteamId());
	    vo.setPerfilPublico(model.isPerfilPublico());
	    return vo;
	}


    public static UsuarioModel toModel(UsuarioVO vo, String hashedPassword) {
        if (vo == null) return null;

        UsuarioModel model = new UsuarioModel();
        model.setId(vo.getId());
        model.setNombreUsuario(vo.getNombreUsuario());
        model.setEmail(vo.getEmail());
        model.setPassword(hashedPassword);
        model.setRol(vo.getRol());
        model.setFechaAlta(LocalDateTime.now());
        model.setSteamId(vo.getSteamId()); 
        model.setPerfilPublico(
        	    vo.getId() == null ? true : vo.isPerfilPublico()
        	);
        return model;
    }

}
