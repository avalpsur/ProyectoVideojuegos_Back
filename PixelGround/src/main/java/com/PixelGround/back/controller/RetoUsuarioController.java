package com.PixelGround.back.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PixelGround.back.dto.CompletarRetoRequest;
import com.PixelGround.back.service.RetoUsuarioService;
import com.PixelGround.back.vo.RetoUsuarioVO;

@RestController
@RequestMapping("/api/retos/usuarios")
public class RetoUsuarioController {

    private final RetoUsuarioService service;

    public RetoUsuarioController(RetoUsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RetoUsuarioVO> unirse(@RequestBody RetoUsuarioVO vo) {
        return ResponseEntity.ok(service.unirseAReto(vo));
    }

    @PutMapping("/{id}/completar")
    public ResponseEntity<RetoUsuarioVO> completar(
            @PathVariable Long id,
            @RequestBody CompletarRetoRequest request
    ) {
        RetoUsuarioVO resultado = service.marcarComoCompletado(id, request.getComentario(), request.getImagenUrl());
        return resultado != null ? ResponseEntity.ok(resultado) : ResponseEntity.notFound().build();
    }


    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<RetoUsuarioVO>> porUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorUsuario(id));
    }

    @GetMapping("/reto/{id}")
    public ResponseEntity<List<RetoUsuarioVO>> porReto(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorReto(id));
    }

    @GetMapping("/completados")
    public ResponseEntity<List<RetoUsuarioVO>> completados() {
        return ResponseEntity.ok(service.obtenerCompletados());
    }
}
