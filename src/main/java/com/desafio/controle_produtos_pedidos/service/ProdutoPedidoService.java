package com.desafio.controle_produtos_pedidos.service;

import com.desafio.controle_produtos_pedidos.domain.dto.PedidoDTO;
import com.desafio.controle_produtos_pedidos.domain.dto.ProdutoDTO;
import com.desafio.controle_produtos_pedidos.domain.dto.ProdutoPedidoDTO;
import com.desafio.controle_produtos_pedidos.domain.exception.DataNotFoundException;
import com.desafio.controle_produtos_pedidos.domain.request.ProdutoPedidoRequest;
import com.desafio.controle_produtos_pedidos.model.Pedido;
import com.desafio.controle_produtos_pedidos.model.Produto;
import com.desafio.controle_produtos_pedidos.model.ProdutoPedido;
import com.desafio.controle_produtos_pedidos.repository.PedidoRepository;
import com.desafio.controle_produtos_pedidos.repository.ProdutoPedidoRepository;
import com.desafio.controle_produtos_pedidos.repository.ProdutoRepository;
import com.desafio.controle_produtos_pedidos.service.converter.PedidoConverter;
import com.desafio.controle_produtos_pedidos.service.converter.ProdutoConverter;
import com.desafio.controle_produtos_pedidos.service.converter.ProdutoPedidoConverter;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class ProdutoPedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoPedidoRepository produtoPedidoRepository;
    private final ProdutoPedidoConverter produtoPedidoConverter;
    private final ProdutoConverter produtoConverter;
    private final PedidoConverter pedidoConverter;

    public ProdutoPedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, ProdutoPedidoRepository produtoPedidoRepository, ProdutoPedidoConverter produtoPedidoConverter, ProdutoConverter produtoConverter, PedidoConverter pedidoConverter) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.produtoPedidoRepository = produtoPedidoRepository;
        this.produtoPedidoConverter = produtoPedidoConverter;
        this.produtoConverter = produtoConverter;
        this.pedidoConverter = pedidoConverter;
    }

    @Transactional
    public ProdutoPedidoDTO adicionarProdutoAoPedido(ProdutoPedidoRequest request) {
        Pedido pedido = pedidoRepository.findById(request.getPedido()).orElseThrow(() -> new DataNotFoundException("Pedido não encontrado: ID = " + request.getPedido()));
        Produto produto = produtoRepository.findById(request.getProduto()).orElseThrow(() -> new DataNotFoundException("Produto não encontrado: ID = " + request.getProduto()));

        ProdutoPedido produtoPedidoConv = produtoPedidoConverter.convertRequestToEntity(request, pedido, produto);
        ProdutoPedido produtoPedidoEntity = produtoPedidoRepository.save(produtoPedidoConv);

        return produtoPedidoConverter.convertEntityToDTO(produtoPedidoEntity);
    }


    @Transactional
    public void removerProdutoDoPedido(ProdutoPedidoRequest request) {
        produtoPedidoRepository.findByPedidoIdAndProdutoId(request.getPedido(), request.getProduto())
                .ifPresentOrElse(
                        produtoPedidoRepository::delete,
                        () -> {
                            throw new DataNotFoundException("Produto não encontrado para o pedido");
                        }
                );
    }

    @Transactional
    public ProdutoPedidoDTO atualizarQuantidadeEValor(ProdutoPedidoRequest request) {
        return produtoPedidoRepository.findByPedidoIdAndProdutoId(request.getPedido(), request.getProduto())
                .map(produtoPedido -> {
                    produtoPedido.setQuantidade(request.getQuantidade());
                    produtoPedido.setValorVenda(request.getValorVenda());
                    return produtoPedidoConverter.convertEntityToDTO(produtoPedidoRepository.save(produtoPedido));
                })
                .orElseThrow(() -> new DataNotFoundException("Produto não encontrado para o pedido"));
    }
}