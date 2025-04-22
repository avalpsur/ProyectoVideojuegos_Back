package com.PixelGround.back.service;

import com.PixelGround.back.converter.UsuarioConverter;
import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.repository.UsuarioRepository;
import com.PixelGround.back.vo.UsuarioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioVO crearUsuario(UsuarioVO usuarioVO, String rawPassword) {
        if (usuarioRepository.findByEmail(usuarioVO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email.");
        }
        if (usuarioRepository.findByNombreUsuario(usuarioVO.getNombreUsuario()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese nombre de usuario.");
        }

        UsuarioModel model = UsuarioConverter.toModel(usuarioVO, passwordEncoder.encode(rawPassword));
        return UsuarioConverter.toVO(usuarioRepository.save(model));
    }

    @Override
    public UsuarioVO obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioConverter::toVO)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }

    @Override
    public List<UsuarioVO> obtenerTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioConverter::toVO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public void cambiarPassword(Long id, String nuevaPassword) {
        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioVO buscarUsuario(String login) {
        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByEmail(login);
        if (usuarioOpt.isEmpty()) {
            usuarioOpt = usuarioRepository.findByNombreUsuario(login);
        }
        return usuarioOpt.map(UsuarioConverter::toVO)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }
}
