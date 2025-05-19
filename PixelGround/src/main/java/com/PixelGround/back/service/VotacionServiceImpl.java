package com.PixelGround.back.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PixelGround.back.converter.VotacionConverter;
import com.PixelGround.back.model.JuegoModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.model.VotacionModel;
import com.PixelGround.back.repository.JuegoRepository;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.repository.VotacionRepository;
import com.PixelGround.back.vo.VotacionVO;

@Service
public class VotacionServiceImpl implements VotacionService {

    @Autowired
    private VotacionRepository votacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    @Autowired
    private ActividadService actividadService;

    @Override
    public VotacionVO votar(VotacionVO vo) {
        UsuarioModel usuario = usuarioRepository.findById(vo.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        JuegoModel juego = juegoRepository.findByApiId(vo.getJuegoApiId())
        	    .orElseGet(() -> {
        	        JuegoModel nuevo = new JuegoModel();
        	        nuevo.setApiId(vo.getJuegoApiId());
        	        nuevo.setNombre(vo.getNombreJuego());
        	        nuevo.setImagenUrl(vo.getImagenUrlJuego());
        	        return juegoRepository.save(nuevo);
        	    });


        VotacionModel votacion = votacionRepository.findByUsuarioAndJuego(usuario, juego)
                .orElse(new VotacionModel());

        votacion.setUsuario(usuario);
        votacion.setJuego(juego);
        votacion.setPuntuacion(vo.getPuntuacion());
        votacion.setFecha(LocalDateTime.now());

        votacion = votacionRepository.save(votacion);

        // Registrar actividad
        String contenidoExtra = "{\"puntuacion\": " + vo.getPuntuacion() + "}";
        actividadService.registrarActividad(usuario, juego, "voto", contenidoExtra);

        return VotacionConverter.toVO(votacion);
    }
    
    @Override
    public List<VotacionVO> obtenerVotacionesPorUsuario(Long usuarioId) {
        List<VotacionModel> votos = votacionRepository.findByUsuarioId(usuarioId);
        return votos.stream()
                    .map(VotacionConverter::toVO)
                    .toList();
    }


}

