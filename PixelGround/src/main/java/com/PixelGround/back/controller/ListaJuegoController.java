package com.PixelGround.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PixelGround.back.config.JwtUtil;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.service.ListaJuegoService;
import com.PixelGround.back.vo.JuegoVO;
import com.PixelGround.back.vo.ListaJuegoVO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/listas")
public class ListaJuegoController {

    @Autowired
    private ListaJuegoService listaJuegoService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<ListaJuegoVO> crearLista(@RequestBody ListaJuegoVO vo) {
        ListaJuegoVO nuevaLista = listaJuegoService.crearLista(vo);
        return ResponseEntity.ok(nuevaLista);
    }

    @PostMapping("/{listaId}/juegos")
    public ResponseEntity<Void> añadirJuegoALista(
            @PathVariable Long listaId,
            @RequestBody JuegoVO juegoVO,
            HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(403).build();
        }

        String token = authHeader.substring(7);
        Long usuarioId = Long.parseLong(jwtUtil.getSubject(token));

        listaJuegoService.añadirJuegoALista(listaId, juegoVO, usuarioId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ListaJuegoVO>> obtenerListasDeUsuario(@PathVariable Long usuarioId) {
        List<ListaJuegoVO> listas = listaJuegoService.obtenerListasDeUsuario(usuarioId);
        return ResponseEntity.ok(listas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLista(@PathVariable Long id, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(403).build();
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.getSubject(token);
        UsuarioModel usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        listaJuegoService.eliminarLista(id, usuario.getId());
        return ResponseEntity.noContent().build();
    }

}

