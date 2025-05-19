package com.PixelGround.back.service;

import java.util.List;

import com.PixelGround.back.vo.VotacionVO;

public interface VotacionService {
    VotacionVO votar(VotacionVO votacionVO);
    List<VotacionVO> obtenerVotacionesPorUsuario(Long usuarioId);

}
