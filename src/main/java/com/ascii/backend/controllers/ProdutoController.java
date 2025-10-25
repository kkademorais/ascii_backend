package com.ascii.backend.controllers;

import com.ascii.backend.model.ProdutoRequestDTO;
import com.ascii.backend.model.ProdutoResponseDTO;
import com.ascii.backend.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

    @Operation(summary = "Registra novo produto com os atributos passados no Request Body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Produto registrado no Banco de Dados corretamente"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Erro na Request JSON, como por ex passar String em atributo Int"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Erro de validação de algum parâmetro na requisição, como valores nulos")
    })
    @Tag(name = "addProduto", description = "Cria um novo produto e insere no banco de dados")
    @PostMapping("/produtos")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Atributos do produto a ser criado",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProdutoRequestDTO.class,
                                    example = "{\n" +
                                            "    \"nome\": \"Café\",\n" +
                                            "    \"precoUnitario\": 7.25,\n" +
                                            "    \"quantidade\": 2,    \n" +
                                            "    \"categoria\": \"Bebidas Quentes\"\n" +
                                            "}"
                            )
                    )
            })
    public ResponseEntity addProduto(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO){
        this.produtoService.addProduto(produtoRequestDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Retorna uma lista com todos os produtos cadastrados")
    @ApiResponse(
            responseCode = "200",
            description = "OK - Lista dos produtos é retornada em formato JSON",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoResponseDTO.class, examples = {"{\n" +
                                    "        \"id\": \"abcd-1234-dfg-56789\",\n" +
                                    "        \"nome\": \"Café\",\n" +
                                    "        \"precoUnitario\": 2.9,\n" +
                                    "        \"quantidade\": 3,\n" +
                                    "        \"valor\": 8.7,\n" +
                                    "        \"categoria\": \"Bebidas Quentes\"\n" +
                                    "    },\n" +
                                    "    {\n" +
                                    "        \"id\": \"1234-567z-8910a\",\n" +
                                    "        \"nome\": \"Chocolate\",\n" +
                                    "        \"precoUnitario\": 4.7,\n" +
                                    "        \"quantidade\": 16,\n" +
                                    "        \"valor\": 75.2,\n" +
                                    "        \"categoria\": \"Doces\"\n" +
                                    "    }"})
            )}
    )
        // Aplicação da pagination
    @Tag(name = "getProdutosList", description = "Retorna a lista de todos os produtos registrados no banco de dados")
    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutosList(@RequestParam(name = "page", defaultValue = "0")int pagina, @RequestParam(name = "size", defaultValue = "5")int tamanho){
        Pageable pageable = PageRequest.of(pagina,tamanho, Sort.by("nome"));
        return ResponseEntity.ok(this.produtoService.getProdutosList(pageable));
    }

    @Operation(summary = "Retorna o produto correspondente ao ID passado como parâmetro na requisição")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - Produto com ID correspondente é retornado, juntamente à seus atributos",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProdutoResponseDTO.class, examples = {"{\n" +
                                            "        \"id\": \"987-654-3210\",\n" +
                                            "        \"nome\": \"Leite\",\n" +
                                            "        \"precoUnitario\": 4.7,\n" +
                                            "        \"quantidade\": 16,\n" +
                                            "        \"valor\": 75.2,\n" +
                                            "        \"categoria\": \"Laticínios\"\n" +
                                            "    }"})
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error - Erro na passagem de algum parâmetro na requisição → ID vazio ou inválido"
            )
    })
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

    @Operation(summary = "Retorna uma lista de produtos com a categoria correspondente passada como parâmetro")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - Produtos com a categoria correspondente são retornados, juntamente à seus atributos",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProdutoResponseDTO.class, examples = {"{\n" +
                                            "        \"id\": \"147-258-369-zxcv\",\n" +
                                            "        \"nome\": \"Brahma\",\n" +
                                            "        \"precoUnitario\": 2.3,\n" +
                                            "        \"quantidade\": 6,\n" +
                                            "        \"valor\": 13.8,\n" +
                                            "        \"categoria\": \"cerveja\"\n" +
                                            "    },\n" +
                                            "    {\n" +
                                            "        \"id\": \"4159-357-8426-abcd\",\n" +
                                            "        \"nome\": \"Heineken\",\n" +
                                            "        \"precoUnitario\": 4.9,\n" +
                                            "        \"quantidade\": 5,\n" +
                                            "        \"valor\": 24.5,\n" +
                                            "        \"categoria\": \"Cerveja\"\n" +
                                            "    }"})
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error - Erro na passagem de algum parâmetro na requisição → categoria vazia ou inválida"
            )
    })
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

    @Operation(summary = "Atualiza os atributos do produto com o ID correspondente passado como parâmetro, especificados na requisição")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found - Produto não cadastrado"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - Produto do ID correspondente tem seus atributos atualizados da forma passada no Request Body"
            )
    })
    @Tag(name = "updateProdutoById", description = "Atualiza os atributos passados na requisição do produto com o ID especificado")
    @PutMapping("/produtos/{id}")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Atributos a serem atualizados do produto correspondente",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProdutoRequestDTO.class,
                                    example = "{\n" +
                                            "    \"nome\": \"Café\",\n" +
                                            "    \"precoUnitario\": 5.50,\n" +
                                            "    \"quantidade\": 4,    \n" +
                                            "    \"categoria\": \"Bebidas Quentes\"\n" +
                                            "}"
                            )
                    )
            }
    )
    public ResponseEntity updateProdutoById(@PathVariable String id, @RequestBody @Valid ProdutoRequestDTO produtoRequestDTO){
        try {
            this.produtoService.updateProdutoById(id,produtoRequestDTO);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
            //throw new RuntimeException(HttpStatus.NOT_FOUND.toString(), e);
        }
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Deleta o produto com o ID correspondente do Banco de Dados")
    @ApiResponse(
            responseCode = "200",
            description = "OK - Produto é removido do Banco de Dados"
    )
    @Tag(name = "deleteProdutoById", description = "Remove o produto do banco de dados, buscando pelo seu ID")
    @DeleteMapping("/produtos/{id}")
    public ResponseEntity deleteProdutoById(@PathVariable @NotBlank String id){
        this.produtoService.deleteProdutoById(id);
        return ResponseEntity.ok().build();
    }

}
