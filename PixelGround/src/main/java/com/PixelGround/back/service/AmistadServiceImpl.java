package com.PixelGround.back.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PixelGround.back.converter.UsuarioConverter;
import com.PixelGround.back.model.AmistadModel;
import com.PixelGround.back.model.AmistadModel.EstadoAmistad;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.AmistadRepository;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.vo.AmistadVO;
import com.PixelGround.back.vo.UsuarioVO;

@Service
public class AmistadServiceImpl implements AmistadService {

    @Autowired
    private AmistadRepository amistadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void enviarSolicitud(Long solicitanteId, Long receptorId) {
        UsuarioModel solicitante = usuarioRepository.findById(solicitanteId)
                .orElseThrow(() -> new IllegalArgumentException("Solicitante no encontrado"));
        UsuarioModel receptor = usuarioRepository.findById(receptorId)
                .orElseThrow(() -> new IllegalArgumentException("Receptor no encontrado"));

        if (amistadRepository.findBySolicitanteAndReceptor(solicitante, receptor).isEmpty()) {
            amistadRepository.save(new AmistadModel(solicitante, receptor, EstadoAmistad.PENDIENTE));
        }
    }

    @Override
    public void aceptarSolicitud(Long receptorId, Long solicitanteId) {
        UsuarioModel receptor = usuarioRepository.findById(receptorId)
                .orElseThrow(() -> new IllegalArgumentException("Receptor no encontrado"));
        UsuarioModel solicitante = usuarioRepository.findById(solicitanteId)
                .orElseThrow(() -> new IllegalArgumentException("Solicitante no encontrado"));

        AmistadModel solicitud = amistadRepository.findBySolicitanteAndReceptor(solicitante, receptor)
                .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada"));

        solicitud.setEstado(EstadoAmistad.ACEPTADA);
        amistadRepository.save(solicitud);
    }

    @Override
    public void rechazarSolicitud(Long receptorId, Long solicitanteId) {
        UsuarioModel receptor = usuarioRepository.findById(receptorId)
                .orElseThrow(() -> new IllegalArgumentException("Receptor no encontrado"));
        UsuarioModel solicitante = usuarioRepository.findById(solicitanteId)
                .orElseThrow(() -> new IllegalArgumentException("Solicitante no encontrado"));

        amistadRepository.findBySolicitanteAndReceptor(solicitante, receptor)
                .ifPresent(amistadRepository::delete);
    }

    @Override
    public List<UsuarioVO> obtenerAmigos(Long idUsuario) {
        List<AmistadModel> amistades = amistadRepository.findAmistadesAceptadasPorUsuario(idUsuario);

        Set<Long> idsUnicos = new HashSet<>();
        List<UsuarioVO> amigos = new ArrayList<>();

        for (AmistadModel a : amistades) {
            UsuarioModel otro = a.getSolicitante().getId().equals(idUsuario)
                    ? a.getReceptor()
                    : a.getSolicitante();

            if (idsUnicos.add(otro.getId())) { 
                amigos.add(UsuarioConverter.toVO(otro));
            }
        }

        return amigos;
    }

    
    @Override
    public List<AmistadVO> obtenerSolicitudesRecibidas(Long idUsuario) {
        UsuarioModel receptor = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        List<AmistadModel> solicitudes = amistadRepository.findByReceptorAndEstado(receptor, EstadoAmistad.PENDIENTE);

        return solicitudes.stream()
                .map(a -> {
                    UsuarioModel solicitante = a.getSolicitante();
                    return new AmistadVO(
                            a.getId(),
                            solicitante.getId(),
                            receptor.getId(),
                            solicitante.getNombreUsuario(),
                            a.getEstado().name(),
                            a.getFechaSolicitud()
                    );
                })
                .toList();
    }

}

