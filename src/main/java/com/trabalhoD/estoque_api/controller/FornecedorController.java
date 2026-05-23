package com.trabalhoD.estoque_api.controller;

import com.trabalhoD.estoque_api.model.Fornecedor;
import com.trabalhoD.estoque_api.repository.FornecedorRepository;
import com.trabalhoD.estoque_api.controller.exception.RecursoNaoEncontradoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorRepository repository;

    // a. insert
    @PostMapping
    public ResponseEntity<Fornecedor> inserir(@Valid @RequestBody Fornecedor fornecedor) {
        Fornecedor salvo = repository.save(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // b. delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Fornecedor existente = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado para exclusão."));
        repository.delete(existente);
        return ResponseEntity.noContent().build();
    }

    // c. update
    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizar(@PathVariable Long id, @Valid @RequestBody Fornecedor atualizado) {
        Fornecedor existente = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado para atualização."));

        existente.setNomeEmpresa(atualizado.getNomeEmpresa());
        existente.setCnpj(atualizado.getCnpj());

        return ResponseEntity.ok(repository.save(existente));
    }

    // d. select by id
    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Long id) {
        Fornecedor fornecedor = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado com o ID: " + id));
        return ResponseEntity.ok(fornecedor);
    }

    // e. select all
    @GetMapping
    public ResponseEntity<List<Fornecedor>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }
}