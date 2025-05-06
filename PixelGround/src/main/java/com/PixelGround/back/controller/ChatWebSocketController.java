package com.PixelGround.back.controller;

import com.PixelGround.back.dto.MensajeSocketDTO;
import com.PixelGround.back.model.MensajeModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.MensajeRepository;
import com.PixelGround.back.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @MessageMapping("/chat")
    public void recibirMensaje(MensajeSocketDTO dto) {
        UsuarioModel remitente = usuarioRepository.findById(dto.getRemitenteId())
                .orElseThrow(() -> new IllegalArgumentException("Remitente no encontrado"));
        UsuarioModel receptor = usuarioRepository.findById(dto.getReceptorId())
                .orElseThrow(() -> new IllegalArgumentException("Receptor no encontrado"));

        MensajeModel mensaje = new MensajeModel(remitente, receptor, dto.getContenido());
        mensajeRepository.save(mensaje);

        simpMessagingTemplate.convertAndSendToUser(
                receptor.getId().toString(),
                "/queue/messages",
                dto
        );
    }
}
