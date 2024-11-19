package com.projecao.fabricadesoftwarecrm.service;

import com.projecao.fabricadesoftwarecrm.model.Produto;
import com.projecao.fabricadesoftwarecrm.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<Produto> listaProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscaProduto(Integer id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        return produtoOptional.orElse(null);
    }

    public Produto adicionaProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public boolean deletaProduto(Integer id) {
        if (produtoRepository.findById(id).isPresent()) {
            produtoRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public Produto atualizaProduto(Integer id, Produto produto) {
        return produtoRepository.findById(id)
                .map(produtoAtual -> {
                    produtoAtual.setNome(Optional.ofNullable(produto.getNome()).orElse(produtoAtual.getNome()));
                    produtoAtual.setPreco(Optional.ofNullable(produto.getPreco()).orElse(produtoAtual.getPreco()));
                    return produtoRepository.save(produtoAtual);
                }).orElse(null);
    }
}
