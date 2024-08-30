package com.desafio.controle_produtos_pedidos.controller;

import com.desafio.controle_produtos_pedidos.domain.dto.DepartamentoDTO;
import com.desafio.controle_produtos_pedidos.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> obterDepartamentosComProdutosNoIntervalo(Long startId, Long endId) {
        return ResponseEntity.ok(this.departamentoService.obterDepartamentosComProdutosNoIntervalo(startId, endId));
    }

}
