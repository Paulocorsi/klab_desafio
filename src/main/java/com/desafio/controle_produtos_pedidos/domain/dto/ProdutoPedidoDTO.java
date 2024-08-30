package com.desafio.controle_produtos_pedidos.domain.dto;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoPedidoDTO {

    private Long produto;
    private Long pedido;
    private BigDecimal quantidade;
    private BigDecimal valorVenda;
}
