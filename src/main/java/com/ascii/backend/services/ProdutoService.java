package com.ascii.backend.services;

import com.ascii.backend.model.Produto;
import com.ascii.backend.model.ProdutoRequestDTO;
import com.ascii.backend.model.ProdutoResponseDTO;
import com.ascii.backend.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){this.produtoRepository = produtoRepository;}

    public void addProduto(ProdutoRequestDTO produtoRequestDTO){
        Produto produtoAdd = new Produto(produtoRequestDTO);
        produtoAdd.setValor(produtoRequestDTO.precoUnitario() * produtoRequestDTO.quantidade());
        this.produtoRepository.save(produtoAdd);
    }

    public List<ProdutoResponseDTO> getProdutosList(){
        return this.produtoRepository
                .findAll()
                .stream()
                .map(produto -> new ProdutoResponseDTO(produto))
                .toList();
    }

    public void updateProdutoById(String id, ProdutoRequestDTO produtoRequestDTO) throws Exception{
        if(this.produtoRepository.findById(id).isPresent()){
        Produto produtoUpdate = new Produto(produtoRequestDTO);
        produtoUpdate.setId(id);
        produtoUpdate.setValor(produtoRequestDTO.precoUnitario() * produtoRequestDTO.quantidade());
        this.produtoRepository.save(produtoUpdate);
        }
        else{
            throw new Exception("Produto não cadastrado");
        }
    }

    public void deleteProdutoById(String id){this.produtoRepository.deleteById(id);}


}
