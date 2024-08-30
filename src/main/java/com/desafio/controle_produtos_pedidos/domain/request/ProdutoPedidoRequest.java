package com.desafio.controle_produtos_pedidos.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPedidoRequest {

    private Long produto;
    private Long pedido;
    private BigDecimal quantidade;
    private BigDecimal valorVenda;
}
