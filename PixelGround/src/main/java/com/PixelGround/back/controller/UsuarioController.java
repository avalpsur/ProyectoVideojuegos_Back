package com.PixelGround.back.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PixelGround.back.service.UsuarioService;
import com.PixelGround.back.vo.UsuarioVO;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<UsuarioVO> registrar(@RequestBody Map<String, Object> datos) {
        String nombreUsuario = (String) datos.get("nombreUsuario");
        String email = (String) datos.get("email");
        String password = (String) datos.get("password");

        UsuarioVO vo = new UsuarioVO();
        vo.setNombreUsuario(nombreUsuario);
        vo.setEmail(email);
        vo.setRol("JUGADOR"); 

        UsuarioVO nuevo = usuarioService.crearUsuario(vo, password);
        return ResponseEntity.ok(nuevo);
    }


    @GetMapping
    public ResponseEntity<List<UsuarioVO>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioVO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/cambiar-password")
    public ResponseEntity<Void> cambiarPassword(@PathVariable Long id, @RequestParam String nuevaPassword) {
        usuarioService.cambiarPassword(id, nuevaPassword);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioVO> obtenerPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.buscarUsuario(email));
    }

}
