package com.desafio.controle_produtos_pedidos.service.converter;

import com.desafio.controle_produtos_pedidos.domain.dto.ProdutoDTO;
import com.desafio.controle_produtos_pedidos.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConverter {

    public ProdutoDTO convertEntityToDTO(Produto entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, ProdutoDTO.class);
    }

}
