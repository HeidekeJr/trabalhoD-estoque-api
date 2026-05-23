package com.trabalhoD.estoque_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "A quantidade atual não pode ser nula.")
    @Column(nullable = false)
    private Integer quantidadeAtual;

    @NotNull(message = "O ponto de pedido automático não pode ser nulo.")
    @Column(nullable = false)
    private Integer pontoDePedido;

    @ManyToMany
    @JoinTable(
            name = "produto_fornecedor",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "fornecedor_id")
    )
    private List<Fornecedor> fornecedores;
}