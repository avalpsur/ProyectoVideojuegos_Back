package com.PixelGround.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	registry.addEndpoint("/ws")
        .setAllowedOriginPatterns("*")
        .withSockJS();


    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Mensajes que se reciben del cliente deben ir con este prefijo
        registry.setApplicationDestinationPrefixes("/app");

        // Broker simple para enviar mensajes a un usuario (rutas tipo /user/{id}/queue/messages)
        registry.enableSimpleBroker("/user");

        // Indica que los destinos dirigidos a un usuario usarán este prefijo
        registry.setUserDestinationPrefix("/user");
    }
}
