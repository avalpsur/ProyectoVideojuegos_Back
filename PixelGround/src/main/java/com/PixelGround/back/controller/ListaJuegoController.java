package com.PixelGround.back.controller;

import com.PixelGround.back.service.ListaJuegoService;
import com.PixelGround.back.vo.JuegoVO;
import com.PixelGround.back.vo.ListaJuegoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listas")
public class ListaJuegoController {

    @Autowired
    private ListaJuegoService listaJuegoService;

    @PostMapping
    public ResponseEntity<ListaJuegoVO> crearLista(@RequestBody ListaJuegoVO vo) {
        ListaJuegoVO nuevaLista = listaJuegoService.crearLista(vo);
        return ResponseEntity.ok(nuevaLista);
    }

    @PostMapping("/{listaId}/juegos")
    public ResponseEntity<Void> añadirJuegoALista(
            @PathVariable Long listaId,
            @RequestBody JuegoVO juegoVO) {

        listaJuegoService.añadirJuegoALista(listaId, juegoVO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ListaJuegoVO>> obtenerListasDeUsuario(@PathVariable Long usuarioId) {
        List<ListaJuegoVO> listas = listaJuegoService.obtenerListasDeUsuario(usuarioId);
        return ResponseEntity.ok(listas);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLista(@PathVariable Long id) {
        listaJuegoService.eliminarLista(id);
        return ResponseEntity.noContent().build();
    }

}
