package com.ascii.backend.model;

public record ProdutoRequestDTO(
        String nome,
        Double precoUnitario,
        Integer quantidade,
        //Double valor,
        String categoria
)
{}
