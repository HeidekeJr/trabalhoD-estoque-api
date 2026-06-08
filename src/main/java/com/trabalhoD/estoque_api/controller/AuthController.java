package com.trabalhoD.estoque_api.controller;

import com.trabalhoD.estoque_api.dto.RegisterDTO;
import com.trabalhoD.estoque_api.model.Usuario;
import com.trabalhoD.estoque_api.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public  ResponseEntity<Usuario> register(@RequestBody RegisterDTO data){

        Usuario usuario = new Usuario();

        usuario.setLogin(data.login());
        usuario.setSenha(encoder.encode(data.senha()));

        usuario.setRole("ROLE_USER!");

        return ResponseEntity.ok(repository.save(usuario));
    }

}
