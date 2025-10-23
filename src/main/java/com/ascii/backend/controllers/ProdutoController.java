package com.ascii.backend.controllers;

import com.ascii.backend.model.ProdutoRequestDTO;
import com.ascii.backend.model.ProdutoResponseDTO;
import com.ascii.backend.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){this.produtoService = produtoService;}

    @PostMapping("/produtos")
    public ResponseEntity addProduto(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO){
        this.produtoService.addProduto(produtoRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutosList(){
        return ResponseEntity.ok(this.produtoService.getProdutosList());
    }

}
