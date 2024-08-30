package com.desafio.controle_produtos_pedidos.repository;

import com.desafio.controle_produtos_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = """
              SELECT 
                  p.id AS pedidoId,
                  p.data AS dataPedido,
                  pr.id AS produtoId,
                  pr.descricao AS descricaoProduto,
                  COALESCE(pp.quantidade, 0) AS quantidade,
            ROUND(COALESCE(pp.valor_venda, 0), 2) AS valorVenda,
            ROUND(COALESCE(pp.quantidade, 0) * COALESCE(pp.valor_venda, 0), 2) AS valorTotalProduto
              FROM 
                  pedidos p
              LEFT JOIN 
                  produto_pedido pp ON p.id = pp.pedido_id
              LEFT JOIN 
                  produtos pr ON pp.produto_id = pr.id
              WHERE 
                  p.data BETWEEN :dataInicial AND :dataFinal
              ORDER BY 
                  p.id, pr.id
              """, nativeQuery = true)
    List<Object[]> findPedidosPorPeriodo(@Param("dataInicial") Date dataInicial, @Param("dataFinal") Date dataFinal);

}
