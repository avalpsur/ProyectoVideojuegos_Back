package com.PixelGround.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PixelGround.back.service.VotacionService;
import com.PixelGround.back.vo.VotacionVO;

@RestController
@RequestMapping("api/votaciones")
@CrossOrigin
public class VotacionController {

    @Autowired
    private VotacionService votacionService;
    
    @PostMapping
    public VotacionVO votar(@RequestBody VotacionVO vo) {
        return votacionService.votar(vo);
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<VotacionVO>> obtenerVotacionesUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(votacionService.obtenerVotacionesPorUsuario(usuarioId));
    }



}
