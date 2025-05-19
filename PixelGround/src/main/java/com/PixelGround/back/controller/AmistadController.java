package com.PixelGround.back.controller;

import com.PixelGround.back.config.JwtUtil;
import com.PixelGround.back.dto.SolicitudAmistadDTO;
import com.PixelGround.back.service.AmistadService;
import com.PixelGround.back.service.UsuarioService;
import com.PixelGround.back.vo.UsuarioVO;
import com.PixelGround.back.vo.AmistadVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/amistades")
public class AmistadController {

    @Autowired
    private AmistadService amistadService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    private Long getUsuarioIdDesdeToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.getSubject(token);
        return usuarioService.buscarUsuario(email).getId();
    }

    @PostMapping("/solicitar")
    public ResponseEntity<Void> enviarSolicitud(HttpServletRequest request, @RequestBody SolicitudAmistadDTO dto) {
        Long solicitanteId = getUsuarioIdDesdeToken(request);
        amistadService.enviarSolicitud(solicitanteId, dto.getReceptorId());
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

