package com.projecao.fabricadesoftwarecrm.controller;

import com.projecao.fabricadesoftwarecrm.model.Produto;
import com.projecao.fabricadesoftwarecrm.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public List<Produto> listaProdutos() {
        return produtoService.listaProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscaProduto(@PathVariable("id") Integer id) {
        Produto produto = produtoService.buscaProduto(id);

        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto adicionaProduto(@RequestBody Produto produto) {
        return produtoService.adicionaProduto(produto);
    }

    @DeleteMapping("/{id}")
    public boolean deletaProduto(@PathVariable("id") Integer id) {
        return produtoService.deletaProduto(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Produto> atualizaProduto(@PathVariable("id") Integer id, @RequestBody Produto produto) {

        Produto produtoAtualizado = produtoService.atualizaProduto(id, produto);

        if (produtoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }
}
