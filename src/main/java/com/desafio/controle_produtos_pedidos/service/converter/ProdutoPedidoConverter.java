package com.desafio.controle_produtos_pedidos.service.converter;

import com.desafio.controle_produtos_pedidos.domain.dto.ProdutoPedidoDTO;
import com.desafio.controle_produtos_pedidos.domain.request.ProdutoPedidoRequest;
import com.desafio.controle_produtos_pedidos.model.Pedido;
import com.desafio.controle_produtos_pedidos.model.Produto;
import com.desafio.controle_produtos_pedidos.model.ProdutoPedido;
import com.desafio.controle_produtos_pedidos.model.ProdutoPedidoId;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Component;

@Component
public class ProdutoPedidoConverter {

    public ProdutoPedidoDTO convertEntityToDTO(ProdutoPedido produtoPedido) {
        return ProdutoPedidoDTO.builder()
                .pedido(produtoPedido.getPedido().getId())
                .produto(produtoPedido.getProduto().getId())
                .quantidade(produtoPedido.getQuantidade())
                .valorVenda(produtoPedido.getValorVenda())
                .build();
    }

    public ProdutoPedido convertRequestToEntity(ProdutoPedidoRequest request, Pedido pedido, Produto produto) {
        ModelMapper modelMapper = new ModelMapper();
        ProdutoPedido produtoPedido = modelMapper.map(request, ProdutoPedido.class);
        produtoPedido.setId(new ProdutoPedidoId(produto.getId(), pedido.getId()));
        produtoPedido.setPedido(pedido);
        produtoPedido.setProduto(produto);
        return produtoPedido;
    }
}
