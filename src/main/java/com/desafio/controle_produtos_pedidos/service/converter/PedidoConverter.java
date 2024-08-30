package com.desafio.controle_produtos_pedidos.service.converter;

import com.desafio.controle_produtos_pedidos.domain.dto.PedidoDTO;
import com.desafio.controle_produtos_pedidos.domain.dto.ProdutoDTO;
import com.desafio.controle_produtos_pedidos.domain.request.PedidoRequest;
import com.desafio.controle_produtos_pedidos.model.Pedido;
import com.desafio.controle_produtos_pedidos.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PedidoConverter {

    public Pedido convertRequestToEntity(PedidoRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(request, Pedido.class);
    }

    public PedidoDTO convertEntityToDTO(Pedido entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, PedidoDTO.class);
    }

    public Pedido convertDTOtoEntity(PedidoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Pedido.class);
    }
}
