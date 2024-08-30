package com.desafio.controle_produtos_pedidos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProdutoPedidoId implements Serializable {
    @Column(name = "produto_id")
    private Long produtoId;

    @Column(name = "pedido_id")
    private Long pedidoId;
}
