package com.PixelGround.back.controller;

import com.PixelGround.back.config.JwtUtil;
import com.PixelGround.back.dto.MensajeSocketDTO;
import com.PixelGround.back.model.MensajeModel;
import com.PixelGround.back.repository.MensajeRepository;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.vo.UsuarioVO;
import com.PixelGround.back.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatRestController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MensajeRepository mensajeRepository;

    @GetMapping("/historial")
    public ResponseEntity<List<MensajeSocketDTO>> obtenerHistorial(
        @RequestParam Long receptorId,
        HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.getSubject(token);

        UsuarioVO usuario = usuarioService.buscarUsuario(email);

        usuarioRepository.findById(usuario.getId())
            .orElseThrow(() -> new IllegalArgumentException("Usuario autenticado no encontrado"));

        List<MensajeModel> mensajes = mensajeRepository.findMensajesEntreUsuarios(
            usuario.getId(), receptorId
        );

        List<MensajeSocketDTO> dtoList = mensajes.stream()
            .map(m -> new MensajeSocketDTO(
                m.getRemitente().getId(),
                m.getReceptor().getId(),
                m.getContenido()
            ))
            .toList();

        return ResponseEntity.ok(dtoList);
    }
}
