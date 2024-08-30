package com.desafio.controle_produtos_pedidos.domain.dto;

import lombok.*;
import org.springframework.web.bind.annotation.Mapping;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoPedidoPorPeriodoDTO {
    private Long pedidoId;
    private Date dataPedido;
    private Long produtoId;
    private String descricaoProduto;
    private BigDecimal quantidade;
    private BigDecimal valorVenda;
    private BigDecimal valorTotalProduto;
}
