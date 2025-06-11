package com.PixelGround.back.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class SteamServiceImpl implements SteamService {

    private static final String API_KEY = "2426C2307771FCDF454C70731D33725E";

    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> obtenerJuegosRecientes(String steamId) {
        String url = "https://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v1/"
            + "?key=" + API_KEY
            + "&steamid=" + steamId
            + "&format=json";

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response == null || !response.containsKey("response")) return List.of();

        Map<String, Object> responseBody = (Map<String, Object>) response.get("response");
        return (List<Map<String, Object>>) responseBody.getOrDefault("games", List.of());
    }
}