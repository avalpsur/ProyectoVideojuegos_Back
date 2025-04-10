package com.PixelGround.back.converter;

import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.vo.UsuarioVO;

import java.time.LocalDateTime;

public class UsuarioConverter {

    public static UsuarioVO toVO(UsuarioModel model) {
        if (model == null) return null;

        return new UsuarioVO(
            model.getId(),
            model.getNombreUsuario(),
            model.getEmail(),
            model.getRol()
        );
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

        return model;
    }
}
