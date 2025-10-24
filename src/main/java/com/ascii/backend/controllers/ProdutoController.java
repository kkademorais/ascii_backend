package com.ascii.backend.controllers;

import com.ascii.backend.model.ProdutoRequestDTO;
import com.ascii.backend.model.ProdutoResponseDTO;
import com.ascii.backend.services.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ProdutoController", description = "Controller do Produto para cuidar dos Endpoints")
@RestController
@RequestMapping("/api")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){this.produtoService = produtoService;}

    @Tag(name = "addProduto", description = "Cria um novo produto e insere no banco de dados")
    @PostMapping("/produtos")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true)
    public ResponseEntity addProduto(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO){
        this.produtoService.addProduto(produtoRequestDTO);
        return ResponseEntity.ok().build();
    }

        // Aplicação da pagination
    @Tag(name = "get ProdutosList", description = "Retorna a lista de todos os produtos registrados no banco de dados")
    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutosList(@RequestParam(name = "page", defaultValue = "0")int pagina, @RequestParam(name = "size", defaultValue = "5")int tamanho){
        Pageable pageable = PageRequest.of(pagina,tamanho, Sort.by("nome"));
        return ResponseEntity.ok(this.produtoService.getProdutosList(pageable));
    }

    @Tag(name = "GET-with-parms", description = "Busca por produtos a partir de um parâmetro, seja individual a partir do Id ou um conjunto a partir da Categoria")
    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProdutoResponseDTO> getProdutoById(@PathVariable String id){
        try{
            return ResponseEntity.ok(this.produtoService.getProdutoById(id));
        }
        catch (Exception e){
            //return new ResponseEntity<ProdutoResponseDTO>(e.getMessage(), HttpStatus.NOT_FOUND);
            throw new RuntimeException(HttpStatus.NOT_FOUND.toString(), e);
        }
    }

        // Aplicação da pagination
    @Tag(name = "GET-with-parms", description = "Busca por produtos a partir de um parâmetro, seja individual a partir do Id ou um conjunto a partir da Categoria")
    @GetMapping("/produtos/categoria/{categoria}")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutoListByCategoria(@PathVariable String categoria, @RequestParam(name = "page", defaultValue = "0")int pagina, @RequestParam(name = "size", defaultValue = "5")int tamanho){
        try{
            Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("nome"));
            return ResponseEntity.ok(this.produtoService.getProdutoListByCategoria(categoria, pageable));
        }
        catch (Exception e){
            //return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            throw new RuntimeException(HttpStatus.NOT_FOUND.toString(), e);
        }
    }

    @Tag(name = "updateProdutoById", description = "Atualiza os atributos passados na requisição do produto com o ID especificado")
    @PutMapping("/produtos/{id}")
    public ResponseEntity updateProdutoById(@PathVariable String id, @RequestBody @Valid ProdutoRequestDTO produtoRequestDTO){
        try {
            this.produtoService.updateProdutoById(id,produtoRequestDTO);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
            //throw new RuntimeException(HttpStatus.NOT_FOUND.toString(), e);
        }
        return ResponseEntity.ok().build();
    }

    @Tag(name = "deleteProdutoById", description = "Remove o produto do banco de dados, buscando pelo seu ID")
    @DeleteMapping("/produtos/{id}")
    public ResponseEntity deleteProdutoById(@PathVariable String id){
        this.produtoService.deleteProdutoById(id);
        return ResponseEntity.ok().build();
    }

}
