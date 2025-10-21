package com.ascii.backend.controllers;

import com.ascii.backend.model.ProdutoRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProdutoController {

    private final produtoService produtoService;

    public ProdutoController(produtoService produtoService){this.produtoService = produtoService;}

    @GetMapping("/produtos")
    public RequestBody getProdutos(ProdutoRequestDTO produtoRequestDTO){
        this.produtoService.getProdutos(produtoRequestDTO);
    }

}
