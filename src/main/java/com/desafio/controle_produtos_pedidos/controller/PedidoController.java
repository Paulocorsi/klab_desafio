package com.desafio.controle_produtos_pedidos.controller;

import com.desafio.controle_produtos_pedidos.domain.dto.PedidoDTO;
import com.desafio.controle_produtos_pedidos.domain.request.PedidoRequest;
import com.desafio.controle_produtos_pedidos.model.Pedido;
import com.desafio.controle_produtos_pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoRequest pedido) {
        return ResponseEntity.ok(this.pedidoService.criarPedido(pedido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obterPedidoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.pedidoService.buscarPedidoPorId(id));
    }

    @GetMapping("buscarTodosPedidosPorPeriodo/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<PedidoDTO>> buscarTodosPedidosPorPeriodo(
            @PathVariable("dataInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") String dataInicialStr,
            @PathVariable("dataFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") String dataFinalStr) {
        return ResponseEntity.ok(this.pedidoService.buscarTodosPedidosPorPeriodo(dataInicialStr, dataFinalStr));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        this.pedidoService.excluirPedido(id);
        return ResponseEntity.noContent().build();
    }
}
