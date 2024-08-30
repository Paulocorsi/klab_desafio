package com.desafio.controle_produtos_pedidos.controller;

import com.desafio.controle_produtos_pedidos.domain.dto.ProdutoPedidoDTO;
import com.desafio.controle_produtos_pedidos.domain.request.ProdutoPedidoRequest;
import com.desafio.controle_produtos_pedidos.service.ProdutoPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produtoPedido")
public class ProdutoPedidoController {


    private final ProdutoPedidoService produtoPedidoService;

    @PostMapping("/adicionarProdutoAoPedido")
    public ResponseEntity<ProdutoPedidoDTO> adicionarProdutoAoPedido(@RequestBody ProdutoPedidoRequest pedido) {
        return ResponseEntity.ok(this.produtoPedidoService.adicionarProdutoAoPedido(pedido));
    }

    @DeleteMapping
    public ResponseEntity<ProdutoPedidoDTO> removerProdutoDoPedido(@RequestBody ProdutoPedidoRequest pedido) {
        this.produtoPedidoService.removerProdutoDoPedido(pedido);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("atualizarQuantidadeEValor/")
    public ResponseEntity<ProdutoPedidoDTO> atualizarQuantidadeEValor(@RequestBody ProdutoPedidoRequest pedido) {
        return ResponseEntity.ok(this.produtoPedidoService.atualizarQuantidadeEValor(pedido));
    }

}
