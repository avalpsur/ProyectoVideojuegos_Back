package com.PixelGround.back.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.PixelGround.back.converter.RetoUsuarioConverter;
import com.PixelGround.back.model.RetoModel;
import com.PixelGround.back.model.RetoUsuarioModel;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.RetoRepository;
import com.PixelGround.back.repository.RetoUsuarioRepository;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.vo.RetoUsuarioVO;

@Service
public class RetoUsuarioServiceImpl implements RetoUsuarioService {

    private final RetoUsuarioRepository repository;
    private final RetoUsuarioConverter converter;
    private final UsuarioRepository usuarioRepository;
    private final RetoRepository retoRepository;

    public RetoUsuarioServiceImpl(
        RetoUsuarioRepository repository,
        RetoUsuarioConverter converter,
        UsuarioRepository usuarioRepository,
        RetoRepository retoRepository
    ) {
        this.repository = repository;
        this.converter = converter;
        this.usuarioRepository = usuarioRepository;
        this.retoRepository = retoRepository;
    }

    @Override
    public RetoUsuarioVO unirseAReto(RetoUsuarioVO vo) {
        RetoUsuarioModel model = converter.toModel(vo);

        UsuarioModel usuario = usuarioRepository.findById(vo.getIdUsuario())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        RetoModel reto = retoRepository.findById(vo.getIdReto())
            .orElseThrow(() -> new RuntimeException("Reto no encontrado"));

        // Asignamos las relaciones correctamente
        model.setUsuario(usuario);
        model.setReto(reto);
        model.setCompletado(false);

        // Guardamos
        RetoUsuarioModel saved = repository.save(model);

        return converter.toVO(saved);
    }

    @Override
    public RetoUsuarioVO marcarComoCompletado(Long id, String comentario, String imagenUrl) {
        RetoUsuarioModel model = repository.findById(id).orElse(null);
        if (model == null) return null;

        model.setCompletado(true);
        model.setComentario(comentario);
        model.setImagenPruebaUrl(imagenUrl);
        model.setFechaCompletado(LocalDate.now());

        return converter.toVO(repository.save(model));
    }

    @Override
    public List<RetoUsuarioVO> obtenerPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId)
                .stream().map(converter::toVO).collect(Collectors.toList());
    }

    @Override
    public List<RetoUsuarioVO> obtenerPorReto(Long retoId) {
        return repository.findByRetoId(retoId)
                .stream().map(converter::toVO).collect(Collectors.toList());
    }

    @Override
    public List<RetoUsuarioVO> obtenerCompletados() {
        return repository.findByCompletadoTrueOrderByFechaCompletadoDesc()
                .stream().map(converter::toVO).collect(Collectors.toList());
    }
}
