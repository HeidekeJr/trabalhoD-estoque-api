package com.trabalhoD.estoque_api.controller;

import com.trabalhoD.estoque_api.controller.exception.RecursoNaoEncontradoException;
import com.trabalhoD.estoque_api.model.Produto;
import com.trabalhoD.estoque_api.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    // a. insert
    @PostMapping
    public ResponseEntity<Produto> inserir(@Valid @RequestBody Produto produto) {
        Produto salvo = repository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // b. delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Produto existente = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado."));
        repository.delete(existente);
        return ResponseEntity.noContent().build();
    }

    // c. update
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto atualizado) {
        Produto existente = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado."));

        existente.setNome(atualizado.getNome());
        existente.setQuantidadeAtual(atualizado.getQuantidadeAtual());
        existente.setPontoDePedido(atualizado.getPontoDePedido());

        return ResponseEntity.ok(repository.save(existente));
    }

    // d. select by id
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com o ID: " + id));
        return ResponseEntity.ok(produto);
    }

    // e. select all
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }
}
