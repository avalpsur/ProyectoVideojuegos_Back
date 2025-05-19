package com.PixelGround.back.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PixelGround.back.converter.ActividadConverter;
import com.PixelGround.back.model.ActividadModel;
import com.PixelGround.back.model.AmistadModel;
import com.PixelGround.back.model.JuegoModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.ActividadRepository;
import com.PixelGround.back.repository.AmistadRepository;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.vo.ActividadVO;

@Service
public class ActividadServiceImpl implements ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AmistadRepository amistadRepository;

    @Override
    public void registrarActividad(UsuarioModel usuario, JuegoModel juego, String tipo, String contenidoExtra) {
        ActividadModel actividad = new ActividadModel();
        actividad.setUsuario(usuario);
        actividad.setJuego(juego);
        actividad.setTipo(tipo);
        actividad.setContenidoExtra(contenidoExtra);
        actividad.setFecha(LocalDateTime.now());
        actividadRepository.save(actividad);
    }

    @Override
    public List<ActividadVO> obtenerFeedUsuarioYAmigos(Long usuarioId) {
        UsuarioModel usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<AmistadModel> amistades = amistadRepository.findAmistadesAceptadasPorUsuario(usuario.getId());

        List<UsuarioModel> amigos = new ArrayList<>(
        	    amistades.stream()
        	        .map(a -> a.getSolicitante().getId().equals(usuario.getId()) ? a.getReceptor() : a.getSolicitante())
        	        .toList()
        	);


        amigos.add(usuario);

        List<ActividadModel> actividades = actividadRepository.findByUsuarioInOrderByFechaDesc(amigos);
        return actividades.stream()
            .map(ActividadConverter::toVO)
            .toList();
    }

}

