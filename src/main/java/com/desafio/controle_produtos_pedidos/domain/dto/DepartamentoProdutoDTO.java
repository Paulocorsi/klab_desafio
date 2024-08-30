package com.desafio.controle_produtos_pedidos.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoProdutoDTO {

    private Long deptoId;
    private String deptoDescricao;
    private Long produtoId;
    private String produtoDescricao;
    private BigDecimal produtoPrecoVenda;
}
