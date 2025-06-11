package com.PixelGround.back.service;

import java.util.List;
import java.util.Map;

public interface SteamService {
    List<Map<String, Object>> obtenerJuegosRecientes(String steamId);
}
