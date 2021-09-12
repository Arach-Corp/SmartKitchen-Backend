package com.arachcorp.smartkitchen.rest.utils;

import com.arachcorp.smartkitchen.entities.Lote;
import com.arachcorp.smartkitchen.entities.Produto;
import com.arachcorp.smartkitchen.rest.dto.lote.LoteDTO;
import com.arachcorp.smartkitchen.rest.dto.lote.LoteInsertDTO;
import com.arachcorp.smartkitchen.services.LoteService;
import com.arachcorp.smartkitchen.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/lotes")
public class LoteController {

    @Autowired
    private LoteService loteService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<LoteDTO>> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "perPage", defaultValue = "10") int perPage,
            @RequestParam(required = false) Long produtoId
    ) {
        final Pageable pageable = PageableUtils.createPageable(page, perPage);
        Page<Lote> lotes = null;
        if (Objects.nonNull(produtoId)) {
            final Produto produto = produtoService.getById(produtoId);
            lotes = loteService.findAllByProduto(produto, pageable);
        } else {
            lotes = loteService.findAll(pageable);
        }
        final Page<LoteDTO> loteDTOS = PageConverterUtils.convertLotePage(lotes, pageable);
        return ResponseEntity.ok(loteDTOS);
    }

    @PostMapping
    public ResponseEntity<LoteDTO> registerLote(@Valid @RequestBody LoteInsertDTO dto) {
        final Produto produto = produtoService.getById(dto.getProdutoId());
        Lote lote = new Lote(null, dto.getDataFabricacao(), dto.getDataValidade(), produto);
        lote = loteService.save(lote);
        final URI uri = UriUtils.createFromCurrentRequest("/{id}", lote.getId());
        return ResponseEntity.created(uri).body(LoteDTO.of(lote));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteDTO> findById(@PathVariable Long id) {
        final Lote lote = loteService.getById(id);
        return ResponseEntity.ok(LoteDTO.of(lote));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        loteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
