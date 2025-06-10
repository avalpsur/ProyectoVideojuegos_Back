package com.PixelGround.back.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.PixelGround.back.config.JwtUtil;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.service.RetoService;
import com.PixelGround.back.vo.RetoVO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/retos")
public class RetoController {

    private final RetoService retoService;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;

    public RetoController(RetoService retoService, JwtUtil jwtUtil, UsuarioRepository usuarioRepository) {
        this.retoService = retoService;
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<RetoVO> crearReto(@RequestBody RetoVO retoVO, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.getSubject(token);

        UsuarioModel creador = usuarioRepository.findByEmail(email).orElse(null);
        if (creador == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(retoService.crearReto(retoVO, creador));
    }

    @GetMapping("/activos")
    public ResponseEntity<List<RetoVO>> obtenerActivos() {
        return ResponseEntity.ok(retoService.obtenerRetosActivos());
    }

    @GetMapping
    public ResponseEntity<List<RetoVO>> obtenerTodos() {
        return ResponseEntity.ok(retoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RetoVO> obtenerPorId(@PathVariable Long id) {
        RetoVO reto = retoService.obtenerPorId(id);
        return reto != null ? ResponseEntity.ok(reto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        retoService.eliminarReto(id);
        return ResponseEntity.noContent().build();
    }
}
