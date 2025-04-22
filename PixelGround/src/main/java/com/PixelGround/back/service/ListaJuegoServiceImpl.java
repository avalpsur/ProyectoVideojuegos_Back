package com.PixelGround.back.service;

import com.PixelGround.back.converter.ListaJuegoConverter;
import com.PixelGround.back.model.JuegoModel;
import com.PixelGround.back.model.ListaJuegoModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.ListaJuegoRepository;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.vo.JuegoVO;
import com.PixelGround.back.vo.ListaJuegoVO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListaJuegoServiceImpl implements ListaJuegoService {

    @Autowired
    private ListaJuegoRepository listaJuegoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JuegoService juegoService;

    @Override
    public ListaJuegoVO crearLista(ListaJuegoVO vo) {
        UsuarioModel usuario = usuarioRepository.findById(vo.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        ListaJuegoModel model = ListaJuegoConverter.toModel(vo, usuario);
        return ListaJuegoConverter.toVO(listaJuegoRepository.save(model));
    }

    @Override
    @Transactional
    public void añadirJuegoALista(Long listaId, JuegoVO juegoVO) {
        ListaJuegoModel lista = listaJuegoRepository.findById(listaId)
                .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        JuegoModel juego = juegoService.obtenerOcrearJuego(juegoVO);

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
    public void eliminarLista(Long listaId) {
        listaJuegoRepository.deleteById(listaId);
    }

}
