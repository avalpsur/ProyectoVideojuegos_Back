package com.PixelGround.back.controller;

import com.PixelGround.back.dto.SolicitudAmistadDTO;
import com.PixelGround.back.service.AmistadService;
import com.PixelGround.back.vo.UsuarioVO;
import com.PixelGround.back.vo.AmistadVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amistades")
public class AmistadController {

    @Autowired
    private AmistadService amistadService;

    @PostMapping("/solicitar")
    public ResponseEntity<Void> enviarSolicitud(@RequestBody SolicitudAmistadDTO dto) {
        amistadService.enviarSolicitud(dto.getSolicitanteId(), dto.getReceptorId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/aceptar")
    public ResponseEntity<Void> aceptarSolicitud(@RequestBody SolicitudAmistadDTO dto) {
        amistadService.aceptarSolicitud(dto.getReceptorId(), dto.getSolicitanteId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/rechazar")
    public ResponseEntity<Void> rechazarSolicitud(@RequestBody SolicitudAmistadDTO dto) {
        amistadService.rechazarSolicitud(dto.getReceptorId(), dto.getSolicitanteId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/amigos/{id}")
    public ResponseEntity<List<UsuarioVO>> obtenerAmigos(@PathVariable Long id) {
        return ResponseEntity.ok(amistadService.obtenerAmigos(id));
    }

    @GetMapping("/solicitudes/{id}")
    public ResponseEntity<List<AmistadVO>> obtenerSolicitudes(@PathVariable Long id) {
        return ResponseEntity.ok(amistadService.obtenerSolicitudesRecibidas(id));
    }
}

