package com.desafio.controle_produtos_pedidos.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartamentoDTO {
    private Long id;
    private String descricao;
    private Set<ProdutoDTO> produtos;
}
