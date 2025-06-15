package com.PixelGround.back.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PixelGround.back.converter.ListaJuegoConverter;
import com.PixelGround.back.model.JuegoModel;
import com.PixelGround.back.model.ListaJuegoModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.JuegoRepository;
import com.PixelGround.back.repository.ListaJuegoRepository;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.vo.JuegoVO;
import com.PixelGround.back.vo.ListaJuegoVO;

import jakarta.transaction.Transactional;

@Service
public class ListaJuegoServiceImpl implements ListaJuegoService {

    @Autowired
    private ListaJuegoRepository listaJuegoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private JuegoRepository juegoRepository;

    @Override
    public ListaJuegoVO crearLista(ListaJuegoVO vo) {
        UsuarioModel usuario = usuarioRepository.findById(vo.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        ListaJuegoModel model = ListaJuegoConverter.toModel(vo, usuario, juegoRepository);
        return ListaJuegoConverter.toVO(listaJuegoRepository.save(model));
    }


    @Override
    @Transactional
    public void añadirJuegoALista(Long listaId, JuegoVO juegoVO, Long usuarioId) {
        ListaJuegoModel lista = listaJuegoRepository.findById(listaId)
            .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        boolean autorizado = lista.getUsuario().getId().equals(usuarioId) ||
                             lista.getMiembros().stream().anyMatch(u -> u.getId().equals(usuarioId));

        if (!autorizado) {
            throw new RuntimeException("No tienes permiso para modificar esta lista.");
        }

        JuegoModel juego = juegoRepository.findByApiId(juegoVO.getApiId())
            .orElseGet(() -> {
                JuegoModel nuevoJuego = new JuegoModel();
                nuevoJuego.setApiId(juegoVO.getApiId());
                nuevoJuego.setNombre(juegoVO.getNombre());
                nuevoJuego.setImagenUrl(juegoVO.getImagenUrl());
                return juegoRepository.save(nuevoJuego);
            });

        lista.getJuegos().add(juego);
        listaJuegoRepository.save(lista);
    }


    @Override
    public List<ListaJuegoVO> obtenerListasDeUsuario(Long usuarioId) {
        UsuarioModel usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return listaJuegoRepository.findByUsuario(usuario).stream()
                .map(ListaJuegoConverter::toVO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarLista(Long listaId, Long usuarioId) {
        ListaJuegoModel lista = listaJuegoRepository.findById(listaId)
                .orElseThrow(() -> new RuntimeException("La lista con ID " + listaId + " no existe"));

        if (lista.getUsuario() == null || !lista.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("No tienes permisos para eliminar esta lista");
        }

        listaJuegoRepository.delete(lista);
    }
    
    @Override
    public List<ListaJuegoVO> obtenerListasPublicas() {
        return listaJuegoRepository.findByPublicaTrue()
            .stream()
            .map(ListaJuegoConverter::toVO)
            .collect(Collectors.toList());
    }
    
    @Override
    public void agregarUsuario(Long listaId, UsuarioModel usuario) {
        ListaJuegoModel lista = listaJuegoRepository.findById(listaId)
            .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        lista.getMiembros().add(usuario);
        listaJuegoRepository.save(lista);
    }
    
    @Override
    public ListaJuegoVO obtenerListaPorId(Long id, UsuarioModel usuario) {
        ListaJuegoModel lista = listaJuegoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        boolean puedeVer = lista.isPublica()
            || lista.getUsuario().getId().equals(usuario.getId())
            || lista.getMiembros().contains(usuario);

        if (!puedeVer) {
            throw new RuntimeException("No tienes permiso para ver esta lista");
        }

        return ListaJuegoConverter.toVO(lista);
    }

    @Override
    public void cambiarVisibilidad(Long listaId, Long usuarioId) {
        ListaJuegoModel lista = listaJuegoRepository.findById(listaId)
            .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        if (!lista.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("Solo el creador puede cambiar la visibilidad");
        }

        lista.setPublica(!lista.isPublica());
        listaJuegoRepository.save(lista);
    }

    @Override
    public void eliminarJuegoDeLista(Long listaId, String apiId, Long usuarioId) {
        ListaJuegoModel lista = listaJuegoRepository.findById(listaId)
            .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        boolean autorizado = lista.getUsuario().getId().equals(usuarioId)
            || lista.getMiembros().stream().anyMatch(u -> u.getId().equals(usuarioId));

        if (!autorizado) {
            throw new RuntimeException("No tienes permiso para modificar esta lista");
        }

        JuegoModel juego = juegoRepository.findByApiId(apiId)
        	    .orElseThrow(() -> new RuntimeException("Juego no encontrado"));

        	lista.getJuegos().remove(juego);
        listaJuegoRepository.save(lista);
    }



}
