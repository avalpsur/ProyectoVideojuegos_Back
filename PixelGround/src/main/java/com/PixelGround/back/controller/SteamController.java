package com.PixelGround.back.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.service.SteamService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/steam")
public class SteamController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SteamService steamService;

    @GetMapping("/recientes")
    public ResponseEntity<?> obtenerJuegosRecientes(Authentication authentication) {
        String email = authentication.getName();
        Optional<UsuarioModel> optional = usuarioRepository.findByEmail(email);
        if (optional.isEmpty()) return ResponseEntity.status(404).body("Usuario no encontrado");

        UsuarioModel usuario = optional.get();
        if (usuario.getSteamId() == null) {
            return ResponseEntity.badRequest().body("El usuario no tiene una cuenta de Steam vinculada");
        }

        List<Map<String, Object>> juegos = steamService.obtenerJuegosRecientes(usuario.getSteamId());
        return ResponseEntity.ok(juegos);
    }
    
    @GetMapping("/login")
    public void redirigirSteamLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String returnTo = request.getScheme() + "://" + request.getServerName() + ":4200/steam-callback";

        String steamLoginUrl = "https://steamcommunity.com/openid/login"
                + "?openid.ns=http://specs.openid.net/auth/2.0"
                + "&openid.mode=checkid_setup"
                + "&openid.return_to=" + URLEncoder.encode(returnTo, StandardCharsets.UTF_8)
                + "&openid.realm=" + request.getScheme() + "://" + request.getServerName() + ":4200"
                + "&openid.identity=http://specs.openid.net/auth/2.0/identifier_select"
                + "&openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select";

        response.sendRedirect(steamLoginUrl);
    }



}
