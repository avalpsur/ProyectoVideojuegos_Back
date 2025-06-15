package com.PixelGround.back.service;

import java.util.List;

import com.PixelGround.back.model.UsuarioModel;
import com.PixelGround.back.vo.UsuarioVO;

public interface UsuarioService {

    UsuarioVO crearUsuario(UsuarioVO usuarioVO, String rawPassword);

    UsuarioVO obtenerUsuarioPorId(Long id);

    List<UsuarioVO> obtenerTodos();

    void eliminarUsuario(Long id);

    void cambiarPassword(Long id, String nuevaPassword);

    UsuarioVO buscarUsuario(String login);List<UsuarioVO> buscarUsuariosPorNombre(String nombre);
    
    UsuarioVO cambiarRol(Long id, String nuevoRol);
    
    public UsuarioVO buscarPorNombreUsuario(String nombreUsuario);
    
    void vincularSteamPorEmail(String email, String steamId);
    

}
