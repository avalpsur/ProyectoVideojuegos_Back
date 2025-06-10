package com.PixelGround.back.service;

import com.PixelGround.back.model.RetoUsuarioModel;
import com.PixelGround.back.repository.RetoUsuarioRepository;
import com.PixelGround.back.converter.RetoUsuarioConverter;
import com.PixelGround.back.vo.RetoUsuarioVO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetoUsuarioServiceImpl implements RetoUsuarioService {

    private final RetoUsuarioRepository repository;
    private final RetoUsuarioConverter converter;

    public RetoUsuarioServiceImpl(RetoUsuarioRepository repository, RetoUsuarioConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public RetoUsuarioVO unirseAReto(RetoUsuarioVO vo) {
        RetoUsuarioModel model = converter.toModel(vo);

        System.out.println("→ VO recibido: usuarioId=" + vo.getIdUsuario() + ", retoId=" + vo.getIdReto());
        System.out.println("→ Usuario en model: " + model.getUsuario());
        System.out.println("→ Reto en model: " + model.getReto());

        model.setCompletado(false);

        RetoUsuarioModel saved = repository.save(model);

        saved.setUsuario(model.getUsuario());
        saved.setReto(model.getReto());

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
