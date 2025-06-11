package com.PixelGround.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PixelGround.back.config.JwtUtil;
import com.PixelGround.back.dto.HiloRequestDTO;
import com.PixelGround.back.dto.MensajeRequestDTO;
import com.PixelGround.back.dto.TemaRequestDTO;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.service.ForoService;
import com.PixelGround.back.vo.HiloForoVO;
import com.PixelGround.back.vo.MensajeForoVO;
import com.PixelGround.back.vo.TemaForoVO;


@RestController
@RequestMapping("/api/foro")
public class ForoController {

    @Autowired
    private ForoService foroService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/temas")
    public ResponseEntity<List<TemaForoVO>> obtenerTemas() {
        return ResponseEntity.ok(foroService.obtenerTemas());
    }

    @GetMapping("/tema/{temaId}/hilos")
    public ResponseEntity<List<HiloForoVO>> obtenerHilosPorTema(@PathVariable Long temaId) {
        return ResponseEntity.ok(foroService.obtenerHilosPorTema(temaId));
    }
    
    @PostMapping("/tema")
    public ResponseEntity<TemaForoVO> crearTema(
            @RequestBody TemaRequestDTO dto,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(foroService.crearTema(dto.getNombre(), dto.getDescripcion(), token));
    }


    @PostMapping("/hilo")
    public ResponseEntity<HiloForoVO> crearHilo(
            @RequestBody HiloRequestDTO dto,
            @RequestHeader("Authorization") String token) {

        Long usuarioId = obtenerIdUsuarioDesdeToken(token);
        return ResponseEntity.ok(foroService.crearHilo(dto.getTemaId(), dto.getTitulo(), dto.getContenido(), usuarioId));
    }

    @PostMapping("/hilo/{hiloId}/responder")
    public ResponseEntity<MensajeForoVO> responderHilo(
            @PathVariable Long hiloId,
            @RequestBody MensajeRequestDTO dto,
            @RequestHeader("Authorization") String token) {

        Long usuarioId = obtenerIdUsuarioDesdeToken(token);
        return ResponseEntity.ok(foroService.responderAHilo(hiloId, dto.getContenido(), usuarioId));
    }

    @DeleteMapping("/hilo/{hiloId}")
    public ResponseEntity<Void> eliminarHilo(
            @PathVariable Long hiloId,
            @RequestHeader("Authorization") String token) {

        foroService.eliminarHilo(hiloId, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/hilo/{hiloId}/cambiar-tema/{nuevoTemaId}")
    public ResponseEntity<HiloForoVO> cambiarTema(
            @PathVariable Long hiloId,
            @PathVariable Long nuevoTemaId,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(foroService.cambiarTemaDelHilo(hiloId, nuevoTemaId, token));
    }

    private Long obtenerIdUsuarioDesdeToken(String token) {
        String email = jwtUtil.getSubject(token.replace("Bearer ", ""));
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"))
                .getId();
    }
    
    @DeleteMapping("/tema/{temaId}")
    public ResponseEntity<Void> eliminarTema(
            @PathVariable Long temaId,
            @RequestHeader("Authorization") String token) {

        foroService.eliminarTema(temaId, token);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/hilo/{hiloId}/respuestas")
    public ResponseEntity<List<MensajeForoVO>> obtenerRespuestas(@PathVariable Long hiloId) {
        return ResponseEntity.ok(foroService.obtenerRespuestasDeHilo(hiloId));
    }

    
    @GetMapping("/hilo/{hiloId}")
    public ResponseEntity<HiloForoVO> obtenerHiloPorId(@PathVariable Long hiloId) {
        return ResponseEntity.ok(foroService.obtenerHiloPorId(hiloId));
    }


}
