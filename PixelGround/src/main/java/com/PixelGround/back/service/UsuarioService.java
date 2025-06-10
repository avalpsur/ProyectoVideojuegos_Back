package com.PixelGround.back.service;

import com.PixelGround.back.vo.UsuarioVO;

import java.util.List;

public interface UsuarioService {

    UsuarioVO crearUsuario(UsuarioVO usuarioVO, String rawPassword);

    UsuarioVO obtenerUsuarioPorId(Long id);

    List<UsuarioVO> obtenerTodos();

    void eliminarUsuario(Long id);

    void cambiarPassword(Long id, String nuevaPassword);

    UsuarioVO buscarUsuario(String login);List<UsuarioVO> buscarUsuariosPorNombre(String nombre);
    
    UsuarioVO cambiarRol(Long id, String nuevoRol);
    
    public UsuarioVO buscarPorNombreUsuario(String nombreUsuario);

}
