package com.PixelGround.back.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PixelGround.back.converter.ReviewConverter;
import com.PixelGround.back.model.JuegoModel;
import com.PixelGround.back.model.ReviewModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.JuegoRepository;
import com.PixelGround.back.repository.ReviewRepository;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.vo.ReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired private ReviewRepository reviewRepo;
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private JuegoRepository juegoRepo;
    @Autowired private ReviewConverter converter;
    @Autowired private ActividadService actividadService;

    @Override
    public ReviewVO crearReview(ReviewVO vo) {
        UsuarioModel usuario = usuarioRepo.findById(vo.getIdUsuario())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        JuegoModel juego = juegoRepo.findByApiId(vo.getIdJuego().toString())
            .orElseGet(() -> {
                JuegoModel nuevo = new JuegoModel();
                nuevo.setApiId(vo.getIdJuego().toString());
                nuevo.setNombre(vo.getNombreJuego());
                nuevo.setImagenUrl(vo.getImagenUrlJuego()); 
                return juegoRepo.save(nuevo);
            });

        ReviewModel review = converter.toModel(vo, usuario, juego);
        ReviewModel savedReview = reviewRepo.save(review);

        actividadService.registrarActividad(
            usuario,
            juego,
            "reseña",
            vo.getContenido()
        );

        return converter.toVO(savedReview);
    }


    @Override
    public List<ReviewVO> obtenerPorJuego(Long idJuego) {
        return reviewRepo.findByJuegoApiId(String.valueOf(idJuego))
            .stream()
            .map(converter::toVO)
            .toList();
    }

    @Override
    public List<ReviewVO> obtenerPorUsuario(Long idUsuario) {
        return reviewRepo.findByUsuarioId(idUsuario)
            .stream()
            .map(converter::toVO)
            .toList();
    }

    @Override
    public ReviewVO editarReview(Long id, ReviewVO vo) {
        ReviewModel review = reviewRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Review no encontrada"));

        review.setContenido(vo.getContenido());
        review.setFecha(LocalDateTime.now());
        return converter.toVO(reviewRepo.save(review));
    }

    @Override
    public void eliminarReview(Long id) {
        reviewRepo.deleteById(id);
    }
}
