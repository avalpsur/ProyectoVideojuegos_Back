package com.PixelGround.back.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PixelGround.back.config.JwtUtil;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.UsuarioRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> datos) {
        String login = datos.get("login");
        String password = datos.get("password");

        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByEmail(login);
        if (usuarioOpt.isEmpty()) {
            usuarioOpt = usuarioRepository.findByNombreUsuario(login);
        }

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Usuario no encontrado.");
        }

        UsuarioModel usuario = usuarioOpt.get();
        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            return ResponseEntity.status(401).body("Contraseña incorrecta.");
        }

        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRol());
        return ResponseEntity.ok(token);
    }

}
