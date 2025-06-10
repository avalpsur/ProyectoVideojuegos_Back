package com.PixelGround.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PixelGround.back.config.JwtUtil;
import com.PixelGround.back.service.VotacionService;
import com.PixelGround.back.vo.VotacionVO;

@RestController
@RequestMapping("/api/votaciones")
public class VotacionController {

    @Autowired
    private VotacionService votacionService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public VotacionVO votar(@RequestBody VotacionVO vo) {
        return votacionService.votar(vo);
    }

   /* @GetMapping("/usuario/voto/{juegoApiId}")
    public ResponseEntity<Integer> obtenerVotoDelUsuario(
            @PathVariable String juegoApiId,
            @RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.getSubject(token); // ✔️ uso correcto del método existente

        int puntuacion = votacionService.obtenerPuntuacionDeUsuario(email, juegoApiId);
        return ResponseEntity.ok(puntuacion);
    }*/

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<VotacionVO>> obtenerVotacionesUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(votacionService.obtenerVotacionesPorUsuario(usuarioId));
    }
}

