package com.desafio.controle_produtos_pedidos.repository;

import com.desafio.controle_produtos_pedidos.model.ProdutoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Long> {
    Optional<ProdutoPedido> findByPedidoIdAndProdutoId(Long pedidoId, Long produtoId);

}
