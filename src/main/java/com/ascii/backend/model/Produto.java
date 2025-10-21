package com.ascii.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity(name = "produtos")
@Table(name = "produtos")
public class Produto {

    @Id
    private UUID produto_uid;
    private String nome;
    private Double precoUnitario;
    private int quantidade;
    private Double valor;
    private String categoria;

    public Produto(String nome, Double precoUnitario, int quantidade, Double valor, String categoria){
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.valor = valor;
        this.categoria = categoria;
    }
    public Produto(ProdutoResponseDTO produtoResponseDTO){
        this.nome = produtoResponseDTO.nome;
        this.precoUnitario = produtoResponseDTO.precoUnitario;
        this.quantidade = produtoResponseDTO.quantidade;
        this.valor - produtoResponseDTO.valor;
        this.categoria = produtoResponseDTO.categoria;
    }
    public Produto(){}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public Double getPrecoUnitario() {return precoUnitario;}
    public void setPrecoUnitario(Double precoUnitario) {this.precoUnitario = precoUnitario;}

    public int getQuantidade() {return quantidade;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}

    public Double getValor() {return valor;}
    public void setValor(Double valor) {this.valor = valor;}

    public String getCategoria() {return categoria;}
    public void setCategoria(String categoria) {this.categoria = categoria;}

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", precoUnitario=" + precoUnitario +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
