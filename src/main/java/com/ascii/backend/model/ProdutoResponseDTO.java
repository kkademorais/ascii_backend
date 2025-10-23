package com.ascii.backend.model;

public record ProdutoResponseDTO(
        String nome,
        Double precoUnitario,
        Integer quantidade,
        Double valor,
        String categoria
){
    public ProdutoResponseDTO(Produto produto){
        this(produto.getNome(), produto.getPrecoUnitario(), produto.getQuantidade(), produto.getValor(), produto.getCategoria());
    }
}
