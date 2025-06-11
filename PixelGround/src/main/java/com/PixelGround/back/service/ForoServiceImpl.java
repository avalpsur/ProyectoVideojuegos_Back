package com.PixelGround.back.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PixelGround.back.config.JwtUtil;
import com.PixelGround.back.converter.HiloForoConverter;
import com.PixelGround.back.converter.MensajeForoConverter;
import com.PixelGround.back.converter.TemaForoConverter;
import com.PixelGround.back.model.HiloForoModel;
import com.PixelGround.back.model.MensajeForoModel;
import com.PixelGround.back.model.TemaForoModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.HiloForoRepository;
import com.PixelGround.back.repository.MensajeForoRepository;
import com.PixelGround.back.repository.TemaForoRepository;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.vo.HiloForoVO;
import com.PixelGround.back.vo.MensajeForoVO;
import com.PixelGround.back.vo.TemaForoVO;

@Service
public class ForoServiceImpl implements ForoService {

    @Autowired
    private TemaForoRepository temaRepo;

    @Autowired
    private HiloForoRepository hiloRepo;

    @Autowired
    private MensajeForoRepository mensajeRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TemaForoConverter temaConverter;

    @Autowired
    private HiloForoConverter hiloConverter;

    @Autowired
    private MensajeForoConverter mensajeConverter;

    @Override
    public List<TemaForoVO> obtenerTemas() {
        return temaRepo.findAll().stream()
            .map(temaConverter::toVO)
            .collect(Collectors.toList());
    }

    @Override
    public List<HiloForoVO> obtenerHilosPorTema(Long temaId) {
        return hiloRepo.findByTemaId(temaId).stream()
            .map(hiloConverter::toVO)
            .collect(Collectors.toList());
    }

    @Override
    public HiloForoVO crearHilo(Long temaId, String titulo, String contenido, Long usuarioId) {
        TemaForoModel tema = temaRepo.findById(temaId)
            .orElseThrow(() -> new RuntimeException("Tema no encontrado"));
        UsuarioModel usuario = usuarioRepo.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        HiloForoModel hilo = new HiloForoModel();
        hilo.setTitulo(titulo);
        hilo.setContenido(contenido);
        hilo.setFechaCreacion(LocalDateTime.now());
        hilo.setCreador(usuario);
        hilo.setTema(tema);

        return hiloConverter.toVO(hiloRepo.save(hilo));
    }

    @Override
    public MensajeForoVO responderAHilo(Long hiloId, String contenido, Long usuarioId) {
        HiloForoModel hilo = hiloRepo.findById(hiloId)
            .orElseThrow(() -> new RuntimeException("Hilo no encontrado"));
        UsuarioModel autor = usuarioRepo.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        MensajeForoModel mensaje = new MensajeForoModel();
        mensaje.setContenido(contenido);
        mensaje.setFecha(LocalDateTime.now());
        mensaje.setAutor(autor);
        mensaje.setHilo(hilo);

        return mensajeConverter.toVO(mensajeRepo.save(mensaje));
    }

    @Override
    public void eliminarHilo(Long hiloId, String token) {
        validarAdmin(token);
        if (!hiloRepo.existsById(hiloId)) {
            throw new RuntimeException("El hilo no existe.");
        }
        hiloRepo.deleteById(hiloId);
    }

    @Override
    public HiloForoVO cambiarTemaDelHilo(Long hiloId, Long nuevoTemaId, String token) {
        validarAdmin(token);

        HiloForoModel hilo = hiloRepo.findById(hiloId)
            .orElseThrow(() -> new RuntimeException("Hilo no encontrado"));
        TemaForoModel nuevoTema = temaRepo.findById(nuevoTemaId)
            .orElseThrow(() -> new RuntimeException("Tema destino no encontrado"));

        hilo.setTema(nuevoTema);
        return hiloConverter.toVO(hiloRepo.save(hilo));
    }

    private void validarAdmin(String token) {
        if (!jwtUtil.tieneRol(token, "ADMIN")) {
            throw new RuntimeException("Acceso denegado: se requiere rol ADMIN");
        }
    }
    
    @Override
    public TemaForoVO crearTema(String nombre, String descripcion, String token) {
        if (!jwtUtil.tieneRol(token.replace("Bearer ", ""), "ADMIN")) {
            throw new RuntimeException("Solo los administradores pueden crear temas.");
        }

        TemaForoModel tema = new TemaForoModel();
        tema.setNombre(nombre);
        tema.setDescripcion(descripcion);

        return temaConverter.toVO(temaRepo.save(tema));
    }

    @Override
    public void eliminarTema(Long temaId, String token) {
        if (!jwtUtil.tieneRol(token.replace("Bearer ", ""), "ADMIN")) {
            throw new RuntimeException("Solo los administradores pueden eliminar temas.");
        }

        if (!temaRepo.existsById(temaId)) {
            throw new RuntimeException("El tema no existe.");
        }

        temaRepo.deleteById(temaId);
    }
    
    @Override
    public List<MensajeForoVO> obtenerRespuestasDeHilo(Long hiloId) {
        List<MensajeForoModel> mensajes = mensajeRepo.findByHiloId(hiloId);
        return mensajes.stream()
            .map(mensajeConverter::toVO)
            .collect(Collectors.toList());
    }
    
    @Override
    public HiloForoVO obtenerHiloPorId(Long hiloId) {
        HiloForoModel hilo = hiloRepo.findById(hiloId)
            .orElseThrow(() -> new RuntimeException("Hilo no encontrado"));
        return hiloConverter.toVO(hilo);
    }



}
