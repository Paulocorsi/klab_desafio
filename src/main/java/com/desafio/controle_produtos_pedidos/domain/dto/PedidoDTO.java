package com.desafio.controle_produtos_pedidos.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Long id;
    private Date data;
    private Set<ProdutoDTO> produtos = new HashSet<>();
    private BigDecimal valorTotalPedido;
}
