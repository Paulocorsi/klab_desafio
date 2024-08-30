package com.desafio.controle_produtos_pedidos.repository;

import com.desafio.controle_produtos_pedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
