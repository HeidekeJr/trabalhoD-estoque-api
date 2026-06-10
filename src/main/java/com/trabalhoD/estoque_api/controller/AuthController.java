package com.trabalhoD.estoque_api.controller;

import com.trabalhoD.estoque_api.controller.exception.LoginInvalidoException;
import com.trabalhoD.estoque_api.controller.exception.UsuarioJaExistenteException;
import com.trabalhoD.estoque_api.dto.LoginDTO;
import com.trabalhoD.estoque_api.dto.RegisterDTO;
import com.trabalhoD.estoque_api.model.Usuario;
import com.trabalhoD.estoque_api.repository.UsuarioRepository;
import com.trabalhoD.estoque_api.security.TokenService;

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
    public ResponseEntity<Usuario> register(@RequestBody RegisterDTO data){

        if(repository.findByLogin(data.login()).isPresent()){
            throw new UsuarioJaExistenteException("Login já cadastrado!");
        }

        Usuario usuario = new Usuario();

        usuario.setLogin(data.login());
        usuario.setSenha(encoder.encode(data.senha()));
        usuario.setRole("ROLE_USER");

        return ResponseEntity.ok(repository.save(usuario));
    }

    @PostMapping("/login")
    public  ResponseEntity<String> login(@RequestBody LoginDTO data){

        Usuario usuario = repository.findByLogin(data.login()).orElseThrow(() -> new LoginInvalidoException("Usuário não encontrado!"));

        if (!encoder.matches(data.senha(), usuario.getSenha())){
            throw new RuntimeException("Senha Inválida!");
        }

        String token = tokenService.gerarToken(usuario.getLogin());

        return ResponseEntity.ok(token);
    }

    @Autowired
    private TokenService  tokenService;

}
