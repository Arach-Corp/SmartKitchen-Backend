package com.arachcorp.smartkitchen.rest;

import com.arachcorp.smartkitchen.entities.Produto;
import com.arachcorp.smartkitchen.rest.dto.produto.ProdutoDTO;
import com.arachcorp.smartkitchen.rest.dto.produto.ProdutoInsertDTO;
import com.arachcorp.smartkitchen.rest.utils.PageConverterUtils;
import com.arachcorp.smartkitchen.rest.utils.PageableUtils;
import com.arachcorp.smartkitchen.rest.utils.UriUtils;
import com.arachcorp.smartkitchen.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Api
@Slf4j
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @ApiOperation("${produto-api.getall.description}")
    @ApiResponse(code = 200, message = "OK")
    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "perPage", defaultValue = "10") int perPage
    ){
        final Pageable pageable = PageableUtils.createPageable(page, perPage);
        final Page<Produto> produtos = produtoService.getAll(pageable);
        final Page<ProdutoDTO> results = PageConverterUtils.convertProdutoPage(produtos, pageable);
        return ResponseEntity.ok(results);
    }

    @ApiOperation("${produto-api.create.description}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "BAD_REQUEST")
    })
    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@Valid @RequestBody ProdutoInsertDTO dto){
        Produto produto = dto.toProduto();
        produto = produtoService.create(produto);
        final URI uri = UriUtils.createFromCurrentRequest("/{id}", produto.getId());
        final ProdutoDTO produtoDTO = ProdutoDTO.of(produto);
        return ResponseEntity.created(uri).body(produtoDTO);
    }

    @ApiOperation("${produto-api.getbyid.description}")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "NOT_FOUND")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getById(@PathVariable Long id) {
        final Produto produto = produtoService.getById(id);
        final ProdutoDTO dto = ProdutoDTO.of(produto);
        return ResponseEntity.ok(dto);
    }

    @ApiOperation("${produto-api.update.description}")
    @ApiResponses({
            @ApiResponse(code = 204, message = "UPDATED"),
            @ApiResponse(code = 404, message = "NOT_FOUND"),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById( @PathVariable final Long id, @Valid @RequestBody final ProdutoInsertDTO dto){
        produtoService.update(dto.toProduto(), id);
        return ResponseEntity.noContent().build();
    }


    @ApiOperation("${produto-api.deletebyid.description}")
    @ApiResponses({
            @ApiResponse(code = 204, message = "DELETED"),
            @ApiResponse(code = 404, message = "NOT_FOUND"),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
