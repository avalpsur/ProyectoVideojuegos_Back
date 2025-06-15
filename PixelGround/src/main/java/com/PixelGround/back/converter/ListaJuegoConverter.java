package com.PixelGround.back.converter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.PixelGround.back.model.JuegoModel;
import com.PixelGround.back.model.ListaJuegoModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.JuegoRepository;
import com.PixelGround.back.vo.ListaJuegoVO;

public class ListaJuegoConverter {

    public static ListaJuegoVO toVO(ListaJuegoModel model) {
        ListaJuegoVO vo = new ListaJuegoVO();
        vo.setId(model.getId());
        vo.setNombre(model.getNombre());
        vo.setDescripcion(model.getDescripcion());
        vo.setUsuarioId(model.getUsuario().getId());
        vo.setPublica(model.isPublica());

        vo.setJuegosId(
        	    model.getJuegos().stream()
        	        .map(JuegoModel::getApiId)
        	        .collect(Collectors.toSet())
        	);


        vo.setMiembrosId(
            model.getMiembros().stream()
                .map(UsuarioModel::getId)
                .collect(Collectors.toSet())
        );

        return vo;
    }

    public static ListaJuegoModel toModel(ListaJuegoVO vo, UsuarioModel usuario, JuegoRepository juegoRepository) {
        ListaJuegoModel model = new ListaJuegoModel();
        model.setId(vo.getId());
        model.setNombre(vo.getNombre());
        model.setDescripcion(vo.getDescripcion());
        model.setPublica(vo.isPublica());
        model.setUsuario(usuario);

        Set<JuegoModel> juegos = vo.getJuegosId().stream()
            .map(apiId -> juegoRepository.findByApiId(apiId)
                .orElseThrow(() -> new RuntimeException("Juego con apiId " + apiId + " no encontrado")))
            .collect(Collectors.toSet());

        model.setJuegos(juegos);
        model.setMiembros(new HashSet<>());

        return model;
    }


}

