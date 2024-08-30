package com.desafio.controle_produtos_pedidos.service;

import com.desafio.controle_produtos_pedidos.domain.dto.DepartamentoDTO;
import com.desafio.controle_produtos_pedidos.domain.dto.DepartamentoProdutoDTO;
import com.desafio.controle_produtos_pedidos.domain.dto.ProdutoDTO;
import com.desafio.controle_produtos_pedidos.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public List<DepartamentoDTO> obterDepartamentosComProdutosNoIntervalo(Long startId, Long endId) {
        List<DepartamentoProdutoDTO> result = this.departamentoRepository.findDepartamentosAndProdutosInRange(startId, endId);
        Map<Long, DepartamentoDTO> departamentoMap = new HashMap<>();

        result.forEach(depto -> {
            DepartamentoDTO departamento = departamentoMap.get(depto.getDeptoId());

            if (departamento == null) {
                departamento = DepartamentoDTO.builder()
                        .id(depto.getDeptoId())
                        .descricao(depto.getDeptoDescricao())
                        .produtos(new HashSet<>())
                        .build();
                departamentoMap.put(departamento.getId(), departamento);
            }

            ProdutoDTO produto = ProdutoDTO.builder()
                    .id(depto.getProdutoId())
                    .descricao(depto.getProdutoDescricao())
                    .preco(depto.getProdutoPrecoVenda())
                    .build();
            departamento.getProdutos().add(produto);
        });

        return new ArrayList<>(departamentoMap.values());
    }
}