package com.ascii.backend.model;

import jakarta.persistence.*;


@Entity(name = "produtos")
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private Double precoUnitario;
    private Integer quantidade;
    private Double valor;
    private String categoria;

    public Produto(String nome, Double precoUnitario, Integer quantidade, Double valor, String categoria){
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.valor = valor;
        this.categoria = categoria;
    }
    public Produto(ProdutoRequestDTO produtoRequestDTO){
        this.nome = produtoRequestDTO.nome();
        this.precoUnitario = produtoRequestDTO.precoUnitario();
        this.quantidade = produtoRequestDTO.quantidade();
        //this.valor = produtoRequestDTO.valor();
        this.categoria = produtoRequestDTO.categoria();
    }
    public Produto(){}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public Double getPrecoUnitario() {return precoUnitario;}
    public void setPrecoUnitario(Double precoUnitario) {this.precoUnitario = precoUnitario;}

    public Integer getQuantidade() {return quantidade;}
    public void setQuantidade(Integer quantidade) {this.quantidade = quantidade;}

    public Double getValor() {return valor;}
    public void setValor(Double valor) {this.valor = valor;}

    public String getCategoria() {return categoria;}
    public void setCategoria(String categoria) {this.categoria = categoria;}

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

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
