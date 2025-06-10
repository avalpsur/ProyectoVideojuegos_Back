package com.PixelGround.back.controller;

import com.PixelGround.back.service.ReviewService;
import com.PixelGround.back.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewVO> crearReview(@RequestBody ReviewVO vo) {
        ReviewVO creada = reviewService.crearReview(vo);
        return ResponseEntity.ok(creada);
    }

    @GetMapping("/juego/{idJuego}")
    public ResponseEntity<List<ReviewVO>> obtenerReviewsPorJuego(@PathVariable Long idJuego) {
        List<ReviewVO> reviews = reviewService.obtenerPorJuego(idJuego);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReviewVO>> obtenerReviewsPorUsuario(@PathVariable Long idUsuario) {
        List<ReviewVO> reviews = reviewService.obtenerPorUsuario(idUsuario);
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewVO> editarReview(@PathVariable Long id, @RequestBody ReviewVO vo) {
        ReviewVO actualizada = reviewService.editarReview(id, vo);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReview(@PathVariable Long id) {
        reviewService.eliminarReview(id);
        return ResponseEntity.noContent().build();
    }
}
