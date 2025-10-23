package com.ascii.backend.controllers;

import com.ascii.backend.model.ProdutoRequestDTO;
import com.ascii.backend.model.ProdutoResponseDTO;
import com.ascii.backend.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

        // Aplicação da pagination
    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutosList(@RequestParam(name = "page", defaultValue = "0")int pagina, @RequestParam(name = "size", defaultValue = "5")int tamanho){
        Pageable pageable = PageRequest.of(pagina,tamanho, Sort.by("nome"));
        return ResponseEntity.ok(this.produtoService.getProdutosList(pageable));
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProdutoResponseDTO> getProdutoById(@PathVariable String id){
        try{
            return ResponseEntity.ok(this.produtoService.getProdutoById(id));
        }
        catch (Exception e){
          throw new RuntimeException(HttpStatus.NOT_FOUND.toString(), e);
        }
    }

        // Aplicação da pagination
    @GetMapping("/produtos/categoria/{categoria}")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutoListByCategoria(@PathVariable String categoria, @RequestParam(name = "page", defaultValue = "0")int pagina, @RequestParam(name = "size", defaultValue = "5")int tamanho){
        try{
            Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("nome"));
            return ResponseEntity.ok(this.produtoService.getProdutoListByCategoria(categoria, pageable));
        }
        catch (Exception e){
            throw new RuntimeException(HttpStatus.NOT_FOUND.toString(), e);
        }
    }


    @PutMapping("/produtos/{id}")
    public ResponseEntity updateProdutoById(@PathVariable String id, @RequestBody @Valid ProdutoRequestDTO produtoRequestDTO){
        try {
            this.produtoService.updateProdutoById(id,produtoRequestDTO);
        } catch (Exception e) {
            throw new RuntimeException(HttpStatus.NOT_FOUND.toString(), e);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity deleteProdutoById(@PathVariable String id){
        this.produtoService.deleteProdutoById(id);
        return ResponseEntity.ok().build();
    }

}
