package com.PixelGround.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PixelGround.back.config.JwtUtil;
import com.PixelGround.back.model.JuegoModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.JuegoRepository;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.service.ActividadService;
import com.PixelGround.back.vo.ActividadVO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/actividad")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/registrar")
    public ResponseEntity<Void> registrarActividad(@RequestBody ActividadVO actividadVO, HttpServletRequest request) {
        Long usuarioId = getUsuarioIdDesdeToken(request);

        UsuarioModel usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        JuegoModel juego = juegoRepository.findByApiId(actividadVO.getJuegoApiId())
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));

        actividadService.registrarActividad(
                usuario,
                juego,
                actividadVO.getTipo(),
                actividadVO.getContenidoExtra()
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/feed")
    public ResponseEntity<List<ActividadVO>> obtenerFeed(HttpServletRequest request) {
        Long usuarioId = getUsuarioIdDesdeToken(request);
        return ResponseEntity.ok(actividadService.obtenerFeedUsuarioYAmigos(usuarioId));
    }

    private Long getUsuarioIdDesdeToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
        }

        String email = jwtUtil.getSubject(token);
        UsuarioModel usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario.getId();
    }
    

}


