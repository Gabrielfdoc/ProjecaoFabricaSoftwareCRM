package com.projecao.fabricadesoftwarecrm.service;

import com.projecao.fabricadesoftwarecrm.model.ItemPedido;
import com.projecao.fabricadesoftwarecrm.model.Pedido;
import com.projecao.fabricadesoftwarecrm.model.Produto;
import com.projecao.fabricadesoftwarecrm.model.Usuario;
import com.projecao.fabricadesoftwarecrm.repository.PedidoRepository;
import com.projecao.fabricadesoftwarecrm.repository.ProdutoRepository;
import com.projecao.fabricadesoftwarecrm.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public Pedido registrarPedido(Pedido pedido) {

        Usuario usuario = usuarioRepository.findById(pedido.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        pedido.setUsuario(usuario);

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            if (produto.getQuantidadeEstoque() < item.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para: " + produto.getNome());
            }

            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());
            produtoRepository.save(produto);

            item.setProduto(produto);
            item.setPedido(pedido);
        }

        return pedidoRepository.save(pedido);
    }
}

