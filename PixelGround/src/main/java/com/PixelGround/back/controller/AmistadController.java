package com.PixelGround.back.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PixelGround.back.config.JwtUtil;
import com.PixelGround.back.dto.SolicitudAmistadDTO;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.service.AmistadService;
import com.PixelGround.back.service.UsuarioService;
import com.PixelGround.back.vo.AmistadVO;
import com.PixelGround.back.vo.UsuarioVO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/amistades")
public class AmistadController {

    @Autowired
    private AmistadService amistadService;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private Long getUsuarioIdDesdeToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.getSubject(token);
        return usuarioService.buscarUsuario(email).getId();
    }

    @PostMapping("/solicitar")
    public ResponseEntity<Void> solicitarAmistad(@RequestBody Map<String, Object> body, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String email = jwtUtil.getSubject(token);
        UsuarioModel solicitante = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Solicitante no encontrado"));

        Object raw = body.get("receptor");
        if (raw == null) return ResponseEntity.badRequest().build();
        Long idReceptor = Long.valueOf(raw.toString());

        amistadService.enviarSolicitud(solicitante.getId(), idReceptor);
        return ResponseEntity.ok().build();
    }



    @PostMapping("/aceptar")
    public ResponseEntity<Void> aceptarSolicitud(HttpServletRequest request, @RequestBody SolicitudAmistadDTO dto) {
        Long receptorId = getUsuarioIdDesdeToken(request);
        amistadService.aceptarSolicitud(receptorId, dto.getSolicitanteId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/rechazar")
    public ResponseEntity<Void> rechazarSolicitud(HttpServletRequest request, @RequestBody SolicitudAmistadDTO dto) {
        Long receptorId = getUsuarioIdDesdeToken(request);
        amistadService.rechazarSolicitud(receptorId, dto.getSolicitanteId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/amigos")
    public ResponseEntity<List<UsuarioVO>> obtenerAmigos(HttpServletRequest request) {
        Long usuarioId = getUsuarioIdDesdeToken(request);
        return ResponseEntity.ok(amistadService.obtenerAmigos(usuarioId));
    }

    @GetMapping("/solicitudes")
    public ResponseEntity<List<AmistadVO>> obtenerSolicitudes(HttpServletRequest request) {
        Long usuarioId = getUsuarioIdDesdeToken(request);
        return ResponseEntity.ok(amistadService.obtenerSolicitudesRecibidas(usuarioId));
    }
}

