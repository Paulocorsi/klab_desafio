package com.desafio.controle_produtos_pedidos.repository;

import com.desafio.controle_produtos_pedidos.domain.dto.DepartamentoProdutoDTO;
import com.desafio.controle_produtos_pedidos.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    @Query("""
            SELECT new com.desafio.controle_produtos_pedidos.domain.dto.DepartamentoProdutoDTO(
                  d.id,
                  d.descricao,
                  p.id,
                  p.descricao,
                  ROUND(COALESCE(p.preco, 0), 2))
            FROM Departamento d 
            JOIN d.produtos p 
            WHERE 
            d.id BETWEEN :startId AND :endId 
            ORDER BY 
            d.id ASC, 
            p.descricao ASC
            """)
    List<DepartamentoProdutoDTO> findDepartamentosAndProdutosInRange(@Param("startId") Long startId, @Param("endId") Long endId);
}
