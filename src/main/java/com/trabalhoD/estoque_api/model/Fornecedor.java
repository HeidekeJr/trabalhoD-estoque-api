package com.trabalhoD.estoque_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Fornecedor {

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da empresa é obrigatório.")
    @Column(nullable = false)
    private String nomeEmpresa;

    @NotBlank(message = "O CNPJ é obrigatório.")
    @Column(nullable = false, unique = true)
    private String cnpj;

    @ManyToMany(mappedBy = "fornecedores")
    @JsonIgnore // Evita loop infinito no JSON ao listar produtos e fornecedores
    private List<Produto> produtos;
}