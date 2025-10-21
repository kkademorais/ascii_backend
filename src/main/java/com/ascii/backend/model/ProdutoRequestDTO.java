package com.ascii.backend.model;

public record ProdutoRequestDTO(
        String nome,
        Double precoUnitario,
        int quantidade,
        Double valor,
        String categoria
)
{}
