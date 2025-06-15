package com.PixelGround.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<ListaJuegoVO> crearLista(@RequestBody ListaJuegoVO vo, Authentication auth) {
        String email = auth.getName();
        UsuarioModel usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        vo.setUsuarioId(usuario.getId()); 
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
            System.out.println("Error: Authorization header inválido o ausente.");
            return ResponseEntity.status(403).build();
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.getSubject(token);
        System.out.println("Token recibido. Usuario identificado: " + email);

        UsuarioModel usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> {
                System.out.println("Error: Usuario no encontrado con email: " + email);
                return new RuntimeException("Usuario no encontrado");
            });

        System.out.println("Añadiendo juego con apiId: " + juegoVO.getApiId() + " a la lista " + listaId + " por el usuario ID: " + usuario.getId());
        listaJuegoService.añadirJuegoALista(listaId, juegoVO, usuario.getId());

        return ResponseEntity.ok().build();
    }


    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ListaJuegoVO>> obtenerListasDeUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(listaJuegoService.obtenerListasDeUsuario(usuarioId));
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

    @PutMapping("/{listaId}/unirse")
    public ResponseEntity<Void> unirseALista(@PathVariable Long listaId, Authentication auth) {
        String email = auth.getName();
        UsuarioModel usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        listaJuegoService.agregarUsuario(listaId, usuario);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/publicas")
    public ResponseEntity<List<ListaJuegoVO>> obtenerListasPublicas() {
        return ResponseEntity.ok(listaJuegoService.obtenerListasPublicas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaJuegoVO> obtenerListaPorId(@PathVariable Long id, Authentication auth) {
        String email = auth.getName();
        UsuarioModel usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return ResponseEntity.ok(listaJuegoService.obtenerListaPorId(id, usuario));
    }

    @PatchMapping("/{id}/visibilidad")
    public ResponseEntity<Void> cambiarVisibilidad(@PathVariable Long id, Authentication auth) {
        String email = auth.getName();
        UsuarioModel usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        listaJuegoService.cambiarVisibilidad(id, usuario.getId());
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{listaId}/juegos/{apiId}")
    public ResponseEntity<Void> eliminarJuegoDeLista(@PathVariable Long listaId, @PathVariable String apiId, Authentication auth) {
        String email = auth.getName();
        UsuarioModel usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        listaJuegoService.eliminarJuegoDeLista(listaId, apiId, usuario.getId());
        return ResponseEntity.noContent().build();
    }

}

